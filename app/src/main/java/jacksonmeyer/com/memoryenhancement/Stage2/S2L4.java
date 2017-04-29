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

public class S2L4 extends AppCompatActivity implements View.OnClickListener {
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
    @Bind(R.id.questionTextView1)
    TextView QuestionTextView1;
    @Bind(R.id.questionTextView2)
    TextView QuestionTextView2;
    @Bind(R.id.questionTextView3)
    TextView QuestionTextView3;
    @Bind(R.id.questionTextView4)
    TextView QuestionTextView4;
    @Bind(R.id.questionTextView5)
    TextView QuestionTextView5;

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
    private final long startTime = 9 * 1000;
    private final long interval = 1000;
    private String TAG = "debug";

    private Integer apples = 0;
    private Integer oranges = 0;
    private Integer grapefruits = 0;
    private Integer pineapples = 0;
    private Integer kiwis = 0;
    private Integer correctButtonNumber = 0;
    private String questionObject = "apples";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l4);
        ButterKnife.bind(this);

        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L4.this);
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
        setFruitNumbers();
        getCorrectButtonNumber();
        getAnswerFruit();
        startQuestion();
        Log.d(TAG, "numbers of fruit: " + apples + oranges + grapefruits + correctButtonNumber + questionObject);
    }

    private void getCorrectButtonNumber() {
        correctButtonNumber = 1 + (int)(Math.random() * ((4 - 1)));
    }

    private void getAnswerFruit() {

        List<String> fruitList = new ArrayList<String>();
        fruitList.add("apples");
        fruitList.add("oranges" );
        fruitList.add("grapefruits");
        fruitList.add("pineapples" );
        fruitList.add("kiwis");

        Integer arrayNumber =  (int)(Math.random() * ((6 - 1)));
        questionObject = fruitList.get(arrayNumber);
        Log.d(TAG, "getAnswerFruit: " + questionObject);
    }

    private void setFruitNumbers() {
        //random fruit numbers
        apples = 15 + (int)(Math.random() * ((5)));
        oranges = 10 + (int)(Math.random() * ((5)));
        grapefruits = 5 + (int)(Math.random() * ((5)));
        pineapples = 20 + (int)(Math.random() * ((5)));
        kiwis = 25 + (int)(Math.random() * ((5)));
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
        final Animation fadeOut = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadeout);
        final Animation fadeIn = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadein);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView.startAnimation(fadeIn);
                QuestionTextView.setVisibility(View.VISIBLE);
            }
        },0);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView.startAnimation(fadeOut);
                QuestionTextView.setVisibility(View.INVISIBLE);
                QuestionTextView1.setText(apples + " Apples");
                QuestionTextView1.setVisibility(View.VISIBLE);
                QuestionTextView1.startAnimation(fadeIn);
            }
        },1400);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView1.startAnimation(fadeOut);
                QuestionTextView1.setVisibility(View.INVISIBLE);
                QuestionTextView2.setText(oranges + " Oranges");
                QuestionTextView2.setVisibility(View.VISIBLE);
                QuestionTextView2.startAnimation(fadeIn);
            }
        },2800);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView2.startAnimation(fadeOut);
                QuestionTextView2.setVisibility(View.INVISIBLE);
                QuestionTextView3.setText(grapefruits + " Avacados");
                QuestionTextView3.setVisibility(View.VISIBLE);
                QuestionTextView3.startAnimation(fadeIn);
            }
        },4200);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView3.startAnimation(fadeOut);
                QuestionTextView3.setVisibility(View.INVISIBLE);
                QuestionTextView4.setText(pineapples + " Pineapple");
                QuestionTextView4.setVisibility(View.VISIBLE);
                QuestionTextView4.startAnimation(fadeIn);
            }
        },5600);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView4.startAnimation(fadeOut);
                QuestionTextView4.setVisibility(View.INVISIBLE);
                QuestionTextView5.setText(kiwis + " Kiwi");
                QuestionTextView5.setVisibility(View.VISIBLE);
                QuestionTextView5.startAnimation(fadeIn);
            }
        },7000);
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                QuestionTextView5.startAnimation(fadeOut);
                QuestionTextView5.setVisibility(View.INVISIBLE);
                setCorrectButtons();

            }
        },8400);
    }

    private void setCorrectButtons() {
        correctButtonNumber = 1 + (int) (Math.random() * ((4 - 1)));

        if (questionObject.equals("apples")) {
            if (correctButtonNumber == 1) {
                Answer1.setText(String.valueOf(apples));
                Answer2.setText(String.valueOf(grapefruits));
                Answer3.setText(String.valueOf(kiwis));
                Answer4.setText(String.valueOf(oranges));
            } else if (correctButtonNumber == 2) {
                Answer1.setText(String.valueOf(oranges));
                Answer2.setText(String.valueOf(apples));
                Answer3.setText(String.valueOf(kiwis));
                Answer4.setText(String.valueOf(grapefruits));
            } else if (correctButtonNumber == 3) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(kiwis));
                Answer3.setText(String.valueOf(apples));
                Answer4.setText(String.valueOf(oranges));
            } else if (correctButtonNumber == 4) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(kiwis));
                Answer4.setText(String.valueOf(apples));
            }
        } else if (questionObject.equals("oranges")) {
            if (correctButtonNumber == 1) {
                Answer1.setText(String.valueOf(oranges));
                Answer2.setText(String.valueOf(grapefruits));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(kiwis));
            } else if (correctButtonNumber ==2) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(grapefruits));
                Answer4.setText(String.valueOf(apples));
            } else if (correctButtonNumber == 3) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(apples));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(grapefruits));
            } else if (correctButtonNumber == 4) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(apples));
                Answer3.setText(String.valueOf(grapefruits));
                Answer4.setText(String.valueOf(oranges));
            }
        } else if (questionObject.equals("grapefruits")){
            if (correctButtonNumber == 1) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(kiwis));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(apples));
            } else if (correctButtonNumber ==2) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(grapefruits));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(apples));
            } else if (correctButtonNumber == 3) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(grapefruits));
                Answer4.setText(String.valueOf(apples));
            } else if (correctButtonNumber == 4) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(apples));
                Answer4.setText(String.valueOf(grapefruits));
            }
        } else if (questionObject.equals("pineapples")) {
            if (correctButtonNumber == 1) {
                Answer1.setText(String.valueOf(pineapples));
                Answer2.setText(String.valueOf(grapefruits));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(kiwis));
            } else if (correctButtonNumber ==2) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(pineapples));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(kiwis));
            } else if (correctButtonNumber == 3) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(pineapples));
                Answer4.setText(String.valueOf(kiwis));
            } else if (correctButtonNumber == 4) {
                Answer1.setText(String.valueOf(grapefruits));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(kiwis));
                Answer4.setText(String.valueOf(pineapples));
            }
        } else {
            if (correctButtonNumber == 1) {
                Answer1.setText(String.valueOf(kiwis));
                Answer2.setText(String.valueOf(apples));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf(grapefruits));
            } else if (correctButtonNumber ==2) {
                Answer1.setText(String.valueOf(apples));
                Answer2.setText(String.valueOf(kiwis));
                Answer3.setText(String.valueOf(oranges));
                Answer4.setText(String.valueOf((grapefruits)));
            } else if (correctButtonNumber == 3) {
                Answer1.setText(String.valueOf(apples));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(kiwis));
                Answer4.setText(String.valueOf(grapefruits));
            } else if (correctButtonNumber == 4) {
                Answer1.setText(String.valueOf(pineapples));
                Answer2.setText(String.valueOf(oranges));
                Answer3.setText(String.valueOf(apples));
                Answer4.setText(String.valueOf(kiwis));
            }
        }
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            if (correctButtonNumber == 1 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == Answer2) {
            if (correctButtonNumber == 2 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();;
            }
        } else if (view == Answer3) {
            if (correctButtonNumber == 3 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Answer4) {
            if (correctButtonNumber == 4 ) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L5.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L4.class);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " + questionObject + " did Jackson buy " + "?");
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

            Animation fadeIn = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadein);
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


    private void showFailText() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L4.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL24COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L4.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L4.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L4.this, StageTwoActivity.class);
        startActivity(intent);
    }
}