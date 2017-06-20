package testsdcard.cai.maiyu.rx_java_rx_android_test.java;

/**
 * Created by maiyu on 2017/6/18.
 * 观察者接口
 */

public interface Watcher {
    //通过update()方法监听到状态的通知
    public void update(String str);
}
