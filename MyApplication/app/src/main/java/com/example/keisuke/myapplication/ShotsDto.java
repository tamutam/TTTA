package com.example.keisuke.myapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keisuke on 2015/03/25.
 */
public class ShotsDto {

    public static final String TAG = ShotsDto.class.getSimpleName();

    public static List<Shots> parseShotsList(JSONObject jsonObject, String category) {
        return parseShotsList(jsonObject.optJSONArray("shorts"), category);
    }

    public static List<Shots> parseShotsList(JSONArray jsonArray, String category) {
        List<Shots> shotsList = new ArrayList<Shots>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Shots shots = ShotsDto.parseShots(jsonArray.optJSONObject(i), category);
            shotsList.add(shots);
        }

        return shotsList;
    }
//クラスタにデータを格納した後にshotsに保存
    public static Shots parseShots(JSONObject jsonObject, String category) {
        Shots shotsDTO = Shots.getShots(jsonObject.optInt("id"));
        if (shotsDTO == null) {
            shotsDTO = new Shots();
        }
        //JSONObject shots = jsonArray.getJSONObject(i);


        shotsDTO.setShotsId(jsonObject.optInt("id"));
        shotsDTO.setCategory("everyone");
        shotsDTO.setTitle(jsonObject.optString("title"));
        shotsDTO.setImageUrl(jsonObject.optString("image_url"));
        shotsDTO.setImageTeaserUrl(jsonObject.optString("image_teaser_url"));
        shotsDTO.setPlayerName(player.getString("name"));
        shotsDTO.setLikesCount(Integer.parseInt(jsonObject.optString("likes_count")));

         Shots shots = new Shots(shotsDTO);
         shots.save();

    return shots;
    }

    public static JSONObject player(JSONObject jsonObject){
        return jsonObject.optJSONObject("player");
    }

}
