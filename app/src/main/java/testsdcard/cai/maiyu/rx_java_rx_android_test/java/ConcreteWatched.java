package testsdcard.cai.maiyu.rx_java_rx_android_test.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maiyu on 2017/6/18.
 * 实现被观察者的类
 */

public class ConcreteWatched implements Watched {

    //创建List集合
    private List<Watcher> list = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        //添加观察者对象
        list.add(watcher);

    }

    @Override
    public void removeWatcher(Watcher watcher) {
        //移除观察者对象
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {

        //遍历，通知所有观察者被观察对象的消息变化
        for(Watcher watcher : list){
            watcher.update(str);
        }

    }
}
