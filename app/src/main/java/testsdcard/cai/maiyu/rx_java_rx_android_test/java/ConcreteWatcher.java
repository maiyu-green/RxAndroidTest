package testsdcard.cai.maiyu.rx_java_rx_android_test.java;

/**
 * Created by maiyu on 2017/6/18.
 * 实现观察者接口的类
 */

public class ConcreteWatcher implements Watcher {
    @Override
    public void update(String str) {

        //打印出被观察者的消息的变化
        System.out.println(str);
    }
}
