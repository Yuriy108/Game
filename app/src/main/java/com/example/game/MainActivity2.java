package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        textViewResult=findViewById(R.id.textView);
        if(intent!=null ){
            int myscore=intent.getIntExtra("key",0);
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
            int max=preferences.getInt("max",0);
            textViewResult.setText("Ваш результат"+Integer.toString(myscore)+"\n"+"Максимальный результат "+max);


        }
    }

    public void back(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}