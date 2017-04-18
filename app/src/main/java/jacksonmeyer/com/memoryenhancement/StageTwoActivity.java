package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L1;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L10;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L11;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L12;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L13;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L14;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L15;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L16;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L17;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L18;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L19;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L2;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L20;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L3;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L4;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L5;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L6;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L7;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L8;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L9;

public class StageTwoActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.levelOne)
    TextView Level1;
    @Bind(R.id.levelTwo)
    TextView Level2;
    @Bind(R.id.levelThree)
    TextView Level3;
    @Bind(R.id.levelFour)
    TextView Level4;
    @Bind(R.id.levelFive)
    TextView Level5;
    @Bind(R.id.levelSix)
    TextView Level6;
    @Bind(R.id.levelSeven)
    TextView Level7;
    @Bind(R.id.levelEight)
    TextView Level8;
    @Bind(R.id.levelNine)
    TextView Level9;
    @Bind(R.id.levelTen)
    TextView Level10;
    @Bind(R.id.levelEleven)
    TextView Level11;
    @Bind(R.id.levelTwelve)
    TextView Level12;
    @Bind(R.id.levelThirteen)
    TextView Level13;
    @Bind(R.id.levelFourteen)
    TextView Level14;
    @Bind(R.id.levelFifteen)
    TextView Level15;
    @Bind(R.id.levelSixteen)
    TextView Level16;
    @Bind(R.id.levelseventeen)
    TextView Level17;
    @Bind(R.id.levelEighteen)
    TextView Level18;
    @Bind(R.id.levelNineteen)
    TextView Level19;
    @Bind(R.id.levelTwenty)
    TextView Level20;

    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.nextArrow)
    ImageView NextArrow;
    @Bind(R.id.titleTextView)
    TextView TitleTextView;
    @Bind(R.id.hardTextView)
    TextView HardTextView;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(StageTwoActivity.this);
        String passed21 = mSharedPreferences.getString(Constants.S1LEVEL21COMPLETE, null);
        String passed22 = mSharedPreferences.getString(Constants.S1LEVEL22COMPLETE, null);
        String passed23 = mSharedPreferences.getString(Constants.S1LEVEL23COMPLETE, null);
        String passed24 = mSharedPreferences.getString(Constants.S1LEVEL24COMPLETE, null);
        String passed25 = mSharedPreferences.getString(Constants.S1LEVEL25COMPLETE, null);
        String passed26 = mSharedPreferences.getString(Constants.S1LEVEL26COMPLETE, null);
        String passed27 = mSharedPreferences.getString(Constants.S1LEVEL27COMPLETE, null);
        String passed28 = mSharedPreferences.getString(Constants.S1LEVEL28COMPLETE, null);
        String passed29= mSharedPreferences.getString(Constants.S1LEVEL29COMPLETE, null);
        String passed30 = mSharedPreferences.getString(Constants.S1LEVEL30COMPLETE, null);
        String passed31 = mSharedPreferences.getString(Constants.S1LEVEL31COMPLETE, null);
        String passed32 = mSharedPreferences.getString(Constants.S1LEVEL32COMPLETE, null);
        String passed33 = mSharedPreferences.getString(Constants.S1LEVEL33COMPLETE, null);
        String passed34 = mSharedPreferences.getString(Constants.S1LEVEL34COMPLETE, null);
        String passed35 = mSharedPreferences.getString(Constants.S1LEVEL35COMPLETE, null);
        String passed36 = mSharedPreferences.getString(Constants.S1LEVEL36COMPLETE, null);
        String passed37 = mSharedPreferences.getString(Constants.S1LEVEL37COMPLETE, null);
        String passed38 = mSharedPreferences.getString(Constants.S1LEVEL38COMPLETE, null);
        String passed39 = mSharedPreferences.getString(Constants.S1LEVEL39COMPLETE, null);
        String passed40 = mSharedPreferences.getString(Constants.S1LEVEL40COMPLETE, null);

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);
        HardTextView.setTypeface(Rubix);
        Level1.setTypeface(Rubix); Level2.setTypeface(Rubix); Level3.setTypeface(Rubix); Level4.setTypeface(Rubix);
        Level5.setTypeface(Rubix); Level6.setTypeface(Rubix); Level7.setTypeface(Rubix); Level8.setTypeface(Rubix);
        Level9.setTypeface(Rubix); Level10.setTypeface(Rubix); Level11.setTypeface(Rubix); Level12.setTypeface(Rubix);
        Level13.setTypeface(Rubix); Level14.setTypeface(Rubix); Level15.setTypeface(Rubix); Level16.setTypeface(Rubix);
        Level17.setTypeface(Rubix); Level18.setTypeface(Rubix); Level19.setTypeface(Rubix); Level20.setTypeface(Rubix);

        if (passed21 == null) {
        } else if (passed21.equals("true")) {
            Level1.setBackgroundResource(R.drawable.yellow_circle);
            Level1.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed22 == null) {
        } else if (passed22.equals("true")) {
            Level2.setBackgroundResource(R.drawable.yellow_circle);
            Level2.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed23 == null) {
        } else if (passed23.equals("true")) {
            Level3.setBackgroundResource(R.drawable.yellow_circle);
            Level3.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed24 == null) {
        } else if (passed24.equals("true")) {
            Level4.setBackgroundResource(R.drawable.yellow_circle);
            Level4.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed25 == null) {
        } else if (passed25.equals("true")) {
            Level5.setBackgroundResource(R.drawable.yellow_circle);
            Level5.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed26 == null) {
        } else if (passed26.equals("true")) {
            Level6.setBackgroundResource(R.drawable.yellow_circle);
            Level6.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed27 == null) {
        } else if (passed27.equals("true")) {
            Level7.setBackgroundResource(R.drawable.yellow_circle);
            Level7.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed28 == null) {
        } else if (passed28.equals("true")) {
            Level8.setBackgroundResource(R.drawable.yellow_circle);
            Level8.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed29 == null) {
        } else if (passed29.equals("true")) {
            Level9.setBackgroundResource(R.drawable.yellow_circle);
            Level9.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed30 == null) {
        } else if (passed30.equals("true")) {
            Level10.setBackgroundResource(R.drawable.yellow_circle);
            Level10.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed31 == null) {
        } else if (passed31.equals("true")) {
            Level11.setBackgroundResource(R.drawable.yellow_circle);
            Level11.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed32 == null) {
        } else if (passed32.equals("true")) {
            Level12.setBackgroundResource(R.drawable.yellow_circle);
            Level12.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed33 == null) {
        } else if (passed33.equals("true")) {
            Level13.setBackgroundResource(R.drawable.yellow_circle);
            Level13.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed34 == null) {
        } else if (passed34.equals("true")) {
            Level14.setBackgroundResource(R.drawable.yellow_circle);
            Level14.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed35 == null) {
        } else if (passed35.equals("true")) {
            Level15.setBackgroundResource(R.drawable.yellow_circle);
            Level15.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed36 == null) {
        } else if (passed36.equals("true")) {
            Level16.setBackgroundResource(R.drawable.yellow_circle);
            Level16.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed37 == null) {
        } else if (passed37.equals("true")) {
            Level17.setBackgroundResource(R.drawable.yellow_circle);
            Level17.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed38 == null) {
        } else if (passed38.equals("true")) {
            Level18.setBackgroundResource(R.drawable.yellow_circle);
            Level18.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed39 == null) {
        } else if (passed39.equals("true")) {
            Level19.setBackgroundResource(R.drawable.yellow_circle);
            Level19.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed40 == null) {
        } else if (passed40.equals("true")) {
            Level20.setBackgroundResource(R.drawable.yellow_circle);
            Level20.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        Level1.setOnClickListener(this);
        Level2.setOnClickListener(this);
        Level3.setOnClickListener(this);
        Level4.setOnClickListener(this);
        Level5.setOnClickListener(this);
        Level6.setOnClickListener(this);
        Level7.setOnClickListener(this);
        Level8.setOnClickListener(this);
        Level9.setOnClickListener(this);
        Level10.setOnClickListener(this);
        Level11.setOnClickListener(this);
        Level12.setOnClickListener(this);
        Level13.setOnClickListener(this);
        Level14.setOnClickListener(this);
        Level15.setOnClickListener(this);
        Level16.setOnClickListener(this);
        Level17.setOnClickListener(this);
        Level18.setOnClickListener(this);
        Level19.setOnClickListener(this);
        Level20.setOnClickListener(this);
        BackArrow.setOnClickListener(this);
        NextArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == BackArrow) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == NextArrow) {
            Intent intent = new Intent(this, StageThreeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level1) {
            Intent intent = new Intent(this, S1L1.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level2) {
            Intent intent = new Intent(this, S1L2.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level3) {
            Intent intent = new Intent(this, S1L3.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level4) {
            Intent intent = new Intent(this, S1L4.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level5) {
            Intent intent = new Intent(this, S1L5.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level6) {
            Intent intent = new Intent(this, S1L6.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level7) {
            Intent intent = new Intent(this, S1L7.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level8) {
            Intent intent = new Intent(this, S1L8.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level9) {
            Intent intent = new Intent(this, S1L9.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level10) {
            Intent intent = new Intent(this, S1L10.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level11) {
            Intent intent = new Intent(this, S1L11.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level12) {
            Intent intent = new Intent(this, S1L12.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level13) {
            Intent intent = new Intent(this, S1L13.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level14) {
            Intent intent = new Intent(this, S1L14.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level15) {
            Intent intent = new Intent(this, S1L15.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level16) {
            Intent intent = new Intent(this, S1L16.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level17) {
            Intent intent = new Intent(this, S1L17.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level18) {
            Intent intent = new Intent(this, S1L18.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level19) {
            Intent intent = new Intent(this, S1L19.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        } else if (view == Level20) {
            Intent intent = new Intent(this, S1L20.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
