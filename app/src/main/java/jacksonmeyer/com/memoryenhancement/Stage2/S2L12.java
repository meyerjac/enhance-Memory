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

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

import static jacksonmeyer.com.memoryenhancement.R.id.answer3;
import static jacksonmeyer.com.memoryenhancement.R.id.answer4;
import static jacksonmeyer.com.memoryenhancement.R.id.lightBulbRelativeLayout;

public class S2L12 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.buttonLayout)
    TableLayout ButtonLayout;
    @Bind(R.id.relativeLayout)
    android.widget.RelativeLayout RelativeLayout;
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
    @Bind(answer4)
    Button Answer4;
    @Bind(R.id.colorOneImageView)
    ImageView ShownColorImageView;

    private String TAG = "debug";
    private CountDownTimer countDownTimer;
    private final long startTime = 6 * 1000;
    private final long interval = 1000;
    private ArrayList assignedColorPattern = new ArrayList();
    private ArrayList guessedColorPattern = new ArrayList();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l12);
        ButterKnife.bind(this);



        //get shared preferences data, just the number of Lightbulbs earned and displayed
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L12.this);
        mEditor = mSharedPreferences.edit();
        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);


        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        RelativeLayout.setVisibility(View.INVISIBLE);
        ButtonLayout.setVisibility(View.INVISIBLE);

        Typeface Rubix = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Regular.ttf");
        NumberOfLightbulbs.setTypeface(Rubix);

        setClickListerners();
        setSequence();
        startTimer();
        startQuestion();

    }

    private void setClickListerners() {
        //SETS ALL THE CLICK LISTENERS
        Answer1.setOnClickListener(this);
        Answer2.setOnClickListener(this);
        Answer3.setOnClickListener(this);
        Answer4.setOnClickListener(this);
        Next.setOnClickListener(this);
        Replay.setOnClickListener(this);
        BackArrow.setOnClickListener(this);
    }

    private void setSequence() {
        for (int i = 0; i < 6; i++) {
            Integer color = 1 + (int) (Math.random() * ((4 - 1)));
            if (color == 1) {
                assignedColorPattern.add("blue");
            } else if (color == 2) {
                assignedColorPattern.add("pink");
            } else if (color == 3) {
                assignedColorPattern.add("white");
            } else {
                assignedColorPattern.add("green");
            }
        }
        Log.d(TAG, "setSequence: " + assignedColorPattern.toString());
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
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (assignedColorPattern.get(0).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(0).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(0).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                } else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }
                ShownColorImageView.setVisibility(View.VISIBLE);

            }
        },0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                if (assignedColorPattern.get(1).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(1).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(1).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                } else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }


                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShownColorImageView.setVisibility(View.VISIBLE);
                    }
                },300);

            }
        },900);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                if (assignedColorPattern.get(2).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(2).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(2).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                } else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShownColorImageView.setVisibility(View.VISIBLE);
                    }
                },300);


            }
        },1800);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                if (assignedColorPattern.get(3).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(3).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(3).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                }else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShownColorImageView.setVisibility(View.VISIBLE);
                    }
                },300);


            }
        },2700);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                if (assignedColorPattern.get(4).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(4).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(4).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                }else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShownColorImageView.setVisibility(View.VISIBLE);
                    }
                },300);


            }
        },3600);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                if (assignedColorPattern.get(5).equals("blue")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_blue_circle);
                } else if (assignedColorPattern.get(5).equals("pink")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_pink_circle);
                } else if (assignedColorPattern.get(5).equals("white")) {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_white_circle);
                }else {
                    ShownColorImageView.setBackgroundResource(R.drawable.solid_greenish_circle);
                }

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShownColorImageView.setVisibility(View.VISIBLE);
                    }
                },300);


            }
        },4500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ShownColorImageView.setVisibility(View.INVISIBLE);
                showButtons();
            }
        },5400);
    }

    private void showButtons() {
        askQuestion();
        Animation fadeIn = AnimationUtils.loadAnimation(S2L12.this, R.anim.fadein);
        ButtonLayout.setVisibility(View.VISIBLE);
        ButtonLayout.startAnimation(fadeIn);
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


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if (view == Answer1) {
            guessedColorPattern.add("green");
            Log.d(TAG, "onClick: " + guessedColorPattern);
            checkPatternLength();
        } else if (view == Answer2) {
            guessedColorPattern.add("blue");
            Log.d(TAG, "onClick: " + guessedColorPattern);
            checkPatternLength();
        } else if (view == Answer3) {
            guessedColorPattern.add("pink");
            Log.d(TAG, "onClick: " + guessedColorPattern);
            checkPatternLength();
        } else if (view == Answer4) {
            guessedColorPattern.add("white");
            Log.d(TAG, "onClick: " + guessedColorPattern);
            checkPatternLength();
        } else if (view.equals(BackArrow)) {
            Intent intent = new Intent(this, StageTwoActivity.class);
            startActivity(intent);
        } else if (view.equals(Next)) {
            Intent intent = new Intent(this, S2L13.class);
            startActivity(intent);
        } else if (view.equals(Replay)) {
            Intent intent = new Intent(this, S2L12.class);
            startActivity(intent);
        } else {
            Log.d(TAG, "onClick: " + "wrong click");
        }

    }

    private void checkPatternLength() {
        if (guessedColorPattern.size() == 6) {
            Answer1.setEnabled(false);
            Answer2.setEnabled(false);
            Answer3.setEnabled(false);
            Answer4.setEnabled(false);

            if ((guessedColorPattern.get(0).equals(assignedColorPattern.get(0))) &&
                    (guessedColorPattern.get(1).equals(assignedColorPattern.get(1))) &&
                    (guessedColorPattern.get(2).equals(assignedColorPattern.get(2))) &&
                    (guessedColorPattern.get(3).equals(assignedColorPattern.get(3))) &&
                    (guessedColorPattern.get(4).equals(assignedColorPattern.get(4))) &&
                    (guessedColorPattern.get(5).equals(assignedColorPattern.get(5)))) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        }else {
            Log.d(TAG, "checkPatternLength: " + "else");
        }

    }

    private void onCorrectAnswerTap() {
        String passed = "true";
        addClearToSharedPreference(passed);
        showCheckmarkAndContinue();
    }

    private void onWrongAnswerTap() {
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
                Animation fade = AnimationUtils.loadAnimation(S2L12.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L12.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L12.this, R.anim.bounce);
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
        mEditor.putString(Constants.S2LEVEL32COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L12.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L12.this, R.anim.fadein);
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
                Integer questionPoints = 25;
                addPointsToSharedPreference(questionPoints);
            }
        }, 1500);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L12.this, R.anim.bounce);
                CountdownTimerTextView.startAnimation(bounceAnim);
                CountdownTimerTextView.startAnimation(bounceAnim);
                bounceAnim.setRepeatMode(Animation.REVERSE);
            }
        }, 1800);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L12.this, StageTwoActivity.class);
        startActivity(intent);
    }
}
