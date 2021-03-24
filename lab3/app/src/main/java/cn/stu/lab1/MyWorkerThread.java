package cn.stu.lab1;

import android.os.Handler;
import android.os.HandlerThread;

public class MyWorkerThread extends HandlerThread {
    private Handler mWorkerHandler;

    public MyWorkerThread(String name) {
        super(name);
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }

    public void prepareHandler(){
        mWorkerHandler = new Handler(getLooper());
    }
}
