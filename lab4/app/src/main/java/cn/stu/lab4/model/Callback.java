package cn.stu.lab4.model;

public interface Callback<T> {

    void onError(Throwable error);

    void onResults(T data);

}
