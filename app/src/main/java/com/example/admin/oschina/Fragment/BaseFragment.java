package com.example.admin.oschina.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/6/16.
 */
public abstract class BaseFragment extends Fragment {
    public View mRootView;
    public boolean isLoading;
    public boolean isData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = initView(inflater);
            ButterKnife.bind(this,mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isData){
            isData = true;
            initData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isLoading){
            isLoading = true;
            initData();
        }
    }

    protected abstract void initData();

    protected abstract View initView(LayoutInflater inflater);

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
