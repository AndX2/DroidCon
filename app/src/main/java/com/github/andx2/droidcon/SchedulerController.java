package com.github.andx2.droidcon;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 16.04.2017.
 */

public class SchedulerController extends BaseController {

    public static final String TAG = "Scheduler";
    private RecyclerView schedulerRecycler;
    private Toolbar toolbar;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
        View view = inflater.inflate(R.layout.scheduler_view, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.scheduler_toolbar);
        toolbar.setTitle(TAG);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(this);
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
//        setTitle();
//        newsRecycler = (RecyclerView) view.findViewById(R.id.news_recycler);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        newsRecycler.setLayoutManager(layoutManager);
//        List<News> list = new ArrayList<>();
//        for(int i = 0; i< 10; i++){
//            list.add(new News("https://andx2.github.io/droidcon/static/photo/kak_eto_bylo_raskryvaem_detali_droidcon_moscow_2016_4.jpg",
//                    "Title",
//                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
//                            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco " +
//                            "laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
//                            "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
//                            "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
//        }
//        NewsListAdapter adapter = new NewsListAdapter(getApplicationContext(), list);
//        newsRecycler.swapAdapter(adapter, true);
        schedulerRecycler = (RecyclerView)view.findViewById(R.id.scheduler_recycler);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        schedulerRecycler.setLayoutManager(layoutManager);
        List<Session> list = new ArrayList<>();
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("app4all", "https://andx2.github.io/droidcon/static/img/app4all.png", null));
        List<Speaker> spList = new ArrayList<>();
        spList.add(new Speaker("Alexander", "Vasilev", "https://andx2.github.io/droidcon/static/img/photo_round.png",
                null, null, companyList));
        for (int i = 0; i < 10; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, i * 40);
            list.add(new Session("Welcome", "desc", "body", null, spList, calendar, 1));
            for (int j = 1; j < 3; j++){
                list.add(new Session("Welcome", "desc", "body", null, spList, calendar, j));
            }
        }
        SchedulerListAdapter adapter = new SchedulerListAdapter(getApplicationContext(), list);
        schedulerRecycler.swapAdapter(adapter, true);
    }

//    @Override
//    protected String getTitle() {
//        return "Scheduler";
//    }
}
