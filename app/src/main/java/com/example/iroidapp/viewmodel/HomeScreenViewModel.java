package com.example.iroidapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iroidapp.models.Data;
import com.example.iroidapp.repositories.IroidRepository;

public class HomeScreenViewModel extends ViewModel {

    private IroidRepository iroidRepository;

    public MutableLiveData<Boolean> getmIroidRequestTimeout() {
        return iroidRepository.getmIroidRequestTimeout();
    }

    public LiveData<Data> getmData(){
      return iroidRepository.getIroidData();
    }

    public void callIroidResponseApi(){
        iroidRepository.callIroidResponseApi();
    }

    public HomeScreenViewModel() {
        this.iroidRepository = IroidRepository.getInstance();
    }
}
