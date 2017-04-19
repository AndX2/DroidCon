package com.github.andx2.droidcon.mvp;

import android.util.Log;

/**
 * Created by Andrew on 05.04.2017.
 */

public abstract class AbstractPresenter<V extends IView> {

    protected V view;

    protected void takeView (V view){
        this.view = view;
    }

    protected V getView(){
        return view;
    }

    public void dropView(){
        Log.d(getClass().getName(), "AbsPresenter dropView");
        view = null;
    }




}
