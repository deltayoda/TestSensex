package in.androidtest.bhavesh.newsapp.ui.sensex_module;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import in.androidtest.bhavesh.newsapp.models.NewsApi.Data;
import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;
import in.androidtest.bhavesh.newsapp.network.NetworkClient;
import in.androidtest.bhavesh.newsapp.network.NetworkInterface;
import in.androidtest.bhavesh.newsapp.ui.Constants;
import in.androidtest.bhavesh.newsapp.ui.main.MainViewInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SensexPresenter implements SensexPresenterInterface {

    SensexViewInterface mvi;
    private String TAG = "SensexPresenter";

    public SensexPresenter(SensexViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void getModuleDate() {
        getSensexObservable().subscribeWith(getSenseObserver());
    }

    public Observable<JsonArray> getSensexObservable(){
        return NetworkClient.getRetrofitNew().create(NetworkInterface.class)
                .getDemoSensex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public DisposableObserver<JsonArray> getSenseObserver(){
        return new DisposableObserver<JsonArray>() {

            @Override
            public void onNext(@NonNull JsonArray response) {
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<Data>>(){}.getType();
                ArrayList<Data> dataArrayList = (ArrayList<Data>) gson.fromJson(response, collectionType);
                mvi.displaySensexData(dataArrayList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                mvi.displayError("Error fetching Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                mvi.hideProgressBar();
            }
        };
    }
}
