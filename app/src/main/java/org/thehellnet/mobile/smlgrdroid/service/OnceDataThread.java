package org.thehellnet.mobile.smlgrdroid.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.thehellnet.mobile.smlgrdroid.config.C;
import org.thehellnet.mobile.smlgrdroid.config.I;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public class OnceDataThread {
    private final Context context;
    private final RequestQueue queue;
    private final Handler handler;
    private final Runnable runnable;

    public OnceDataThread(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
        this.handler = new Handler();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                getOnceJson();
            }
        };
    }

    public void getOnce() {
        handler.postDelayed(runnable, 500);
    }

    private void getOnceJson() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, C.server.ONCE_URL, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                OnceData onceData = new OnceData();
                JSONObject data;

                try {
                    if (!response.getBoolean("success"))
                        return;
                    data = response.getJSONObject("data");
                    onceData.setMaxPower(data.getInt("maxPower"));
                } catch (JSONException e) {
                    return;
                }

                try {
                    onceData.setTodayMaxProd(data.getInt("todayMaxProduction"));
                    onceData.setTodayMaxTime(data.getLong("todayMaxTime"));
                    onceData.setTodayStart(data.getLong("todayStartTime"));
                    onceData.setTodayStop(data.getLong("todayStopTime"));
                } catch (JSONException e) {
                    onceData.setTodayMaxProd(0);
                    onceData.setTodayMaxTime(0);
                    onceData.setTodayStart(0);
                    onceData.setTodayStop(0);
                }

                try {
                    onceData.setYesterdayProd(data.getInt("yesterdayProduction"));
                    onceData.setYesterdayMaxProd(data.getInt("yesterdayMaxProduction"));
                    onceData.setYesterdayMaxTime(data.getLong("yesterdayMaxTime"));
                    onceData.setYesterdayStart(data.getLong("yesterdayStartTime"));
                    onceData.setYesterdayStop(data.getLong("yesterdayStopTime"));
                } catch (JSONException e) {
                    onceData.setYesterdayProd(0);
                    onceData.setYesterdayMaxProd(0);
                    onceData.setYesterdayMaxTime(0);
                    onceData.setYesterdayStart(0);
                    onceData.setYesterdayStop(0);
                }

                sendData(onceData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("updateUi", "Error: " + error.toString());
            }
        });

        queue.add(req);
    }

    private void sendData(Serializable data) {
        Intent intent = new Intent(I.UpdateUI.INTENT_ONCE);
        intent.putExtra("data", data);
        context.sendBroadcast(intent);
    }
}
