package com.fgz.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.fgz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyCoursesActivity extends AppCompatActivity {

//    SimpleAdapter adapter = new SimpleAdapter(thid,)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_courses);
        //获取列表视图
        ListView listviewLeft = (ListView) findViewById(R.id.list_course_type);
        String [] title = new String[]{"汉子王国","英语王国","科学王国"};
        //创建一个list 集合
        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        //通过for循环将文字放到map中
        for(int i=0;i<title.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name",title[i]);
            listItems.add(map);
        }
        //左侧
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,R.layout.study_courses_left_list_item,new String[]{"name"},new int[]{R.id.textView});
        listviewLeft.setAdapter(adapter);
        listviewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选择项的值
                Map<String,Object> map = (Map<String,Object>) parent.getItemAtPosition(position);
                Toast.makeText(StudyCoursesActivity.this,map.get("name").toString(),Toast.LENGTH_SHORT).show();
            }
        });
        //获取列表视图
        ListView listviewRight = (ListView) findViewById(R.id.list_course_content);
        int[] imageId = new int[]{R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,R.mipmap.img05,R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,};
        //创建一个list 集合
        List<Map<String,Object>> listItemsRight = new ArrayList<Map<String,Object>>();
        //通过for循环将文字放到map中
        for(int i=0;i<imageId.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",imageId);
            listItemsRight.add(map);
        }
        //右侧

    }
    private Integer[] imageId = {R.mipmap.img01,R.mipmap.img02,R.mipmap.img03,R.mipmap.img04,R.mipmap.img05,R.mipmap.img06,R.mipmap.img07,R.mipmap.img08,};

    //创建ImageAdapter
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;
        public ImageAdapter(Context c){
            mContext = c;
        }
        @Override
        public int getCount() {
            return imageId.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            Log.d("fgz","打印"+position);
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView==null){
                imageView=new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(100,90));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                imageView=(ImageView) convertView;
            }
            imageView.setImageResource(imageId[position]);
            return imageView;
        }
    }
}
