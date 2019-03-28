package com.fgz.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fgz.R;
import com.fgz.activity.MainActivity;
import com.fgz.activity.StudyCoursesActivity;
import com.fgz.fragment.base.BaseFragment;

/**
 *
 */
public class EntertainmentFragment extends BaseFragment {
   ImageView imageView;
    @Override
    protected int setLayoutID() {
        return R.layout.fragment_entertainment;
    }

    @Override
    public void initView(View view) {
        imageView =(ImageView) view.findViewById(R.id.entertainment_bg);
        //添加点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"不知道怎么跳转到应用",Toast.LENGTH_SHORT).show();
                //构建Intent
               // Intent startTo = activity(StudyCoursesActivity.class);
                //跳转界面，调用startActivity
              //  startActivity(startTo);
            }
        });

    }
}
