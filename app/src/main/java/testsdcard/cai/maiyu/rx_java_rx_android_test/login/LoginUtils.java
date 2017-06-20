package testsdcard.cai.maiyu.rx_java_rx_android_test.login;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by maiyu on 2017/6/20.
 * 登录类
 */

public class LoginUtils {
    //定义OkHttpClent对象
    private OkHttpClient okHttpClient ;
    public LoginUtils(){
        okHttpClient = new OkHttpClient();
    }

    /**
     * 登录实现
     * @param url
     * @param params
     * @return  String
     */
    public Observable<String> login(final String url , final Map<String , String> params){

        return  Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                //判断是否已经绑定
                if(!subscriber.isUnsubscribed()){

                    //创建FormEncodingBuilder对象
                    FormEncodingBuilder builder = new FormEncodingBuilder();

                    //添加参数
                    if(params != null && !params.isEmpty()){
                        for(Map.Entry<String , String> entry : params.entrySet()){

                            builder.add(entry.getKey() , entry.getValue());
                        }
                    }
                    //创建RequestBody对象
                    RequestBody body = builder.build();

                    //创建Request对象
                     Request request = new Request.Builder().url(url).post(body).build();

                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {

                            if(response.isSuccessful()){
                                //订阅
                                subscriber.onNext(response.body().string());
                            }
                            subscriber.onCompleted();
                        }
                    });
                }



            }
        });


    }
}
