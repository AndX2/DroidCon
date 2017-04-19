package com.github.andx2.droidcon;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.goka.kenburnsview.KenBurnsView;
import com.goka.kenburnsview.LoopViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew on 13.04.2017.
 */

public class HomeController extends BaseController implements View.OnClickListener {

    public static final String TAG = "Home";

    private RecyclerView newsRecycler;
    private KenBurnsView toolbarSlider;
    private Toolbar toolbar;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.home_view, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.home_toolbar);
        toolbar.setTitle(TAG);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(this);
        initToolbarImgSlider(view);
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        newsRecycler = (RecyclerView) view.findViewById(R.id.news_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        newsRecycler.setLayoutManager(layoutManager);
        List<News> list = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            list.add(new News("https://andx2.github.io/droidcon/static/photo/kak_eto_bylo_raskryvaem_detali_droidcon_moscow_2016_4.jpg",
                    "Title",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
                            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                            "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
                            "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        }
        NewsListAdapter adapter = new NewsListAdapter(getApplicationContext(), list);
        newsRecycler.swapAdapter(adapter, true);

    }

    private void initToolbarImgSlider(View view) {

        toolbarSlider = (KenBurnsView) view.findViewById(R.id.img_toolbar_slider);
        toolbarSlider.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String[] urls = {"https://andx2.github.io/droidcon/static/photo/Cs9llhCWYAE9oDY.jpg",
                "https://andx2.github.io/droidcon/static/photo/all.jpg",
                "https://andx2.github.io/droidcon/static/photo/b978a7a612464cc3a582ee94686ed8cb.jpg"};
        toolbarSlider.initStrings(Arrays.asList(urls));
//        toolbarSlider.setSwapMs(3750);
//        toolbarSlider.setFadeInOutMs(750);

//        // ResourceIDs
//        List<Integer> resourceIDs = Arrays.asList(SampleImages.IMAGES_RESOURCE);
//        toolbarSlider.loadResourceIDs(resourceIDs);

        // LoopViewListener
        LoopViewPager.LoopViewPagerListener listener = new LoopViewPager.LoopViewPagerListener() {
            @Override
            public View OnInstantiateItem(int page) {
                TextView counterText = new TextView(getApplicationContext());
                counterText.setText(String.valueOf(page));
                return counterText;
            }

            @Override
            public void onPageScroll(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("LoopViewPagerListener", "onPageScroll pos = " + position);

            }

            @Override
            public void onPageSelected(int position) {
                toolbarSlider.forceSelected(position);
                Log.d("LoopViewPagerListener", "onPageScroll pos = " + position);
            }

            @Override
            public void onPageScrollChanged(int page) {
                Log.d("LoopViewPagerListener", "onPageScroll pos = " + page);
            }
        };

        // LoopView
        LoopViewPager loopViewPager = new LoopViewPager(getActivity(), urls.length, listener);

        FrameLayout viewPagerFrame = (FrameLayout) view.findViewById(R.id.img_toolbar_frame);
        viewPagerFrame.addView(loopViewPager);

        toolbarSlider.setPager(loopViewPager);
    }

//    @Override
//    public boolean handleBack() {
//        switchDrawer();
//        return super.handleBack();
//    }




    //    @Override
//    protected String getTitle() {
//        return "Home";
//    }
}
