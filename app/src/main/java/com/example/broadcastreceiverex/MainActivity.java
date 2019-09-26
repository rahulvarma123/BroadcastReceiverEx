package com.example.broadcastreceiverex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TestBroadcastReceiver testBroadcastReceiver;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void invoke(View view) {

        Intent intent=new Intent();
        intent.setAction("Colors");
        localBroadcastManager.sendBroadcast(intent);
       // sendBroadcast(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter("Colors");
        //intentFilter.addAction("Colors");
        localBroadcastManager=LocalBroadcastManager.getInstance(this);

        testBroadcastReceiver=new TestBroadcastReceiver();
        localBroadcastManager.registerReceiver(testBroadcastReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(testBroadcastReceiver);
        testBroadcastReceiver=null;
        localBroadcastManager=null;
    }

    public class TestBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this," Test Broadcast Receiver",Toast.LENGTH_LONG).show();
        }
    }
}
