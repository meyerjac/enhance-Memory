package jacksonmeyer.com.memoryenhancement.Stage2;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S2L13 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
    android.widget.RelativeLayout LightBulbRelativeLayout;
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
    @Bind(R.id. checkXImageView)
    ImageView CheckXImageView;

    @Bind(R.id.colorOneImageView)
    ImageView ColorOneImageView;
    @Bind(R.id.colorTwoImageView)
    ImageView ColorTwoImageView;
    @Bind(R.id.colorThreeImageView)
    ImageView ColorThreeImageView;
    @Bind(R.id.colorFourImageView)
    ImageView ColorFourImageView;
    @Bind(R.id.colorFiveImageView)
    ImageView ColorFiveImageView;
    @Bind(R.id.colorSixImageView)
    ImageView ColorSixImageView;

    private CountDownTimer countDownTimer;
    private final long startTime = 5 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private Integer trackNumber= 0;
    private String answerColor ="" ;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l13);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L13.this);
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
        ColorFiveImageView.setOnClickListener(this);
        ColorSixImageView.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        setTrack();
        setColors();
        startTimer();
        setAnswerColor();
        startQuestion();

        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
        ColorFiveImageView.setEnabled(false);
        ColorSixImageView.setEnabled(false);
    }

    private void setAnswerColor() {
        List<String> colors = new ArrayList<String>();
        colors.add("pink");
        colors.add("blue" );
        colors.add("green");
        colors.add("Yellow");
        colors.add("red");
        colors.add("white");

        Integer arrayNumber =  (int)(Math.random() * ((6 - 1)));
        answerColor = colors.get(arrayNumber);
    }


    private void setTrack() {
        trackNumber = 1 + (int) (Math.random() * ((4 - 1)));
        Log.d(TAG, "setTrack: " + trackNumber);
    }

    private void setColors() {
        if (trackNumber == 1) {
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorGreenish);
            ColorThreeImageView.setBackgroundResource(R.color.colorBlueish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorRed);
            ColorSixImageView.setBackgroundResource(R.color.colorWhite);
        } else if (trackNumber == 2) {
            ColorOneImageView.setBackgroundResource(R.color.colorYellowish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorRed);
            ColorFourImageView.setBackgroundResource(R.color.colorWhite);
            ColorFiveImageView.setBackgroundResource(R.color.colorPinkish);
            ColorSixImageView.setBackgroundResource(R.color.colorGreenish);
        } else if (trackNumber == 3) {
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorGreenish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorWhite);
            ColorSixImageView.setBackgroundResource(R.color.colorRed);
        } else {

        }
    }
    private void startTimer() {
        countDownTimer = new MyCountDownTimer(startTime, interval);
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
                ColorFiveImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorSixImageView.setBackgroundResource(R.color.colorLightGrayish);
            }
        },4800);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showButtons();
            }
        },5000);
    }

    private void showButtons() {
        askQuestion();
        CountdownTimerTextView.setText("Time's up!");

    }

    private void askQuestion() {
        AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + answerColor + " square was!" );
        AnswerQuestionTextView.setVisibility(View.VISIBLE);
        ColorOneImageView.setEnabled(true);
        ColorTwoImageView.setEnabled(true);
        ColorThreeImageView.setEnabled(true);
        ColorFourImageView.setEnabled(true);
        ColorFiveImageView.setEnabled(true);
        ColorSixImageView.setEnabled(true);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        0, -400);
                mAnimation1.setDuration(500);

                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);
            }
        },0);
    }

    //inefienct click funnel
    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == ColorOneImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("pink")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("yellow")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("pink")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorTwoImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("green")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("blue")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("blue")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorThreeImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("blue")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("red")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("green")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorFourImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("yellow")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("white")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("yellow")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorFiveImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("red")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("pink")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("white")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorSixImageView) {
            if (trackNumber == 1) {
                if (answerColor.equals("white")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (answerColor.equals("green")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (answerColor.equals("red")) {
                    onCorrectAnswerTap();
                } else {
                    onWrongAnswerTap();
                }
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L14.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L13.class);
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
        ColorFiveImageView.setEnabled(false);
        ColorSixImageView.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
        ColorFiveImageView.setEnabled(false);
        ColorSixImageView.setEnabled(false);
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
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                ColorRelativeLayout.startAnimation(fadeOut);
                ColorRelativeLayout.setVisibility(View.INVISIBLE);
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
        }, 700);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L13.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL33COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S2L13.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                ColorRelativeLayout.startAnimation(fadeOut);
                ColorRelativeLayout.setVisibility(View.INVISIBLE);
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
        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L13.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L13.this, StageTwoActivity.class);
        startActivity(intent);
    }
}

