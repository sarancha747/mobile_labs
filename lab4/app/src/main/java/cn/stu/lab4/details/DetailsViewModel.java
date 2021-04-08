package cn.stu.lab4.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import cn.stu.lab4.BaseViewModel;
import cn.stu.lab4.model.Callback;
import cn.stu.lab4.model.Cancellable;
import cn.stu.lab4.model.Holiday;
import cn.stu.lab4.model.HolidayService;
import cn.stu.lab4.model.Result;

public class DetailsViewModel extends BaseViewModel {

    private MutableLiveData<Result<Holiday>> repositoryLiveData = new MutableLiveData<>();

    {
        repositoryLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    public DetailsViewModel(HolidayService holidayService) {
        super(holidayService);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) cancellable.cancel();
    }

    public void loadRepositoryById(String data) {
        repositoryLiveData.setValue(Result.loading());
        cancellable = getHolidayService().getHolidayByData(data, new Callback<Holiday>() {
            @Override
            public void onError(Throwable error) {
                repositoryLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResults(Holiday data) {
                repositoryLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Holiday>> getResults() {
        return repositoryLiveData;
    }

}
