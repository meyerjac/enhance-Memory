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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.answer3;
import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;


public class S1L2 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.buttonLayout)
    RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    RelativeLayout RelativeLayout;
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
    Button Answer1;
    @Bind(R.id.answer2)
    Button Answer2;
    @Bind(answer3)
    Button Answer3;
    @Bind(R.id.image1)
    ImageView Image1;
    @Bind(R.id.image2)
    ImageView Image2;
    @Bind(R.id.image3)
    ImageView Image3;
    @Bind(R.id.image4)
    ImageView Image4;

    private CountDownTimer countDownTimer;
    private final long startTime = 7 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private Integer numberTrack = 1;
    private Integer correctButtonPosition = 1;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l2);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L2.this);
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

        GetTrack();
        startTimer();
        startQuestion();
    }

    private void GetTrack() {
        //random trackNumer
        numberTrack = 1+ (int)(Math.random() * ((6 - 1)));
        Log.d(TAG, "GetTrack: " + numberTrack);
    }

    private void startTimer() {
        countDownTimer = new S1L2.MyCountDownTimer(startTime, interval);
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
        if(numberTrack == 1) {
            Log.d(TAG, "in startQuestion " + 1);
            setAnimationsAndRun();
        } else if(numberTrack == 2) {
            Log.d(TAG, "in startQuestion " + 2);
            setAnimationsAndRun();
        } else if(numberTrack == 3) {
            Log.d(TAG, "in startQuestion " + 3);
            setAnimationsAndRun();
        } else if(numberTrack == 4) {
            Log.d(TAG, "in startQuestion " + 4);
            setAnimationsAndRun();
        } else if(numberTrack == 5) {
            Log.d(TAG, "in startQuestion" + 5);
            setAnimationsAndRun();
        } else {
            Log.d(TAG, "Error!");
        }
    }

    private void setAnimationsAndRun() {
        final Animation fadeOut = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadeout);
        final Animation fadeIn = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadein);

        if (numberTrack == 1) {
            Image1.setImageDrawable(getResources().getDrawable(R.drawable.s2_rooster));
            Image2.setImageDrawable(getResources().getDrawable(R.drawable.s2elephant));
            Image3.setImageDrawable(getResources().getDrawable(R.drawable.s2turtle));
            Image4.setImageDrawable(getResources().getDrawable(R.drawable.s2jelly));
        } else if (numberTrack == 2){
            Image1.setImageDrawable(getResources().getDrawable(R.drawable.s2elephant));
            Image2.setImageDrawable(getResources().getDrawable(R.drawable.s2whale));
            Image3.setImageDrawable(getResources().getDrawable(R.drawable.s2_rooster));
            Image4.setImageDrawable(getResources().getDrawable(R.drawable.s2turtle));
        } else if (numberTrack == 3){
            Image1.setImageDrawable(getResources().getDrawable(R.drawable.s2turtle));
            Image2.setImageDrawable(getResources().getDrawable(R.drawable.s2whale));
            Image3.setImageDrawable(getResources().getDrawable(R.drawable.s2jelly));
            Image4.setImageDrawable(getResources().getDrawable(R.drawable.s2_rooster));
        } else if (numberTrack == 4){
            Image1.setImageDrawable(getResources().getDrawable(R.drawable.s2whale));
            Image2.setImageDrawable(getResources().getDrawable(R.drawable.s2elephant));
            Image3.setImageDrawable(getResources().getDrawable(R.drawable.s2jelly));
            Image4.setImageDrawable(getResources().getDrawable(R.drawable.s2turtle));
        } else if (numberTrack == 5){
            Image1.setImageDrawable(getResources().getDrawable(R.drawable.s2_rooster));
            Image2.setImageDrawable(getResources().getDrawable(R.drawable.s2elephant));
            Image3.setImageDrawable(getResources().getDrawable(R.drawable.s2whale));
            Image4.setImageDrawable(getResources().getDrawable(R.drawable.s2jelly));
        } else {
            Log.d(TAG, "something went wrong in setAnimationsAndRun");
        }


        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Image1.startAnimation(fadeIn);
                Image1.setVisibility(View.VISIBLE);
            }
        },0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Image1.startAnimation(fadeOut);
                Image1.setVisibility(View.INVISIBLE);
                Image2.setVisibility(View.VISIBLE);
                Image2.startAnimation(fadeIn);
            }
        },1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Image2.startAnimation(fadeOut);
                Image2.setVisibility(View.INVISIBLE);
                Image3.setVisibility(View.VISIBLE);
                Image3.startAnimation(fadeIn);
            }
        },3000);
        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                Image3.startAnimation(fadeOut);
                Image3.setVisibility(View.INVISIBLE);
                Image4.setVisibility(View.VISIBLE);
                Image4.startAnimation(fadeIn);
            }
        },4500);
        final Handler handler5 = new Handler();
        handler5.postDelayed(new Runnable() {
            @Override
            public void run() {
                Image4.startAnimation(fadeOut);
                Image4.setVisibility(View.INVISIBLE);
            }
        },6000);
        final Handler handler6 = new Handler();
        handler6.postDelayed(new Runnable() {
            @Override
            public void run() {
                setCorrectButtons();
            }
        },7000);
    }

    private void setCorrectButtons() {
        Animation fadeIn = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
        if (numberTrack == 1) {
            correctButtonPosition = 1;
            Answer1.setText("Whale");
            Answer2.setText("Jellyfish");
            Answer3.setText("Turtle");
        }
        else if (numberTrack == 2) {
            correctButtonPosition = 3;
            Answer1.setText("Whale");
            Answer2.setText("Turtle");
            Answer3.setText("Jellyfish");
        }
        else if (numberTrack == 3) {
            correctButtonPosition = 2;
            Answer1.setText("Whale");
            Answer2.setText("Elephant");
            Answer3.setText("Jellyfish");
        }
        else if (numberTrack == 4) {
            correctButtonPosition = 2;
            Answer1.setText("Whale");
            Answer2.setText("Rooster");
            Answer3.setText("Turtle");
        }
        else if (numberTrack == 5) {
            correctButtonPosition = 3;
            Answer1.setText("Whale");
            Answer2.setText("Rooster");
            Answer3.setText("Turtle");
        }
        else {
            Log.d(TAG, "setCorrectButtons: WRONG ELSE");
        }


    }


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            if (correctButtonPosition == 1 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == Answer2) {
            if (correctButtonPosition == 2 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == Answer3) {
            if (correctButtonPosition == 3 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S1L3.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L2.class);
            startActivity(intent);
        }

        else {
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
            CountdownTimerTextView.setText("Time's up!");
            askQuestion();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownTimerTextView.setText("" + millisUntilFinished / 1000);
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
    }


    //show what happens if the person answers the qustion wrong
    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L2.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL2COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L2.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L2.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L2.this, StageOneActivity.class);
        startActivity(intent);
    }
}
