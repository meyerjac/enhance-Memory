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
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

public class S1L8 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.buttonLayout)
    android.widget.RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(R.id.next)
    TextView Next;
    @Bind(R.id.replay)
    TextView Replay;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;

    //"Buttons"
    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.checkXImageView)
    ImageView CheckXImageView;
    @Bind(R.id.answerStar1)
    ImageView AnswerStar1;
    @Bind(R.id.answerStar2)
    ImageView AnswerStar2;
    @Bind(R.id.answerStar3)
    ImageView AnswerStar3;

    //"these are the faces the run across the screen"
    @Bind(R.id.star1)
    ImageView Star1;
    @Bind(R.id.star2)
    ImageView Star2;
    @Bind(R.id.star3)
    ImageView Star3;
    @Bind(R.id.star4)
    ImageView Star4;

    private CountDownTimer countDownTimer;
    private final long startTime = 5 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";


    private Integer trackNumber = 0;
    private Integer correctButtonNumber = 0;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l8);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L8.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);


        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        AnswerStar1.setOnClickListener(this);
        AnswerStar2.setOnClickListener(this);
        AnswerStar3.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        startTimer();
        getTrack();
        startQuestion();
    }


    private void getTrack() {
        //random fruit numbers

        trackNumber = (1 + (int)(Math.random() * ((6 - 1))));
        Log.d(TAG, trackNumber.toString());
    }

    private void startTimer() {
        countDownTimer = new S1L8.MyCountDownTimer(startTime, interval);
        CountdownTimerTextView.setText("5");

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 0);
    }

    //MAKING ALL THE FACES MOVE ACCROSS THE SCREEN
    private void startQuestion() {
        if (trackNumber == 1) {
            Star1.setImageDrawable(getResources().getDrawable(R.drawable.star_1));
            Star2.setImageDrawable(getResources().getDrawable(R.drawable.star_2));
            Star3.setImageDrawable(getResources().getDrawable(R.drawable.star_3));
            Star4.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
        } else if (trackNumber == 2) {
            Star1.setImageDrawable(getResources().getDrawable(R.drawable.star_2));
            Star2.setImageDrawable(getResources().getDrawable(R.drawable.star_3));
            Star3.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
            Star4.setImageDrawable(getResources().getDrawable(R.drawable.star5));
        } else if (trackNumber == 3) {
            Star1.setImageDrawable(getResources().getDrawable(R.drawable.star_3));
            Star2.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
            Star3.setImageDrawable(getResources().getDrawable(R.drawable.star5));
            Star4.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
        } else if (trackNumber == 4) {
            Star1.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
            Star2.setImageDrawable(getResources().getDrawable(R.drawable.star5));
            Star3.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
            Star4.setImageDrawable(getResources().getDrawable(R.drawable.star_7));
        } else if (trackNumber == 5) {
            Star1.setImageDrawable(getResources().getDrawable(R.drawable.star5));
            Star2.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
            Star3.setImageDrawable(getResources().getDrawable(R.drawable.star_7));
            Star4.setImageDrawable(getResources().getDrawable(R.drawable.star_8));
        } else {

        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        1000, -3000);
                mAnimation1.setDuration(2000);

                mAnimation1.setFillAfter(true);
                Star1.startAnimation(mAnimation1);

            }
        },500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(-1000, 3000,
                        0, 0);
                mAnimation1.setDuration(2000);

                mAnimation1.setFillAfter(true);
                Star2.startAnimation(mAnimation1);
            }
        },1500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -1000, 3000);
                mAnimation1.setDuration(2000);

                mAnimation1.setFillAfter(true);
                Star3.startAnimation(mAnimation1);

            }
        },2500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -3000,
                        0, 0);
                mAnimation1.setDuration(2000);

                mAnimation1.setFillAfter(true);
                Star4.startAnimation(mAnimation1);

            }
        },3500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setCorrectButtons();

            }
        },4000);
    }



    private void setCorrectButtons() {
        if (trackNumber == 1) {
            AnswerStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_3));
            AnswerStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
            AnswerStar3.setImageDrawable(getResources().getDrawable(R.drawable.star5));
        } else  if (trackNumber == 2) {
            AnswerStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
            AnswerStar2.setImageDrawable(getResources().getDrawable(R.drawable.star5));
            AnswerStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
        } else  if (trackNumber == 3) {
            AnswerStar1.setImageDrawable(getResources().getDrawable(R.drawable.star5));
            AnswerStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
            AnswerStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_7));
        } else  if (trackNumber == 4) {
            AnswerStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_8));
            AnswerStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_7));
            AnswerStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
        } else  if (trackNumber == 5) {
            AnswerStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_6));
            AnswerStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_7));
            AnswerStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_4));
        } else {
            Log.d(TAG, "setCorrectButtons: " + "else");
        }

    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == AnswerStar1) {
            if ((trackNumber == 2) || (trackNumber == 4)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == AnswerStar2) {
            if (trackNumber == 5) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == AnswerStar3) {
            if ((trackNumber == 1) || (trackNumber == 3)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S1L9.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L8.class);
            startActivity(intent);
        }
        else {
            Log.d(TAG, "onClick: " + "wrong click");
        }
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
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

            Animation fadeIn = AnimationUtils.loadAnimation(S1L8.this, R.anim.fadein);
            ButtonLayout.startAnimation(fadeIn);
            ButtonLayout.setVisibility(View.VISIBLE);

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


    //SHOW WHAT HAPPENS IF THEY ANSWER THE QUESTION WRONG, NO CHANGE NEEDED BELOW
    private void onCorrectAnswerTap() {
        AnswerStar1.setEnabled(false);
        AnswerStar2.setEnabled(false);
        AnswerStar3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        AnswerStar1.setEnabled(false);
        AnswerStar2.setEnabled(false);
        AnswerStar3.setEnabled(false);
        showFailText();
    }


    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L8.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L8.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L8.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL8COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L8.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L8.this, R.anim.fadein);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L8.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L8.this, StageOneActivity.class);
        startActivity(intent);
    }
}
