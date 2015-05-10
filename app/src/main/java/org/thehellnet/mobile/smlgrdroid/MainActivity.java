package org.thehellnet.mobile.smlgrdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {
    public enum Status {POWEROFF, LOADING, RUNNING};

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

        initValues(Status.POWEROFF);
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
}
