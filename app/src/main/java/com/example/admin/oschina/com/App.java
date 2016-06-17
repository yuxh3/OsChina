package com.example.admin.oschina.com;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.admin.oschina.FileUtil.FileUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.twotoasters.jazzylistview.JazzyEffect;
import com.twotoasters.jazzylistview.effects.CardsEffect;
import com.twotoasters.jazzylistview.effects.CurlEffect;
import com.twotoasters.jazzylistview.effects.FadeEffect;
import com.twotoasters.jazzylistview.effects.FanEffect;
import com.twotoasters.jazzylistview.effects.FlipEffect;
import com.twotoasters.jazzylistview.effects.SlideInEffect;
import com.twotoasters.jazzylistview.effects.StandardEffect;
import com.twotoasters.jazzylistview.effects.WaveEffect;
import com.twotoasters.jazzylistview.effects.ZipperEffect;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 2016/6/15.
 */
public class App extends Application {
    private static final String TAG = "App";
    private static App mInstance = null;
    public static Context sContext;
    public static Handler sHandler;
    public static Thread sMainThread;
    public static Map<String, Activity> sOpenActivity;
    public static int scrollType = 8;
    public static boolean isListEffect = false;

    public App() {
        mInstance = this;
    }

    public static App getApp() {
        if (mInstance != null && mInstance instanceof App) {
            Log.i(TAG, "1111111111");
            return (App) mInstance;

        } else {
            mInstance = new App();
            mInstance.onCreate();
            Log.i(TAG, "1111111111222222222222222");

            return (App) mInstance;
        }


    }

    @Override
    public void onCreate() {
        super.onCreate();
        catchException();
        initImageLoader();
        mInstance = this;
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, AppConstants.APP_IMAGE)))
                .diskCacheSize(100 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void catchException() {

        sContext = this.getApplicationContext();
        sHandler = new Handler();
        sMainThread = Thread.currentThread();
        sOpenActivity = new HashMap<>();
    }


//    public float getScreenDensity() {
//        if (this.displayMetrics == null) {
//            setDisplayMetrics(getResources().getDisplayMetrics());
//        }
//        return this.displayMetrics.density;
//    }
//
//    public int getScreenHeight() {
//        if (this.displayMetrics == null) {
//            setDisplayMetrics(getResources().getDisplayMetrics());
//        }
//        return this.displayMetrics.heightPixels;
//    }
//
//    public int getScreenWidth() {
//        if (this.displayMetrics == null) {
//            setDisplayMetrics(getResources().getDisplayMetrics());
//        }
//        return this.displayMetrics.widthPixels;
//    }
//
//    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
//        this.displayMetrics = DisplayMetrics;
//    }
//
//    public int dp2px(float f) {
//        return (int) (0.5F + f * getScreenDensity());
//    }
//
//    public int px2dp(float pxValue) {
//        return (int) (pxValue / getScreenDensity() + 0.5f);
//    }

    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }

    public static void clearRecord() {
        FileUtil.clearCameraRecord(sContext);
    }

    public static JazzyEffect setListViewStyle() {
        switch (scrollType) {
            case 0:
                return new SlideInEffect();
            case 1:
                return new CurlEffect();
            case 2:
                return new CardsEffect();
            case 3:
                return new FadeEffect();
            case 4:
                return new FanEffect();
            case 5:
                return new FlipEffect();
            case 6:
                return new ZipperEffect();
            case 7:
                return new WaveEffect();
            case 8:
                return new StandardEffect();
        }
        return new StandardEffect();
    }


}
