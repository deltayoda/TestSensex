package in.androidtest.bhavesh.newsapp.ui.sensex_module;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.guilhe.views.CircularProgressView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import in.androidtest.bhavesh.newsapp.CommonResourse;
import in.androidtest.bhavesh.newsapp.R;
import in.androidtest.bhavesh.newsapp.adapters.IRecyclerClickListner;
import in.androidtest.bhavesh.newsapp.adapters.SensexAdapter;
import in.androidtest.bhavesh.newsapp.models.NewsApi.Data;

public class SensexMainActivity extends AppCompatActivity implements SensexViewInterface,IRecyclerClickListner {

    private String TAG = "SensexMainActivity";
    public  final long NUM_ANIMATION_DURATION = 0500;


    @BindView(R.id.recyclerTime)
    RecyclerView recyclerTime;

    @BindView(R.id.progressCircular)
    CircularProgressView progressCircular;

    @BindView(R.id.txtProgressBlue)
    TextView txtProgressBlue;

    @BindView(R.id.txtProgressYellow)
    TextView txtProgressYellow;

    @BindView(R.id.txtDesription)
    TextView txtDesription;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tbText)
    TextView tbText;

    RecyclerView.Adapter adapter;
    SensexPresenter sensexPresenter;

    private ArrayList<Data> dataArrayList;
    private int shareValue;
    private int fixedValue;

    //RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensex_main);
        ButterKnife.bind(this);
        setupMVP();
        setupViews();
        getSensexData();
    }

    private void getSensexData() {
        sensexPresenter.getModuleDate();
    }

    private void setupMVP() {
        sensexPresenter = new SensexPresenter(this);
    }

    private void setupViews(){
        //Added in Part 2 of the series
        setToolbar();
        recyclerTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    private void setToolbarTitle(int grievance_id) {
        tbText.setText("#"+String.valueOf(grievance_id));
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displaySensexData(ArrayList<Data> s) {
        dataArrayList = s;
        adapter = new SensexAdapter(s, this,this);
        recyclerTime.setAdapter(adapter);
    }


    @Override
    public void displayError(String s) {

    }

    @Override
    public void onRecyclerClick(int position) {
        changeProgress(dataArrayList.get(position).getEquity());
        txtDesription.setText(dataArrayList.get(position).getPoint());
    }

    private void changeProgress(String equity) {
        progressCircular.setProgress(Float.parseFloat(equity));
        shareValue = Integer.parseInt(equity);
        fixedValue = 100 - shareValue;

        txtProgressBlue.setText(""+fixedValue);
        txtProgressYellow.setText(""+shareValue);

        int oldtxtProgressBlue = Integer.parseInt(txtProgressBlue.getText().toString());
        int oldtxtProgressYellow = Integer.parseInt(txtProgressYellow.getText().toString());

        CommonResourse.setNumberTextViewAnimationAndStart(txtProgressBlue, oldtxtProgressBlue, (int) Math.ceil(fixedValue), NUM_ANIMATION_DURATION);
        CommonResourse.setNumberTextViewAnimationAndStart(txtProgressYellow, oldtxtProgressYellow, (int) Math.ceil(shareValue), NUM_ANIMATION_DURATION);
    }
}
