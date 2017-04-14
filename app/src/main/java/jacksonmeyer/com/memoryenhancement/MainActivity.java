package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.titleTextView)
    TextView TitleTextView;
    @Bind(R.id.noAdsButton)
    TextView NoAdsButton;
    @Bind(R.id.rateButton)
    TextView RateButton;
    @Bind(R.id.playButton)
    TextView PlayButton;
    @Bind(R.id. numberOfLightbulbs)
    TextView NumberOfLightbulbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        RateButton.setTypeface(Rubix);
        PlayButton.setTypeface(Rubix);
        NoAdsButton.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);

        PlayButton.setOnClickListener(this);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                TitleTextView.startAnimation(bounceAnim);
                TitleTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 0);
    }

    @Override
    public void onClick(View view) {
        if (view == PlayButton) {
            Intent intent = new Intent(MainActivity.this, LevelsActivity.class);
            startActivity(intent);
        }
    }
}
