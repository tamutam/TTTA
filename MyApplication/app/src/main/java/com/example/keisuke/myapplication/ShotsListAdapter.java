package com.example.keisuke.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by keisuke on 2015/03/24.
 */
public class ShotsListAdapter extends BaseAdapter {

    private Context context;
    private List<Shots> shotsList;

    // 毎回findViewByIdをしなくてよくし、高速化に使用するholderクラス
    private static class ViewHolder {
        TextView title;
        TextView playerName;
        TextView likesCount;
        NetworkImageView image;
    }

    public ShotsListAdapter(Context context, List<Shots> shotsList){
        this.context = context;
        this.shotsList = shotsList;
    }

    @Override
    public int getCount() {
        return shotsList.size();
    }

    @Override
    public Object getItem(int position) {
        return shotsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // ListViewの１つ１つを作成する部分、ListViewRow
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        // 初めて表示されるListViewRowの場合
        // このnullチェックをすることで、リストをスクロールするときの表示が高速化される
        if(view == null){
            // ListViewRowを取得する
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(R.layout.list_row, parent, false);

            TextView title = (TextView)view.findViewById(R.id.title);
            TextView playerName = (TextView)view.findViewById(R.id.player_name);
            TextView likesCount = (TextView)view.findViewById(R.id.likes_count);
            NetworkImageView image = (NetworkImageView)view.findViewById(R.id.image);

            // holderにviewを持たせておく
            holder = new ViewHolder();
            holder.title = title;
            holder.playerName = playerName;
            holder.likesCount = likesCount;
            holder.image = image;
            view.setTag(holder);
            // 表示されたことのあるListViewRowの場合
        }else{
            // 初めて表示されるときにつけておいたtagを元にviewを取得する
            holder = (ViewHolder)view.getTag();
        }

        // ListViewRowの中のそれぞれのViewにデータを反映する
        holder.title.setText(shotsList.get(position).title);
        holder.playerName.setText(shotsList.get(position).playerName);
        holder.likesCount.setText(Integer.toString(shotsList.get(position).likesCount));

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        holder.image.setImageUrl(shotsList.get(position).imageTeaserUrl, imageLoader);

        return view;
    }

    public boolean add (List<Shots> shots){
        boolean ress = shotsList.addAll(shots);
        if(ress){
            notifyDataSetChanged();
        }
        return ress;
    }

}