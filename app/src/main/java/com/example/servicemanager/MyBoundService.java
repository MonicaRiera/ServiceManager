package com.example.servicemanager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyBoundService extends Service {

    private String TAG = "TestService";
    private  IBinder iBinder = new Binder();

    public MyBoundService() {
    }

    public MyBoundService(IBinder b) {
        this.iBinder = b;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind done");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    public int getInfoFromService() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
