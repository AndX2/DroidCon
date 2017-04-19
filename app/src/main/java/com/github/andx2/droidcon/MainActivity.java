package com.github.andx2.droidcon;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActionBarProvider,
        DrawerOpenerProvider{

    private Router router;
    private ViewGroup container;
//    private Toolbar toolbar;
//    private int isAppBarCollapse = 0;
//    private AppBarLayout appBarLayout;
//    private KenBurnsView toolbarSlider;
    private DrawerLayout drawer;

    private DownloadManager downloadManager;
    private long downloadTermsRef;
    private IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
    private BroadcastReceiver downloadTermsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (downloadTermsRef == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)) {
                try {
                    Intent openTermsIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT,
                            downloadManager.getUriForDownloadedFile(downloadTermsRef));
                    startActivity(openTermsIntent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Exception receive file", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.all_session_24dp);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        container = (ViewGroup) findViewById(R.id.controller_container);
        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        initToolbarImgSlider();

//        appBarLayout = (AppBarLayout) findViewById(R.id.main_app_bar_layout);
//        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
//                    Log.d("onOffsetChanged", "Collapsed");
//                    // Collapsed
//                    isAppBarCollapse = 1;
//                } else if (verticalOffset == 0) {
//                    Log.d("onOffsetChanged", "Expanded");
//                    // Expanded
//                    isAppBarCollapse = 0;
//                } else {
//                    Log.d("onOffsetChanged", "Somewhere in between");
//                    // Somewhere in between
//                }
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        outState.putInt("isAppBarCollapse", isAppBarCollapse);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("onRestoreInstanceState", savedInstanceState.getInt("isAppBarCollapse") + "");
//        isAppBarCollapse = savedInstanceState.getInt("isAppBarCollapse");
//        setAppBarState();

    }

//    private void setAppBarState() {
//        if (isAppBarCollapse == 0) {
//            appBarLayout.setExpanded(true);
//        } else {
//            appBarLayout.setExpanded(false);
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (downloadTermsReceiver.isOrderedBroadcast())
            unregisterReceiver(downloadTermsReceiver);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!router.handleBack()) {
                super.onBackPressed();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_session) {
            Log.d("onNavItemSelected", "all session selected");
            Controller controller = router.getControllerWithTag(HomeController.TAG);
            if (controller == null) {
                controller = new HomeController();
            }
            router.setRoot(RouterTransaction.with(controller));
//            toolbarSlider.setVisibility(View.VISIBLE);
//            isAppBarCollapse = 0;
//            setAppBarState();
//            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
//            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
//                    AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP |
//                    AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
//            toolbarSlider.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_my_schedule) {
            Log.d("onNavItemSelected", "my scheduler selected");
            Controller controller = router.getControllerWithTag(SchedulerController.TAG);
            if (controller == null) {
                controller = new SchedulerController();
            }
            router.setRoot(RouterTransaction.with(controller));
//            toolbarSlider.setVisibility(View.GONE);
//            toolbar.setTitle("Scheduler");
//            isAppBarCollapse = 1;
//            setAppBarState();
//            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
//            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);

        } else if (id == R.id.nav_speakers) {

        } else if (id == R.id.nav_contacts) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_terms) {
            downloadTerms();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void downloadTerms() {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(getString(R.string.url_terms));
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(true);
        downloadTermsRef = downloadManager.enqueue(request);
        registerReceiver(downloadTermsReceiver, intentFilter);
    }

//    private void initToolbarImgSlider() {
//
//        toolbarSlider = (KenBurnsView) findViewById(R.id.img_toolbar_slider);
//        toolbarSlider.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        String[] urls = {"https://andx2.github.io/droidcon/static/photo/Cs9llhCWYAE9oDY.jpg",
//                "https://andx2.github.io/droidcon/static/photo/all.jpg",
//                "https://andx2.github.io/droidcon/static/photo/b978a7a612464cc3a582ee94686ed8cb.jpg"};
//        toolbarSlider.initStrings(Arrays.asList(urls));
////        toolbarSlider.setSwapMs(3750);
////        toolbarSlider.setFadeInOutMs(750);
//
////        // ResourceIDs
////        List<Integer> resourceIDs = Arrays.asList(SampleImages.IMAGES_RESOURCE);
////        toolbarSlider.loadResourceIDs(resourceIDs);
//
//        // LoopViewListener
//        LoopViewPager.LoopViewPagerListener listener = new LoopViewPager.LoopViewPagerListener() {
//            @Override
//            public View OnInstantiateItem(int page) {
//                TextView counterText = new TextView(getApplicationContext());
//                counterText.setText(String.valueOf(page));
//                return counterText;
//            }
//
//            @Override
//            public void onPageScroll(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("LoopViewPagerListener", "onPageScroll pos = " + position);
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                toolbarSlider.forceSelected(position);
//                Log.d("LoopViewPagerListener", "onPageScroll pos = " + position);
//            }
//
//            @Override
//            public void onPageScrollChanged(int page) {
//                Log.d("LoopViewPagerListener", "onPageScroll pos = " + page);
//            }
//        };
//
//        // LoopView
//        LoopViewPager loopViewPager = new LoopViewPager(this, urls.length, listener);
//
//        FrameLayout viewPagerFrame = (FrameLayout) findViewById(R.id.img_toolbar_frame);
//        viewPagerFrame.addView(loopViewPager);
//
//        toolbarSlider.setPager(loopViewPager);
//    }

    public void DrawerOpen(){
        Log.d("DrawerOpen", "isDrawerOpen = " + drawer.isDrawerOpen(GravityCompat.START));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
