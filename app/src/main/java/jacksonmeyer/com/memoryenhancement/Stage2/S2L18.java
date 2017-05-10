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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.Constants;
import jacksonmeyer.com.memoryenhancement.R;
import jacksonmeyer.com.memoryenhancement.StageOneActivity;
import jacksonmeyer.com.memoryenhancement.StageTwoActivity;

public class S2L18 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.questionTextView)
    TextView QuestionTextView;
    @Bind(R.id.next)
    TextView Next;
    @Bind(R.id.replay)
    TextView Replay;
    @Bind(R.id.numberOfLightbulbs)
    TextView NumberOfLightbulbs;

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
    @Bind(R.id. checkXImageView)
    ImageView CheckXImageView;


    private CountDownTimer countDownTimer;
    private final long startTime = 10 * 1000;
    private final long interval = 1000;
    private Integer correctButton = null;
    private String TAG = "debug";

    private String ingredient1 = null;
    private String ingredient2 = null;
    private String ingredient3 = null;
    private String ingredient4 = null;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s2_l18);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(S2L18.this);
        mEditor = mSharedPreferences.edit();

        String oldTotal = mSharedPreferences.getString(Constants.LIGHTBULB_INTEGER_COUNT, null);
        NumberOfLightbulbs.setText(oldTotal);
        Log.d(TAG, "onCreate: " + oldTotal);

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

        setPizzaAndQuestion();
    }

    private void setPizzaAndQuestion() {

        //random colors assigned to the story
        List<String> ingredients = new ArrayList<String>();
        ingredients.add("pepperoni");
        ingredients.add("olives" );
        ingredients.add("salami");
        ingredients.add("pineapple");
        ingredients.add("canadian bacon");

        Map<Integer, String> actualIngredients = new HashMap<Integer, String>();

        for(int i = 1; i < 5; i ++ ) {
            Integer randomNum = (int)(Math.random() * ((6 - 1)));
            actualIngredients.put(i, ingredients.get(randomNum));
        }

        ingredient1 = actualIngredients.get(1);
        ingredient2 = actualIngredients.get(2);
        ingredient3 = actualIngredients.get(3);
        ingredient4 = actualIngredients.get(4);


        //accounting for using the same ingredient
        String more1 = "";
        String more2 = "";
        String more3 = "";
        if (ingredient4.equals(ingredient3) || ingredient4.equals(ingredient2) || ingredient4.equals(ingredient1)) {
            more3 = "more ";
        } if (ingredient3.equals(ingredient2) || ingredient3.equals(ingredient1)) {
            more2 = "more ";
        } if (ingredient2.equals(ingredient1)) {
            more1 = "more ";
        }

        QuestionTextView.setText("On Megan's homemade pizza she put on a base of marinera sauce, " +
                "mozzeralla cheese, first added " + ingredient1 + ", then she added " + more1 + " " + ingredient2 +
                ", added some " + more2 + " " + ingredient3 + ", and topped it with " + more3 + ingredient4 );

        countDownTimer = new MyCountDownTimer(startTime, interval);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 200);
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
        if(view == Button1) {
            if (correctButton == 1) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button2) {
            if (correctButton == 2) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == Button3) {
            if (correctButton == 3) {
                onCorrectAnswerTap();
            } else {
                onWrongAnswerTap();
            }
        } else if (view == BackArrow){
            Intent intent = new Intent(S2L18.this, StageOneActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pushrightin, R.anim.pushrightout);
        } else if (view == Replay){
            Intent intent = new Intent(S2L18.this, S2L18.class);
            startActivity(intent);
        } else if (view == Next){
            Intent intent = new Intent(S2L18.this, S2L19.class);
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
                Animation fade = AnimationUtils.loadAnimation(S2L18.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Wrong");
                Animation fade = AnimationUtils.loadAnimation(S2L18.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L18.this, R.anim.bounce);
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
        mEditor.putString(Constants.S1LEVEL18COMPLETE, passed).apply();
    }

    private void showCheckmarkAndContinue() {
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fade = AnimationUtils.loadAnimation(S2L18.this, R.anim.fadeout);
                CountdownTimerTextView.startAnimation(fade);
                CountdownTimerTextView.startAnimation(fade);
            }
        }, 0);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountdownTimerTextView.setText("Great Job!");
                Animation fade = AnimationUtils.loadAnimation(S2L18.this, R.anim.fadein);
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
                Animation bounceAnim = AnimationUtils.loadAnimation(S2L18.this, R.anim.bounce);
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
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText());
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

    private void getCorrectButtonColors() {
        Integer randomCorrectButton = 1 + (int) (Math.random() * ((3 - 1) + 1));
        correctButton = randomCorrectButton;

        if (correctButton == 1) {
            Button1.setText("peppers");
            if (!(ingredient4.equals(ingredient2))) {
                Button2.setText(ingredient4);
                Button3.setText(ingredient2);
            } else if(!(ingredient4.equals(ingredient3)))  {
                Button2.setText(ingredient4);
                Button3.setText(ingredient3);
            } else {
                Button2.setText(ingredient4);
                Button3.setText(ingredient1);
            }
        } else if (correctButton == 2) {
            Button2.setText("onions");
            if (!(ingredient1.equals(ingredient2))) {
                Button1.setText(ingredient2);
                Button3.setText(ingredient1);
            } else if(!(ingredient1.equals(ingredient3)))  {
                Button1.setText(ingredient1);
                Button3.setText(ingredient3);
            } else {
                Button1.setText(ingredient1);
                Button3.setText(ingredient4);
            }
        } else {
            Button3.setText("onions");
            if (!(ingredient1.equals(ingredient3))) {
                Button1.setText(ingredient1);
                Button2.setText(ingredient3);
            } else if(!(ingredient1.equals(ingredient2)))  {
                Button1.setText(ingredient1);
                Button2.setText(ingredient2);
            } else {
                Button1.setText(ingredient1);
                Button2.setText(ingredient4);
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(S2L18.this, StageTwoActivity.class);
        startActivity(intent);
    }
}