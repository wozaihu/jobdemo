package com.example.jobdemo.base;

import com.example.jobdemo.bean.ImageShowBean;
import com.example.jobdemo.bean.PictureBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("jsacgpic?key=umRFhxOxmmZLKWaT0wX7oaPS7zvWEEjL&return=json")
    Call<PictureBean> getPicture();

    @GET("jsacgpic?key=umRFhxOxmmZLKWaT0wX7oaPS7zvWEEjL&return=json")
    Observable<PictureBean> ObservableGetPicture();

    @GET("gqbz?key=umRFhxOxmmZLKWaT0wX7oaPS7zvWEEjL&return=json")
    Observable<PictureBean> getGQBZPicture();

    @GET("sinepic?key=umRFhxOxmmZLKWaT0wX7oaPS7zvWEEjL&return=json")
    Observable<PictureBean> getSinePicPicture();

    @GET("p?")
    Observable<ImageShowBean> getCartoonPicture(@Query("p") String page);

    @GET("j?pn=50")
    Observable<ImageShowBean> getImage(
            @Query("q") String name,
            @Query("sn") int sn);
}
