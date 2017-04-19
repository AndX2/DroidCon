package com.github.andx2.droidcon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.bluelinelabs.conductor.Controller;

/**
 * Created by Andrew on 16.04.2017.
 */

public abstract class BaseController extends Controller implements View.OnClickListener {
    protected BaseController() {
    }

    protected BaseController(Bundle args) {
        super(args);
    }

//    protected ActionBar getActionBar() {
//        ActionBarProvider actionBarProvider = ((ActionBarProvider)getActivity());
//        return actionBarProvider != null ? actionBarProvider.getSupportActionBar() : null;
//    }

    protected void switchDrawer() {
        DrawerOpenerProvider drawerOpenerProvider = (DrawerOpenerProvider) getActivity();
        drawerOpenerProvider.DrawerOpen();
    }

    @Override
    protected void onAttach(@NonNull View view) {
//        setTitle();
        super.onAttach(view);
    }

    //    protected void setTitle() {
//        Controller parentController = getParentController();
//        while (parentController != null) {
//            if (parentController instanceof BaseController && ((BaseController)parentController).getTitle() != null) {
//                return;
//            }
//            parentController = parentController.getParentController();
//        }
//
//        String title = getTitle();
//        ActionBar actionBar = getActionBar();
//        if (title != null && actionBar != null) {
//            actionBar.setTitle(title);
//        }
//    }
//
//    protected String getTitle() {
//        return null;
//    }
    @Override
    public void onClick(View v) {
        switchDrawer();
    }


}
