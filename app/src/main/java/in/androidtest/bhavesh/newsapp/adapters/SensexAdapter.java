package in.androidtest.bhavesh.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidtest.bhavesh.newsapp.R;
import in.androidtest.bhavesh.newsapp.models.NewsApi.Article;
import in.androidtest.bhavesh.newsapp.models.NewsApi.Data;
import in.androidtest.bhavesh.newsapp.ui.detail.WebViewDetailActivity;
import in.androidtest.bhavesh.newsapp.ui.sensex_module.SensexMainActivity;

public class SensexAdapter extends RecyclerView.Adapter<SensexAdapter.SensexViewHolder>  {

    ArrayList<Data> dataArrayList;
    Context context;
    int row_index = -1;
    IRecyclerClickListner iRecyclerClickListner;

    public SensexAdapter(ArrayList<Data> articleList,Context context, IRecyclerClickListner iRecyclerClickListner) {
        this.dataArrayList = articleList;
        this.iRecyclerClickListner = iRecyclerClickListner;
        this.context = context;
    }
    @Override
    public SensexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_sensex,parent,false);
        return new SensexViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SensexViewHolder holder, final int position) {

        holder.txtTime.setText(dataArrayList.get(position).getDate());

        holder.linearTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                iRecyclerClickListner.onRecyclerClick(position);
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.linearTime.setBackground(context.getResources().getDrawable(R.drawable.rectangle_round_white_background));
            holder.txtTime.setTextColor(context.getResources().getColor(R.color.blue_shade));
        }
        else
        {
            holder.linearTime.setBackground(context.getResources().getDrawable(R.drawable.rectangle_round_transparent_background));
            holder.txtTime.setTextColor(context.getResources().getColor(R.color.white));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class SensexViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linearTime)
        LinearLayout linearTime;

        @BindView(R.id.txtTime)
        TextView txtTime;

        public SensexViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}