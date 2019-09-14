package cse.uom.rivindu.sensorapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tvIlluminance;
    private TextView tvAccX;
    private TextView tvAccY;
    private TextView tvAccZ;
    private SensorManager sensorManager;
    private Sensor light;
    private Sensor accelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvIlluminance = (TextView) findViewById(R.id.tvIlluminance);
        this.tvAccX = (TextView) findViewById(R.id.tvAccX);
        this.tvAccY = (TextView) findViewById(R.id.tvAccY);
        this.tvAccZ = (TextView) findViewById(R.id.tvAccZ);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void updateValue(View view){
        Button button = (Button) view;
        if (button.getText().equals("Start")){
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            button.setText("Stop");
            button.setBackgroundColor(Color.parseColor("#ffcc0000"));
        }
        else{
            sensorManager.unregisterListener(this);
            button.setText("Start");
            button.setBackgroundColor(Color.parseColor("#ff669900"));
        }

    }

    public void stopUpdate(View view){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float luxOfLight = event.values[0];
            tvIlluminance.setText(Float.toString(luxOfLight));
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float acceleartionX = event.values[0];
            float acceleartionY = event.values[1];
            float acceleartionZ = event.values[2];
            tvAccX.setText(Float.toString(acceleartionX));
            tvAccY.setText(Float.toString(acceleartionY));
            tvAccZ.setText(Float.toString(acceleartionZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
