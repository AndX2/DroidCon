package com.github.andx2.droidcon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    //todo drop this constructor: it for tests

    public NewsListAdapter(Context context, List<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    private List<News> newsList;
    private Context context;

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.home_view_recycler_item, parent, false);
        return new NewsListAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(newsList.get(position).getTitle());
        holder.tvBody.setText(newsList.get(position).getBody());
        holder.tvBody.setLayoutParams(
                new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        Picasso.with(context)
                .load(newsList.get(position).getTitlePicUrl())
                .into(holder.imgTitle);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvBody;
        private ImageView imgTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_recycler_title);
            tvBody = (TextView) itemView.findViewById(R.id.tv_item_recycler_body);
            imgTitle = (ImageView) itemView.findViewById(R.id.img_item_recycler_home);
        }
    }
}
