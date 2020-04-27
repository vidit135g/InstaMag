package com.tac.github.instamag;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Toast;
import com.tac.github.instamag.adaptor.TabsPagerAdapter;
import com.tac.github.instamag.tabs.DownloadFragment;
import com.tac.github.instamag.tabs.HistoryFragment;

public class MainActivity extends AppCompatActivity implements CheckRefreshClickListener,
    DownloadFragment.OnPostDownload{

  DrawerLayout androidDrawerLayout;
  Toolbar toolbar;
  TabLayout tabLayout;
  public ViewPager viewPager;
  private TabsPagerAdapter mAdapter;
  private WebView webView;
  View bottomsheet;
  android.support.v4.view.ViewPager indicator;
  AppCompatButton pixelbutton;
  ExtensiblePageIndicator ind;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initNavDrawerToggel();
    Utilities.getStoragePermission(MainActivity.this);
    final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fadebutton);
    final Animation an1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fadefirst);
    pixelbutton=findViewById(R.id.pixelbutton);
    indicator=findViewById(R.id.container);
    ind=findViewById(R.id.flexibleIndicator);
    pixelbutton.startAnimation(an);
    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

      @Override
      public void onPageSelected(int arg0) {

        if (arg0 == 2) {
          pixelbutton.setVisibility(View.VISIBLE);
          ind.setVisibility(View.GONE);
          pixelbutton.startAnimation(an);
        }else{
          ind.setVisibility(View.VISIBLE);
          pixelbutton.setVisibility(View.GONE);
        }

      }

      @Override
      public void onPageScrolled(int arg0, float arg1, int arg2) {

        System.out.println("onPageScrolled");
      }

      @Override
      public void onPageScrollStateChanged(int num) {
      }
    });
    ExtensiblePageIndicator extensiblePageIndicator = findViewById(R.id.flexibleIndicator);
    MainFragmentAdapter mSimpleFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
    mSimpleFragmentAdapter.addFragment(MainFragment.newInstance(R.color.white, R.drawable.slider,"Trail Blaze","Download photos and videos just by sharing the post link."));
    mSimpleFragmentAdapter.addFragment(MainFragment.newInstance(R.color.white, R.drawable.slide3,"Easy to route & lovely to look at","Schedule route puts images and maps on your map"));
    mSimpleFragmentAdapter.addFragment(MainFragment.newInstance(R.color.white, R.drawable.slide2,"Push Notifications","Real time notifications of your frined's location without any hassels"));
    ViewPager mViewPager = findViewById(R.id.container);
    mViewPager.setAdapter(mSimpleFragmentAdapter);
    extensiblePageIndicator.initViewPager(mViewPager);

  }

  private void initNavDrawerToggel() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_insta);
    //toolbar.setTitle("InstaGrabber");

    // Initilization
    bottomsheet=findViewById(R.id.recyclerlist);
    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomsheet);
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int height = size.y;
    int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      // In landscape
      behavior.setPeekHeight((height/6));
    } else {
      // In portrait
      behavior.setPeekHeight((height/2)-90);
    }

    int defheight=behavior.getPeekHeight();
    behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
      @Override
      public void onStateChanged(@NonNull View view, int i) {
        if(i== BottomSheetBehavior.STATE_EXPANDED){
          behavior.setPeekHeight(defheight);
        }
      }

      @Override
      public void onSlide(@NonNull View view, float v) {

      }
    });
    viewPager = (ViewPager) findViewById(R.id.pager);
    androidDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_design_support_layout);
    mAdapter =  new TabsPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(mAdapter);

    tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    tabLayout.addTab(tabLayout.newTab().setText("Download"));
    tabLayout.addTab(tabLayout.newTab().setText("History"));

    tabLayout.setupWithViewPager(viewPager);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }




  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    // This is required to make the drawer toggle work
    /*
         * if you have other menu items in your activity/toolbar
         * handle them here and return true
     */
    switch (item.getItemId()) {
      case R.id.instalogo:

        RoundDialogFragment showRoundDialogFragment =
                RoundDialogFragment.newInstance();

        showRoundDialogFragment.show(getSupportFragmentManager(),
                "add_menu_fragment");

        return true;
      case R.id.home:
        Toast.makeText(this, "ccxcx", Toast.LENGTH_SHORT).show();
        startApplication("com.instagram.android");
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }

  }
  public void startApplication(String packageName) {
    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
    if (launchIntent != null) {
      startActivity(launchIntent);
    } else {
      showInMarket(packageName);
    }
  }
  private void showInMarket(String packageName)
  {
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
  private void callInstagram() {
    String apppackage = "com.instagram.android";
    try {
      Intent i = getPackageManager().getLaunchIntentForPackage(apppackage);
      startActivity(i);
    } catch (Exception  e) {
      Toast.makeText(this, "Sorry, Instagram Apps Not Found", Toast.LENGTH_LONG).show();
    }

  }
  @Override public void refreshList() {
    Fragment fragment=mAdapter.getFragment(1);
    ((HistoryFragment) fragment).refresh();

  }


  @Override
  public void onHistoryClick() {
    TabLayout.Tab tab2 = tabLayout.getTabAt(1);
    tab2.select();
  }

  @Override
  public void onDownloadClick() {
    TabLayout.Tab tab2 = tabLayout.getTabAt(0);
    tab2.select();
  }

  @Override
  public void onShareClick() {
    final String appPackageName = getApplicationContext().getPackageName();
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this cool Instagram photos and videos app at: https://play.google.com/store/apps/details?id=" + appPackageName);
    sendIntent.setType("text/plain");
    this.startActivity(sendIntent);
  }

  @Override
  public void onGalleryClick() {
    final String appPackageName = getApplicationContext().getPackageName();
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this cool Instagram photos and videos app at: https://play.google.com/store/apps/details?id=" + appPackageName);
    sendIntent.setType("text/plain");
    this.startActivity(sendIntent);
  }


  @Override
  public void onAboutClick() {
   aboutDialog();
  }


  public interface FragmentRefresh{
      void refresh();
  }

  private void aboutDialog() {
    new AboutDialogFragment().show(getSupportFragmentManager(), null);
  }


}
