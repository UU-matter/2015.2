package ru.mirea.oop.practice.coursej.tg;

import com.google.gson.annotations.SerializedName;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;

public final class Result<E> {
    @SerializedName("ok")
    public Boolean ok;
    @SerializedName("result")
    public E result;

    public static <E> E call(Call<Result<E>> callable) throws IOException {
        if (callable == null)
            return null;
        Response<Result<E>> result = callable.execute();
        if (result.isSuccess() && result.body().result != null)
            return result.body().result;
        if (!result.body().ok) {
            throw new RuntimeException(result.message());
        }
        System.out.println(result.errorBody());
        return null;
    }
}
