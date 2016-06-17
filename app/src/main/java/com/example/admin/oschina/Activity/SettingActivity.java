package com.example.admin.oschina.Activity;

import com.example.admin.oschina.R;
import com.example.admin.oschina.com.App;
import com.leaking.slideswitch.SlideSwitch;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by admin on 2016/6/16.
 */
public class SettingActivity extends BaseAppCompatActivity {
    @Bind(R.id.state_swit)
    SlideSwitch stateSwit;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {

        initActionBar("设置", -1);
        App.scrollType = App.scrollType == 8 ? 0 : App.scrollType + 1;
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.state_swit)
    public void onClick() {
        if (App.isListEffect){
            App.scrollType = 8;
        }else {
            
        }
    }
}
