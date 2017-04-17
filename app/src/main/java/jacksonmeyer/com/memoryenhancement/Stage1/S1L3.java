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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.animalRelativeLayout;
import static jacksonmeyer.com.memoryenhancement.R.id.answer3;

public class S1L3 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.buttonLayout)
    RelativeLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    RelativeLayout RelativeLayout;
    @Bind(animalRelativeLayout)
    RelativeLayout AnimalRelativeLayout;

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

    @Bind(R.id.answer1)
    Button Answer1;
    @Bind(R.id.answer2)
    Button Answer2;
    @Bind(answer3)
    Button Answer3;

    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id. checkXImageView)
    ImageView CheckXImageView;
    @Bind(R.id.image1)
    ImageView Image1;
    @Bind(R.id.image2)
    ImageView Image2;
    @Bind(R.id.image3)
    ImageView Image3;
    @Bind(R.id.image4)
    ImageView Image4;

    private CountDownTimer countDownTimer;
    private final long startTime = 5 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";
    private String answerPosition = "upperLeft";
    private Integer numberTrack = 1;
    private Integer correctButtonPosition = 1;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l3);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L3.this);
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
        GetPosition();
        startTimer();
        startQuestion();
    }

    private void GetTrack() {
        //random trackNumer
        numberTrack = 1+ (int)(Math.random() * ((6 - 1)));
        Log.d(TAG, "GetTrack: " + numberTrack);
    }

    private void GetPosition() {
        //random position
        List<String> positionList = new ArrayList<String>();
        positionList.add("upper left");
        positionList.add("upper right" );
        positionList.add("lower left");
        positionList.add("lower right");

        Integer positionNumber = (int)(Math.random() * ((5 - 1)));
        Log.d(TAG, "GetPosition: " + positionNumber.toString());
        answerPosition = positionList.get(positionNumber);
        Log.d(TAG, "GetPosition: " + answerPosition);
    }

    private void startTimer() {
        countDownTimer = new S1L3.MyCountDownTimer(startTime, interval);
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
        final Animation fadeOut = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadeout);
        final Animation fadeIn = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadein);

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
                AnimalRelativeLayout.setVisibility(View.VISIBLE);
                AnimalRelativeLayout.startAnimation(fadeIn);
            }
        },0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimalRelativeLayout.startAnimation(fadeOut);
                AnimalRelativeLayout.setVisibility(View.INVISIBLE);
                setCorrectButtons();

            }
        },4000);
    }

    private void setCorrectButtons() {
        Animation fadeIn = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);

        if (numberTrack == 1) {
            correctButtonPosition = 1;
            if (answerPosition.equals("upper left")) {
                Answer1.setText("Rooster");
                Answer2.setText("Jellyfish");
                Answer3.setText("Turtle");
            } else if (answerPosition.equals("upper right")) {
                Answer1.setText("Elephant");
                Answer2.setText("Jellyfish");
                Answer3.setText("Turtle");
            } else if (answerPosition.equals("lower left")) {
                Answer1.setText("Turtle");
                Answer2.setText("Jellyfish");
                Answer3.setText("Turtle");
            } else if (answerPosition.equals("lower right")) {
                Answer1.setText("Jellyfish");
                Answer2.setText("Rooster");
                Answer3.setText("Turtle");
            } else {
                Log.d(TAG, "setCorrectButtons: " + "something went wrong");
            }
        }
        else if (numberTrack == 2) {
            correctButtonPosition = 3;
            if (answerPosition.equals("upper left")) {
                Answer1.setText("Rooster");
                Answer2.setText("Turtle");
                Answer3.setText("Elephant");
            } else if (answerPosition.equals("upper right")) {
                Answer1.setText("Elephant");
                Answer2.setText("Rooster");
                Answer3.setText("Whale");
            } else if (answerPosition.equals("lower left")) {
                Answer1.setText("Turtle");
                Answer2.setText("Elephant");
                Answer3.setText("Rooster");
            } else if (answerPosition.equals("lower right")) {
                Answer1.setText("Elephant");
                Answer2.setText("Rooster");
                Answer3.setText("Turtle");
            } else {
                Log.d(TAG, "setCorrectButtons: " + "something went wrong");
            }
        }
        else if (numberTrack == 3) {
            correctButtonPosition = 2;
            if (answerPosition.equals("upper left")) {
                Answer1.setText("Rooster");
                Answer2.setText("Turtle");
                Answer3.setText("Elephant");
            } else if (answerPosition.equals("upper right")) {
                Answer1.setText("Jellyfish");
                Answer2.setText("Whale");
                Answer3.setText("Rooster");
            } else if (answerPosition.equals("lower left")) {
                Answer1.setText("Turtle");
                Answer2.setText("Jellyfish");
                Answer3.setText("Rooster");
            } else if (answerPosition.equals("lower right")) {
                Answer1.setText("Whale");
                Answer2.setText("Rooster");
                Answer3.setText("Turtle");
            } else {
                Log.d(TAG, "setCorrectButtons: " + "something went wrong");
            }
        }
        else if (numberTrack == 4) {
            correctButtonPosition = 2;
            if (answerPosition.equals("upper left")) {
                Answer1.setText("Jellyfish");
                Answer2.setText("Whale");
                Answer3.setText("Elephant");
            } else if (answerPosition.equals("upper right")) {
                Answer1.setText("Whale");
                Answer2.setText("Elephant");
                Answer3.setText("Jellyfish");
            } else if (answerPosition.equals("lower left")) {
                Answer1.setText("Turtle");
                Answer2.setText("Jellyfish");
                Answer3.setText("Whale");
            } else if (answerPosition.equals("lower right")) {
                Answer1.setText("Whale");
                Answer2.setText("Turtle");
                Answer3.setText("Jellyfish");
            } else {
                Log.d(TAG, "setCorrectButtons: " + "something went wrong");
            }
        }
        else if (numberTrack == 5) {
            correctButtonPosition = 3;
            if (answerPosition.equals("upper left")) {
                Answer1.setText("Elephant");
                Answer2.setText("Whale");
                Answer3.setText("Rooster");
            } else if (answerPosition.equals("upper right")) {
                Answer1.setText("Rooster");
                Answer2.setText("Jellyfish");
                Answer3.setText("Elephant");
            } else if (answerPosition.equals("lower left")) {
                Answer1.setText("Rooster");
                Answer2.setText("Jellyfish");
                Answer3.setText("Whale");
            } else if (answerPosition.equals("lower right")) {
                Answer1.setText("Whale");
                Answer2.setText("Rooster");
                Answer3.setText("Jellyfish");
            } else {
                Log.d(TAG, "setCorrectButtons: " + "something went wrong");
            }
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
            Intent intent = new Intent(this, S1L4.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S1L3.class);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " +  answerPosition + "?");
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
                Animation fade = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L3.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL3COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L3.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L3.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L3.this, StageOneActivity.class);
        startActivity(intent);
    }
}