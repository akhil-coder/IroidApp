package com.example.iroidapp.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.iroidapp.util.AppExecutors;
import com.example.iroidapp.models.Data;
import com.example.iroidapp.requests.response.IroidResponse;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.iroidapp.util.Constants.NETWORK_TIMEOUT;

public class IroidApiClient {
    private static final String TAG = "IroidApiClient";

    private static IroidApiClient instance;
    private MutableLiveData<Data> mData;
    private MutableLiveData<Boolean> mIroidRequestTimeout = new MutableLiveData<>();

    private RetrieveIroidRunnable mRetrieveIroidRunnable;

    public IroidApiClient() {
        this.mData = new MutableLiveData<>()    ;
    }

    public static IroidApiClient getInstance(){
        if(instance == null){
            instance = new IroidApiClient();
        }
        return instance;
    }

    public MutableLiveData<Boolean> getmIroidRequestTimeout() {
        return mIroidRequestTimeout;
    }

    public MutableLiveData<Data> getmData() {
        return mData;
    }

    public void callIroidResponseApi(){
        if(mRetrieveIroidRunnable != null){
            mRetrieveIroidRunnable = null;
        }
        mRetrieveIroidRunnable = new RetrieveIroidRunnable();

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveIroidRunnable);
        mIroidRequestTimeout.setValue(false);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Let the user know it is timeout
                mIroidRequestTimeout.postValue(true);
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveIroidRunnable implements Runnable{

        public RetrieveIroidRunnable() {
        }

        @Override
        public void run() {
            try {
                Response response = getIroid().execute();
                    Data data = ((IroidResponse) response.body()).getData();
                    mData.postValue(data);
            } catch (IOException e) {
                e.printStackTrace();
                mData.postValue(null);
            }
        }

        private Call<IroidResponse> getIroid(){
            return ServiceGenerator.getIroidApi().getRecipe("api/common");
        }
    }

}
