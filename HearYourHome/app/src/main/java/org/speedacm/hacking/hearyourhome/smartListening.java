package org.speedacm.hacking.hearyourhome;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;

import com.lisnr.hflat.android.LisnrCallback;
import com.lisnr.hflat.android.services.exceptions.InvalidApiKey;
import com.lisnr.hflat.sdk.LisnrTone;

import com.lisnr.hflat.android.Lisnr;
import com.lisnr.hflat.android.LisnrService;
import com.lisnr.hflat.android.services.smartlistening.SmartListeningCallback;


/**
 * Created by Jacob on 4/16/2016.
 */
public class smartListening {
    LisnrService sdk;
    ListenToTone listen;
    LisnrCallback callback;

    public smartListening(LisnrService sdk, ListenToTone listen){
        this.sdk = sdk;
        this.listen = listen;
    }

    void startListening(){
        sdk.startListening();
        sdk.setBackgroundEnabled(true);
    }

    void didIHearTone(String a){
        Log.d(a, a);
    }

    void killTone(String id){
        MediaPlayer mediaPlayer = MediaPlayer.create(listen.getApplicationContext(),Integer.parseInt(id));
        Log.d("Playing Tone","Playing Tone");
    }

}
