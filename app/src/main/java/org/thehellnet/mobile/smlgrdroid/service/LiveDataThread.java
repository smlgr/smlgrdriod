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
                LiveData liveData = new LiveData();

                try {
                    if (!response.getBoolean("success"))
                        return;

                    JSONObject data = response.getJSONObject("data");

                    liveData.setAc_power(data.getInt("ac_power"));
                    liveData.setAc_voltage(data.getInt("ac_voltage"));
                    liveData.setAc_current(data.getInt("ac_current"));
                    liveData.setAc_frequency(data.getInt("ac_frequency"));
                    liveData.setDc1_voltage(data.getInt("dc1_voltage"));
                    liveData.setDc1_current(data.getInt("dc1_current"));
                    liveData.setDc2_voltage(data.getInt("dc2_voltage"));
                    liveData.setDc2_current(data.getInt("dc2_current"));
                    liveData.setTemperature(data.getInt("temperature"));
                    liveData.setProduction(data.getInt("production"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    sendData(liveData);
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

    private void sendData(Serializable data) {
        Intent intent = new Intent(I.UpdateUI.INTENT_LIVE);
        intent.putExtra("data", data);
        context.sendBroadcast(intent);
    }
}
