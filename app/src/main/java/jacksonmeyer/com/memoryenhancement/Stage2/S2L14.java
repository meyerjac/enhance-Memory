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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.answer3;
import static jacksonmeyer.com.memoryenhancement.R.id.answer4;

public class S2L14 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;

    @Bind(R.id.next)
    TextView Next;
    @Bind(R.id.replay)
    TextView Replay;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;

    @Bind(R.id.questionTextView)
    TextView QuestionTextView;
    @Bind(R.id.questionTextView2)
    TextView QuestionTextView2;
    @Bind(R.id.questionTextView3)
    TextView QuestionTextView3;
    @Bind(R.id.questionTextView4)
    TextView QuestionTextView4;
    @Bind(R.id.questionTextView5)
    TextView QuestionTextView5;
    @Bind(R.id.questionTextView6)
    TextView QuestionTextView6;
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
    @Bind(answer4)
    Button Answer4;

    @Bind(R.id.backArrow)
    ImageView BackArrow;
    @Bind(R.id.checkXImageView)
    ImageView CheckXImageView;

    private CountDownTimer countDownTimer;
    private final long startTime = 7 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";

    private Integer numberOne = 0;
    private Integer numberTwo = 0;
    private Integer numberThree = 0;
    private Integer numberFour = 0;
    private Integer numberFive = 0;
    private Integer numberSix = 0;
    private String answerPosition = "";
    private Integer correctButtonNumber = 0;
    private ArrayList numbers = new ArrayList<Integer>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l14);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        Answer4.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);

        startTimer();
        setNumbers();
        getAnswerObject();
        getCorrectButtonNumber();
        startQuestion();
    }

    private void getCorrectButtonNumber() {
        correctButtonNumber = 1 + (int)(Math.random() * ((4 - 1)));
    }

    private void getAnswerObject() {
        List<String> highLow = new ArrayList<String>();
        highLow.add("highest");
        highLow.add("lowest" );
        highLow.add("second-highest");
        highLow.add("second-lowest" );

        Integer arrayNumber =  (int)(Math.random() * ((5 - 1)));
        answerPosition = highLow.get(arrayNumber);
    }


    private void setNumbers() {
        //random fruit numbers
        numberOne = (1 + (int)(Math.random() * ((100- 1))));
        numberThree = (1 +(int)(Math.random() * ((100 - 1))));
        numberFour = (1 + (int)(Math.random() * ((100 - 1))));
        numberTwo = ((int)(Math.random() * ((100 - 1))));
        numberFive = ((int)(Math.random() * ((100 - 1))));
        numberSix = ((int)(Math.random() * ((100 - 1))));
        numbers.add(numberOne);
        numbers.add(numberTwo);
        numbers.add(numberThree);
        numbers.add(numberFour);
        numbers.add(numberFive);
        numbers.add(numberSix);

        Log.d(TAG, "setCorrectButtons:  " + numbers);
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
        final Animation fadeIn = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadein);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView.setText(String.valueOf(numberOne));;
                QuestionTextView.startAnimation(fadeIn);
                QuestionTextView.setVisibility(View.VISIBLE);

                QuestionTextView2.setText(String.valueOf(numberTwo));
                QuestionTextView2.startAnimation(fadeIn);
                QuestionTextView2.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(-500, 2500,
                        -300, 2000);
                mAnimation1.setDuration(3500);
                mAnimation1.setFillAfter(true);
                QuestionTextView.startAnimation(mAnimation1);

                TranslateAnimation mAnimation2 = new TranslateAnimation(500, -1800,
                        0, 0);
                mAnimation2.setDuration(3500);
                QuestionTextView2.startAnimation(mAnimation2);
                mAnimation2.setFillAfter(true);
            }
        },0);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView5.setText(String.valueOf(numberFive));;
                QuestionTextView5.startAnimation(fadeIn);
                QuestionTextView5.setVisibility(View.VISIBLE);

                QuestionTextView6.setText(String.valueOf(numberSix));
                QuestionTextView6.startAnimation(fadeIn);
                QuestionTextView6.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(500, -2500,
                        -300, 2000);
                mAnimation1.setDuration(3500);
                mAnimation1.setFillAfter(true);
                QuestionTextView5.startAnimation(mAnimation1);

                TranslateAnimation mAnimation2 = new TranslateAnimation(-500, 1800,
                        300, -2000);
                mAnimation2.setDuration(3500);
                QuestionTextView6.startAnimation(mAnimation2);
                mAnimation2.setFillAfter(true);
            }
        },2000);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView3.setText(String.valueOf(numberThree));
                QuestionTextView3.startAnimation(fadeIn);
                QuestionTextView3.setVisibility(View.VISIBLE);

                QuestionTextView4.setText(String.valueOf(numberFour));
                QuestionTextView4.startAnimation(fadeIn);
                QuestionTextView4.setVisibility(View.VISIBLE);

                TranslateAnimation mAnimation1 = new TranslateAnimation(-500, 2000,
                        300, -1000);
                mAnimation1.setDuration(3500);
                mAnimation1.setFillAfter(true);
                QuestionTextView3.startAnimation(mAnimation1);

                TranslateAnimation mAnimation2 = new TranslateAnimation(500, -1800,
                        0, 0);
                mAnimation2.setDuration(3500);
                mAnimation2.setFillAfter(true);
                QuestionTextView4.startAnimation(mAnimation2);

            }
        },4000);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                setCorrectButtons();

            }
        },4500);
    }

    private void setCorrectButtons() {
        Integer lowNumber =  1 + (int)(Math.random() * ((15 - 1)));
        Integer highNumber =  1 + (int)(Math.random() * ((100 - 80)));
        Collections.sort(numbers);
        if (answerPosition.equals("highest")) {
            Answer1.setText(String.valueOf(highNumber));
            Answer2.setText(String.valueOf(numbers.get(5)));
            Answer3.setText(String.valueOf(numbers.get(3)));
            Answer4.setText(String.valueOf(numbers.get(2)));
        } else if (answerPosition.equals("second-highest")) {
            Answer1.setText(String.valueOf(numbers.get(4)));
            Answer2.setText(String.valueOf(numbers.get(3)));
            Answer3.setText(String.valueOf(highNumber));
            Answer4.setText(String.valueOf(numbers.get(5)));
        } else if (answerPosition.equals("second-lowest")) {
            Answer1.setText(String.valueOf(numbers.get(2)));
            Answer2.setText(String.valueOf(numbers.get(0)));
            Answer3.setText(String.valueOf(lowNumber));
            Answer4.setText(String.valueOf(numbers.get(1)));
        } else {
            Log.d(TAG, "setCorrectButtons: " + "in here");
            Answer1.setText(String.valueOf(numbers.get(1)));
            Answer2.setText(String.valueOf(lowNumber));
            Answer3.setText(String.valueOf(numbers.get(0)));
            Answer4.setText(String.valueOf(numbers.get(2)));
        }
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            if (answerPosition.equals("second-highest")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer2) {
            if (answerPosition.equals("highest")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer3) {
            if (answerPosition.equals("lowest")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer4) {
            if (answerPosition.equals("second-lowest")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L15.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L14.class);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + answerPosition + " number shown" + "?");
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

            Animation fadeIn = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadein);
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


    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L14.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL34COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L14.this, R.anim.fadein);
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
                Integer questionPoints = 25;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L14.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L14.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
