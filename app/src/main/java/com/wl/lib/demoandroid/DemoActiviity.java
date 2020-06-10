package com.wl.lib.demoandroid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 作者：author wl
 * 时间：2019/7/22:09:29
 * 说明：
 */
public class DemoActiviity extends AppCompatActivity {

    ImageView iv;
    Handler handler1,handler2;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo);
        iv = findViewById(R.id.iv_iv);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.INTERNET)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            downloadPic(iv);
                        }else {
                            Toast.makeText(DemoActiviity.this,"抱歉了！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler1 = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler2 = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        }).start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void downloadPic(ImageView iv) {
        Glide.with(this).load("http://pic25.nipic.com/20121117/9252150_165726249000_2.jpg")
                .fitCenter()
                .transform(new RoundTranform(this))
                .placeholder(R.drawable.ic_launcher)
                .crossFade()
                .into(iv);
    }

    static class RoundTranform extends BitmapTransformation {

        public RoundTranform(Context context) {
            super(context);
        }

        public RoundTranform(BitmapPool bitmapPool) {
            super(bitmapPool);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            int size = Math.min(outWidth,outHeight);
            int x = (outWidth - size)/2;
            int y = (outHeight - size)/2;
            Matrix matrix = new Matrix();
            matrix.postScale(0.01f,0.01f);
            toTransform = Bitmap.createBitmap(toTransform,0,0,outWidth,outHeight,matrix,true);
            Bitmap squared = Bitmap.createBitmap(toTransform,x,y,size,size);
            Bitmap result = pool.get(size,size, Bitmap.Config.ARGB_8888);
            if (result==null){
                result = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(squared,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP));
            Canvas canvas = new Canvas(result);
            float r = size/2f;
            canvas.drawCircle(r,r,r,paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    static class OvalTranfrom{

    }

}
