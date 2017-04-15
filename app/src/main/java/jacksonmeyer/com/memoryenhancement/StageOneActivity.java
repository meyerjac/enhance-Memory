package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
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

public class StageOneActivity extends AppCompatActivity implements View.OnClickListener {
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

    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_one);
        ButterKnife.bind(this);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(StageOneActivity.this);
        String passed1 = mSharedPreferences.getString(Constants.S1LEVEL1COMPLETE, null);
        String passed2 = mSharedPreferences.getString(Constants.S1LEVEL2COMPLETE, null);
        String passed3 = mSharedPreferences.getString(Constants.S1LEVEL3COMPLETE, null);

        if (passed1 == null) {
            Log.d("onCreate: ", "1null");
        } else if (passed1.equals("true")) {
            Level1.setBackgroundResource(R.drawable.yellow_circle);
            Level1.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed2 == null) {
            Log.d("onCreate: ", "null2");
        } else if (passed2.equals("true")) {
            Level2.setBackgroundResource(R.drawable.yellow_circle);
            Level2.setTextColor(getResources().getColor(R.color.colorYellowish));
        } else {
            Log.d("onCreate: ", "didn't make it");
        }

        if (passed3 == null) {
            Log.d("onCreate: ", "null3");
        } else if (passed2.equals("true")) {
            Level3.setBackgroundResource(R.drawable.yellow_circle);
            Level3.setTextColor(getResources().getColor(R.color.colorYellowish));
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
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Level1) {
            Intent intent = new Intent(StageOneActivity.this, S1L1.class);
            startActivity(intent);
        } else if (view == Level2) {
            Intent intent = new Intent(StageOneActivity.this, S1L2.class);
            startActivity(intent);
        } else if (view == Level3) {
            Intent intent = new Intent(StageOneActivity.this, S1L3.class);
            startActivity(intent);
        } else if (view == Level4) {
            Intent intent = new Intent(StageOneActivity.this, S1L4.class);
            startActivity(intent);
        } else if (view == Level5) {
            Intent intent = new Intent(StageOneActivity.this, S1L5.class);
            startActivity(intent);
        } else if (view == Level6) {
            Intent intent = new Intent(StageOneActivity.this, S1L6.class);
            startActivity(intent);
        } else if (view == Level7) {
            Intent intent = new Intent(StageOneActivity.this, S1L7.class);
            startActivity(intent);
        } else if (view == Level8) {
            Intent intent = new Intent(StageOneActivity.this, S1L8.class);
            startActivity(intent);
        } else if (view == Level9) {
            Intent intent = new Intent(StageOneActivity.this, S1L9.class);
            startActivity(intent);
        } else if (view == Level10) {
            Intent intent = new Intent(StageOneActivity.this, S1L10.class);
            startActivity(intent);
        } else if (view == Level11) {
            Intent intent = new Intent(StageOneActivity.this, S1L11.class);
            startActivity(intent);
        } else if (view == Level12) {
            Intent intent = new Intent(StageOneActivity.this, S1L12.class);
            startActivity(intent);
        } else if (view == Level13) {
            Intent intent = new Intent(StageOneActivity.this, S1L13.class);
            startActivity(intent);
        } else if (view == Level14) {
            Intent intent = new Intent(StageOneActivity.this, S1L14.class);
            startActivity(intent);
        } else if (view == Level15) {
            Intent intent = new Intent(StageOneActivity.this, S1L15.class);
            startActivity(intent);
        } else if (view == Level16) {
            Intent intent = new Intent(StageOneActivity.this, S1L16.class);
            startActivity(intent);
        } else if (view == Level17) {
            Intent intent = new Intent(StageOneActivity.this, S1L17.class);
            startActivity(intent);
        } else if (view == Level18) {
            Intent intent = new Intent(StageOneActivity.this, S1L18.class);
            startActivity(intent);
        } else if (view == Level19) {
            Intent intent = new Intent(StageOneActivity.this, S1L19.class);
            startActivity(intent);
        } else if (view == Level20) {
            Intent intent = new Intent(StageOneActivity.this, S1L20.class);
            startActivity(intent);
        }
    }
}
