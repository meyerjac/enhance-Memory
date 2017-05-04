package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StageThreeActivity extends AppCompatActivity implements View.OnClickListener {
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
    @Bind(R.id.titleTextView)
    TextView TitleTextView;
    @Bind(R.id.geniusTextView)
    TextView GeniusTextView;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_three);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(StageThreeActivity.this);
        String passed41 = mSharedPreferences.getString(Constants.S3LEVEL41COMPLETE, null);
        String passed42 = mSharedPreferences.getString(Constants.S3LEVEL42COMPLETE, null);
        String passed43 = mSharedPreferences.getString(Constants.S3LEVEL43COMPLETE, null);
        String passed44 = mSharedPreferences.getString(Constants.S3LEVEL44COMPLETE, null);
        String passed45 = mSharedPreferences.getString(Constants.S3LEVEL45COMPLETE, null);
        String passed46 = mSharedPreferences.getString(Constants.S3LEVEL46COMPLETE, null);
        String passed47 = mSharedPreferences.getString(Constants.S3LEVEL47COMPLETE, null);
        String passed48 = mSharedPreferences.getString(Constants.S3LEVEL48COMPLETE, null);
        String passed49 = mSharedPreferences.getString(Constants.S3LEVEL49COMPLETE, null);
        String passed50 = mSharedPreferences.getString(Constants.S3LEVEL50COMPLETE, null);
        String passed51 = mSharedPreferences.getString(Constants.S3LEVEL51COMPLETE, null);
        String passed52 = mSharedPreferences.getString(Constants.S3LEVEL52COMPLETE, null);
        String passed53 = mSharedPreferences.getString(Constants.S3LEVEL53COMPLETE, null);
        String passed54 = mSharedPreferences.getString(Constants.S3LEVEL54COMPLETE, null);
        String passed55 = mSharedPreferences.getString(Constants.S3LEVEL55COMPLETE, null);
        String passed56 = mSharedPreferences.getString(Constants.S3LEVEL56COMPLETE, null);
        String passed57 = mSharedPreferences.getString(Constants.S3LEVEL57COMPLETE, null);
        String passed58 = mSharedPreferences.getString(Constants.S3LEVEL58COMPLETE, null);
        String passed59 = mSharedPreferences.getString(Constants.S3LEVEL59COMPLETE, null);
        String passed60 = mSharedPreferences.getString(Constants.S3LEVEL60COMPLETE, null);

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);
        GeniusTextView.setTypeface(Rubix);

