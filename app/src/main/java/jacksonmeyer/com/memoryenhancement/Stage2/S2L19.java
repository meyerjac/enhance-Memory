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
import android.widget.TableLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S2L19 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
    RelativeLayout LightBulbRelativeLayout;
    @Bind(R.id.clothingRelativeLayout)
    RelativeLayout ClothingRelativeLayout;
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;

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
    ImageView ClothingOneImageView;
    @Bind(R.id.clothingTwoImageView)
    ImageView ClothingTwoImageView;
    @Bind(R.id.clothingThreeImageView)
    ImageView ClothingThreeImageView;
    @Bind(R.id.clothingFourImageView)
    ImageView ClothingFourImageView;
    @Bind(R.id.clothingFiveImageView)
    ImageView ClothingFiveImageView;
    @Bind(R.id.clothingSixImageView)
    ImageView ClothingSixImageView;
    @Bind(R.id.answer1)
    ImageView Answer1;
    @Bind(R.id.answer2)
    ImageView Answer2;
    @Bind(R.id.answer3)
    ImageView Answer3;
    @Bind(R.id.answer4)
    ImageView Answer4;

    private CountDownTimer countDownTimer;
    private final long startTime = 7 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private Integer trackNumber= 0;
    private String answerColor ="" ;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l19);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L19.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);


        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
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

        setTrack();
        startTimer();
        startQuestion();
    }


    private void setTrack() {
        trackNumber = 1 + (int) (Math.random() * ((8 - 1)));
        setClothes();
    }

    private void setClothes() {
        if (trackNumber == 1) {
            ClothingOneImageView.setImageResource(R.drawable.bra);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.hat);
            ClothingFourImageView.setImageResource(R.drawable.shirt);
            ClothingFiveImageView.setImageResource(R.drawable.pants);
            ClothingSixImageView.setImageResource(R.drawable.gloves);
        } else if (trackNumber == 2) {
            ClothingOneImageView.setImageResource(R.drawable.hat);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.pants);
            ClothingFourImageView.setImageResource(R.drawable.shirt);
            ClothingFiveImageView.setImageResource(R.drawable.bra);
            ClothingSixImageView.setImageResource(R.drawable.sock);
        } else if (trackNumber == 3) {
            ClothingOneImageView.setImageResource(R.drawable.gloves);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.pants);
            ClothingFourImageView.setImageResource(R.drawable.sock);
            ClothingFiveImageView.setImageResource(R.drawable.bra);
            ClothingSixImageView.setImageResource(R.drawable.hat);
        } else if (trackNumber == 4) {
            ClothingOneImageView.setImageResource(R.drawable.sock);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.gloves);
            ClothingFourImageView.setImageResource(R.drawable.shirt);
            ClothingFiveImageView.setImageResource(R.drawable.pants);
            ClothingSixImageView.setImageResource(R.drawable.shoe);
        } else if (trackNumber == 5) {
            ClothingOneImageView.setImageResource(R.drawable.shirt);
            ClothingThreeImageView.setImageResource(R.drawable.gloves);
            ClothingTwoImageView.setImageResource(R.drawable.sock);
            ClothingFourImageView.setImageResource(R.drawable.hat);
            ClothingFiveImageView.setImageResource(R.drawable.pants);
            ClothingSixImageView.setImageResource(R.drawable.shoe);
        } else if (trackNumber == 6) {
            ClothingOneImageView.setImageResource(R.drawable.sock);
            ClothingThreeImageView.setImageResource(R.drawable.pants);
            ClothingTwoImageView.setImageResource(R.drawable.shirt);
            ClothingFourImageView.setImageResource(R.drawable.hat);
            ClothingFiveImageView.setImageResource(R.drawable.panties);
            ClothingSixImageView.setImageResource(R.drawable.gloves);
        } else if (trackNumber == 7) {
            ClothingOneImageView.setImageResource(R.drawable.pants);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.gloves);
            ClothingFourImageView.setImageResource(R.drawable.hat);
            ClothingFiveImageView.setImageResource(R.drawable.shirt);
            ClothingSixImageView.setImageResource(R.drawable.shoe);
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
        ClothingRelativeLayout.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingOneImageView.startAnimation(mAnimation1);

            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingTwoImageView.startAnimation(mAnimation1);

            }
        },1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingThreeImageView.startAnimation(mAnimation1);

            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingFourImageView.startAnimation(mAnimation1);

            }
        },3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingFiveImageView.startAnimation(mAnimation1);

            }
        },4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(2500);
                mAnimation1.setFillAfter(true);
                ClothingSixImageView.startAnimation(mAnimation1);

            }
        },5000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showButtons();
            }
        },7000);
    }

    private void showButtons() {
        Animation fadeIn = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadein);
        if ((trackNumber ==  1) || (trackNumber ==  3) || (trackNumber ==  6)) {
            Answer1.setImageResource(R.drawable.gloves);
            Answer2.setImageResource(R.drawable.panties);
            Answer3.setImageResource(R.drawable.hat);
            Answer4.setImageResource(R.drawable.shoe);
        } else if ((trackNumber ==  2)) {
            Answer1.setImageResource(R.drawable.sock);
            Answer2.setImageResource(R.drawable.gloves);
            Answer3.setImageResource(R.drawable.bra);
            Answer4.setImageResource(R.drawable.hat);
        } else if ((trackNumber ==  4)) {
            Answer1.setImageResource(R.drawable.shirt);
            Answer2.setImageResource(R.drawable.panties);
            Answer3.setImageResource(R.drawable.hat);
            Answer4.setImageResource(R.drawable.pants);
        } else if (trackNumber == 5) {
            Answer1.setImageResource(R.drawable.panties);
            Answer2.setImageResource(R.drawable.shoe);
            Answer3.setImageResource(R.drawable.pants);
            Answer4.setImageResource(R.drawable.hat);
        } else if (trackNumber == 7) {
            Answer1.setImageResource(R.drawable.panties);
            Answer2.setImageResource(R.drawable.sock);
            Answer3.setImageResource(R.drawable.gloves);
            Answer4.setImageResource(R.drawable.hat);
        }

        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
        askQuestion();
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



    //inefienct click funnel
    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            if (trackNumber == 5) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else if (view == Answer2) {
            if ((trackNumber == 2) || (trackNumber == 7)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer3) {
            if (trackNumber == 4) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer4) {
            if ((trackNumber == 1) || (trackNumber == 3) || (trackNumber == 6)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L20.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L19.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void onCorrectAnswerTap() {
        Answer1.setEnabled(false);
        Answer2.setEnabled(false);
        Answer3.setEnabled(false);
        Answer4.setEnabled(false);

        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        Answer1.setEnabled(false);
        Answer2.setEnabled(false);
        Answer3.setEnabled(false);
        Answer4.setEnabled(false);
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
                Animation fade = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);

                CheckXImageView.setImageResource(R.drawable.wrong_mark);
                CheckXImageView.setVisibility(View.VISIBLE);
                CheckXImageView.startAnimation(fade);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -200, 200);
                mAnimation1.setDuration(1000);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);

            }
        }, 700);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L19.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL39COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S2L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);
                CheckXImageView.setImageResource(R.drawable.check_correct);
                CheckXImageView.startAnimation(fade);
                CheckXImageView.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -500, 1500);
                mAnimation1.setDuration(1000);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L19.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L19.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
