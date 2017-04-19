package com.github.andx2.droidcon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public class SchedulerListAdapter extends RecyclerView.Adapter<SchedulerListAdapter.ViewHolder> {

    //todo drop this constructor: it for tests

    public SchedulerListAdapter(Context context, List<Session> sessionList){
        this.context = context;
        this.sessionList = sessionList;
    }

    private List<Session> sessionList;
    private Context context;

    @Override
    public SchedulerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.scheduler_item, parent, false);
        return new SchedulerListAdapter.ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(SchedulerListAdapter.ViewHolder holder, int position) {
//        holder.tvTitle.setText(newsList.get(position).getTitle());
//        holder.tvBody.setText(newsList.get(position).getBody());
//        Picasso.with(context)
//                .load(newsList.get(position).getTitlePicUrl())
//                .into(holder.imgTitle);

    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView tvTitle;
//        private TextView tvBody;
//        private ImageView imgTitle;

        public ViewHolder(View itemView) {
            super(itemView);

//            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_recycler_title);
//            tvBody = (TextView) itemView.findViewById(R.id.tv_item_recycler_body);
//            imgTitle = (ImageView) itemView.findViewById(R.id.img_item_recycler_home);
        }
    }
}
