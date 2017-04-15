package jacksonmeyer.com.memoryenhancement.Stage1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jacksonmeyer.com.memoryenhancement.R;


public class S1L1 extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.coundownTimerTextView)
    TextView CountdownTimerTextView;
    @Bind(R.id.answerQuestionTextView)
    TextView AnswerQuestionTextView;
    @Bind(R.id.questionTextView)
    TextView QuestionTextView;
    @Bind(R.id.button1)
    Button Button1;
    @Bind(R.id.button2)
    Button Button2;
    @Bind(R.id.button3)
    Button Button3;

    private CountDownTimer countDownTimer;
    private final long startTime = 10 * 1000;
    private final long interval = 1000;
    private String questionObject = null;
    private String answerColor = null;
    private String TAG = "debug";
    private Integer correctButton = null;

    private String color1 = null;
    private String color2 = null;
    private String color3  = null;
    private String color4  = null;
    private String color5  = null;
    private String color6 = null;
    private String color7  = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1_l1);
        ButterKnife.bind(this);

        AnswerQuestionTextView.setVisibility(View.INVISIBLE);
        setColorsAndObjects();

        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
    }

    private void setColorsAndObjects() {

            //random colors assigned to the story
        List<String> colorList = new ArrayList<String>();
        colorList.add("blue");
        colorList.add("yellow" );
        colorList.add("pink");
        colorList.add("green");
        colorList.add("white");

        Map<Integer, String> colors = new HashMap<Integer, String>();

        for(int i = 1; i < 8; i ++ ) {
            Integer randomNum = (int)(Math.random() * ((5 - 1)));
            colors.put(i, colorList.get(randomNum));
        }

        color1 = colors.get(1);
        color2 = colors.get(2);
        color3 = colors.get(3);
        color4 = colors.get(4);
        color5 = colors.get(5);
        color6 = colors.get(6);
        color7 = colors.get(7);

        //find out the object that will be assigned
        List<String> susansObjects = new ArrayList<String>();
        susansObjects.add("dress" );
        susansObjects.add("socks");
        susansObjects.add("shoes");
        susansObjects.add("house");
        susansObjects.add("umbrella");
        susansObjects.add("car");
        susansObjects.add("purse");

        Integer randomObject = (int)(Math.random() * ((8 - 1)));
        questionObject =  susansObjects.get(randomObject);

        if (questionObject.equals("dress")) {
            answerColor = color1;
        } else if (questionObject.equals("socks")) {
            answerColor = color2;
        } else if (questionObject.equals("shoes")) {
            answerColor = color3;
        } else if (questionObject.equals("house")) {
            answerColor = color4;
        } else if (questionObject.equals("umbrella")) {
            answerColor = color5;
        } else if (questionObject.equals("car")) {
            answerColor = color6;
        } else if (questionObject.equals("purse")){
            answerColor = color7;
        }

        QuestionTextView.setText("Susan woke up, put on her "
                + color1 + " dress, slipped on her " + color2 + " socks, under her " + color3 + " shoes.  " +
                "she ran out of her " + color4 + " house with her " + color5 + " umbrella and her " +
                color6 + " purse and jumped in her " + color7 + " car" );


        countDownTimer = new MyCountDownTimer(startTime, interval);
        CountdownTimerTextView.setText("10");

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer.start();
            }
        }, 500);
    }

    @Override
    public void onClick(View view) {
        if(view == Button1) {
                if (correctButton == 1) {
                    sendCorrectStuff();
                } else {
                    sendFailureStuff();
                }
        } else if (view == Button2) {
            if (correctButton == 2) {
                sendCorrectStuff();
            } else {
                sendFailureStuff();
            }

        } else if (view == Button3) {
            if (correctButton == 3) {
                sendCorrectStuff();
            } else {
                sendFailureStuff();
            }

        }
    }

    private void sendFailureStuff() {
        Log.d(TAG, "fail");
    }

    private void sendCorrectStuff() {
        Log.d(TAG, "success");
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
//            QuestionTextView.setVisibility(View.INVISIBLE);
            getCorrectButtonColors();
            AnswerQuestionTextView.setText(AnswerQuestionTextView.getText() + " " +  questionObject + "?");
            AnswerQuestionTextView.setVisibility(View.VISIBLE);

        }
    }

    private void getCorrectButtonColors() {
        Integer randomCorrectButton = 1 + (int)(Math.random() * ((3 - 1) + 1));
        correctButton = randomCorrectButton;
        Log.d("debug", correctButton.toString());
        Log.d("dev", answerColor);
        Log.d("deb ", questionObject);

        if (correctButton == 1) {
            if (answerColor.equals("blue")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            } else if (answerColor.equals("yellow")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            } else if (answerColor.equals("pink")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
            } else if (answerColor.equals("green")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            } else if (answerColor.equals("white")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            }

        } else if (correctButton == 2) {
            if (answerColor.equals("blue")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
            } else if (answerColor.equals("yellow")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            } else if (answerColor.equals("pink")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
            } else if (answerColor.equals("green")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
            } else if (answerColor.equals("white")) {
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
            }

        } else if (correctButton == 3) {
            if (answerColor.equals("blue")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
            } else if (answerColor.equals("yellow")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
            } else if (answerColor.equals("pink")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorYellowish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
            } else if (answerColor.equals("green")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorBlueish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
            } else if (answerColor.equals("white")) {
                Button1.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorPinkish));
                Button2.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorGreenish));
                Button3.setBackgroundColor(ContextCompat.getColor(S1L1.this, R.color.colorWhite));
            }
        }
    }
}