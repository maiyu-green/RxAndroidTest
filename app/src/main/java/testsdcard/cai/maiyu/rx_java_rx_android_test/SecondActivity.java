package testsdcard.cai.maiyu.rx_java_rx_android_test;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import testsdcard.cai.maiyu.rx_java_rx_android_test.login.LoginUtils;
import testsdcard.cai.maiyu.rx_java_rx_android_test.utils.DownLoadUtils;

/**
 * Created by maiyu on 2017/6/20.
 * 在实际开发中Activity充当的角色太多了：
 * 1.UI主线程负责绘制UI
 * 2.开启子线程获取网络数据
 * 3.赋值到控件中
 * 4.判断逻辑等等
 */

/**
 * 下载张图片：
 * 1.AsyncTask
 * 2.Handler机制
 * 3.发现有更好的即观察者模式
 */
public class SecondActivity extends AppCompatActivity {

    //tag
    private static final String TAG = "SecondActivity";
    //定义按钮
    private Button mBtnLoad;
    //定义ImageView
    private ImageView mImg;
    //定义DownLoadUtils
    private DownLoadUtils utils;
    //定义图片地址
    private String imgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497953788142&di=370d893f7390ea12dc770083b171c1f2&imgtype=0&src=http%3A%2F%2Fxke8.com%2Fuploads%2Fallimg%2Fc150921%2F1442O51G4A0-1I13.jpg";


    //定义登录对象
    private LoginUtils loginUtils;
    //定义登录url
    private String loginUrl = "";

    //添加进度条窗口
    private ProgressDialog pd ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        //绑定id
        mBtnLoad    =   (Button)findViewById(R.id.btn_load);
        mImg        =   (ImageView)findViewById(R.id.img_view);
        //初始化DownLoadUtils
        utils = new DownLoadUtils();

        //初始化LoginUtils
        loginUtils = new LoginUtils();

        //初始化进度条
        pd = new ProgressDialog(this);
        pd.setTitle("图片加载中...");
       // pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        /**
         * 为按钮设置监听
         */
        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //下载图片------设置订阅执行方式（通过网络获取数据）-----设置主线程显示
                utils.downloadImg(imgPath).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<byte[]>() {
                            @Override
                            public void onCompleted() {
                                pd.dismiss();
                                Log.i(TAG , "onCompleted");//对话框取消
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG , "onError: " + e);
                            }

                            @Override
                            public void onNext(byte[] bytes) {

                                pd.show();
                                //显示图片，这里没有对图片进行压缩等处理
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0 , bytes.length);
                                mImg.setImageBitmap(bitmap);
                            }
                        });

            }
        });
    }



    public void toLogin(){


        Map<String , String> params = new HashMap<String , String>();
        params.put("username" , "获取用户名");
        params.put("password" , "获取密码");

        loginUtils.login(loginUrl , params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG , "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG , "onError : " + e);
                    }

                    @Override
                    public void onNext(String s) {

                        //跳转...
                        Intent intent = new Intent();
                        startActivity(intent);
                    }
                });

    }
}
