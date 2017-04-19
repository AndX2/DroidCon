package com.github.andx2.droidcon.utils;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Andrew on 09.01.2017.
 */
public class CustomCachingGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        // set size & external vs. internal
        int cacheSize100MegaBytes = 104_857_600;
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );
        Log.d(getClass().getName(), "Glide applyOptions");

        //builder.setDiskCache(
        //new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        Log.d(getClass().getName(), "Glide registerComponents");
        // nothing to do here
    }
}

/* #1  * Using GlideModule

<application>
...
<meta-data
        android:name="com.github.andx2.androidmiddlelearn.utils.CustomCachingGlideModule"
        android:value="GlideModule" />
</application>
*/

/* #2 * include into proguard
-keepnames class * com.github.andx2.androidmiddlelearn.utils.CustomCachingGlideModule
 */

/* #3dependency module
compile 'com.github.bumptech.glide:glide:3.7.0'
*/


/* #4  *Using listener callback for handling events
    RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            Log.d("GlideTAG", "error - " + e);
            // important to return false so the error placeholder can be placed
            return false;
        }
        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            Log.d("GlideTAG", "ready - ");
            return false;
        }
    };

    ImageView imglogo = (ImageView) findViewById(R.id.img_logo);
Glide
        .with(this)
        .load("http://sk.uploads.im/t/1RqCA.png")
        .listener( requestListener )
        .fitCenter()
        .into(imglogo);
*/