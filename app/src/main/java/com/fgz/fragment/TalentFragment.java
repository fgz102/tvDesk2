package com.fgz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.fgz.R;
import com.fgz.entity.TalentType;
import com.fgz.fragment.base.BaseFragment;
import com.fgz.fragment.base.TitleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TalentFragment extends BaseFragment {
    private List<TalentType> talentTypesList;//才艺列表数据
    private GridView gridView;//列表

    /**
     * 设置界面的布局文件
     * @return
     */
    @Override
    protected int setLayoutID() {
        return R.layout.fragment_talent;
    }

    /**
     * 初始化控件
     * @param view
     */
    @Override
    public void initView(View view) {
       gridView =(GridView) view.findViewById(R.id.talent_gridview);
        setData();
    }

    @Override
    public void setFunction() {
        super.setFunction();
        //设置标题
        //加载数据
    }

    /**
     * 初始化界面数据
     */
    private void initDataFromNet(){

        //创建适配器
        ShowTLAdapter adapter = new ShowTLAdapter(getActivity().getApplicationContext(),talentTypesList);
        //设置适配器
        gridView.setAdapter(adapter);
    }

    public class ShowTLAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private Context mContext;//上下文
        private List<TalentType> talentTypeList;//数据源
        public ShowTLAdapter(Context c,List<TalentType> tList){
            mContext = c;
            talentTypeList = tList;
        }

        @Override
        public int getCount() {
            return talentTypeList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            //参数 上下文
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            //添加布局文件
            convertView = layoutInflater.inflate(R.layout.item_talent, null);
            //获取当前Position的控件并设置属性
            //获取数据
            TalentType courseInfo = talentTypeList.get(position);
            //设置属性
            ImageView bg = (ImageView) convertView.findViewById(R.id.ItemImage);
            bg.setImageResource(courseInfo.getImageUrl());


            return convertView;
        }
    }
    private void setData() {
        talentTypesList = new ArrayList<TalentType>();
        TalentType item = new TalentType();
        item.setImageUrl(R.mipmap.img01);
        talentTypesList.add(item);
        TalentType item1 = new TalentType();
        item1.setImageUrl(R.mipmap.img02);
        talentTypesList.add(item1);
        TalentType item2 = new TalentType();
        item2.setImageUrl(R.mipmap.img03);
        talentTypesList.add(item2);
    }
}
