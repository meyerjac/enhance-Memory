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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S1L17 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.buttonLayout)
    android.widget.RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
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
    @Bind(R.id.answer1)
    ImageView Answer1;
    @Bind(R.id.answer2)
    ImageView Answer2;
    @Bind(R.id.answer3)
    ImageView Answer3;

    @Bind(R.id.colorOneImageView)
    ImageView shownWeatherImageView;


    private CountDownTimer countDownTimer;
    private final long startTime = 5 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private InterstitialAd mInterstitial;
    private ArrayList assignedWeatherPattern = new ArrayList();
    private ArrayList guessedWeatherPattern = new ArrayList();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l17);
        ButterKnife.bind(this);

        //loadAd
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-6644231159782645/2453348819");
        AdRequest request = new AdRequest.Builder()
                .addTestDevice("A449F2C6C55C7DC233B43DA5E09FD24C")
                .build();
        mInterstitial.loadAd(request);

        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(S1L17.this, S1L18.class);
                startActivity(intent);
            }
        });




        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L17.this);
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
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        setSequence();
        startTimer();
        startQuestion();

    }

    private void setSequence() {
        for (int i = 0; i < 4; i++) {
            Integer weather = 1 + (int) (Math.random() * ((4 - 1)));
            if (weather == 1) {
                assignedWeatherPattern.add("rain");
            } else if (weather == 2) {
                assignedWeatherPattern.add("sun");
            } else {
                assignedWeatherPattern.add("thunder");
            }
        }
        Log.d(TAG, "setSequence: " + assignedWeatherPattern.toString());
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
                if (assignedWeatherPattern.get(0).equals("rain")) {
                    shownWeatherImageView.setImageResource(R.drawable.rainy);
                } else if (assignedWeatherPattern.get(0).equals("sun")) {
                    shownWeatherImageView.setImageResource(R.drawable.sunny);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.thunderstorms);
                }
                shownWeatherImageView.setVisibility(View.VISIBLE);
            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shownWeatherImageView.setVisibility(View.INVISIBLE);

                if (assignedWeatherPattern.get(1).equals("rain")) {
                    shownWeatherImageView.setImageResource(R.drawable.rainy);
                } else if (assignedWeatherPattern.get(1).equals("sun")) {
                    shownWeatherImageView.setImageResource(R.drawable.sunny);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.thunderstorms);
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
                if (assignedWeatherPattern.get(2).equals("rain")) {
                    shownWeatherImageView.setImageResource(R.drawable.rainy);
                } else if (assignedWeatherPattern.get(2).equals("sun")) {
                    shownWeatherImageView.setImageResource(R.drawable.sunny);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.thunderstorms);
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
                if (assignedWeatherPattern.get(3).equals("rain")) {
                    shownWeatherImageView.setImageResource(R.drawable.rainy);
                } else if (assignedWeatherPattern.get(3).equals("sun")) {
                    shownWeatherImageView.setImageResource(R.drawable.sunny);
                } else {
                    shownWeatherImageView.setImageResource(R.drawable.thunderstorms);
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
                showButtons();
            }
        },4000);
    }

    private void showButtons() {
        askQuestion();
        Animation fadeIn = AnimationUtils.loadAnimation(S1L17.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
        CountdownTimerTextView.setText("Time's up!");

    }

    private void askQuestion() {
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
            guessedWeatherPattern.add("rain");
            checkPatternLength();
        } else if (view == Answer2) {
            guessedWeatherPattern.add("sun");
            checkPatternLength();
        } else if (view == Answer3) {
            guessedWeatherPattern.add("thunder");
            checkPatternLength();
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            if(mInterstitial.isLoaded()) {
                mInterstitial.show();
            } else {
                Intent intent = new Intent(this, S1L18.class);
                startActivity(intent);
            }
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L17.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void checkPatternLength() {
        if (guessedWeatherPattern.size() == 4) {
            Answer1.setEnabled(false);
            Answer2.setEnabled(false);
            Answer3.setEnabled(false);

            if ((guessedWeatherPattern.get(0).equals(assignedWeatherPattern.get(0))) &&
            (guessedWeatherPattern.get(1).equals(assignedWeatherPattern.get(1))) &&
                    (guessedWeatherPattern.get(2).equals(assignedWeatherPattern.get(2))) &&
                    (guessedWeatherPattern.get(3).equals(assignedWeatherPattern.get(3)))) {
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
                Animation fade = AnimationUtils.loadAnimation(S1L17.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L17.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L17.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL17COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L17.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L17.this, R.anim.fadein);
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
                Integer questionPoints = 10;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L17.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L17.this, StageOneActivity.class);
        startActivity(intent);
    }
}
