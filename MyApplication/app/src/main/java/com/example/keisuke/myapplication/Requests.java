package com.example.keisuke.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
/**
 * Created by keisuke on 2015/03/22.
 */
public class Requests {
   // java String tagJsonObj = “json_obj_req;
   // mRequestQueue = Volley.newRequestQueue(context);

    public static final String TAG = Requests.class.getSimpleName();

    public static JsonObjectRequest jsonObjReq(final String category, int page) {
        return new JsonObjectRequest(
                // HTTPメソッド名を設定する。GETかPOSTか等
                Request.Method.GET
                // リクエスト先のURLを設定する
                , "http://api.dribbble.com/shots/" + category + "/page=" + page
                // リクエストパラメーターを設定する
                , null
                // 通信成功時のリスナーを設定する
                , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // 通信成功時の処理
                        ShotsDto.parseShotsList(response, category);
                    }
                }
                // 通信失敗時のリスナーを設定する
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 通信失敗時の処理
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        );

        //  mRequestQueue.add(jsonObjReq, tagJsonObj);
    }
}
