package cn.stu.lab4.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HolidayApi {
    @GET("api/v2/publicholidays/2021/US")
    Call<List<RepositoryNetworkEntity>> getRepositories();
}
