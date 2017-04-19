package com.github.andx2.droidcon;

/**
 * Created by Andrew on 05.04.2017.
 */

public interface AppConfig {

    interface Net{
        String STATIC_BASE_URL = "https://AndX2.github.io/";
        int STATIC_MAX_CONNECT_TIMEOUT = 2_000;
        int STATIC_MAX_READ_TIMEOUT = 2_000;
    }

    interface Splash{
        int SHOW_TIME_MILIS = 2_000;
        int PARTNER_LOGO_WIDTH_DP = 150;
    }

}
