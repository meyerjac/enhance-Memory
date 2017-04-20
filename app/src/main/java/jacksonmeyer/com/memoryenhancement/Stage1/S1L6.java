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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

public class S1L6 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.buttonLayout)
    android.widget.RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    RelativeLayout RelativeLayout;
    @Bind(R.id.faceSampleRelativeLayout)
    RelativeLayout FaceSampleRelativeLayout;

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

    @Bind(R.id.faceView1)
    ImageView FaceView1;
    @Bind(R.id.faceView2)
    ImageView FaceView2;
    @Bind(R.id.faceView3)
    ImageView FaceView3;
    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.checkXImageView)
    ImageView CheckXImageView;

    @Bind(R.id.position1)
    ImageView Position1;
    @Bind(R.id.position2)
    ImageView Position2;
    @Bind(R.id.position3)
    ImageView Position3;

    private CountDownTimer countDownTimer;
    private final long startTime = 5 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private String answerPosition = "left";
    private Integer numberTrack = 1;
    private Integer correctButtonPosition = 1;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l6);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L6.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        FaceView1.setOnClickListener(this);
        FaceView2.setOnClickListener(this);
        FaceView3.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        GetPosition();
        startTimer();
        startQuestion();
    }


    private void GetPosition() {
        //random position
        List<String> positionList = new ArrayList<String>();
        positionList.add("left");
        positionList.add("middle");
        positionList.add("right");

        Integer positionNumber = (int) (Math.random() * ((4 - 1)));
        Log.d(TAG, "GetPosition: " + positionNumber.toString());
        answerPosition = positionList.get(positionNumber);
        Log.d(TAG, "GetPosition: " + answerPosition);
    }

    private void startTimer() {
        countDownTimer = new S1L6.MyCountDownTimer(startTime, interval);
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
        setAnimationsAndRun();
    }

    private void setAnimationsAndRun() {
        final Animation fadeOut = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadeout);
        final Animation fadeIn = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadein);

        Position1.setImageDrawable(getResources().getDrawable(R.drawable.sadface));
        Position2.setImageDrawable(getResources().getDrawable(R.drawable.happyface));
        Position3.setImageDrawable(getResources().getDrawable(R.drawable.cryface));



        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                FaceSampleRelativeLayout.setVisibility(View.VISIBLE);
                FaceSampleRelativeLayout.startAnimation(fadeIn);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                FaceSampleRelativeLayout.startAnimation(fadeOut);
                FaceSampleRelativeLayout.setVisibility(View.INVISIBLE);
                setCorrectButtons();

            }
        }, 4000);
    }

    private void setCorrectButtons() {
        Animation fadeIn = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
    }


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == FaceView1) {
            if (answerPosition.equals("middle")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
                ;
            }
        } else if (view == FaceView2) {
            if (answerPosition.equals("left")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
                ;
            }
        } else if (view == FaceView3) {
            if (answerPosition.equals("right")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageOneActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S1L7.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L6.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void onCorrectAnswerTap() {
        FaceView1.setEnabled(false);
        FaceView2.setEnabled(false);
        FaceView3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
        FaceView1.setEnabled(false);
        FaceView2.setEnabled(false);
        FaceView3.setEnabled(false);
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
            if (answerPosition.equals("middle")) {
                AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " in the " + answerPosition + "?");
            } else {
                AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " on the " + answerPosition + "?");
            }
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


    //show what happens if the person answers the qustion wrong
    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L6.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL6COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L6.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L6.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L6.this, StageOneActivity.class);
        startActivity(intent);
    }
}