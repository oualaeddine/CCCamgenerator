package berrdevbox.com.cccamgenerator;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by berre on 7/28/2017.
 * Project : CCCamgenerator.
 */

public class AdService extends Service {


    private InterstitialAd mInterstitialAd;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //do something useful
        startRepeatingTask();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }


    private final static int INTERVAL = 1000 * 60 * 5; //2 minutes
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            showAd();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    private void showAd() {
       /* Context context = getApplicationContext();
        Intent i = new Intent(context, AdActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);*/
        MobileAds.initialize(this, getString(R.string.ad_app_id));

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }

            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                //mInterstitialAd.loadAd(new AdRequest.Builder().build());

               /* Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(FLAG_ACTIVITY_CLEAR_TOP);i.addFlags(FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);*/
            }
        });
    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    void startRepeatingTask() {
        mHandlerTask.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask);
    }
}