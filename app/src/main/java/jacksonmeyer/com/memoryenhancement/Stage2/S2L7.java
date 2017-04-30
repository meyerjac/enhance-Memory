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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.answer3;
import static jacksonmeyer.com.memoryenhancement.R.id.answer4;

public class S2L7 extends AppCompatActivity implements View.OnClickListener {
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
    private final long startTime = 4 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";

    private String digit1 = "";
    private String digit2 = "";
    private String digit3 = "";
    private String digit4 = "";
    private String digit5 = "";
    private String digit6 = "";
    private String answerDigit = "";
    private Integer correctButtonNumber = 0;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l7);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L7.this);
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
        setDigitNumbers();
        getCorrectButtonNumber();
        getDigitObject();
        startQuestion();
    }

    private void getCorrectButtonNumber() {
        correctButtonNumber = 1 + (int)(Math.random() * ((5 - 1)));
    }

    private void getDigitObject() {
        List<String> digits = new ArrayList<String>();
        digits.add("hundredths");
        digits.add("tenths");
        digits.add("ones" );
        digits.add("tens");
        digits.add("hundreds");
        digits.add("thousands");

        Integer arrayNumber =  (int)(Math.random() * ((6 - 1)));
        answerDigit = digits.get(arrayNumber);
    }

    private void setDigitNumbers() {
        Integer arrayLength = 10;
            List<String> numbers = new ArrayList<String>();
        numbers.add("0");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");

        for(int i = 0; i <= 5; i ++) {
            Log.d(TAG, numbers.toString());
            if (i == 0) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit1 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit1);
            } else if (i == 1) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit2 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit2);
            } else if (i == 2) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit3 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit3);
            } else if (i == 3) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit4 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit4);
            } else if (i == 4) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit5 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit5);
            } else if (i == 5) {
                Integer randomNum =  (int)(Math.random() * (arrayLength - 1));
                digit6 = numbers.get(randomNum);
                numbers.remove(numbers.get(randomNum));
                arrayLength -= 1;
                Log.d("debug1", digit6);
            }
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
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadein);

                QuestionTextView.setText(digit1 + digit2 + digit3 + digit4 + "." + digit5 + digit6);
                QuestionTextView.startAnimation(fadeIn);
                QuestionTextView.setVisibility(View.VISIBLE);

                if (digit1.equals("8")) {
                    TranslateAnimation mAnimation1 = new TranslateAnimation(-1500, 1500,
                            -300, 2500);
                    mAnimation1.setDuration(4000);

                    mAnimation1.setFillAfter(true);
                    QuestionTextView.startAnimation(mAnimation1);
                    setCorrectButtons();
                } else {
                    TranslateAnimation mAnimation1 = new TranslateAnimation(1000, -1000,
                            -300, 2500);
                    mAnimation1.setDuration(4000);
                    mAnimation1.setFillAfter(true);
                    QuestionTextView.startAnimation(mAnimation1);
                    setCorrectButtons();
                }
            }
        },0);
    }

    private void setCorrectButtons() {
        if (answerDigit.equals("hundredths")) {
            Answer1.setText(digit6);
            Answer2.setText(digit4);
            Answer3.setText(digit3);
            Answer4.setText(digit5);
        } else if (answerDigit.equals("tenths")) {
            Answer1.setText(digit3);
            Answer2.setText(digit4);
            Answer3.setText(digit2);
            Answer4.setText(digit5);
        } else if (answerDigit.equals("ones")) {
            Answer1.setText(digit2);
            Answer2.setText(digit4);
            Answer3.setText(digit3);
            Answer4.setText(digit5);
        } else if (answerDigit.equals("tens")) {
            Answer1.setText(digit1);
            Answer2.setText(digit4);
            Answer3.setText(digit3);
            Answer4.setText(digit5);
        } else if (answerDigit.equals("hundreds")) {
            Answer1.setText(digit3);
            Answer2.setText(digit4);
            Answer3.setText(digit2);
            Answer4.setText(digit5);
        } else if (answerDigit.equals("thousands")) {
            Answer1.setText(digit2);
            Answer2.setText(digit4);
            Answer3.setText(digit6);
            Answer4.setText(digit1);
        }

    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            if (answerDigit.equals("hundredths")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer2) {
            if (answerDigit.equals("ones")) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == Answer3) {
            if (answerDigit.equals("tens") || (answerDigit.equals("hundreds"))) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer4) {
            if (answerDigit.equals("tenths") || (answerDigit.equals("thousands"))) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L8.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L7.class);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + answerDigit + " position " + "?");
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

            Animation fadeIn = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadein);
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
                Animation fade = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L7.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL27COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L7.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L7.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L7.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
