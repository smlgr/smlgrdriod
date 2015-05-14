package org.thehellnet.mobile.smlgrdroid.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.thehellnet.mobile.smlgrdroid.R;
import org.thehellnet.mobile.smlgrdroid.config.I;
import org.thehellnet.mobile.smlgrdroid.service.LiveData;
import org.thehellnet.mobile.smlgrdroid.service.LiveDataThread;

public class MainActivity extends Activity {
    public enum Status {OFF, LOADING};

    private LiveDataThread liveDataThread;
    private LiveDataReceiver liveDataReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMaxPower(2750);
        handleThreads(true);
    }

    @Override
    protected void onPause() {
        handleThreads(false);
        super.onPause();
    }

    private void handleThreads(boolean status) {
        if(status) {
            initValues(Status.LOADING);
            liveDataThread = new LiveDataThread(getApplicationContext());
            liveDataReceiver = new LiveDataReceiver();
            registerReceiver(liveDataReceiver, new IntentFilter(I.UpdateUI.INTENT));
            liveDataThread.start();
        } else {
            liveDataThread.stop();
            unregisterReceiver(liveDataReceiver);
            liveDataReceiver = null;
            liveDataThread = null;
            initValues(Status.OFF);
        }
    }

    private void updateMaxPower(int value) {
        ProgressBar powerBar = (ProgressBar) findViewById(R.id.power_progress_bar);
        powerBar.setMax(value);

        TextView minPower = (TextView) findViewById(R.id.power_label_min);
        minPower.setText(String.format("%d", 0));
        TextView maxPower = (TextView) findViewById(R.id.power_label_max);
        maxPower.setText(String.format("%d", value));
    }

    private void updatePowerBar(int value) {
        ProgressBar powerBar = (ProgressBar) findViewById(R.id.power_progress_bar);
        powerBar.setProgress(value);
        TextView powerValue = (TextView) findViewById(R.id.power_value);
        powerValue.setText(String.format("%d", value));
    }

    private void initValues(Status status) {
        String text = "";

        if(status == Status.LOADING) {
            text = getString(R.string.activity_generic_loading);
        }

        updatePowerBar(0);

        TextView todayProd = (TextView) findViewById(R.id.today_prod_value);
        todayProd.setText(text);
        TextView todayMax = (TextView) findViewById(R.id.today_max_value);
        todayMax.setText(text);
        TextView todayMaxTime = (TextView) findViewById(R.id.today_max_time);
        todayMaxTime.setText(text);
        TextView todayPoweron = (TextView) findViewById(R.id.today_poweron_value);
        todayPoweron.setText(text);
        TextView todayPoweroff = (TextView) findViewById(R.id.today_poweroff_value);
        todayPoweroff.setText(text);

        TextView yesterdayProd = (TextView) findViewById(R.id.yesterday_prod_value);
        yesterdayProd.setText(text);
        TextView yesterdayMax = (TextView) findViewById(R.id.yesterday_max_value);
        yesterdayMax.setText(text);
        TextView yesterdayMaxTime = (TextView) findViewById(R.id.yesterday_max_time);
        yesterdayMaxTime.setText(text);
        TextView yesterdayPoweron = (TextView) findViewById(R.id.yesterday_poweron_value);
        yesterdayPoweron.setText(text);
        TextView yesterdayPoweroff = (TextView) findViewById(R.id.yesterday_poweroff_value);
        yesterdayPoweroff.setText(text);

        TextView todayVoltage = (TextView) findViewById(R.id.today_voltage_value);
        todayVoltage.setText(text);
        TextView todayTemp = (TextView) findViewById(R.id.today_temp_value);
        todayTemp.setText(text);
        TextView todayFrequency = (TextView) findViewById(R.id.today_frequency_value);
        todayFrequency.setText(text);

        TextView lastcontact = (TextView) findViewById(R.id.lastcontact_value);
        lastcontact.setText(text);
    }

    private void updateLiveData(LiveData data) {
        updatePowerBar(data.getPower());

        TextView todayProd = (TextView) findViewById(R.id.today_prod_value);
        todayProd.setText(String.format("%.01f KW/h", data.getTodayProd()));

        TextView todayVoltage = (TextView) findViewById(R.id.today_voltage_value);
        todayVoltage.setText(String.format("%.01f V", data.getTodayVoltage()));
        TextView todayTemp = (TextView) findViewById(R.id.today_temp_value);
        todayTemp.setText(String.format("%d °C", data.getTodayTemp()));
        TextView todayFrequency = (TextView) findViewById(R.id.today_frequency_value);
        todayFrequency.setText(String.format("%.02f Hz", data.getTodayFrequency()));
    }

    private class LiveDataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateLiveData((LiveData) intent.getSerializableExtra(I.UpdateUI.LIVE));
        }
    }
}
