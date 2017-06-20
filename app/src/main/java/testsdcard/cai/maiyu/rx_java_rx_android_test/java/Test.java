package testsdcard.cai.maiyu.rx_java_rx_android_test.java;

/**
 * Created by maiyu on 2017/6/18.
 * 测试类
 */

public class Test {
    public static void main(String[] args)throws Exception{

        //新建一个被观察者对象：小明
        Watched xiaoming = new ConcreteWatched();

        //新建3个观察者来监听小明的状态
        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        //把监听器安装在小明身上
        xiaoming.addWatcher(watcher1);
        xiaoming.addWatcher(watcher2);
        xiaoming.addWatcher(watcher3);

        //小明的状改变了
        xiaoming.notifyWatchers("我打算要偷东西了");

    }
}


//输入如下：
/**
 我打算要偷东西了
 我打算要偷东西了
 我打算要偷东西了
 */
