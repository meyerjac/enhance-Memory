package jacksonmeyer.com.memoryenhancement.Stage1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S1L15 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
    RelativeLayout LightBulbRelativeLayout;
    @Bind(R.id.clothingRelativeLayout)
    RelativeLayout ColorRelativeLayout;

    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.next)
    TextView Next;
    @Bind(R.id.replay)
    TextView Replay;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id.checkXImageView)
    ImageView CheckXImageView;

    @Bind(R.id.colorOneImageView)
    ImageView ColorOneImageView;
    @Bind(R.id.colorTwoImageView)
    ImageView ColorTwoImageView;
    @Bind(R.id.colorThreeImageView)
    ImageView ColorThreeImageView;
    @Bind(R.id.colorFourImageView)
    ImageView ColorFourImageView;

    private CountDownTimer countDownTimer;
    private final long startTime = 4 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private Integer trackNumber= 0;
    private String answerColor ="" ;
    private String ifFirstColorClickIsGood = "no" ;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l15);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L15.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);


        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        ColorOneImageView.setOnClickListener(this);
        ColorTwoImageView.setOnClickListener(this);
        ColorThreeImageView.setOnClickListener(this);
        ColorFourImageView.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        setTrack();
        setColors();
        startTimer();
        startQuestion();

        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
    }

    private void setTrack() {
        trackNumber = 1 + (int) (Math.random() * ((6 - 1)));
        Log.d(TAG, "setTrack: " + trackNumber);
    }

    private void setColors() {
        if (trackNumber == 1) {
            answerColor = "blue";
            ColorOneImageView.setBackgroundResource(R.color.colorBlueish);
            ColorTwoImageView.setBackgroundResource(R.color.colorGreenish);
            ColorThreeImageView.setBackgroundResource(R.color.colorBlueish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 2) {
            answerColor = "yellow";
            ColorOneImageView.setBackgroundResource(R.color.colorYellowish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorPinkish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 3) {
            answerColor = "blue";
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorBlueish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 4) {
            answerColor = "pink";
            ColorOneImageView.setBackgroundResource(R.color.colorYellowish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorPinkish);
            ColorFourImageView.setBackgroundResource(R.color.colorPinkish);
        } else if (trackNumber == 5) {
            answerColor = "pink";
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorPinkish);
            ColorThreeImageView.setBackgroundResource(R.color.colorGreenish);
            ColorFourImageView.setBackgroundResource(R.color.colorBlueish);
        } else {

        }
    }
    private void startTimer() {
        countDownTimer = new MyCountDownTimer(startTime, interval);
        CountdownTimerTextView.setText("10");

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 0);
    }

    private void startQuestion() {
        showSequence();
    }

    private void showSequence() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColorOneImageView.setVisibility(View.VISIBLE);
                ColorTwoImageView.setVisibility(View.VISIBLE);
                ColorThreeImageView.setVisibility(View.VISIBLE);
                ColorFourImageView.setVisibility(View.VISIBLE);
                ColorRelativeLayout.setVisibility(View.VISIBLE);

            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColorOneImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorTwoImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorThreeImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorFourImageView.setBackgroundResource(R.color.colorLightGrayish);

                showButtons();
            }
        },3500);
    }

    private void showButtons() {
        askQuestion();
        CountdownTimerTextView.setText("Time's up!");

    }

    private void askQuestion() {
        AnswerQuestionTextView.setVisibility(View.VISIBLE);
        ColorOneImageView.setEnabled(true);
        ColorTwoImageView.setEnabled(true);
        ColorThreeImageView.setEnabled(true);
        ColorFourImageView.setEnabled(true);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        0, -300);
                mAnimation1.setDuration(400);
                AnswerQuestionTextView.startAnimation(mAnimation1);
                mAnimation1.setFillAfter(true);
            }
        },100);
    }



    //inefficient click funnel
    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == ColorOneImageView) {
            if (trackNumber == 1) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 2) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 3) {
                onWrongAnswerTap();
            } else if (trackNumber == 4) {
                onWrongAnswerTap();
            } else if (trackNumber == 5) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            }
        } else if (view == ColorTwoImageView) {
            if (trackNumber == 1) {
                onWrongAnswerTap();
            } else if (trackNumber == 2) {
                onWrongAnswerTap();
            } else if (trackNumber == 3) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 4) {
                onWrongAnswerTap();
            } else if (trackNumber == 5) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            }
        } else if (view == ColorThreeImageView) {
            if (trackNumber == 1) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 2) {
                onWrongAnswerTap();
            } else if (trackNumber == 3) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 4) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 5) {
                onWrongAnswerTap();
            }
        } else if (view == ColorFourImageView) {
            if (trackNumber == 1) {
                onWrongAnswerTap();
            } else if (trackNumber == 2) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 3) {
                onWrongAnswerTap();
            } else if (trackNumber == 4) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    onCorrectAnswerTap();
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 5) {
                onWrongAnswerTap();
            }
            } else if (view.equals(BackArrow)) {
                Intent intent = new Intent(S1L15.this, StageOneActivity.class);
                startActivity(intent);
            } else if (view.equals(Next)) {
                Intent intent = new Intent(S1L15.this, S1L16.class);
                startActivity(intent);
            } else if (view.equals(Replay)) {
                Intent intent = new Intent(S1L15.this, S1L15.class);
                startActivity(intent);
            } else {
                Log.d(TAG, "onClick: " + "wrong click");
            }

        }


    private void onCorrectAnswerTap() {
        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
        showFailText();
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownTimerTextView.setText("" + millisUntilFinished / 1000);
        }
    }


    //show what happens if the person answers the qustion wrong
    private void showFailText() {
        final Animation fade = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadein);
        final Animation fadeOut = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadeout);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                ColorRelativeLayout.startAnimation(fadeOut);
                ColorRelativeLayout.setVisibility(View.INVISIBLE);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);
                CheckXImageView.setImageResource(R.drawable.wrong_mark);
                CheckXImageView.setVisibility(View.VISIBLE);
                CheckXImageView.startAnimation(fade);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -300, 0);
                mAnimation1.setDuration(500);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);

            }
        }, 500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L15.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 2000);
    }

    private void addPointsToSharedPreference(Integer questionPoints) {
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        Integer oldTotalInt = Integer.parseInt(oldTotal);
        Integer newTotalInt = oldTotalInt + questionPoints;
        NumberOfLightbulbs.setText(String.valueOf(newTotalInt));
        mEditor.putString(Constants.LIGHTBULB_INTEGER_COUNT, newTotalInt.toString()).apply();
    }

    private void addClearToSharedPreference(String passed) {
        mEditor.putString(Constants.S1LEVEL15COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S1L15.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                ColorRelativeLayout.startAnimation(fadeOut);
                ColorRelativeLayout.setVisibility(View.INVISIBLE);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);
                CheckXImageView.setImageResource(R.drawable.check_correct);
                CheckXImageView.startAnimation(fade);
                CheckXImageView.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -300, 0);
                mAnimation1.setDuration(500);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);
                NumberOfLightbulbs.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500).start();

            }
        }, 1000);
        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                NumberOfLightbulbs.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start();
                Integer questionPoints = 10;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L15.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L15.this, StageOneActivity.class);
        startActivity(intent);
    }
}