package com.github.andx2.droidcon.mvp;

import com.github.andx2.droidcon.mvp.AbstractPresenter;

/**
 * Created by Andrew on 05.04.2017.
 */

public abstract class AbstractView <P extends AbstractPresenter> {

    protected P presenter;
}
