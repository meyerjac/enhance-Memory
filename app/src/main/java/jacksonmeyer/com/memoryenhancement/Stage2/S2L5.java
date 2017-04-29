package jacksonmeyer.com.memoryenhancement.Stage2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

public class S2L5 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.questionTextView)
    TextView QuestionTextView;
    @Bind(R.id.answer1)
    Button Button1;
    @Bind(R.id.answer2)
    Button Button2;
    @Bind(R.id.answer3)
    Button Button3;
    @Bind(R.id.answer4)
    Button Button4;
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(R.id.lightBulbRelativeLayout)
    RelativeLayout LightBulbRelativeLayout;
    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.next)
    TextView Next;
    @Bind(R.id.replay)
    TextView Replay;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id. checkXImageView)
    ImageView CheckXImageView;


    private CountDownTimer countDownTimer;
    private final long startTime = 10 * 1000;
    private final long interval = 1000;
    private String questionObject = null;
    private String answerColor = null;
    private Integer correctButton = null;
    private String TAG = "debug";

    private String color1 = null;
    private String color2 = null;
    private String color3  = null;
    private String color4 = null;
    private String color5  = null;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l5);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L5.this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);
        setColorsAndObjects();


        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);
    }

    private void setColorsAndObjects() {

        //random colors assigned to the story
        List<String> colorList = new ArrayList<String>();
        colorList.add("blue");
        colorList.add("yellow" );
        colorList.add("pink");
        colorList.add("green");
        colorList.add("white");

        Map<Integer, String> colors = new HashMap<Integer, String>();

        for(int i = 1; i < 6; i ++ ) {
            Integer randomNum = (int)(Math.random() * ((6 - 1)));
            colors.put(i, colorList.get(randomNum));
        }

        color1 = colors.get(1);
        color2 = colors.get(2);
        color3 = colors.get(3);
        color4 = colors.get(4);
        color5 = colors.get(5);

        //find out the object that will be assigned
        List<String> justinsObjects = new ArrayList<String>();
        justinsObjects.add("shoes" );
        justinsObjects.add("backpack");
        justinsObjects.add("fishing pole");
        justinsObjects.add("cooler");
        justinsObjects.add("car");


        Integer randomObject = (int)(Math.random() * ((5 - 1)));
        questionObject =  justinsObjects.get(randomObject);

        if (questionObject.equals("shoes")) {
            answerColor = color1;
        } else if (questionObject.equals("backpack")) {
            answerColor = color2;
        } else if (questionObject.equals("fishing pole")) {
            answerColor = color3;
        } else if (questionObject.equals("cooler")) {
            answerColor = color4;
        } else if (questionObject.equals("car")) {
            answerColor = color5;
        }

        QuestionTextView.setText("On his way out the door, Justin grabbed his "
                + color1 + " shoes, put on his " + color2 + " backpack , " +
                "and grabbed his " + color3 + " fishing pole, but he forgot to grab his " + color4 + " cooler," +
                " when he jumped into his " + color5 + " car");

        countDownTimer = new MyCountDownTimer(startTime, interval);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 200);
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if(view == Button1) {
            if (correctButton == 1) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button2) {
            if (correctButton == 2) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button3) {
            if (correctButton == 3) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button4) {
            if (correctButton == 4) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else if (view == BackArrow){
            Intent intent = new Intent(S2L5.this, StageTwoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == Replay){
            Intent intent = new Intent(S2L5.this, S2L5.class);
            startActivity(intent);
        } else if (view == Next){
            Intent intent = new Intent(S2L5.this, S2L6.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        }
    }

    private void onWrongAnswerTap() {
        Button1.setEnabled(false);
        Button2.setEnabled(false);
        Button3.setEnabled(false);
        showFailText();
    }

    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L5.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L5.this, R.anim.fadein);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);

                CheckXImageView.setImageResource(R.drawable.wrong_mark);
                CheckXImageView.setVisibility(View.VISIBLE);
                CheckXImageView.startAnimation(fade);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -500, 0);
                mAnimation1.setDuration(500);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);



            }
        }, 1000);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L5.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 2000);
    }

    private void onCorrectAnswerTap() {
        Button1.setEnabled(false);
        Button2.setEnabled(false);
        Button3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }



    private void addPointsToSharedPreference(Integer questionPoints) {
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        Integer oldTotalInt = Integer.parseInt(oldTotal);
        Integer newTotalInt = oldTotalInt + questionPoints;
        NumberOfLightbulbs.setText(String.valueOf(newTotalInt));
        mEditor.putString(Constants.LIGHTBULB_INTEGER_COUNT, newTotalInt.toString()).apply();
    }

    private void addClearToSharedPreference(String passed) {{
        mEditor.putString(Constants.S2LEVEL25COMPLETE, passed).apply();
    }
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L5.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L5.this, R.anim.fadein);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);
                CheckXImageView.setImageResource(R.drawable.check_correct);
                CheckXImageView.startAnimation(fade);
                CheckXImageView.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -500, 0);
                mAnimation1.setDuration(500);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);
                NumberOfLightbulbs.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500).start();

            }
        }, 1000);
        final Handler handler6 = new Handler();
        handler6.postDelayed(new Runnable() {
            @Override
            public void run() {
                NumberOfLightbulbs.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
                Integer questionPoints = 25;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L5.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);
    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            CountdownTimerTextView.setText("Time's up!");
            askQuestion();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownTimerTextView.setText("" + millisUntilFinished / 1000);
        }


        private void askQuestion() {
            getCorrectButtonColors();
            QuestionTextView.setVisibility(View.INVISIBLE);
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " +  questionObject + "?");
            ButtonLayout.setVisibility(View.VISIBLE);
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

            final Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                            0, -500);
                    mAnimation1.setDuration(500);

                    mAnimation1.setFillAfter(true);
                    AnswerQuestionTextView.startAnimation(mAnimation1);
                }
            },0);
        }
    }

    private void getCorrectButtonColors() {
        Integer randomCorrectButton = 1 + (int)(Math.random() * ((4 - 1) + 1));
        correctButton = randomCorrectButton;
        Log.d("debug", correctButton.toString());
        Log.d("dev", answerColor);
        Log.d("deb ", questionObject);

        if (correctButton == 1) {
            if (answerColor.equals("blue")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            } else if (answerColor.equals("yellow")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
            } else if (answerColor.equals("pink")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
            } else if (answerColor.equals("green")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
            } else if (answerColor.equals("white")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
            }

        } else if (correctButton == 2) {
            if (answerColor.equals("blue")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
            } else if (answerColor.equals("yellow")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            } else if (answerColor.equals("pink")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
            } else if (answerColor.equals("green")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
            } else if (answerColor.equals("white")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
            }

        } else if (correctButton == 3) {
            if (answerColor.equals("blue")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            } else if (answerColor.equals("yellow")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            } else if (answerColor.equals("pink")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            } else if (answerColor.equals("green")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
            } else if (answerColor.equals("white")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
            }
        } else if (correctButton == 4) {
            if (answerColor.equals("blue")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
            } else if (answerColor.equals("yellow")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
            } else if (answerColor.equals("pink")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
            } else if (answerColor.equals("green")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
            } else if (answerColor.equals("white")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorYellowish));
                Button4.setBackgroundColor(ContextCompat.getColor(S2L5.this, R.color.colorWhite));
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L5.this, StageOneActivity.class);
        startActivity(intent);
    }
}