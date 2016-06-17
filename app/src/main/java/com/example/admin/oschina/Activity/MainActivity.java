package com.example.admin.oschina.Activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.oschina.Fragment.ExploreFragment;
import com.example.admin.oschina.Fragment.HomeFragment;
import com.example.admin.oschina.Fragment.MeFragment;
import com.example.admin.oschina.Fragment.SlidingMenuFragment;
import com.example.admin.oschina.Fragment.TweetFragment;
import com.example.admin.oschina.R;
import com.example.admin.oschina.com.App;

import butterknife.Bind;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabhost;
    @Bind(R.id.dl_left)
    DrawerLayout mDlLeft;
    @Bind(R.id.fl_sliding_menu)
    FrameLayout mFlSlidingMenu;
    @Bind(R.id.btn_quick_options)
    ImageView mBtnQuickOptions;

    private ActionBarDrawerToggle mDrawerToggle;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private ImageView mBtn_route;
    private View mBtn_photo;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        App.sOpenActivity.put(MainActivity.class.getName(), this);
        mBtnQuickOptions .setOnClickListener(this);
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        initDialog();
        initTabGuide();
        initActionBar(getResources().getString(R.string.app_name), R.menu.main_activity_menu);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_sliding_menu,new SlidingMenuFragment()).commit();

        mDlLeft.addDrawerListener(mDrawListener);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDlLeft,R.string.open,R.string.close);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();//该方法会自动和ctivity关联，将开关的图片显现在activity上
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(MainActivity.this,"测试成功",Toast.LENGTH_SHORT).show();
        }
        return mDrawerToggle.onOptionsItemSelected(item) ||super.onOptionsItemSelected(item);
    }

    private void initTabGuide() {

        mLayoutInflater = LayoutInflater.from(this);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        TabInfos[] values = TabInfos.values();
        int count = values.length;
        for (int i = 0;i<count;i++){
            TabInfos value = values[i];
            View view = getTabItemView(value);
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(value.tag)
                    .setIndicator(view);
            if (i == 2){
                view.setVisibility(View.INVISIBLE);
            }
            mTabhost.addTab(tabSpec,value.clz,null);
        }
    }

    private View getTabItemView(TabInfos value) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv);
        TextView textView = (TextView) view.findViewById(R.id.tab_tv);
        imageView.setImageResource(value.iconId);
        textView.setText(value.tag);
        return view;
    }

    @Override
    public void onBackPressed() {
        if (mDlLeft.isDrawerOpen(mFlSlidingMenu)){
            mDlLeft.closeDrawer(mFlSlidingMenu);
        }else {
            super.onBackPressed();
        }
    }

    private void initDialog() {
        View view = View.inflate(this,R.layout.dialog_quick,null);
        view.findViewById(R.id.btn_quickoption_icon_text).setOnClickListener(this);
        view.findViewById(R.id.btn_quickoption_icon_album).setOnClickListener(this);

        mBtn_photo = view.findViewById(R.id.btn_quickoption_icon_photo);
        mBtn_photo.setOnClickListener(this);
        mBtn_route = (ImageView) view.findViewById(R.id.btn_quickoption_route);
        mBtn_route.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialog);
        mDialog = builder.create();
        mDialog.setView(view,0,0,0,0);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, SendCommentActivity.class);
        switch (v.getId()) {
            case R.id.btn_quick_options:
                showDialog();
                break;
            case R.id.btn_quickoption_icon_text:
                intent.putExtra("category", 0);
                startActivity(intent);
                mDialog.dismiss();
                break;
            case R.id.btn_quickoption_icon_album:
                intent.putExtra("category", 1);
                startActivity(intent);
                mDialog.dismiss();
                break;
            case R.id.btn_quickoption_icon_photo:
                intent.putExtra("category", 2);
                startActivity(intent);
                mDialog.dismiss();
                break;
            case R.id.btn_quickoption_route:
                mDialog.dismiss();
                break;
        }
    }

    Handler mHandle = new Handler();
    private void showDialog() {
        mDialog.show();
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = getResources().getDisplayMetrics().widthPixels;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        mDialog.getWindow().setAttributes(params);
        mHandle.postDelayed(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator rotation = ObjectAnimator.ofFloat(mBtn_photo,"rotation", 0, 10, -10, 6, -6, 3, -3, 0);
                rotation.setDuration(800);
                rotation.start();
            }
        },2500);
    }

    private DrawerLayout.DrawerListener mDrawListener = new DrawerLayout.DrawerListener(){

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView,slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {

            mDrawerToggle.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {

            mDrawerToggle.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerStateChanged(int newState) {

            mDrawerToggle.onDrawerStateChanged(newState);
        }
    };


    enum TabInfos{
        NEWS("综合",R.drawable.tab_icon_new,HomeFragment.class,null),
        TWEET("动弹", R.drawable.tab_icon_tweet, TweetFragment.class, null),
        QUICK("快速", R.drawable.tab_icon_new, null, null),
        EXPLORER("发现", R.drawable.tab_icon_explore, ExploreFragment.class, null),
        ME("我", R.drawable.tab_icon_me, MeFragment.class, null);

        String tag;
        int iconId;
        Class clz;
        Bundle bundle;


        TabInfos(String tag, int iconId, Class clz, Bundle bundle) {

            this.tag = tag;
            this.iconId = iconId;
            this.clz = clz;
            this.bundle = bundle;
        }
    }
}
