package testsdcard.cai.maiyu.rx_java_rx_android_test.java2;

import java.util.Observable;

/**
 * Created by maiyu on 2017/6/18.
 * 创建一个被观察者类
 */

public class SimpleObservable extends Observable {

    //定义一个数据
    private int data = 0;

    //get方法
    public int getData() {
        return data;
    }

    /**
     * setData方法
     * @param i
     */
    public void setData(int i) {
        if(this.data != i) {
            this.data = i;

            //设置状态改变
            setChanged();
            //表示状态改变，通知观察者
            notifyObservers();
        }
    }
}
