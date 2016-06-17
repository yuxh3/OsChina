package com.example.admin.oschina.Activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.oschina.R;

/**
 * Created by admin on 2016/6/16.
 */
public class SplshActivity extends Activity{

    private LinearLayout ll;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv = (ImageView) findViewById(R.id.iv);

        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"rotation", 0, 10, -10, 6, -6, 3, -3, 0);
        animator.setDuration(3000);
        animator.start();

        mHandler.sendEmptyMessageDelayed(0,4000);

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplshActivity.this, MainActivity.class));
            finish();
        }
    };
}
