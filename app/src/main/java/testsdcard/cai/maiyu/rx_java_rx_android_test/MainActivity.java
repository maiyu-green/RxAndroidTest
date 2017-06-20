package testsdcard.cai.maiyu.rx_java_rx_android_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import testsdcard.cai.maiyu.rx_java_rx_android_test.android_rx.RxUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 第一种方法
     * @param view
     */
    public void btnOnClick(View view){
        RxUtils.createObservable();
    }

    /**
     * 第二种方法
     * @param view
     */
    public void btnOnClick2(View view){
        RxUtils.createObservable2();
    }

    /**
     * from方法测试
     */
    public void btnOnClick3(View view){
        RxUtils.from();
    }

    /**
     * interval方法测试
     */
    public void interval(View view){
        RxUtils.interval();
    }

    /**
     * just方法测试范围数据
     */

    public void just(View view){
        RxUtils.just();
    }

    /**
     * Observable.range()方法
     * @param view
     */
    public void range(View view){
        RxUtils.range();
    }

    /**
     * Observable.filter()方法：用于过滤
     * @param view
     */
    public void filter(View view){
        RxUtils.filter();
    }

}
