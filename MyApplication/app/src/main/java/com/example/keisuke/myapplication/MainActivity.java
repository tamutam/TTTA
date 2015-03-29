package com.example.keisuke.myapplication;

//import android.app.ActionBar;

//import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.Locale;

//import android.support.v7.app.ActionBar;

//import android.view.View;

//import android.app.FragmentTransaction;
//import android.view.Menu;
//import android.view.MenuItem;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v4.view.PagerAdapter;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import com.crashlytics.android.Crashlytics;//クラッシュのレポート？的なの


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener{

    private final static String TAG = "MainActivity";

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);//これなんだろう？←クラッシュのやつっぽい？
        setContentView(R.layout.activity_main);

        //アクションバーにViewPagerのコントロールができるタブを設定
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//ここがnullになるぅ・・・

        //ViewPagerとListFragmentを紐付けるAdapterを作成
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // 生成したAdapterをViewPagerと紐付ける
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // ViewPagreの表示されているページが変わった時に呼ばれるコールバックを設定
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar.setSelectedNavigationItem(position);
                    }
                }
        );

        // アクションバーに設定したタブのタイトルを設定
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
    }




    @Override
    public void onTabSelected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }


    // 今回使うPagerアダプターのクラスを作成
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }


        // 各ページで表示するListFragmentを設定
        @Override
        public Fragment getItem(int position) {
            Log.i(TAG, "getItem");
            Log.i(TAG, "position = " + position);
            ShotsListFragment shotsListFragment;
            switch (position) {
                case 0:
                    shotsListFragment = ShotsListFragment.newInstance("everyone");
                    return shotsListFragment;
                case 1:
                    shotsListFragment = ShotsListFragment.newInstance("debuts");
                    return shotsListFragment;
                case 2:
                    shotsListFragment = ShotsListFragment.newInstance("popular");
                    return shotsListFragment;
                default:
                    return null;
            }
        }

        // タブの数を決定
        @Override
        public int getCount() {
            return 3;
        }


        // タブのタイトルを決定
        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }
}
