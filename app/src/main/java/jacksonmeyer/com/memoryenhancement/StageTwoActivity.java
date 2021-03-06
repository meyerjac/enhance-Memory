package jacksonmeyer.com.memoryenhancement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L1;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L10;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L11;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L12;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L13;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L14;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L15;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L16;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L17;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L18;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L19;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L2;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L20;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L3;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L4;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L5;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L6;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L7;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L8;
import jacksonmeyer.com.memoryenhancement.Stage2.S2L9;

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
    private String proUnlocked = "";
    private String oldTotal;
    private String passed1;
    private String passed2;
    private String passed3;
    private String passed4;
    private String passed5;
    private String passed6;
    private String passed7;
    private String passed8;
    private String passed9;
    private String passed10;
    private String passed11;
    private String passed12;
    private String passed13;
    private String passed14;
    private String passed15;
    private String passed16;
    private String passed17;
    private String passed18;
    private String passed19;
    private String passed20;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_two);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(StageTwoActivity.this);
        passed1 = mSharedPreferences.getString(Constants.S1LEVEL1COMPLETE, null);
        passed2 = mSharedPreferences.getString(Constants.S1LEVEL2COMPLETE, null);
        passed3 = mSharedPreferences.getString(Constants.S1LEVEL3COMPLETE, null);
        passed4 = mSharedPreferences.getString(Constants.S1LEVEL4COMPLETE, null);
        passed5 = mSharedPreferences.getString(Constants.S1LEVEL5COMPLETE, null);
        passed6 = mSharedPreferences.getString(Constants.S1LEVEL6COMPLETE, null);
        passed7 = mSharedPreferences.getString(Constants.S1LEVEL7COMPLETE, null);
        passed8 = mSharedPreferences.getString(Constants.S1LEVEL8COMPLETE, null);
        passed9 = mSharedPreferences.getString(Constants.S1LEVEL9COMPLETE, null);
        passed10 = mSharedPreferences.getString(Constants.S1LEVEL10COMPLETE, null);
        passed11 = mSharedPreferences.getString(Constants.S1LEVEL11COMPLETE, null);
        passed12 = mSharedPreferences.getString(Constants.S1LEVEL12COMPLETE, null);
        passed13 = mSharedPreferences.getString(Constants.S1LEVEL13COMPLETE, null);
        passed14 = mSharedPreferences.getString(Constants.S1LEVEL14COMPLETE, null);
        passed15 = mSharedPreferences.getString(Constants.S1LEVEL15COMPLETE, null);
        passed16 = mSharedPreferences.getString(Constants.S1LEVEL16COMPLETE, null);
        passed17 = mSharedPreferences.getString(Constants.S1LEVEL17COMPLETE, null);
        passed18 = mSharedPreferences.getString(Constants.S1LEVEL18COMPLETE, null);
        passed19 = mSharedPreferences.getString(Constants.S1LEVEL19COMPLETE, null);
        passed20 = mSharedPreferences.getString(Constants.S1LEVEL20COMPLETE, null);
        String passed21 = mSharedPreferences.getString(Constants.S2LEVEL21COMPLETE, null);
        String passed22 = mSharedPreferences.getString(Constants.S2LEVEL22COMPLETE, null);
        String passed23 = mSharedPreferences.getString(Constants.S2LEVEL23COMPLETE, null);
        String passed24 = mSharedPreferences.getString(Constants.S2LEVEL24COMPLETE, null);
        String passed25 = mSharedPreferences.getString(Constants.S2LEVEL25COMPLETE, null);
        String passed26 = mSharedPreferences.getString(Constants.S2LEVEL26COMPLETE, null);
        String passed27 = mSharedPreferences.getString(Constants.S2LEVEL27COMPLETE, null);
        String passed28 = mSharedPreferences.getString(Constants.S2LEVEL28COMPLETE, null);
        String passed29 = mSharedPreferences.getString(Constants.S2LEVEL29COMPLETE, null);
        String passed30 = mSharedPreferences.getString(Constants.S2LEVEL30COMPLETE, null);
        String passed31 = mSharedPreferences.getString(Constants.S2LEVEL31COMPLETE, null);
        String passed32 = mSharedPreferences.getString(Constants.S2LEVEL32COMPLETE, null);
        String passed33 = mSharedPreferences.getString(Constants.S2LEVEL33COMPLETE, null);
        String passed34 = mSharedPreferences.getString(Constants.S2LEVEL34COMPLETE, null);
        String passed35 = mSharedPreferences.getString(Constants.S2LEVEL35COMPLETE, null);
        String passed36 = mSharedPreferences.getString(Constants.S2LEVEL36COMPLETE, null);
        String passed37 = mSharedPreferences.getString(Constants.S2LEVEL37COMPLETE, null);
        String passed38 = mSharedPreferences.getString(Constants.S2LEVEL38COMPLETE, null);
        String passed39 = mSharedPreferences.getString(Constants.S2LEVEL39COMPLETE, null);
        String passed40 = mSharedPreferences.getString(Constants.S2LEVEL40COMPLETE, null);

        oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        TitleTextView.setTypeface(Rubix);
        NumberOfLightbulbs.setTypeface(Rubix);
        HardTextView.setTypeface(Rubix);

        //manage whether or not a level has been unlocked
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
        } else {
            Level2.setBackgroundResource(R.drawable.yellow_circle);
            Level2.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed23 == null) {
        } else {
            Level3.setBackgroundResource(R.drawable.yellow_circle);
            Level3.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed24 == null) {
        } else {
            Level4.setBackgroundResource(R.drawable.yellow_circle);
            Level4.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed25 == null) {
        } else {
            Level5.setBackgroundResource(R.drawable.yellow_circle);
            Level5.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed26 == null) {
        } else {
            Level6.setBackgroundResource(R.drawable.yellow_circle);
            Level6.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed27 == null) {
        } else {
            Level7.setBackgroundResource(R.drawable.yellow_circle);
            Level7.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed28 == null) {
        } else {
            Level8.setBackgroundResource(R.drawable.yellow_circle);
            Level8.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed29 == null) {
        } else {
            Level9.setBackgroundResource(R.drawable.yellow_circle);
            Level9.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed30 == null) {
        } else {
            Level10.setBackgroundResource(R.drawable.yellow_circle);
            Level10.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed31 == null) {
        } else {
            Level11.setBackgroundResource(R.drawable.yellow_circle);
            Level11.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed32 == null) {
        } else {
            Level12.setBackgroundResource(R.drawable.yellow_circle);
            Level12.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed33 == null) {
        } else {
            Level13.setBackgroundResource(R.drawable.yellow_circle);
            Level13.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed34 == null) {
        } else {
            Level14.setBackgroundResource(R.drawable.yellow_circle);
            Level14.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed35 == null) {
        } else {
            Level15.setBackgroundResource(R.drawable.yellow_circle);
            Level15.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed36 == null) {
        } else {
            Level16.setBackgroundResource(R.drawable.yellow_circle);
            Level16.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed37 == null) {
        } else {
            Level17.setBackgroundResource(R.drawable.yellow_circle);
            Level17.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed38 == null) {
        } else {
            Level18.setBackgroundResource(R.drawable.yellow_circle);
            Level18.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed39 == null) {
        } else  {
            Level19.setBackgroundResource(R.drawable.yellow_circle);
            Level19.setTextColor(getResources().getColor(R.color.colorYellowish));
        }

        if (passed40 == null) {
        } else {
            Level20.setBackgroundResource(R.drawable.yellow_circle);
            Level20.setTextColor(getResources().getColor(R.color.colorYellowish));
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
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }

        } else if (view == Level2) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level3) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L3.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level4) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L4.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level5) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L5.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level6) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L6.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level7) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L7.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level8) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L8.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level9) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L9.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level10) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L10.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level11) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L11.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level12) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L12.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level13) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L13.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level14) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L14.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level15) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L15.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level16) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L16.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level17) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L17.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level18) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L18.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level19) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L19.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        } else if (view == Level20) {
            if (hasEnoughPoints()) {
                Intent intent = new Intent(this, S2L20.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
            } else {
                Toast.makeText(this, "You need to pass all of Stage 1, or get 500 points", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean hasEnoughPoints() {
        if (Integer.parseInt(oldTotal) >= 500) {
            return true;
        } else if ((passed1 != null) && (passed2 != null)
                && (passed3 != null) && (passed4 != null)
                && (passed5 != null) && (passed6 != null)
                && (passed7 != null) && (passed8 != null)
                && (passed9 != null) && (passed10 != null)
                && (passed11 != null) && (passed12 != null)
                && (passed13 != null) && (passed14 != null)
                && (passed15 != null) && (passed16 != null)
                && (passed17 != null) && (passed18 != null)
                && (passed19 != null) && (passed20 != null)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
    }
}
