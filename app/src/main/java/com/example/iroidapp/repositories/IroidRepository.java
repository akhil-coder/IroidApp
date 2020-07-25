package com.example.iroidapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.iroidapp.models.Data;
import com.example.iroidapp.requests.IroidApiClient;
import com.example.iroidapp.requests.response.IroidResponse;

import java.util.List;

public class IroidRepository {

    private static IroidRepository instance;
    private IroidApiClient mIroidApiClient;

    public MutableLiveData<Boolean> getmIroidRequestTimeout() {
        return mIroidApiClient.getmIroidRequestTimeout();
    }

    public LiveData<Data> getIroidData(){
        return mIroidApiClient.getmData();
    }

    public void callIroidResponseApi(){
        mIroidApiClient.callIroidResponseApi();
    }

    public static IroidRepository getInstance() {
        if(instance == null){
            instance = new IroidRepository();
        }
        return instance;
    }

    public IroidRepository() {
        mIroidApiClient = mIroidApiClient.getInstance();
    }
}
