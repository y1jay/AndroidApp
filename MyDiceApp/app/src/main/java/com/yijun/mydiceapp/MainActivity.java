package com.yijun.mydiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
     ImageView diceImg1;
     ImageView diceImg2;

     MediaPlayer mp;
    //5. 주사위 실제 이미지(1~6)를 코드로 가져온다.
    // 6.이미지를 int로 가져온 이유는 ? 안드로이드에서 자동으로
    // 우리가 넣은 이미지를, 정수로 바꿔서 관리한다.
    int[] diceImages ={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,
            R.drawable.dice5,R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 0.주사위 이미지2개를 가져온다.
        diceImg1 = findViewById(R.id.imgDice1);
        diceImg2 = findViewById(R.id.imgDice2);
          //1. 버튼을 먼저 가져온다
       mp = MediaPlayer.create(this,R.raw.dice_sound);

        Button btnRoll = findViewById(R.id.btnRollDice);
         // 2. 버튼에 이벤트를 정의한다.

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 엔터엔터엔터 버튼을 누를때마다 온클릭으로 넘어온다
                //3. 이 안에다 동작시키고 싶은 코드를 작성한다
                //3-1. 버튼이 눌렸을때 실행 순서
                Log.i("MyDiceApp","버튼 눌렸습니다.");
                // 로그를 찍는다
                //3.2 랜덤으로 한개의 숫자 가져와서
                Random rand = new Random();
                //랜덤함수
                int diceNumber = rand.nextInt(6);
                // 랜덤정수를 저장                0부터 5까지 가져와라
                Log.i("MyDiceApp",""+diceNumber);
                // 3-3. 가져온 숫자에 맞는 주사위이미지를 셋팅한다.
                // diceNumber는 0~5까지 나온다.
                // 따라서 다이스넘버에 해당하는 숫자가, 이미지배열의 인덱스와 같다.
                diceImg1.setImageResource(diceImages[diceNumber]);

                // 변수(diceNumber)를 이미 만들어놨으니 따로 만들어줄 필요가 없다.
                diceNumber = rand.nextInt(6);
                diceImg2.setImageResource(diceImages[diceNumber]);

                mp.start();
                YoYo.with(Techniques.Wobble)
                        .duration(400)
                        .repeat(0)
                        .playOn(diceImg1);
                YoYo.with(Techniques.Wobble)
                        .duration(400)
                        .repeat(0)
                        .playOn(diceImg2);

            }
        });


    }
}
