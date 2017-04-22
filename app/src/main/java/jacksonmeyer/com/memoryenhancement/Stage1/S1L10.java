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

public class S1L10 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.questionTextView)
    TextView QuestionTextView;
    @Bind(R.id.answer1)
    Button Button1;
    @Bind(R.id.answer2)
    Button Button2;
    @Bind(R.id.answer3)
    Button Button3;
    @Bind(R.id.buttonLayout)
    android.widget.RelativeLayout ButtonLayout;
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
    @Bind(R.id. checkXImageView)
    ImageView CheckXImageView;


    private CountDownTimer countDownTimer;
    private final long startTime = 7 * 1000;
    private final long interval = 1000;
    private String answerPosition = null;
    private String TAG = "debug";
    private Integer trackNumber = 0;

    private String person1 = "";
    private String person2 = "";
    private String person3  = "";


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l10);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S1L10.this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);

        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);


        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        //SETS ALL THE CLICK LISTENERS
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        getTrack();
        getPosition();
        getPeople();
        setQuestion();
    }

    private void getPeople() {
        //find out the position that will be assigned
        List<String> positions = new ArrayList<String>();
        positions.add("first" );
        positions.add("second");
        positions.add("third");

        Integer randomObject = (int)(Math.random() * ((3 - 1)));
        answerPosition =  positions.get(randomObject);
        Log.d(TAG, "getTrack: " + answerPosition);
    }

    private void getPosition() {
        //find out the position that will be assigned
        List<String> people1 = new ArrayList<String>();
        people1.add("Brian" );
        people1.add("Brittany");
        people1.add("Leslie");
        people1.add("Ron");

        List<String> people2 = new ArrayList<String>();
        people2.add("Tom" );
        people2.add("Daniel");
        people2.add("Gary");
        people2.add("Andy");

        List<String> people3 = new ArrayList<String>();
        people3.add("Michael" );
        people3.add("Dwight");
        people3.add("Pam");
        people3.add("Jim");

        Integer one = (int)(Math.random() * ((4 - 1)));
        Integer two = (int)(Math.random() * ((4 - 1)));
        Integer three = (int)(Math.random() * ((4 - 1)));

        person1 = people1.get(one);
        person2 = people2.get(two);
        person3 = people3.get(three);

    }

    private void getTrack() {
            Integer randomNum = 1 + (int)(Math.random() * ((4 - 1)));
            trackNumber = randomNum;
        Log.d(TAG, "getTrack: " + trackNumber);
        }

    private void setQuestion() {
        QuestionTextView.setText("John went to a party and met "
                + person1 + " by the fridge, then he met " + person2 + " by the ping-pong table, then met "
                + person3 + " while eating some nachos " );


        countDownTimer = new S1L10.MyCountDownTimer(startTime, interval);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 200);
    }

    private void getCorrectButtonColors() {
        if (trackNumber == 1) {
            if (answerPosition.equals("first")) {
                Button1.setText(person1);
                Button2.setText(person2);
                Button3.setText(person3);
            } else if (answerPosition.equals("second")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person3);
            } else {
                Button3.setText(person1);
                Button2.setText(person2);
                Button1.setText(person3);
            }
        } else if (trackNumber == 2) {
            if (answerPosition.equals("first")) {
                Button2.setText(person1);
                Button1.setText(person3);
                Button3.setText(person2);
            } else if (answerPosition.equals("second")) {
                Button1.setText(person3);
                Button2.setText(person2);
                Button3.setText(person1);
            } else {
                Button1.setText(person2);
                Button3.setText(person1);
                Button2.setText(person3);
            }
        } else if (trackNumber == 3) {
            if (answerPosition.equals("first")) {
                Button1.setText(person2);
                Button3.setText(person1);
                Button2.setText(person3);
            } else if (answerPosition.equals("second")) {
                Button1.setText(person1);
                Button3.setText(person2);
                Button2.setText(person3);
            } else {
                Button1.setText(person2);
                Button2.setText(person1);
                Button3.setText(person3);
            }
        }
    }


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if(view == Button1) {
            if (trackNumber == 1) {
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
            if (trackNumber == 3) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == BackArrow){
            Intent intent = new Intent(S1L10.this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == Replay){
            Intent intent = new Intent(S1L10.this, S1L10.class);
            startActivity(intent);
        } else if (view == Next){
            Intent intent = new Intent(S1L10.this, S1L11.class);
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

    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L10.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S1L10.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L10.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 2000);
    }

    private void onCorrectAnswerTap() {
        Button1.setEnabled(false);
        Button2.setEnabled(false);
        Button3.setEnabled(false);
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }



    private void addPointsToSharedPreference(Integer questionPoints) {
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        Integer oldTotalInt = Integer.parseInt(oldTotal);
        Integer newTotalInt = oldTotalInt + questionPoints;
        NumberOfLightbulbs.setText(String.valueOf(newTotalInt));
        mEditor.putString(Constants.LIGHTBULB_INTEGER_COUNT, newTotalInt.toString()).apply();
    }

    private void addClearToSharedPreference(String passed) {
        mEditor.putString(Constants.S1LEVEL10COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S1L10.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S1L10.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S1L10.this, R.anim.bounce);
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
            askQuestion();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountdownTimerTextView.setText("" + millisUntilFinished / 1000);
        }


        private void askQuestion() {
            getCorrectButtonColors();
            QuestionTextView.setVisibility(View.INVISIBLE);
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + answerPosition +  " person that John met " + "?");
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S1L10.this, StageOneActivity.class);
        startActivity(intent);
    }
}
