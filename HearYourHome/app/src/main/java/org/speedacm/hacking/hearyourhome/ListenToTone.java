package org.speedacm.hacking.hearyourhome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.lisnr.hflat.android.Lisnr;
import com.lisnr.hflat.android.LisnrCallback;
import com.lisnr.hflat.android.LisnrService;
import com.lisnr.hflat.sdk.LisnrTone;

public class ListenToTone extends AppCompatActivity {
    private final static String key = "ef3176d1-1cd0-43d2-899b-7b1b02e65d83";
    public LisnrTone appearTone;
    smartListening listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_listen_to_tone);
        }catch(Exception e){
            Log.d(e.toString(),e.toString());
        }
        LisnrService sdk = Lisnr.getInstance(this);
        sdk.initWithApiKey(key, this.getApplication());
        //listen.startListening();
        listen = new smartListening(sdk,this);
        sdk.startListening();
        sdk.setBackgroundEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listen_to_tone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** implementation of the callback */
    private LisnrCallback callback = new LisnrCallback() {
        @Override
        public void didHearTone(LisnrTone lisnrTone) {
            /*appearTone =lisnrTone;
            listen.didIHearTone(appearTone.getId());*/
        /* called every time a tone is heard */
        }
        @Override
        public void onToneAppeared(LisnrTone lisnrTone) {
            appearTone =lisnrTone;
            listen.didIHearTone(appearTone.getId(),appearTone.getTimestamp());
/* called once while a tone is continously being heard */
        }
        @Override
        public void onToneDisappeared(String s, double v) {
            /*listen.killTone(appearTone.getId());*/
            appearTone = null;
/* called once when a continously heard tone disappears*/
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
/*register a callback and start listening*/
        Lisnr.getInstance(this).registerCallback(callback);
        Lisnr.getInstance(this).startListening();
    }

}
