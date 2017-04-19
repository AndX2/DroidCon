package com.github.andx2.droidcon.splash;

import com.github.andx2.droidcon.net.StaticService;
import com.github.andx2.droidcon.net.StaticServiceGenerator;
import com.github.andx2.droidcon.net.response.SponsorDto;

import java.io.IOException;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Andrew on 05.04.2017.
 */

public class SponsorModel {

    public SponsorModel() {
        staticService = StaticServiceGenerator.createService(StaticService.class);
    }

    private StaticService staticService;

    private BehaviorSubject<SponsorDto> sponsorSubject = BehaviorSubject.create();

    public Observable<SponsorDto> getSponsors() {

        return Observable.<SponsorDto>create(subscriber -> {
            SponsorDto sponsorDto = null;
            if (subscriber.isUnsubscribed()) {
                //todo: here execute sync code checking cache
                try {
                    sponsorDto = staticService.getSponsors().execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (sponsorDto != null) {
                        subscriber.onNext(sponsorDto);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new IOException());
                    }
                } catch (Exception e) {
                    subscriber.onError(new Throwable());
                }
            }
        })
                .flatMap(sponsors -> {
                    sponsorSubject.onNext(sponsors);
                    return sponsorSubject.asObservable();
                });
    }

}
