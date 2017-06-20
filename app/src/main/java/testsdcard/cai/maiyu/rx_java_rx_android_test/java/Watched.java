package testsdcard.cai.maiyu.rx_java_rx_android_test.java;

/**
 * Created by maiyu on 2017/6/18.
 * 被观察者接口：
 */

public interface Watched {
    //添加观察者
    public void addWatcher(Watcher watcher);
    //移除观察者
    public void removeWatcher(Watcher watcher);
    //提醒所以的观察者
    public void notifyWatchers(String str);

}
