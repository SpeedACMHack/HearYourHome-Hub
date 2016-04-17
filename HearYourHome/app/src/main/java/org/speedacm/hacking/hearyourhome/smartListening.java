package org.speedacm.hacking.hearyourhome;

import android.media.MediaPlayer;
import android.util.Log;

import com.lisnr.hflat.android.LisnrCallback;
import com.lisnr.hflat.android.LisnrService;


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
        //MediaPlayer mediaPlayer = MediaPlayer.create(listen.getApplicationContext(),Integer.parseInt(id));
        if(id.equals("131994")) {
            MediaPlayer mediaPlayer;
            try {
                mediaPlayer = MediaPlayer.create(listen.getApplicationContext(), R.raw.media_12345);
                mediaPlayer.start();
                Log.d(Boolean.toString(mediaPlayer.isPlaying()),Boolean.toString(mediaPlayer.isPlaying()));
            }catch(Exception e){
                Log.d(e.toString(),e.toString());
            }
            //mediaPlayer.stop();
            Log.d("Playing Tone","Playing Tone");
        }
    }

}
