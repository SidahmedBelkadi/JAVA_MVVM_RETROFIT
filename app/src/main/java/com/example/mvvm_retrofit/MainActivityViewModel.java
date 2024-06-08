package com.example.mvvm_retrofit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<UserModel> createNewUserLiveData;

    public MainActivityViewModel() {
        createNewUserLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserModel> getUserObserver() {
        return createNewUserLiveData;
    }

    public void createNewUser(UserModel user) {
        RetrofitServiceInterface retrofitServiceInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitServiceInterface.class);
        retrofitServiceInterface.createUser(user).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    createNewUserLiveData.postValue(response.body());
                }else {
                    createNewUserLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                createNewUserLiveData.postValue(null);
            }
        });
    }




}
