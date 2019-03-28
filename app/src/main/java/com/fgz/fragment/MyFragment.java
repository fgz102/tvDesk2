package com.fgz.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fgz.R;
import com.fgz.fragment.base.BaseFragment;


public class MyFragment extends BaseFragment {

    @Override
    protected int setLayoutID() {
        return R.layout.fragment_my;
    }
}
