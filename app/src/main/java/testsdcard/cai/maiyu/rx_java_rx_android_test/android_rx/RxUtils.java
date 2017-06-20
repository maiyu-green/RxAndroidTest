package testsdcard.cai.maiyu.rx_java_rx_android_test.android_rx;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by maiyu on 2017/6/18.
 */

public class RxUtils {

    private static final String TAG = RxUtils.class.getSimpleName();


    /**
     * 第一种创建方式：
     */
    public static void createObservable(){

        //使用Observable.create方法来创建
        Observable<String > observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("hello");
                subscriber.onNext("good");
                subscriber.onNext(downLoadJson());
                subscriber.onNext("约吗");

                //调用onCompleted()方法来完成
                subscriber.onCompleted();
            }
        });

        //创建Subscriber对象，这里传入字符串类型，因为什么我们是发送字符串
        Subscriber<String> showsub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG , "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG , "onError:"+ e.toString());
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG , "onNext: " + s);
            }
        };

        /**
         * 关联被观察者
         */
        observable.subscribe(showsub);

    }

    /**
     * 定义下载方法
     * @return
     */
    private static String downLoadJson() {
        return "json_data";
    }


    /**
     * 第二种创建方式：
     */

    public static void createObservable2(){

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                //如果没有解除关联
                if(!subscriber.isUnsubscribed()){

                    for(int i = 0 ; i < 10 ; i++){
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }

            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG , "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG , "e: " + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG , "onNext: " +integer);
            }
        });

    }


    /**
     * Observable.from()方法
     * 使用在被观察者，返回的一般都是数据类型
     */
    public static void from(){

        Integer[] items = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9};
        Observable observable = Observable.from(items);

        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {

                Log.i(TAG , o.toString());
            }
        });

    }

    /**
     * interval指定某一时刻进行数据发送：定时器
     */
    public static void interval(){

        //每隔1秒发送一次数据
        Observable observable = Observable.interval(1 , 1 , TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG , o.toString());
            }
        });

    }


    /**
     * 范围数据：如下面依次输出两个数组的元素
     */
    public static void just(){

        //定义两个数组
        Integer[] items1 = {1 , 3 , 5 , 7 , 9};
        Integer[] items2 = {2 , 4 , 6 , 8 , 10};

        //调用Observable.just(数组1，数组2,.....数组10);
        Observable observable   =   Observable.just(items1 , items2);

        //在这里设置Subscriber的类型为Integer[]:对应上面的Integer类型的数组
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG , "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG , "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer[] o) {
                for(Integer i : o)
                    Log.d(TAG , "onNext : " +i);
            }
        });

    }


    /**
     * range方法：定义开始下标与个数
     */
    public static void range(){

        //利用Observable.range(开始下标，个数);
        Observable observable   =   Observable.range(3 , 5);

        //传入Action1的参数类型Integer
        observable.subscribe(new Action1<Integer>() {

            @Override
            public void call(Integer integer) {
                Log.d(TAG , "call : " + integer);
            }
        });
    }


    /**
     * Observable.filter()方法：用于过滤某些东东
     */
    public static void filter(){

        //创建一个数组
        Integer[] items = {1 , 2 , 3 , 4 , 5 , 6};
        //调用from添加
        Observable observable = Observable.from(items);
        //添加过滤器，传入<Integer,Boolean>参数：过滤小于等于3的数字
        observable.filter(new Func1<Integer , Boolean>() {

            @Override
            public Boolean call(Integer integer) {
                return integer > 3;
            }
        })
                //设置订阅者是如何执行的：Schedulers.io---通过网络获取数据
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG , "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG , "onError : " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG , "onNext : " + integer);
            }

        });
    }

}