//        if (passed21 == null) {
//        } else if (passed21.equals("true")) {
//            Level21.setBackgroundResource(R.drawable.yellow_circle);
//            Level21.setTextColor(getResources().getColor(R.color.colorYellowish));
//        }
//
//        if (passed2 == null) {
//        } else if (passed2.equals("true")) {
//            Level2.setBackgroundResource(R.drawable.yellow_circle);
//            Level2.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed3 == null) {
//        } else if (passed3.equals("true")) {
//            Level3.setBackgroundResource(R.drawable.yellow_circle);
//            Level3.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed4 == null) {
//        } else if (passed3.equals("true")) {
//            Level4.setBackgroundResource(R.drawable.yellow_circle);
//            Level4.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed5 == null) {
//        } else if (passed3.equals("true")) {
//            Level5.setBackgroundResource(R.drawable.yellow_circle);
//            Level5.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed6 == null) {
//        } else if (passed3.equals("true")) {
//            Level6.setBackgroundResource(R.drawable.yellow_circle);
//            Level6.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed7 == null) {
//        } else if (passed3.equals("true")) {
//            Level7.setBackgroundResource(R.drawable.yellow_circle);
//            Level7.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed8 == null) {
//        } else if (passed3.equals("true")) {
//            Level8.setBackgroundResource(R.drawable.yellow_circle);
//            Level8.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed9 == null) {
//        } else if (passed3.equals("true")) {
//            Level9.setBackgroundResource(R.drawable.yellow_circle);
//            Level9.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed10 == null) {
//        } else if (passed3.equals("true")) {
//            Level10.setBackgroundResource(R.drawable.yellow_circle);
//            Level10.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed11 == null) {
//        } else if (passed3.equals("true")) {
//            Level11.setBackgroundResource(R.drawable.yellow_circle);
//            Level11.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed12 == null) {
//        } else if (passed3.equals("true")) {
//            Level12.setBackgroundResource(R.drawable.yellow_circle);
//            Level12.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed13 == null) {
//        } else if (passed3.equals("true")) {
//            Level13.setBackgroundResource(R.drawable.yellow_circle);
//            Level13.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed14 == null) {
//        } else if (passed3.equals("true")) {
//            Level14.setBackgroundResource(R.drawable.yellow_circle);
//            Level14.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed15 == null) {
//        } else if (passed3.equals("true")) {
//            Level15.setBackgroundResource(R.drawable.yellow_circle);
//            Level15.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed16 == null) {
//        } else if (passed3.equals("true")) {
//            Level16.setBackgroundResource(R.drawable.yellow_circle);
//            Level16.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed17 == null) {
//        } else if (passed3.equals("true")) {
//            Level17.setBackgroundResource(R.drawable.yellow_circle);
//            Level17.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed18 == null) {
//        } else if (passed3.equals("true")) {
//            Level18.setBackgroundResource(R.drawable.yellow_circle);
//            Level18.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed19 == null) {
//        } else if (passed3.equals("true")) {
//            Level19.setBackgroundResource(R.drawable.yellow_circle);
//            Level19.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//        if (passed20 == null) {
//        } else if (passed3.equals("true")) {
//            Level20.setBackgroundResource(R.drawable.yellow_circle);
//            Level20.setTextColor(getResources().getColor(R.color.colorYellowish));
//        } else {
//            Log.d("onCreate: ", "didn't make it");
//        }
//
//
//
//        Level1.setOnClickListener(this);
//        Level2.setOnClickListener(this);
//        Level3.setOnClickListener(this);
//        Level4.setOnClickListener(this);
//        Level5.setOnClickListener(this);
//        Level6.setOnClickListener(this);
//        Level7.setOnClickListener(this);
//        Level8.setOnClickListener(this);
//        Level9.setOnClickListener(this);
//        Level10.setOnClickListener(this);
//        Level11.setOnClickListener(this);
//        Level12.setOnClickListener(this);
//        Level13.setOnClickListener(this);
//        Level14.setOnClickListener(this);
//        Level15.setOnClickListener(this);
//        Level16.setOnClickListener(this);
//        Level17.setOnClickListener(this);
//        Level18.setOnClickListener(this);
//        Level19.setOnClickListener(this);
//        Level20.setOnClickListener(this);
        BackArrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == BackArrow) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        }
//        } else if (view == Level1) {
//            Intent intent = new Intent(StageOneActivity.this, S1L1.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level2) {
//            Intent intent = new Intent(StageOneActivity.this, S1L2.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level3) {
//            Intent intent = new Intent(StageOneActivity.this, S1L3.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level4) {
//            Intent intent = new Intent(StageOneActivity.this, S1L4.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level5) {
//            Intent intent = new Intent(StageOneActivity.this, S1L5.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level6) {
//            Intent intent = new Intent(StageOneActivity.this, S1L6.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level7) {
//            Intent intent = new Intent(StageOneActivity.this, S1L7.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level8) {
//            Intent intent = new Intent(StageOneActivity.this, S1L8.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level9) {
//            Intent intent = new Intent(StageOneActivity.this, S1L9.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level10) {
//            Intent intent = new Intent(StageOneActivity.this, S1L10.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level11) {
//            Intent intent = new Intent(StageOneActivity.this, S1L11.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level12) {
//            Intent intent = new Intent(StageOneActivity.this, S1L12.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level13) {
//            Intent intent = new Intent(StageOneActivity.this, S1L13.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level14) {
//            Intent intent = new Intent(StageOneActivity.this, S1L14.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level15) {
//            Intent intent = new Intent(StageOneActivity.this, S1L15.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level16) {
//            Intent intent = new Intent(StageOneActivity.this, S1L16.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level17) {
//            Intent intent = new Intent(StageOneActivity.this, S1L17.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level18) {
//            Intent intent = new Intent(StageOneActivity.this, S1L18.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level19) {
//            Intent intent = new Intent(StageOneActivity.this, S1L19.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        } else if (view == Level20) {
//            Intent intent = new Intent(StageOneActivity.this, S1L20.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
//        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}

