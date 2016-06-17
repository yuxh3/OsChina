package com.example.admin.oschina.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/6/15.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private ActionBar mActionBar;
    private int mMenuId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 主页面布局ID,必须实现
     */
    protected abstract int getLayoutID();

    /**
     * @param title  标题文字
     * @param menuId 菜单资源ID，没有菜单传入-1
     */
    public void initActionBar(String title, int menuId) {
        mMenuId = menuId;
        mActionBar = getSupportActionBar(); //显示返回箭头默认是不显示的
        //显示标题
        if (!TextUtils.isEmpty(title)) {

            mActionBar.setDisplayShowTitleEnabled(true);
            mActionBar.setTitle(title);

            Log.i("yy", "111111111111"+title);
        } else {
            mActionBar.setDisplayShowTitleEnabled(false);
        }
        //显示左侧的返回箭头，并且返回箭头和title一直设置返回箭头才能显示
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);


//        mActionBar.setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMenuId != -1) {
            getMenuInflater().inflate(mMenuId, menu);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
