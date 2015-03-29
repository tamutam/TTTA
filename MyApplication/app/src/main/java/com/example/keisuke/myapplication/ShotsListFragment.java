package com.example.keisuke.myapplication;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by keisuke on 2015/03/24.
 */
public class ShotsListFragment extends ListFragment implements AbsListView.OnScrollListener {
    private static final String TAG = ShotsListFragment.class.getSimpleName();
    private ListView mListView;
    private String mCategory;
    private ShotsListAdapter mAdapter;
    private final static int MAX_COUNT = 50;//ページのMAX
    private int mCount = 1;
    private List<Shots> mList;
    private View mFooter;

    public ShotsListFragment() {
        //これいる？
    }

    public static ShotsListFragment newInstance(String category) {
        Log.i(TAG, "newInstance");//////////////////////////////////////////
        Log.i(TAG, "category = " + category);///////////////////////////////
        JsonObjectRequest jsonObjReq = Requests.jsonObjReq(category, 1);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

        // 新しくインスタンスを生成
        ShotsListFragment fragment = new ShotsListFragment();
        Bundle args = new Bundle();
        // 引数で受け取ったcategoryをBundleに保存
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        mListView = getListView();
        mListView.addFooterView(getFooter());
        mListView.setOnScrollListener(this);
        // インスタンス作成時にBundleに保存しておいたcategoryを受け取る
        mCategory = getCategory();

        // ListViewにAdapterをひもづける
        mAdapter =
                new ShotsListAdapter(getActivity(), Shots.getCategoryList(mCategory, mCount));
        setListAdapter(mAdapter);
    }

    private View getFooter() {
        if (mFooter == null) {
            mFooter = getLayoutInflater(getArguments()).inflate(R.layout.listview_footer, null);
        }
        return mFooter;

    }

    // ListViewの１つをクリックした時に呼ばれるコールバック
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "onListItemClick:");
        Log.i(TAG, "position = " + position);
        Log.i(TAG, "id = " + id);
        super.onListItemClick(l, v, position, id);
        // ListViewRowが持っている画像のurlを引数として渡す
        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(mList.get(position).imageUrl);
        detailDialogFragment.show(getFragmentManager(), TAG);
    }

    private String getCategory() {
        if (mCategory == null) {
            mCategory = getArguments().getString("category");
        }
        return mCategory;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.d(TAG, "onScrollStateChanged");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 最後尾までスクロールした場合
        if (totalItemCount == firstVisibleItem + visibleItemCount) {
            Log.i(TAG, "onScroll");
            Log.i(TAG, "firstViewItem = " + firstVisibleItem);
            Log.i(TAG, "visibleItemCount = " + visibleItemCount);
            Log.i(TAG, "totalItemCount = " + totalItemCount);
            additionalReading();
        }
    }

    private List<Shots> getList() {
        if (mList == null) {
            mList = new ArrayList<Shots>();
            addListData();
        }
        return mList;
    }

    private ListView getMyListView() {
        if (mListView == null) {
            mListView = getListView();
        }
        return mListView;
    }

    private void visibleFooter() {
        Log.d(TAG, "visibleFooter");
        getMyListView().addFooterView(getFooter());
    }

    private void invisibleFooter() {
        Log.d(TAG, "invisibleFooter");
        getMyListView().removeFooterView(getFooter());
    }

    private void addListData() {
        Log.d(TAG, "addListData");
        List<Shots> addList = Shots.getCategoryList(getCategory(), mCount);
        getList().addAll(addList);
        mCount++;
    }

    private void additionalReading() {
        Log.d(TAG, "additionalReading");
        if (mCount >= MAX_COUNT) {
            invisibleFooter();
            return;
        }
        mCount++;
        JsonObjectRequest jsonObjReq = Requests.jsonObjReq(mCategory, mCount);
        AppController.getInstance().addToRequestQueue(jsonObjReq);

        addListData();
        // OnActivityCreatedが完了する前にonScrollが呼ばれ、mAdapterがnullの場合がある
        if (mAdapter != null) {
            mAdapter.add(Shots.getCategoryList(mCategory, mCount));
            mAdapter.notifyDataSetChanged();
        }

//        getMyListView().invalidateViews();
    }
}
