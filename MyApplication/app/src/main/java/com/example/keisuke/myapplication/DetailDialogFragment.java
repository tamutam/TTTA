package com.example.keisuke.myapplication;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by keisuke on 2015/03/24.
 */
public class DetailDialogFragment extends DialogFragment {

    static DetailDialogFragment newInstance(String imgUrl) {
        DetailDialogFragment f = new DetailDialogFragment();
        // ListViewRowがクリックされた時に引数として渡された画像のURLをBundlに保存
        Bundle args = new Bundle();
        args.putString("url", imgUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            // Bundleに保存した画像のurlを取得する
            String imgUrl = arguments.getString("url");
            if (imgUrl != null) {
                NetworkImageView networkImageView = (NetworkImageView) v.findViewById(R.id.image);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                networkImageView.setImageUrl(imgUrl, imageLoader);
            }
        }
        return v;
    }


  //  public void show(FragmentManager fragmentManager, String tag) {
  //  }
}