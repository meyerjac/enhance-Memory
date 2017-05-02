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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.Stage1.S1L11;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

public class S2L10 extends AppCompatActivity implements View.OnClickListener {
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
    @Bind(R.id.answer4)
    Button Button4;
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
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
    private final long startTime = 8 * 1000;
    private final long interval = 1000;
    private String answerPosition = null;
    private String TAG = "debug";
    private Integer trackNumber = 0;

    private String person1 = "";
    private String person2 = "";
    private String person3  = "";
    private String person4 = "";
    private String person5  = "";


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l10);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L10.this);
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
        Button4.setOnClickListener(this);
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
        positions.add("fourth");
        positions.add("fifth");

        Integer randomObject = (int)(Math.random() * ((5 - 1)));
        answerPosition =  positions.get(randomObject);

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

        List<String> people4 = new ArrayList<String>();
        people4.add("Chuck" );
        people4.add("Sarah");
        people4.add("Morgan");
        people4.add("Jeff");

        List<String> people5 = new ArrayList<String>();
        people5.add("Lester" );
        people5.add("Sheldon");
        people5.add("Elon");
        people5.add("Kevin");

        Integer one = (int)(Math.random() * ((4 - 1)));
        Integer two = (int)(Math.random() * ((4 - 1)));
        Integer three = (int)(Math.random() * ((4 - 1)));
        Integer four = (int)(Math.random() * ((4 - 1)));
        Integer five = (int)(Math.random() * ((4 - 1)));

        person1 = people1.get(one);
        person2 = people2.get(two);
        person3 = people3.get(three);
        person4 = people4.get(four);
        person5 = people5.get(five);

    }

    private void getTrack() {
        Integer randomNum = 1 + (int)(Math.random() * ((5 - 1)));
        trackNumber = randomNum;
        Log.d(TAG, "getTrack: " + trackNumber);
    }

    private void setQuestion() {
        QuestionTextView.setText("Chris came to my party and met "
                + person1 + " by the door, " + person2 + " by coatrack, "
                + person3 + " while opening the fridge, " + person4 + " while trying to get into " +
                "the pool, and "  + person5 + " waiting in line for the slide");


        countDownTimer = new MyCountDownTimer(startTime, interval);
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
                Button4.setText(person4);
            } else if (answerPosition.equals("second")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person3);
                Button4.setText(person4);
            } else if (answerPosition.equals("third")) {
                Button2.setText(person1);
                Button1.setText(person3);
                Button3.setText(person2);
                Button4.setText(person4);
            } else if (answerPosition.equals("fourth")) {
                Button2.setText(person1);
                Button1.setText(person4);
                Button3.setText(person3);
                Button4.setText(person2);
            }else {
                Button3.setText(person1);
                Button2.setText(person2);
                Button1.setText(person5);
                Button4.setText(person3);
            }
        } else  if (trackNumber == 2) {
            if (answerPosition.equals("first")) {
                Button1.setText(person5);
                Button2.setText(person1);
                Button3.setText(person3);
                Button4.setText(person4);
            } else if (answerPosition.equals("second")) {
                Button2.setText(person2);
                Button1.setText(person3);
                Button3.setText(person5);
                Button4.setText(person4);
            } else if (answerPosition.equals("third")) {
                Button2.setText(person3);
                Button1.setText(person1);
                Button3.setText(person4);
                Button4.setText(person5);
            } else if (answerPosition.equals("fourth")) {
                Button2.setText(person4);
                Button1.setText(person1);
                Button3.setText(person2);
                Button4.setText(person3);
            } else {
                Button3.setText(person1);
                Button2.setText(person5);
                Button1.setText(person4);
                Button4.setText(person3);
            }
        } else if (trackNumber == 3) {
            if (answerPosition.equals("first")) {
                Button1.setText(person3);
                Button2.setText(person2);
                Button3.setText(person1);
                Button4.setText(person4);
            } else if (answerPosition.equals("second")) {
                Button2.setText(person1);
                Button1.setText(person3);
                Button3.setText(person2);
                Button4.setText(person4);
            } else if (answerPosition.equals("third")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person3);
                Button4.setText(person4);
            } else if (answerPosition.equals("fourth")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person4);
                Button4.setText(person5);
            } else {
                Button3.setText(person5);
                Button2.setText(person2);
                Button1.setText(person3);
                Button4.setText(person4);
            }
        } else {
            if (answerPosition.equals("first")) {
                Button2.setText(person1);
                Button1.setText(person3);
                Button3.setText(person2);
                Button4.setText(person5);
            } else if (answerPosition.equals("second")) {
                Button1.setText(person3);
                Button2.setText(person5);
                Button3.setText(person1);
                Button4.setText(person2);
            } else if (answerPosition.equals("third")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person3);
                Button4.setText(person5);
            } else if (answerPosition.equals("fourth")) {
                Button2.setText(person1);
                Button1.setText(person2);
                Button3.setText(person3);
                Button4.setText(person4);
            }else {
                Button3.setText(person1);
                Button2.setText(person2);
                Button1.setText(person3);
                Button4.setText(person5);
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
        } else if (view == Button4) {
            if (trackNumber == 4) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == BackArrow){
            Intent intent = new Intent(S2L10.this, StageTwoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == Replay){
            Intent intent = new Intent(S2L10.this, S2L10.class);
            startActivity(intent);
        } else if (view == Next){
            Intent intent = new Intent(S2L10.this, S1L11.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
        }
    }

    private void onWrongAnswerTap() {
        Button1.setEnabled(false);
        Button2.setEnabled(false);
        Button3.setEnabled(false);
        Button4.setEnabled(false);
        showFailText();
    }

    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L10.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L10.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L10.this, R.anim.bounce);
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
        Button4.setEnabled(false);
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
        mEditor.putString(Constants.S2LEVEL30COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L10.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L10.this, R.anim.fadein);
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
                Integer questionPoints = 25;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L10.this, R.anim.bounce);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + answerPosition +  " person that Chris met " + "?");
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
        Intent intent = new Intent(S2L10.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
