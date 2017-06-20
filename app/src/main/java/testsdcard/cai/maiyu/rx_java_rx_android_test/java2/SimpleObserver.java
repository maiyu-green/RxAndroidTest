package testsdcard.cai.maiyu.rx_java_rx_android_test.java2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by maiyu on 2017/6/18.
 * 观察者
 */

public class SimpleObserver implements Observer {

    /**
     * 初始化，为被观察者添加观察者对象
     * @param observable
     */
    public SimpleObserver(SimpleObservable observable){
        observable.addObserver(this);

    }

    /**
     * 当被观察者的数据改变时，触发此方法
     * @param o
     * @param object
     */
    @Override
    public void update(Observable o, Object object) {

        //强制转换为SimpleObservable类，并执行它的getData()方法
        System.out.println("data is changed: " + ((SimpleObservable)o).getData());

    }
}
