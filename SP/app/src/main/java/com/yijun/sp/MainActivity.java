package com.yijun.sp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editMsg;
    TextView txtMsg;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMsg = findViewById(R.id.editMsg);
        txtMsg = findViewById(R.id.txtMsg);
        btnSave=findViewById(R.id.btnSave);

        //만약, 쉐어드 프리퍼런스에, 내가 저장한msg라는 데이터가 있다면,
        //텍스트뷰에 표시한다.
        SharedPreferences sp = getSharedPreferences("shit",MODE_PRIVATE);
        String value = sp.getString("msg", null);
        if(value !=null){
            txtMsg.setText(value);
        }

        //버튼 클릭하면, 에디트텍스트의 내용을 가져와서, SharedPreferences에 저장.
        // SharedPreferences란 ? 안드로이드에서 제공하는, 폰에 데이터를 저장하는 방법
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editMsg.getText().toString().trim();

                SharedPreferences sp = getSharedPreferences("shit",MODE_PRIVATE);//앱의 이름과 내 앱에서만 사용할것
                SharedPreferences.Editor editor= sp.edit();
                editor.putString("msg", data);
                editor.putInt("msg2",100);
                editor.apply();

            }
        });

    }
}
