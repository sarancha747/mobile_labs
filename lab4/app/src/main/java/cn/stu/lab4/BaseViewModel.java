package cn.stu.lab4;

import androidx.lifecycle.ViewModel;

import cn.stu.lab4.model.HolidayService;

public class BaseViewModel extends ViewModel {

    private HolidayService holidayService;

    public BaseViewModel(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    protected final HolidayService getHolidayService() {
        return holidayService;
    }

}
