package in.androidtest.bhavesh.newsapp.ui.sensex_module;

import org.json.JSONArray;

import java.util.ArrayList;

import in.androidtest.bhavesh.newsapp.models.NewsApi.Data;
import in.androidtest.bhavesh.newsapp.models.NewsApi.NewsMain;

public interface SensexViewInterface {
    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displaySensexData(ArrayList<Data> s);
    void displayError(String s);
}
