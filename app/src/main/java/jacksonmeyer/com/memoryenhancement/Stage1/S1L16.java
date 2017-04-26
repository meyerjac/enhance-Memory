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

public class S1L16 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;

    @Bind(R.id.answer1)
    ImageView Button1;
    @Bind(R.id.answer2)
    ImageView Button2;
    @Bind(R.id.answer3)
    ImageView Button3;
    @Bind(R.id.weatherImageView1)
    ImageView WeatherImageView1;
    @Bind(R.id.weatherImageView2)
    ImageView WeatherImageView2;
    @Bind(R.id.weatherImageView3)
    ImageView WeatherImageView3;

    @Bind(R.id.buttonLayout)
    RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    RelativeLayout RelativeLayout;
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
    @Bind(R.id.checkXImageView)
    ImageView CheckXImageView;

    @Bind(R.id.weatherLayout)
    RelativeLayout WeatherLayout;

    private CountDownTimer countDownTimer;
    private final long startTime = 7 * 1000;
    private final long interval = 1000;
    private Integer correctButton = null;
    private Integer trackNumber = 0;
    private String ObjectDay = "";


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l16);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L16.this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);
        WeatherLayout.setVisibility(View.INVISIBLE);


        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        startTimer();
        getTrackNumber();
        startQuestion();
    }


    private void getTrackNumber() {
        trackNumber = 1 + (int) (Math.random() * ((6 - 1)));
        Log.d("yo", "getAnswerObject: " + trackNumber.toString());
    }

    private void startTimer() {
        countDownTimer = new S1L16.MyCountDownTimer(startTime, interval);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 0);
    }

    private void startQuestion() {
        final Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (trackNumber == 1) {
                    ObjectDay = "Monday";
                    WeatherImageView1.setImageResource(R.drawable.sunny);
                    WeatherImageView2.setImageResource(R.drawable.partly_cloudy);
                    WeatherImageView3.setImageResource(R.drawable.rainy);
                } else if (trackNumber == 2) {
                    ObjectDay = "Monday";
                    WeatherImageView1.setImageResource(R.drawable.cloudy);
                    WeatherImageView2.setImageResource(R.drawable.rainy);
                    WeatherImageView3.setImageResource(R.drawable.snowing);
                } else if (trackNumber == 3) {
                    ObjectDay = "Tuesday";
                    WeatherImageView1.setImageResource(R.drawable.partly_cloudy);
                    WeatherImageView2.setImageResource(R.drawable.cloudy);
                    WeatherImageView3.setImageResource(R.drawable.snowing);
                } else if (trackNumber == 4) {
                    ObjectDay = "Wednesday";
                    WeatherImageView1.setImageResource(R.drawable.partly_cloudy);
                    WeatherImageView2.setImageResource(R.drawable.thunderstorms);
                    WeatherImageView3.setImageResource(R.drawable.snowing);
                } else if (trackNumber == 5) {
                    ObjectDay = "Tuesday";
                    WeatherImageView1.setImageResource(R.drawable.sunny);
                    WeatherImageView2.setImageResource(R.drawable.thunderstorms);
                    WeatherImageView3.setImageResource(R.drawable.snowing);
                } else {
                }
                WeatherLayout.setVisibility(View.VISIBLE);
                WeatherLayout.startAnimation(fadeIn);

            }
        }, 0);
    }
    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Button1) {
            if (trackNumber == 4) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button2) {
            if (trackNumber == 2) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button3) {
            if (trackNumber == 1 || trackNumber == 3 || trackNumber == 5) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == BackArrow) {
            Intent intent = new Intent(S1L16.this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == Replay) {
            Intent intent = new Intent(S1L16.this, S1L16.class);
            startActivity(intent);
        } else if (view == Next) {
            Intent intent = new Intent(S1L16.this, S1L17.class);
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


    private void onCorrectAnswerTap() {
        Button1.setEnabled(false);
        Button2.setEnabled(false);
        Button3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }


    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L16.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
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
        {
            mEditor.putString(Constants.S1LEVEL16COMPLETE, passed).apply();
        }
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadein);
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
                Integer questionPoints = 10;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L16.this, R.anim.bounce);
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
            WeatherLayout.setVisibility(View.INVISIBLE);

            setCorrectButtonImages();
            askQuestion();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownTimerTextView.setText("" + millisUntilFinished / 1000);
        }


        private void askQuestion() {
            Animation fade = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadein);
            Animation fadeOut = AnimationUtils.loadAnimation(S1L16.this, R.anim.fadeout);

            WeatherLayout.setVisibility(View.INVISIBLE);
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + ObjectDay + "?");
            ButtonLayout.setVisibility(View.VISIBLE);
            ButtonLayout.startAnimation(fade);
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
            }, 0);
        }
    }

    private void setCorrectButtonImages() {
        if (trackNumber == 1) {
            Button1.setImageResource(R.drawable.partly_cloudy);
            Button2.setImageResource(R.drawable.rainy);
            Button3.setImageResource(R.drawable.sunny);
        } else if (trackNumber == 2) {
            Button1.setImageResource(R.drawable.rainy);
            Button2.setImageResource(R.drawable.cloudy);
            Button3.setImageResource(R.drawable.snowing);
        } else if (trackNumber == 3) {
            Button1.setImageResource(R.drawable.snowing);
            Button2.setImageResource(R.drawable.partly_cloudy);
            Button3.setImageResource(R.drawable.cloudy);
        } else if (trackNumber == 4) {
            Button1.setImageResource(R.drawable.snowing);
            Button2.setImageResource(R.drawable.partly_cloudy);
            Button3.setImageResource(R.drawable.thunderstorms);
        } else {
            Button1.setImageResource(R.drawable.snowing);
            Button2.setImageResource(R.drawable.sunny);
            Button3.setImageResource(R.drawable.thunderstorms);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, StageOneActivity.class);
        startActivity(intent);
    }
}