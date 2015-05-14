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

import org.joda.time.DateTime;
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
                OnceData data = new OnceData();

                try {
                    data.setMax(response.getInt("max"));
                    data.setTodayStart(new DateTime(response.getString("todayStart")));
                    data.setTodayStop(new DateTime(response.getString("todayStop")));
                    data.setYesterdayProd((float) response.getDouble("yesterdayProd"));
                    data.setYesterdayMax((float) response.getDouble("yesterdayMax"));
                    data.setYesterdayStart(new DateTime(response.getString("yesterdayStart")));
                    data.setYesterdayStop(new DateTime(response.getString("yesterdayStop")));
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    sendData(I.UpdateUI.ONCE, data);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("updateUi", "Error: " + error.toString());
            }
        });

        queue.add(req);
    }

    private void sendData(String type, Serializable data) {
        Intent intent = new Intent(I.UpdateUI.INTENT);
        intent.putExtra(type, data);
        context.sendBroadcast(intent);
    }
}
