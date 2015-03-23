package com.example.keisuke.myapplication;

import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
/**
 * Created by keisuke on 2015/03/22.
 */
public class Requests {
   // java String tagJsonObj = “json_obj_req;
   // mRequestQueue = Volley.newRequestQueue(context);

    public static JsonObjectRequest jsonObjReq = new JsonObjectRequest(
            // HTTPメソッド名を設定する。GETかPOSTか等
            Request.Method.GET
            // リクエスト先のURLを設定する
            ,"http://api.dribbble.com/shots/everyone"
            // リクエストパラメーターを設定する
            ,null
            // 通信成功時のリスナーを設定する
            ,new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            // 通信成功時の処理
        }
    }
            // 通信失敗時のリスナーを設定する
            ,new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // 通信失敗時の処理
        }
    }
    );

  //  mRequestQueue.add(jsonObjReq, tagJsonObj);
}
