package com.example.keisuke.myapplication;

import android.app.ActionBar;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);//これなんだろう？
        setContentView(R.layout.activity_main);

        //アクションバーにViewPagerのコントロールができるタブを設定
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//横線なんだろう？

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

        // 今回使うPagerアダプターのクラスを作成
        public class SectionsPagerAdapter extends FragmentPagerAdapter {
            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            // 各ページで表示するListFragmentを設定
            @Override
            public Fragment getItem(int position) {
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
