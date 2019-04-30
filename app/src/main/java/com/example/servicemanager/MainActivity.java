package com.example.servicemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyBoundService myBoundService;
    private  boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder serviceInfo) {
            myBoundService = new MyBoundService(serviceInfo);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStartedService = findViewById(R.id.activity_main__btn__started_service);
        btnStartedService.setOnClickListener(this);

        final Button btnBoundService = findViewById(R.id.activity_main__btn__bound_service);
        btnBoundService.setOnClickListener(this);

        final ConstraintLayout root = findViewById(R.id.activity_main__view);
        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main__btn__started_service:
                Intent serviceIntent = new Intent(this, MyStartedService.class);
                serviceIntent.putExtra("dummyKey", 25);
                startService(serviceIntent);
                break;

            case R.id.activity_main__btn__bound_service:
                Intent boundIntent = new Intent(this, MyBoundService.class);
                bindService(boundIntent, serviceConnection, Service.BIND_AUTO_CREATE);
                break;

            case R.id.activity_main__view:
                if (isBound) {
                    String info = String.valueOf(myBoundService.getInfoFromService());
                    Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Log.w("MainActivity", "View unknown");
        }
    }
}
