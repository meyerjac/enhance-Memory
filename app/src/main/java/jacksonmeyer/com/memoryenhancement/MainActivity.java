package jacksonmeyer.com.memoryenhancement;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static jacksonmeyer.com.memoryenhancement.R.id.lightbulbImage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(lightbulbImage)
    ImageView LightbulbImage;
    @Bind(R.id.musicButton)
    ImageView MusicButton;
//    @Bind(R.id.cartButton)
//    ImageView CartButton;

    @Bind(R.id.titleTextView)
    TextView TitleTextView;
    @Bind(R.id.titleTextView2)
    TextView TitleTextView2;
//    @Bind(R.id.noAdsButton)
//    TextView NoAdsButton;
    @Bind(R.id.nextButton)
    TextView RateButton;
    @Bind(R.id.playButton)
    TextView PlayButton;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id.animationTextView)
    TextView AnimationTextView;
    @Bind(R.id.ad_view)
    AdView Ad_view;



    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean mIsBound = false;
    private BackgroundSoundService mServ;
    private String musicOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        AdRequest request = new AdRequest.Builder()
                .addTestDevice("A449F2C6C55C7DC233B43DA5E09FD24C")
                .build();
        Ad_view.loadAd(request);



        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        musicOn = mSharedPreferences.getString(Constants.MUSIC_PLAYING, null);

        //GET THE LIGHTBULB COUNT
        if (oldTotal == null) {
            mEditor.putString(Constants.LIGHTBULB_INTEGER_COUNT, "0").apply();
            Integer oldTotalInt = 0;
            NumberOfLightbulbs.setText(String.valueOf(oldTotalInt));
        } else {
            NumberOfLightbulbs.setText(String.valueOf(oldTotal));
        }


        //CHECK WHAT THE USER HAD SET AS THIER PREFERENCE FOR MUSIC
        if ((musicOn == null) || (musicOn.equals("on"))) {
            mEditor.putString(Constants.MUSIC_PLAYING, "on").apply();

            //start Background Music
            Intent music = new Intent();
            music.setClass(this, BackgroundSoundService.class);
            startService(music);
        } else {
            MusicButton.setBackgroundResource(R.drawable.red_circle_and_line);
        }

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        RateButton.setTypeface(Rubix);
        PlayButton.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);

        PlayButton.setOnClickListener(this);
        RateButton.setOnClickListener(this);
        MusicButton.setOnClickListener(this);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                TitleTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 0);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                TitleTextView2.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 250);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug", "onResume: " + "resume");

        String musicOn = mSharedPreferences.getString(Constants.MUSIC_PLAYING, null);
        if ((musicOn == null) || (musicOn.equals("on"))) {
            mEditor.putString(Constants.MUSIC_PLAYING, "on").apply();

            //start Background Music
            Intent music = new Intent();
            music.setClass(this, BackgroundSoundService.class);
            startService(music);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent music = new Intent();
        music.setClass(this, BackgroundSoundService.class);
        stopService(music);
        Log.d("debug", "onDestroy: " + "destroy");
    }

    @Override
    public void onClick(View view) {
        if (view == PlayButton) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
            Intent intent = new Intent(MainActivity.this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == MusicButton) {
            musicOn = mSharedPreferences.getString(Constants.MUSIC_PLAYING, null);
            {
                MusicButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                if (musicOn.equals("on")) {
                    //stop music service...stop music from playing
                    MusicButton.setBackgroundResource(R.drawable.red_circle_and_line);
                    Intent music = new Intent();
                    music.setClass(this, BackgroundSoundService.class);
                    stopService(music);

                    mEditor.putString(Constants.MUSIC_PLAYING, "off").apply();
                } else {
                    MusicButton.setBackgroundResource(R.drawable.gray_circle);

                    //start music service...music playing again
                    Intent music = new Intent();
                    music.setClass(this, BackgroundSoundService.class);
                    startService(music);
                    mEditor.putString(Constants.MUSIC_PLAYING, "on").apply();
                }
            }
        }
//        else if (view == NoAdsButton) {
//            if (NoAdsButton.getText().equals("No Ads")) {
//                NoAdsButton.setText("Ads");
//                NoAdsButton.setBackgroundResource(R.drawable.red_circle_and_line);
//                NoAdsButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
//            }
            else if (view == RateButton) {
                view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
            Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
            }

            }
        }
    }
