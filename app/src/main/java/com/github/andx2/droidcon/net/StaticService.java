package com.github.andx2.droidcon.net;

import com.github.andx2.droidcon.net.response.SponsorDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.OPTIONS;

/**
 * Created by Andrew on 05.04.2017.
 */

public interface StaticService {

    @GET("droidcon/static/sponsor")
    Call<SponsorDto> getSponsors();

}
