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

public class S1L19 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.relativeLayout)
    RelativeLayout RelativeLayout;
    @Bind(lightBulbRelativeLayout)
    RelativeLayout LightBulbRelativeLayout;
    @Bind(R.id.clothingRelativeLayout)
    RelativeLayout ClothingRelativeLayout;
    @Bind(R.id.buttonLayout)
    RelativeLayout ButtonLayout;

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
    @Bind(R.id.answer1)
    ImageView Answer1;
    @Bind(R.id.answer2)
    ImageView Answer2;
    @Bind(R.id.answer3)
    ImageView Answer3;

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
        setContentView(R.layout.activity_s1_l19);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L19.this);
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
        } else if (trackNumber == 2) {
            ClothingOneImageView.setImageResource(R.drawable.hat);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.pants);
            ClothingFourImageView.setImageResource(R.drawable.shirt);
        } else if (trackNumber == 3) {
            ClothingOneImageView.setImageResource(R.drawable.gloves);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.pants);
            ClothingFourImageView.setImageResource(R.drawable.sock);
        } else if (trackNumber == 4) {
            ClothingOneImageView.setImageResource(R.drawable.sock);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.gloves);
            ClothingFourImageView.setImageResource(R.drawable.shirt);
        } else if (trackNumber == 5) {
            ClothingOneImageView.setImageResource(R.drawable.shirt);
            ClothingThreeImageView.setImageResource(R.drawable.gloves);
            ClothingTwoImageView.setImageResource(R.drawable.sock);
            ClothingFourImageView.setImageResource(R.drawable.hat);
        } else if (trackNumber == 6) {
            ClothingOneImageView.setImageResource(R.drawable.sock);
            ClothingThreeImageView.setImageResource(R.drawable.pants);
            ClothingTwoImageView.setImageResource(R.drawable.shirt);
            ClothingFourImageView.setImageResource(R.drawable.hat);
        } else if (trackNumber == 7) {
            ClothingOneImageView.setImageResource(R.drawable.pants);
            ClothingThreeImageView.setImageResource(R.drawable.panties);
            ClothingTwoImageView.setImageResource(R.drawable.gloves);
            ClothingFourImageView.setImageResource(R.drawable.hat);
        }
    }
    private void startTimer() {
        countDownTimer = new MyCountDownTimer(startTime, interval);
        CountdownTimerTextView.setText("5");

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
                mAnimation1.setDuration(3000);
                mAnimation1.setFillAfter(true);
                ClothingOneImageView.startAnimation(mAnimation1);

            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(3000);
                mAnimation1.setFillAfter(true);
                ClothingTwoImageView.startAnimation(mAnimation1);

            }
        },1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(3000);
                mAnimation1.setFillAfter(true);
                ClothingThreeImageView.startAnimation(mAnimation1);

            }
        },2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -2000,
                        0, 0);
                mAnimation1.setDuration(3000);
                mAnimation1.setFillAfter(true);
                ClothingFourImageView.startAnimation(mAnimation1);

            }
        },3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showButtons();
            }
        },5000);
    }

    private void showButtons() {
        Animation fadeIn = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadein);
        if ((trackNumber ==  1) || (trackNumber == 2)) {
            Answer1.setImageResource(R.drawable.gloves);
            Answer2.setImageResource(R.drawable.panties);
            Answer3.setImageResource(R.drawable.hat);
        } else if ((trackNumber ==  3) || (trackNumber == 4)) {
            Answer1.setImageResource(R.drawable.sock);
            Answer2.setImageResource(R.drawable.gloves);
            Answer3.setImageResource(R.drawable.bra);
        } else if ((trackNumber ==  5) || (trackNumber == 6)) {
            Answer1.setImageResource(R.drawable.shirt);
            Answer2.setImageResource(R.drawable.panties);
            Answer3.setImageResource(R.drawable.hat);
        } else {
            Answer1.setImageResource(R.drawable.sock);
            Answer2.setImageResource(R.drawable.hat);
            Answer3.setImageResource(R.drawable.pants);
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
            if ((trackNumber == 1) || (trackNumber == 2) || (trackNumber == 7)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else if (view == Answer2) {
            if ((trackNumber == 5) || (trackNumber == 6)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer3) {
            if ((trackNumber == 3) || (trackNumber == 4)) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S1L20.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L19.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }


    private void onCorrectAnswerTap() {
        Answer1.setEnabled(false);
        Answer2.setEnabled(false);
        Answer3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        Answer1.setEnabled(false);
        Answer2.setEnabled(false);
        Answer3.setEnabled(false);
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
                Animation fade = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                RelativeLayout.setVisibility(View.VISIBLE);
                RelativeLayout.startAnimation(fade);

                CheckXImageView.setImageResource(R.drawable.wrong_mark);
                CheckXImageView.setVisibility(View.VISIBLE);
                CheckXImageView.startAnimation(fade);

                TranslateAnimation mAnimation1 = new TranslateAnimation(0, 0,
                        -500, 1500);
                mAnimation1.setDuration(1000);
                mAnimation1.setFillAfter(true);
                AnswerQuestionTextView.startAnimation(mAnimation1);

            }
        }, 700);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L19.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL19COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadein);
                Animation fadeOut = AnimationUtils.loadAnimation(S1L19.this, R.anim.fadeout);
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
                Integer questionPoints = 10;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L19.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L19.this, StageOneActivity.class);
        startActivity(intent);
    }
}
