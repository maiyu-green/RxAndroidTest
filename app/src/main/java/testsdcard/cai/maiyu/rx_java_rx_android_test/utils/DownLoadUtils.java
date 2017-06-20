package testsdcard.cai.maiyu.rx_java_rx_android_test.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maiyu on 2017/6/20.
 */

public class DownLoadUtils {

    //定义OkHttpClient
    private OkHttpClient okHttpClient ;

    /**
     * 构造函数：实例化OkHttpClient对象
     */
    public DownLoadUtils(){
        okHttpClient = new OkHttpClient();
    }


    /**
     * 创建观察者模式的下载方法，这里
     * @param path      -----传入地址参数
     * @return          ------返回btye[]数组
     */
    public Observable<byte[]> downloadImg(final String path){

        //使用create()方法
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {

                //判断是否已经绑定订阅
                if(!subscriber.isUnsubscribed()){

                    //使用OkhttpClient访问网络步骤
                    //创建Request对象，调用.url()传入地址，build()完成
                    Request request = new Request.Builder().url(path).build();
                    //调用okttpClient.newCall(Request对象).enqueue()执行异步get请求
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            //执行subscriber.onError()方法
                            subscriber.onError(e);
                        }

                        /**
                         * 请求成功
                         * @param response
                         * @throws IOException
                         */
                        @Override
                        public void onResponse(Response response) throws IOException {

                            //判断返回结果是否成功
                            if(response.isSuccessful()){
                                //获取返回的byte[]数组
                                byte[] data = response.body().bytes();
                                //判空
                                if(data != null){
                                    //发射数据
                                    subscriber.onNext(data);
                                }
                            }
                            //完成
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });

    }


}
