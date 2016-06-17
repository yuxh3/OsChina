package com.example.admin.oschina.Fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.admin.oschina.Activity.SettingActivity;
import com.example.admin.oschina.R;
import com.example.admin.oschina.com.App;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by admin on 2016/6/16.
 */
public class SlidingMenuFragment extends BaseFragment {


    @Bind(R.id.rl_setting)
    RelativeLayout rlSetting;
    @Bind(R.id.rl_exit)
    RelativeLayout rlExit;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_sliding_menu, null);
    }

    @OnClick({R.id.rl_setting, R.id.rl_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_setting:
                startActivity(new Intent(App.sContext,SettingActivity.class));
                break;
            case R.id.rl_exit:
                getActivity().finish();
                break;
        }
    }
}
