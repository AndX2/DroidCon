package com.github.andx2.droidcon.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.andx2.droidcon.AppConfig;
import com.github.andx2.droidcon.BuildConfig;
import com.github.andx2.droidcon.mvp.IView;
import com.github.andx2.droidcon.R;
import com.github.andx2.droidcon.net.response.SponsorDto;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Andrew on 04.04.2017.
 */

public class SplashActivity extends AppCompatActivity implements IView {

    private SplashPresenter presenter;
    private Map<View, String> partnersMap = new HashMap<>();
    private View.OnClickListener partnersListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (presenter == null)
            presenter = SplashPresenter.getInstance();

        partnersListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = partnersMap.get(v);
                Log.d(getClass().getName(), "url = " + url);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        };

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.takeView(this);
////        MyTimerTask timerTask = new MyTimerTask();
////        Timer timer = new Timer();
////        timer.schedule(timerTask, 100);
//    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.takeView(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(partnersMap.isEmpty()) presenter.viewReady();

    }

//    class MyTimerTask extends TimerTask {
//        @Override
//        public void run() {
//            getWidth();
//        }
//    }

//    private void getWidth(){
//        LinearLayout llPartnerContainer = (LinearLayout) findViewById(R.id.ll_partner_container);
//        Log.d(getClass().getName(), "onResume llPartnerContainer.getWidth() = " + llPartnerContainer.getWidth());
//
//    }

    @Override
    protected void onStop() {
        presenter.dropView();
        super.onStop();
    }

    public void showSponsors(SponsorDto sponsorDto){

        TextView cardPartnerTitle = (TextView) findViewById(R.id.tv_partners);
        cardPartnerTitle.setText(sponsorDto.getCategory().get(0).getName());

        LinearLayout llPartnerContainer = (LinearLayout) findViewById(R.id.ll_partner_container);
        int number_logo_into_card_width =
                llPartnerContainer.getMeasuredWidth()/ (int)(AppConfig.Splash.PARTNER_LOGO_WIDTH_DP * getDestiny());
        int partnerViewWidth = llPartnerContainer.getMeasuredWidth()/number_logo_into_card_width;
        LinearLayout.LayoutParams itemParams =
                new LinearLayout.LayoutParams(partnerViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams rowContainerParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutInflater layoutInflater = this.getLayoutInflater();

        int count = sponsorDto.getCategory().get(0).getMembers().size();

        for (int i = 0; i < (count + number_logo_into_card_width - 1)/number_logo_into_card_width; i++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(rowContainerParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            for(int j = 0; j < number_logo_into_card_width; j++){
                if((i * number_logo_into_card_width + j) < count){
                    View view = layoutInflater.inflate(R.layout.item_partner, linearLayout, false);
                    view.setLayoutParams(itemParams);
                    TextView tvTitle = (TextView)view.findViewById(R.id.tv_partner_title);
                    tvTitle.setText(sponsorDto.getCategory().get(0)
                            .getMembers().get(i * number_logo_into_card_width + j).getName());
                    partnersMap.put(view,sponsorDto.getCategory().get(0)
                            .getMembers().get(i * number_logo_into_card_width + j).getLink());
                    view.setOnClickListener(partnersListener);
                    ImageView imgLogo = (ImageView)view.findViewById(R.id.img_partner);
                    Picasso
                            .with(this)
                            .load(sponsorDto.getCategory().get(0)
                                    .getMembers().get(i * number_logo_into_card_width + j).getImg())
                            .into(imgLogo);
                    linearLayout.addView(view);
                }
            }
            llPartnerContainer.addView(linearLayout);
        }

        TextView cardInfoTitle = (TextView) findViewById(R.id.tv_info);
        cardInfoTitle.setText(sponsorDto.getCategory().get(1).getName());

        LinearLayout llInfoContainer = (LinearLayout) findViewById(R.id.ll_info_container);

        int countInfo = sponsorDto.getCategory().get(1).getMembers().size();

        for (int i = 0; i < (countInfo + number_logo_into_card_width - 1)/number_logo_into_card_width; i++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(rowContainerParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
            for(int j = 0; j < number_logo_into_card_width; j++){
                if((i * number_logo_into_card_width + j) < countInfo){
                    View view = layoutInflater.inflate(R.layout.item_partner, linearLayout, false);
                    view.setLayoutParams(itemParams);
                    TextView tvTitle = (TextView)view.findViewById(R.id.tv_partner_title);
                    tvTitle.setText(sponsorDto.getCategory().get(1)
                            .getMembers().get(i * number_logo_into_card_width + j).getName());
                    partnersMap.put(view,sponsorDto.getCategory().get(1)
                            .getMembers().get(i * number_logo_into_card_width + j).getLink());
                    view.setOnClickListener(partnersListener);
                    ImageView imgLogo = (ImageView)view.findViewById(R.id.img_partner);
                    Picasso
                            .with(this)
                            .load(sponsorDto.getCategory().get(1)
                                    .getMembers().get(i * number_logo_into_card_width + j).getImg())
                            .into(imgLogo);
                    linearLayout.addView(view);
                }
            }
            llInfoContainer.addView(linearLayout);
        }



//        for (int i = 0; i < count; i++){
//            View view = layoutInflater.inflate(R.layout.item_partner, llPartnerContainer, false);
//            view.setLayoutParams(itemParams);
//            TextView textView = (TextView)view.findViewById(R.id.tv_partner_title);
//            textView.setText(sponsorDto.getCategory().get(0).getMembers().get(i).getName());
//            llPartnerContainer.addView(view);
////            TextView textView = new TextView(this);
////            textView.setLayoutParams(itemParams);
////            textView.setText(sponsorDto.getCategory().get(0).getMembers().get(i).getName());
////            llPartnerContainer.addView(textView);
//        }
    }

    private float getDestiny(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dest = metrics.density;
        return dest;
    }
}
