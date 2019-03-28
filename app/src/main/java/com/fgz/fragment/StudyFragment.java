package com.fgz.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fgz.R;
import com.fgz.activity.StudyCoursesActivity;
import com.fgz.entity.CourseItem;
import com.fgz.fragment.base.BaseFragment;
import com.fgz.utils.CalDp;
import com.fgz.utils.DensityUtils;
import com.fgz.utils.ExchangeBean;
import com.fgz.utils.LocalJsonResolutionUtils;
import com.fgz.view.CustomScrollListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者： FGZ
 * 功能： 学习
 * 创建日期： 2019-03-06
 */
public class StudyFragment extends BaseFragment {
    List<CourseItem> courseList;//Lisr代表集合，<>代表泛型，里面存放CourseItem对象数据类型
    GridView gridView;
    private RecyclerView recyclerView;//列表
    private CustomScrollListener scrollListener;//滑动监听
    /**
     * 设置界面的布局文件
     *
     * @return
     */
    @Override
    protected int setLayoutID() { return R.layout.fragment_study;}


    /**
     * 初始化控件
     *
     * @param view
     */
    @Override
    public void initView(View view) {
        gridView =(GridView) view.findViewById(R.id.grid);
        //添加点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //构建Intent
                Intent startTo = activity(StudyCoursesActivity.class);
                //跳转界面，调用startActivity
                startActivity(startTo);
            }
        });

        setData();
        setGridView();

    }

    private void setGridView() {
        //获取像素密度，控件总体长度
        int size = courseList.size();
        //每个列宽（dp）
        int length = CalDp.toDp(397,DensityUtils.getSysWidth("w"),DensityUtils.getDensity());
        //屏幕密度
        float density = DensityUtils.getDensity();
        //图片间距（px）
        int space =(int) (CalDp.toDp(178)*density);
        Log.d("fgz","space"+space);
        int gridviewWidth = (int) ((size * (length+space)+space) * density);
        int itemWidth = (int) (length);//列宽(px)

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//        gridView.setVerticalScrollBarEnabled(false);
        Log.d("fgz","itemWidth:"+itemWidth+"density:"+density+"gridviewWidth:"+gridviewWidth);
        gridView.setColumnWidth(gridView.AUTO_FIT); // 设置列表项宽
        gridView.setHorizontalSpacing(space); // 设置列表项水平间距
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getActivity().getApplicationContext(),
                courseList);
        gridView.setAdapter(adapter);
    }


    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CourseItem> list;

        public GridViewAdapter(Context _context, List<CourseItem> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //参数 上下文
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //添加布局文件 传入parent设置的高度和宽度就会起作用了。
            convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
            //获取当前Position的控件并设置属性
            //获取数据
            CourseItem courseInfo = list.get(position);
            //设置属性
            ImageView bg = (ImageView) convertView.findViewById(R.id.ItemImage);
            bg.setImageBitmap(courseInfo.getCourseCover());

            return convertView;
        }
    }

    private void setData() {
//        courseList = new ArrayList<CourseItem>();
//        CourseItem item = new CourseItem();
//        item.setCourseCover(R.drawable.c_1);
//        courseList.add(item);
//        CourseItem item1 = new CourseItem();
//        item1.setCourseCover(R.drawable.c_2);
//        courseList.add(item1);
//        CourseItem item2 = new CourseItem();
//        item2.setCourseCover(R.drawable.c_3);
//        courseList.add(item2);
        //得到本地json文本内容
        String fileName = "course_list.json";
        String foodJson = LocalJsonResolutionUtils.getJson(getActivity(),fileName);
        ExchangeBean exchangeBean = LocalJsonResolutionUtils.JsonToObject(foodJson, ExchangeBean.class);
        courseList = new ArrayList<CourseItem>();
        for(int i=0;i<exchangeBean.getData().size();i++){
            CourseItem item = new CourseItem();
            Bitmap img=getImageFromAssetsFile(getActivity(),exchangeBean.getData().get(i).picture);
            item.setCourseCover(img);
            courseList.add(item);
        }
        Log.d("fgz","course:"+exchangeBean.getData().get(0).picture);
        Log.d("fgz","cityList:"+courseList.size());
        //从内部存储读取json数据文件
        //if(){
        //  显示
        // }else{
        //  请求服务器，下载数据在显示
        // }
    }
    /* 读取Assets文件夹中的图片资源
     * @param context
     * @param fileName 图片名称
     * @return
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void setListener() {

    }
    /**
     * 设置功能
     */
    @Override
    public void setFunction() {
        super.setFunction();
        gridView.setSelector(R.drawable.course_bg);
        //设置标题

//        page = HttpCode.START_PAGE;//初始化页数
//        recyclerView.setHasFixedSize(true);
//        refreshLayout.setColorSchemeColors(Color.RED);
//        refreshLayout.setProgressViewOffset(false, 0, 100);//避免提前调用无加载效果
//        refreshLayout.setRefreshing(true);//加载等待
//        onRefresh();//开始加载
    }

}
