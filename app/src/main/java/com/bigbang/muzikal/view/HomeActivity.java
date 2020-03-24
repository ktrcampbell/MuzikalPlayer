package com.bigbang.muzikal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.bigbang.muzikal.R;
import com.bigbang.muzikal.service.MuzikService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bigbang.muzikal.util.DebugLogger.*;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.music_status_textview)
    TextView musicStatusTextView;

    private MuzikService muzikController = null;

    private ServiceConnection muzikConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            muzikController = ((MuzikService.MuzikServiceBinder)service).getMuzikService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//          this is called when the service is disconnected unexpectedly..
        }
    };

    @OnClick(R.id.pause_button)
    public void pauseMusic(View view){
        if(muzikController != null){
            muzikController.pauseMuzik();
            musicStatusTextView.setText("Paused.. Jazz");
        }
    }

    @OnClick(R.id.play_button)
    public void playMusic(View view){
        if(muzikController != null){
            sendNotification();
            muzikController.playMuzik();
            musicStatusTextView.setText("Playing.. Jazz");
        }
    }

    private void sendNotification() {
    }

    @OnClick(R.id.stop_button)
    public void stopMusic(View view){
        muzikController.stopMuzik();
        musicStatusTextView.setText("Stopped.. Jazz");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //How to start a service
        Intent serviceIntent = new Intent(this, MuzikService.class);
//        startService(serviceIntent);

        bindService(serviceIntent, muzikConnection, Service.BIND_AUTO_CREATE);
        //Service started...
        logDebug("Unbind from service...");
//        unbindService(muzikConnection);
//        stopService(serviceIntent);
    }

}
