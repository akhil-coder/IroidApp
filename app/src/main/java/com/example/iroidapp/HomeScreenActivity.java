package com.example.iroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.iroidapp.models.Banners;
import com.example.iroidapp.models.Data;
import com.example.iroidapp.requests.ServiceGenerator;
import com.example.iroidapp.requests.response.IroidResponse;
import com.example.iroidapp.viewmodel.HomeScreenViewModel;

import retrofit2.Call;

public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = "HomeScreenActivity";
    HomeScreenViewModel homeScreenViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        homeScreenViewModel.callIroidResponseApi();
        subscribeObservers();
    }

    public void subscribeObservers(){

        homeScreenViewModel.getmData().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                Log.d(TAG, "onChanged: Data changed ");
                if(data != null){
                    Banners[] banners = data.getBanners();
                    Log.d(TAG, "onChanged: " + banners[0].getName());
                }
            }
        });
    }
}
