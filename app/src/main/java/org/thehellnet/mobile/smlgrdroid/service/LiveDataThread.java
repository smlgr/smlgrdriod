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
public class LiveDataThread {
    private final Context context;
    private final RequestQueue queue;
    private final Handler handler;
    private final Runnable runnable;

    public LiveDataThread(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(this.context);
        this.handler = new Handler();
        this.runnable = new Runnable() {
            @Override
            public void run() {
                getLiveJson();
                handler.postDelayed(runnable, C.server.LIVE_REFRESH);
            }
        };
    }

    public void start() {
        handler.postDelayed(runnable, 500);
    }

    public void stop() {
        handler.removeCallbacks(runnable);
    }

    private void getLiveJson() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, C.server.LIVE_URL, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                LiveData data = new LiveData();

                try {
                    data.setPower((int) response.getDouble("power"));
                    data.setTodayProd((float) response.getDouble("todayProd"));
                    data.setTodayVoltage((float) response.getDouble("todayVoltage"));
                    data.setTodayTemp(response.getInt("todayTemp"));
                    data.setTodayFrequency((float) response.getDouble("todayFrequency"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    sendData(I.UpdateUI.LIVE, data);
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
