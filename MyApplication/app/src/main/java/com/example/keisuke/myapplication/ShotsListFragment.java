package com.example.keisuke.myapplication;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

//import android.support.v4.app.ListFragment;//これ

/**
 * Created by keisuke on 2015/03/24.
 */
public class ShotsListFragment extends ListFragment implements AbsListView.OnScrollListener {
    private static final String TAG = ShotsListFragment.class.getSimpleName();
    private ListView mListView;
    private String mCategory;
    private ShotsListAdapter mAdapter;
    private int mCount = 0;
    private List<Shots> mList;

    public static ShotsListFragment newInstance(String category) {
        // 新しくインスタンスを生成
        ShotsListFragment fragment = new ShotsListFragment();
        Bundle args = new Bundle();
        // 引数で受け取ったcategoryをBundleに保存
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getListView();
        // インスタンス作成時にBundleに保存しておいたcategoryを受け取る
        mCategory = getCategory();

        // ListViewにAdapterをひもづける
        mAdapter =
                new ShotsListAdapter(getActivity(), Shots.getCategoryList(mCategory, mCount));
        setListAdapter(mAdapter);
    }

    // ListViewの１つをクリックした時に呼ばれるコールバック
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "onListItemClick:");
        Log.i(TAG, "position = " + position);
        Log.i(TAG, "id = " + id);
        // ListViewRowが持っている画像のurlを引数として渡す
        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(mList.get(position).imageUrl);
       // detailDialogFragment.show(getFragmentManager(), TAG);
    }

    private String getCategory() {
        if (mCategory == null) {
            mCategory = getArguments().getString("category");
        }
        return mCategory;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
