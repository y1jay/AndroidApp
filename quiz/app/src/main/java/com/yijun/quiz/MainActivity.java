package com.yijun.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtQuestion;
    ProgressBar quizPB;
    TextView txtStats;
    Button btnTrue;
    Button btnFalse;

    QuizModel[] questionArray = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),
    };

    int questionIndex = 0;
    int score =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null ){
            score = savedInstanceState.getInt("SCORE");
            questionIndex = savedInstanceState.getInt("INDEX");
        }else{
            score = 0;
            questionIndex = 0;
        }

        txtQuestion = findViewById(R.id.txtQuestion);
        quizPB = findViewById(R.id.quizPB);
        txtStats =findViewById(R.id.txtStats);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);


        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                evaluateUserAnswer(true);

                questionIndex=(questionIndex+1)%10;
                Log.i("MyQuiz","questionIndex"+questionIndex);
                if( questionIndex ==0){
                    // 알러트 다이얼로그를 이용하려면, AlertDialog.Builder를 객체생성.
                    AlertDialog.Builder quizAlert = new AlertDialog.Builder(MainActivity.this);//(MainActivity.this)메인에서 띄울거다.
                    quizAlert.setTitle("퀴즈 끝");
                    quizAlert.setMessage("당신의 점수는 : "+score);
                    quizAlert.setPositiveButton("종료", new DialogInterface.OnClickListener() { //하위 버튼생성 버튼의액션
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 앱을 종료시키는 코드
                            finish();

                        }
                    });
                   // quizAlert.setNegativeButton("취소",null); // 버튼생성 종료 왼쪽
                    // quizAlert.setNeutralButton("중간",null);// 버튼은 기본적으로 세개를 제공합니다 맨왼쪽
                    quizAlert.show();
                    return;
                }
                // 인덱스에는 10 이상 올 수 없다.
                QuizModel q= questionArray[questionIndex];
                txtQuestion.setText(q.getmQuestion());

            }


        });

            btnFalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    evaluateUserAnswer(false);
                    questionIndex=(questionIndex+1)%10 ;
                    Log.i("MyQuiz","questionIndex"+questionIndex);
                    if( questionIndex ==0){
                        AlertDialog.Builder quizAlert = new AlertDialog.Builder(MainActivity.this);
                        quizAlert.setTitle("퀴즈 끝");
                        quizAlert.setMessage("당신의 점수는 : "+score);
                        quizAlert.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        });
                        quizAlert.show();
                        return;
                    }
                    QuizModel q= questionArray[questionIndex];
                    txtQuestion.setText(q.getmQuestion());
                }

        });


            QuizModel q= questionArray[questionIndex];
             txtQuestion.setText(q.getmQuestion());
             txtStats.setText(score+"점");


             Log.i("MyQuiz","onCreate 호출됨");
    }


    @Override
    protected void onSaveInstanceState(@NonNull  Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SCORE", score);
        // "SCORE"라는 키로, score멤버변수에 저장된 현재 스코어를 저장
        outState.putInt("INDEX", questionIndex);
        Log.i("MyQuiz","onSaveInstanceState 호출");
    }

    //유저의 대답을 체크하는 함수 : 토스트로 "정답입니다","오답입니다"
    void evaluateUserAnswer(boolean userAnswer){
        QuizModel q= questionArray[questionIndex];
        boolean answer = q.getAnswer();

        // 유저의 대답과, 현재 정답을 비교하여, 토스트하는 코드
        if(userAnswer == answer ){
            score = score+1;
            Toast.makeText( MainActivity.this,"맞췄네",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText( MainActivity.this,"틀렸어",Toast.LENGTH_SHORT).show();
        }

        txtStats.setText(score+"점");
        quizPB.incrementProgressBy(1);
    }
}
