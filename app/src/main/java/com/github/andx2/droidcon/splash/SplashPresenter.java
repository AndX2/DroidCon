package com.github.andx2.droidcon.splash;

import android.content.Intent;
import android.util.Log;

import com.github.andx2.droidcon.mvp.AbstractPresenter;
import com.github.andx2.droidcon.AppConfig;
import com.github.andx2.droidcon.BuildConfig;
import com.github.andx2.droidcon.MainActivity;
import com.github.andx2.droidcon.net.StaticService;
import com.github.andx2.droidcon.net.StaticServiceGenerator;
import com.github.andx2.droidcon.net.response.SponsorDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by Andrew on 05.04.2017.
 */

public class SplashPresenter extends AbstractPresenter<SplashActivity> {

    //region Singleton boilerplate
    private static SplashPresenter instance;

    private SplashPresenter() {

    }

    public static synchronized SplashPresenter getInstance() {
        if (instance == null)
            instance = new SplashPresenter();
        return instance;
    }
    //endregion

//    private SponsorModel sponsorModel = new SponsorModel();

    private StaticService staticService;
    private SponsorDto sponsorDto;
    private boolean isNetCalled = false;

    private void getSponsors(Callback<SponsorDto> callback){
        isNetCalled = true;
        staticService = StaticServiceGenerator.createService(StaticService.class);
        staticService.getSponsors().enqueue(callback);
    }

    @Override
    protected void takeView(SplashActivity view) {
        super.takeView(view);
        CloseSplashTimerTask closeSplashTimerTask = new CloseSplashTimerTask();
        Timer closeSplashTimer = new Timer();
        closeSplashTimer.schedule(closeSplashTimerTask, AppConfig.Splash.SHOW_TIME_MILIS);

        //region rx push partners
//        sponsorModel
//                .getSponsors()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        sponsorDto -> {
//                            if (BuildConfig.DEBUG)
//                                Log.d(getClass().getName(), (new Gson()).toJson(sponsorDto));
//                            if (view != null)
//                                view.showSponsors(sponsorDto);
//                        },
//                        throwable -> {
//                            if (BuildConfig.DEBUG)
//                                Log.d(getClass().getName(), "Error sponsorModel.getSponsors()");
//                        });

//        getCloser()
//                .observeOn(Schedulers.io())
//                .first()
//                .timer(AppConfig.Splash.SHOW_TIME_MILIS, TimeUnit.MILLISECONDS)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(Long -> {
//
//        });
        //endregion
    }

    private void closeSplash(){
        if (getView() != null)
            getView().runOnUiThread(() -> {
                Intent intent = new Intent(getView(), MainActivity.class);
                getView().startActivity(intent);
                getView().finish();
            });
    }
    class CloseSplashTimerTask extends TimerTask {
        @Override
        public void run() {
            closeSplash();
        }
    }

    public void viewReady() {
        if(sponsorDto != null){
            getView().showSponsors(sponsorDto);
        }else if(!isNetCalled)
            getSponsors(new Callback<SponsorDto>() {
                @Override
                public void onResponse(Call<SponsorDto> call, Response<SponsorDto> response) {
                    sponsorDto = response.body();
                    if (getView() != null){
                        getView().showSponsors(sponsorDto);
                    }
                }
                @Override
                public void onFailure(Call<SponsorDto> call, Throwable t) {
                    closeSplash();
                }
            });
    }

//    private BehaviorSubject<Long> closeSubject = BehaviorSubject.create();
//    private Observable<Long> getCloser(){
//        closeSubject.onNext(null);
//        return closeSubject.asObservable();
//    }

}
