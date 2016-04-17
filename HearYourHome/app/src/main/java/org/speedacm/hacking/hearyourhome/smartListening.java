package org.speedacm.hacking.hearyourhome;

import android.media.MediaPlayer;
import android.util.Log;

import com.lisnr.hflat.android.LisnrCallback;
import com.lisnr.hflat.android.LisnrService;

import java.io.IOException;

import okhttp3.*;
//import retrofit2.Request;


/**
 * Created by Jacob on 4/16/2016.
 */
public class smartListening{
    LisnrService sdk;
    ListenToTone listen;
    LisnrCallback callback;
    String devName;
    double timestamp;
    String json;
    private static String url  = "http://10.63.2.159/";
    String id;

    public smartListening(LisnrService sdk, ListenToTone listen){
        this.sdk = sdk;
        this.listen = listen;
    }

    void startListening(){
        sdk.startListening();
        sdk.setBackgroundEnabled(true);
    }


    public void getVars(String json, String id){
        this.json = json;
        this.id = id;
    }

    public void startPole(){
        thread.start();
    }

    Thread thread = new Thread(new Runnable()
    {
        @Override
        public void run()
        {

            try
            {
                final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

                OkHttpClient client = new OkHttpClient();

                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                String responseString="";
                try {
                    Response response = client.newCall(request).execute();
                    responseString = response.body().string();
                }catch(Exception e){}
                if(!responseString.equals("")){
                    killTone(id);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    });



    void killTone(String id){
        //MediaPlayer mediaPlayer = MediaPlayer.create(listen.getApplicationContext(),Integer.parseInt(id));
        if(id.equals("131994")) {
            MediaPlayer mediaPlayer;
            try {
                mediaPlayer = MediaPlayer.create(listen.getApplicationContext(), R.raw.media_12345);
                mediaPlayer.start();
                Log.d(Boolean.toString(mediaPlayer.isPlaying()), Boolean.toString(mediaPlayer.isPlaying()));
                mediaPlayer.stop();
            }catch(Exception e){
                Log.d(e.toString(),e.toString());
            }
            //mediaPlayer.stop();
            Log.d("Playing Tone","Playing Tone");
        }
    }

}
