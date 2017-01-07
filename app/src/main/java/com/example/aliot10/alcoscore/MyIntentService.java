package com.example.aliot10.alcoscore;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super(name);
    }
   public MyIntentService(){
       super("");

   }

    @Override
    protected void onHandleIntent(Intent intent) {

        synchronized (this) {
            while (true) {
                try {
                    wait(3600);//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Preferences pref = new Preferences(getBaseContext());

                float promil = pref.getWeightOfAlcoholInBody();
                if (promil > 0.15) {
                    promil -= 0.15;//
                }
                pref.setWeightOfAlcoholInBody(promil);

            }
        }
    }
}
