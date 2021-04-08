package cn.stu.lab4;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;

import cn.stu.lab4.model.HolidayService;

public class ViewModelFactory implements ViewModelProvider.Factory {

    public static final String TAG = ViewModelFactory.class.getSimpleName();

    private HolidayService holidayService;

    public ViewModelFactory(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Constructor<T> constructor = modelClass.getConstructor(HolidayService.class);
            return constructor.newInstance(holidayService);
        } catch (ReflectiveOperationException e) {
            Log.e(TAG, "Error", e);
            RuntimeException wrapper = new RuntimeException();
            wrapper.initCause(e);
            throw wrapper;
        }
    }

}
