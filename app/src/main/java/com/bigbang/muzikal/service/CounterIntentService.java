package com.bigbang.muzikal.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.bigbang.muzikal.util.DebugLogger.*;

public class CounterIntentService extends IntentService {

    public CounterIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        for (int counter = 0; counter < 11 ; counter++) {
            //delays thread by 1000 milliseconds between iterations
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                    e.printStackTrace();
            }
            logDebug(Thread.currentThread().getName() + "Counter: " + counter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logDebug("onDestroy called");
    }
}
