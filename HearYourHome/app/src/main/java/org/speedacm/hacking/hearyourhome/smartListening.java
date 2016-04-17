package org.speedacm.hacking.hearyourhome;

import android.content.ContentValues;
import android.media.MediaPlayer;
import android.util.Log;

import com.lisnr.hflat.android.LisnrCallback;
import com.lisnr.hflat.android.LisnrService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


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

    String lookUp(String id){
        if(id.equals("131994")){
            return "Microwave->Kill";
        }
        return"";
    }

    void didIHearTone(String devName, double timestamp){
        try {
            URL url = new URL("10.63.2.159");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());

                String sendMe = "devicename=" + lookUp(devName) + "&timestamp=" + timestamp;


                dStream.writeBytes(sendMe);

                dStream.flush();
                dStream.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder str = new StringBuilder();
                String line = "";
                while((line = br.readLine())!= null){
                    str.append(line);
                }
                br.close();
                if(!str.toString().equals("")){
                    Log.d("POST Didnt Work","POST Didnt Work");
                }


            } catch (Exception e) {
            }
        }catch(Exception e){}
        Log.d("Posted","Posted");
        killTone(devName);


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
