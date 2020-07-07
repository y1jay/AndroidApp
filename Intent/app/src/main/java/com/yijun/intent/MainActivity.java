package com.yijun.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        composeEmail(new String[]{"abc@naver.com"},"안녕하세요");

    }

    //연락처 선택
    public void selectContact() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, 1);
        }
    }

    //메세지 보내기
    public void composeMmsMessage(String message, Uri attachment) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("smsto:015-3518-8135"));
        i.putExtra("sms body", message);
        i.putExtra(Intent.EXTRA_STREAM, attachment);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);

        }
    }
    public void composeEmail(String[] addresses, String subject){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.putExtra(Intent.EXTRA_EMAIL,addresses);
        i.putExtra(Intent.EXTRA_SUBJECT,subject);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }

    }
}
