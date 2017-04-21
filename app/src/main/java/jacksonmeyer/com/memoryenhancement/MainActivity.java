package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static jacksonmeyer.com.memoryenhancement.R.id.lightbulbImage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(lightbulbImage)
    ImageView LightbulbImage;
    @Bind(R.id.musicButton)
    ImageView MusicButton;
    @Bind(R.id.noMusicButton)
    ImageView NoMusicButton;
    @Bind(R.id.cartButton)
    ImageView CartButton;

    @Bind(R.id.titleTextView)
    TextView TitleTextView;
    @Bind(R.id.noAdsButton)
    TextView NoAdsButton;
    @Bind(R.id.nextButton)
    TextView RateButton;
    @Bind(R.id.playButton)
    TextView PlayButton;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id.animationTextView)
    TextView AnimationTextView;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private boolean mIsBound = false;
    private BackgroundSoundService mServ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        String musicOn = mSharedPreferences.getString(Constants.MUSIC_PLAYING, null);

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
            MusicButton.setVisibility(View.INVISIBLE);
            NoMusicButton.setVisibility(View.VISIBLE);
        }

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        RateButton.setTypeface(Rubix);
        PlayButton.setTypeface(Rubix);
        NoAdsButton.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);

        PlayButton.setOnClickListener(this);
        NoAdsButton.setOnClickListener(this);
        RateButton.setOnClickListener(this);
        CartButton.setOnClickListener(this);
        MusicButton.setOnClickListener(this);
        NoMusicButton.setOnClickListener(this);

        //hides the top top bar
//
//        View decorView = getWindow().getDecorView();
//// Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                TitleTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 0);
    }

    @Override
    public void onClick(View view) {
        if (view == PlayButton) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
            Intent intent = new Intent(MainActivity.this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == MusicButton) {
            //stop music service...stop music from playing
            MusicButton.setVisibility(View.INVISIBLE);
            NoMusicButton.setVisibility(View.VISIBLE);
            NoMusicButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));

            //stop Background Music
            Intent music = new Intent();
            music.setClass(this, BackgroundSoundService.class);
            stopService(music);

            mEditor.putString(Constants.MUSIC_PLAYING, "off").apply();

        } else if (view == NoMusicButton) {
            //start music service...music playing again
            Intent music = new Intent();
            music.setClass(this, BackgroundSoundService.class);
            startService(music);

            NoMusicButton.setVisibility(View.INVISIBLE);
            MusicButton.setVisibility(View.VISIBLE);
            MusicButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));

            mEditor.putString(Constants.MUSIC_PLAYING, "on").apply();
        } else if (view == NoAdsButton) {
            if (NoAdsButton.getText().equals("No Ads")) {
                NoAdsButton.setText("Ads");
                NoAdsButton.setBackgroundResource(R.drawable.red_circle_and_line);
                NoAdsButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
            } else {
            }


            
        } else if (view == RateButton) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));

        } else if (view == CartButton) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));

        }
    }
}
