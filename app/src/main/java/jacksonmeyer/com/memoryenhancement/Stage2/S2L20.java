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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageThreeActivity;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S2L20 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
    android.widget.RelativeLayout LightBulbRelativeLayout;
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
    @Bind(R.id.answer1)
    ImageView Answer1;
    @Bind(R.id.answer2)
    ImageView Answer2;
    @Bind(R.id.answer3)
    ImageView Answer3;
    @Bind(R.id.answer4)
    ImageView Answer4;

    @Bind(R.id.colorOneImageView)
    ImageView shownWeatherImageView;


    private CountDownTimer countDownTimer;
    private final long startTime = 6 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private ArrayList assignedFacePattern = new ArrayList();
    private ArrayList guessedFacePattern = new ArrayList();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l20);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L20.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);


        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
        Answer3.setOnClickListener(this);
        Answer4.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        setSequence();
        startTimer();
        startQuestion();

    }

    private void setSequence() {
        for (int i = 0; i < 6; i++) {
            Integer face = 1 + (int) (Math.random() * ((5 - 1)));
            if (face == 1) {
                assignedFacePattern.add("sad");
            } else if (face == 2) {
                assignedFacePattern.add("smile");
            } else if (face == 3) {
                assignedFacePattern.add("silly");
            } else {
                assignedFacePattern.add("cry");
            }
        }
        Log.d(TAG, "setSequence: " + assignedFacePattern.toString());
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
                if (assignedFacePattern.get(0).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(0).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(0).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                shownWeatherImageView.setVisibility(View.VISIBLE);
            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);

                if (assignedFacePattern.get(1).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(1).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(1).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shownWeatherImageView.setVisibility(View.VISIBLE);
                    }
                },300);
            }
        },1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);
                if (assignedFacePattern.get(2).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(2).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(2).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shownWeatherImageView.setVisibility(View.VISIBLE);
                    }
                },300);
            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);
                if (assignedFacePattern.get(3).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(3).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(3).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shownWeatherImageView.setVisibility(View.VISIBLE);
                    }
                },300);
            }
        },3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);
                if (assignedFacePattern.get(4).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(4).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(4).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shownWeatherImageView.setVisibility(View.VISIBLE);
                    }
                },300);
            }
        },4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);
                if (assignedFacePattern.get(5).equals("sad")) {
                    shownWeatherImageView.setImageResource(R.drawable.sadface);
                } else if (assignedFacePattern.get(5).equals("happy")) {
                    shownWeatherImageView.setImageResource(R.drawable.happyface);
                } else if (assignedFacePattern.get(5).equals("silly")){
                    shownWeatherImageView.setImageResource(R.drawable.tongueoutface);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.cryface);
                }
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shownWeatherImageView.setVisibility(View.VISIBLE);
                    }
                },300);
            }
        },5000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);
                showButtons();
            }
        },6000);
    }

    private void showButtons() {
        askQuestion();
        Animation fadeIn = AnimationUtils.loadAnimation(S2L20.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
        CountdownTimerTextView.setText("Time's up!");

    }

    private void askQuestion() {
        Log.d(TAG, "askQuestion: " + assignedFacePattern);
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


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            guessedFacePattern.add("silly");
            checkPatternLength();
        } else if (view == Answer2) {
            guessedFacePattern.add("sad");
            checkPatternLength();
        } else if (view == Answer3) {
            guessedFacePattern.add("smile");
            checkPatternLength();
        } else if (view == Answer4) {
            guessedFacePattern.add("cry");
            checkPatternLength();
        }else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, StageThreeActivity.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L20.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void checkPatternLength() {
        Log.d(TAG, "setSequence: " + assignedFacePattern.toString() + guessedFacePattern.toString());
        if (guessedFacePattern.size() == 6) {
            Answer1.setEnabled(false);
            Answer2.setEnabled(false);
            Answer3.setEnabled(false);
            Answer4.setEnabled(false);

            if ((guessedFacePattern.get(0).equals(assignedFacePattern.get(0))) &&
                    (guessedFacePattern.get(1).equals(assignedFacePattern.get(1))) &&
                    (guessedFacePattern.get(2).equals(assignedFacePattern.get(2))) &&
                    (guessedFacePattern.get(3).equals(assignedFacePattern.get(3))) &&
                    (guessedFacePattern.get(4).equals(assignedFacePattern.get(4))) &&
                    (guessedFacePattern.get(5).equals(assignedFacePattern.get(5)))) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else {
            Log.d(TAG, "checkPatternLength: " + "else");
        }

    }

    private void onCorrectAnswerTap() {
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
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
                Animation fade = AnimationUtils.loadAnimation(S2L20.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L20.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L20.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL40COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L20.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L20.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L20.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L20.this, StageTwoActivity.class);
        startActivity(intent);
    }
}