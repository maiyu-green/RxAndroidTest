package testsdcard.cai.maiyu.rx_java_rx_android_test.java2;

/**
 * Created by maiyu on 2017/6/18.
 */

public class Test {
    public static void main(String[] args)throws Exception{

        //创建被观察者对象
        SimpleObservable observable = new SimpleObservable();
        //创建观察者对象，把被观察者当做参数
        SimpleObserver observer = new SimpleObserver(observable);

        observable.setData(1);
        observable.setData(2);
        //此处，因为状态还是2，没有改变，不会通知
        observable.setData(2);
        observable.setData(3);

    }
}


/**
 * 结果输出：
 data is changed: 1
 data is changed: 2
 data is changed: 3
 *
 */

