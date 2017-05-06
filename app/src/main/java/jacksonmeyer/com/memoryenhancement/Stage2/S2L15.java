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
import android.widget.TableRow;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S2L15 extends AppCompatActivity implements View.OnClickListener {
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

    @Bind(R.id.first_row)
    TableRow FirstRow;
    @Bind(R.id.second_row)
    TableRow SecondRow;
    @Bind(R.id.third_row)
    TableRow ThirdRow;


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

    @Bind(R.id.colorFiveImageView)
    ImageView ColorFiveImageView;
    @Bind(R.id.colorSixImageView)
    ImageView ColorSixImageView;
    @Bind(R.id.colorSevenImageView)
    ImageView ColorSevenImageView;
    @Bind(R.id.colorEightImageView)
    ImageView ColorEightImageView;

    @Bind(R.id.colorNineImageView)
    ImageView ColorNineImageView;
    @Bind(R.id.colorTenImageView)
    ImageView ColorTenImageView;
    @Bind(R.id.colorElevenImageView)
    ImageView ColorElevenImageView;
    @Bind(R.id.colorTwelveImageView)
    ImageView ColorTwelveImageView;

    private CountDownTimer countDownTimer;
    private final long startTime = 4 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private Integer trackNumber= 0;
    private Integer numberOfClicks= 0;
    private String answerColor ="" ;
    private String ifFirstColorClickIsGood = "no" ;
    private String ifSecondColorClickIsGood = "no" ;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l15);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L15.this);
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
        ColorSevenImageView.setOnClickListener(this);
        ColorEightImageView.setOnClickListener(this);
        ColorNineImageView.setOnClickListener(this);
        ColorTenImageView.setOnClickListener(this);
        ColorElevenImageView.setOnClickListener(this);
        ColorTwelveImageView.setOnClickListener(this);
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
        ColorFiveImageView.setEnabled(false);
        ColorSixImageView.setEnabled(false);
        ColorSevenImageView.setEnabled(false);
        ColorEightImageView.setEnabled(false);
        ColorNineImageView.setEnabled(false);
        ColorTenImageView.setEnabled(false);
        ColorElevenImageView.setEnabled(false);
        ColorTwelveImageView.setEnabled(false);
    }

    private void setTrack() {
        trackNumber = 1 + (int) (Math.random() * ((6 - 1)));
        Log.d(TAG, "setTrack: " + trackNumber);
    }

    private void setColors() {
        if (trackNumber == 1) {
            answerColor = "yellow";
            ColorOneImageView.setBackgroundResource(R.color.colorBlueish);
            ColorTwoImageView.setBackgroundResource(R.color.colorGreenish);
            ColorThreeImageView.setBackgroundResource(R.color.colorRed);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorWhite);
            ColorSixImageView.setBackgroundResource(R.color.colorPinkish);
            ColorSevenImageView.setBackgroundResource(R.color.colorBlueish);
            ColorEightImageView.setBackgroundResource(R.color.colorYellowish);
            ColorNineImageView.setBackgroundResource(R.color.colorRed);
            ColorTenImageView.setBackgroundResource(R.color.colorGreenish);
            ColorElevenImageView.setBackgroundResource(R.color.colorWhite);
            ColorTwelveImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 2) {
            answerColor = "yellow";
            ColorOneImageView.setBackgroundResource(R.color.colorYellowish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorPinkish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorWhite);
            ColorSixImageView.setBackgroundResource(R.color.colorGreenish);
            ColorSevenImageView.setBackgroundResource(R.color.colorGreenish);
            ColorEightImageView.setBackgroundResource(R.color.colorWhite);
            ColorNineImageView.setBackgroundResource(R.color.colorRed);
            ColorTenImageView.setBackgroundResource(R.color.colorRed);
            ColorElevenImageView.setBackgroundResource(R.color.colorYellowish);
            ColorTwelveImageView.setBackgroundResource(R.color.colorBlueish);
        } else if (trackNumber == 3) {
            answerColor = "blue";
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorBlueish);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorGreenish);
            ColorSixImageView.setBackgroundResource(R.color.colorWhite);
            ColorSevenImageView.setBackgroundResource(R.color.colorRed);
            ColorEightImageView.setBackgroundResource(R.color.colorBlueish);
            ColorNineImageView.setBackgroundResource(R.color.colorWhite);
            ColorTenImageView.setBackgroundResource(R.color.colorRed);
            ColorElevenImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwelveImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 4) {
            answerColor = "green";
            ColorOneImageView.setBackgroundResource(R.color.colorGreenish);
            ColorTwoImageView.setBackgroundResource(R.color.colorBlueish);
            ColorThreeImageView.setBackgroundResource(R.color.colorPinkish);
            ColorFourImageView.setBackgroundResource(R.color.colorPinkish);
            ColorFiveImageView.setBackgroundResource(R.color.colorWhite);
            ColorSixImageView.setBackgroundResource(R.color.colorGreenish);
            ColorSevenImageView.setBackgroundResource(R.color.colorWhite);
            ColorEightImageView.setBackgroundResource(R.color.colorYellowish);
            ColorNineImageView.setBackgroundResource(R.color.colorRed);
            ColorTenImageView.setBackgroundResource(R.color.colorRed);
            ColorElevenImageView.setBackgroundResource(R.color.colorGreenish);
            ColorTwelveImageView.setBackgroundResource(R.color.colorYellowish);
        } else if (trackNumber == 5) {
            answerColor = "white";
            ColorOneImageView.setBackgroundResource(R.color.colorPinkish);
            ColorTwoImageView.setBackgroundResource(R.color.colorGreenish);
            ColorThreeImageView.setBackgroundResource(R.color.colorWhite);
            ColorFourImageView.setBackgroundResource(R.color.colorYellowish);
            ColorFiveImageView.setBackgroundResource(R.color.colorPinkish);
            ColorSixImageView.setBackgroundResource(R.color.colorWhite);
            ColorSevenImageView.setBackgroundResource(R.color.colorBlueish);
            ColorEightImageView.setBackgroundResource(R.color.colorYellowish);
            ColorNineImageView.setBackgroundResource(R.color.colorBlueish);
            ColorTenImageView.setBackgroundResource(R.color.colorGreenish);
            ColorElevenImageView.setBackgroundResource(R.color.colorRed);
            ColorTwelveImageView.setBackgroundResource(R.color.colorWhite);
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
                FirstRow.setVisibility(View.VISIBLE);

            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SecondRow.setVisibility(View.VISIBLE);

            }
        },1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ThirdRow.setVisibility(View.VISIBLE);

            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColorOneImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorTwoImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorThreeImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorFourImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorFiveImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorSixImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorSevenImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorEightImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorNineImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorTenImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorElevenImageView.setBackgroundResource(R.color.colorLightGrayish);
                ColorTwelveImageView.setBackgroundResource(R.color.colorLightGrayish);

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
        ColorFiveImageView.setEnabled(true);
        ColorSixImageView.setEnabled(true);
        ColorSevenImageView.setEnabled(true);
        ColorEightImageView.setEnabled(true);
        ColorNineImageView.setEnabled(true);
        ColorTenImageView.setEnabled(true);
        ColorElevenImageView.setEnabled(true);
        ColorTwelveImageView.setEnabled(true);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        0, -100);
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
            numberOfClicks += 1;
            if (trackNumber == 1) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 3) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 4) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 5) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorTwoImageView) {
            numberOfClicks += 1;
            if (trackNumber == 3) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorThreeImageView) {
            numberOfClicks += 1;
            if (trackNumber == 1) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 2) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 3) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 4) {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            } else if (trackNumber == 5) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            }
        } else if (view == ColorFourImageView) {
            numberOfClicks += 1;
            if (trackNumber == 1) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 2) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorFiveImageView) {
            numberOfClicks += 1;

            if (numberOfClicks == 3) {
                onWrongAnswerTap();
            }
        } else if (view == ColorSixImageView) {
            numberOfClicks += 1;
            if (trackNumber == 4) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 5) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifFirstColorClickIsGood.equals("yes")) {
                        if (ifSecondColorClickIsGood.equals("yes")) {
                            onCorrectAnswerTap();
                        } else {
                            ifSecondColorClickIsGood = "yes";
                        }
                    } else {
                        ifFirstColorClickIsGood = "yes";
                    }
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorSevenImageView) {
            numberOfClicks += 1;

            if (numberOfClicks == 3) {
                onWrongAnswerTap();
            }
        } else if (view == ColorEightImageView) {
            numberOfClicks += 1;
            if (trackNumber == 1) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 3) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorNineImageView) {
            numberOfClicks += 1;

            if (numberOfClicks == 3) {
                onWrongAnswerTap();
            }
        } else if (view == ColorTenImageView) {
            numberOfClicks += 1;

            if (numberOfClicks == 3) {
                onWrongAnswerTap();
            }
        } else if (view == ColorElevenImageView) {
            numberOfClicks += 1;
            if (trackNumber == 2) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if (trackNumber == 4) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view == ColorTwelveImageView) {
            numberOfClicks += 1;
            if (trackNumber == 1) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else if  (trackNumber == 5) {
                if (ifFirstColorClickIsGood.equals("yes")) {
                    if (ifSecondColorClickIsGood.equals("yes")) {
                        onCorrectAnswerTap();
                    } else {
                        ifSecondColorClickIsGood = "yes";
                    }
                } else {
                    ifFirstColorClickIsGood = "yes";
                }
            } else {
                if (numberOfClicks == 3) {
                    onWrongAnswerTap();
                }
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(S2L15.this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(S2L15.this, S2L16.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(S2L15.this, S2L15.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void onCorrectAnswerTap() {
        disableButtons();
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void disableButtons() {
        ColorOneImageView.setEnabled(false);
        ColorTwoImageView.setEnabled(false);
        ColorThreeImageView.setEnabled(false);
        ColorFourImageView.setEnabled(false);
        ColorFiveImageView.setEnabled(false);
        ColorSixImageView.setEnabled(false);
        ColorSevenImageView.setEnabled(false);
        ColorEightImageView.setEnabled(false);
        ColorNineImageView.setEnabled(false);
        ColorTenImageView.setEnabled(false);
        ColorElevenImageView.setEnabled(false);
        ColorTwelveImageView.setEnabled(false);
    }

    private void onWrongAnswerTap() {
        disableButtons();
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
        final Animation fade = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadein);
        final Animation fadeOut = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadeout);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadeout);
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
                        -100, 0);
                mAnimation1.setDuration(500);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);

            }
        }, 500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L15.this, R.anim.bounce);
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
                Animation fade = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S2L15.this, R.anim.fadeout);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L15.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L15.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
