package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textViewquest;
    private TextView textViewans1;
    TextView textViewans2;
    TextView textViewans3;
    TextView textViewans4;
    TextView textViewscor;
    TextView textViewtime;
    int score;
    int count;
    int min=5;
    int max=50;
    String quest;
    String textScore;
    int rightanser;
    int numberRightanser;
    ArrayList<TextView> variant;
    Boolean gameOver=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewquest=findViewById(R.id.textViewquest);
        textViewans1=findViewById(R.id.textViewans1);
        textViewans2=findViewById(R.id.textViewans2);
        textViewans3=findViewById(R.id.textViewans3);
        textViewans4=findViewById(R.id.textViewans4);
        textViewscor=findViewById(R.id.textViewscore);
        textViewtime=findViewById(R.id.textViewtime);
        variant=new ArrayList<>();
        variant.add(textViewans1);
        variant.add(textViewans2);
        variant.add(textViewans3);
        variant.add(textViewans4);
        CountDownTimer time= new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long l) {
                long sec=l/1000;
                String timer=String.format("%02d",sec);
                textViewtime.setText(timer);
                if (sec<10){
                    textViewtime.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }

            }

            @Override
            public void onFinish() {
                gameOver=true;
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int max=sharedPreferences.getInt("max",0);
                if(max<score){
                    sharedPreferences.edit().putInt("max",score).apply();
                }
                Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
                intent.putExtra("key",score);
                startActivity(intent);

            }
        };
        time.start();
        play();

    }
    public  void genquest(){
        int a =(int)(Math.random()*(max-min))+min;
        int b =(int)(Math.random()*(max-min))+min;
        numberRightanser=(int)(Math.random()*4);
        rightanser=a+b;
        quest=String.format(Locale.getDefault(),"%s + %s",a,b);
        textViewquest.setText(quest);

    }
    public  int genans(){
        int result;
        do{
         result=(int)(Math.random()*10-5)+rightanser;}
        while (result==rightanser);
        return result;

    }
     public void play(){
        genquest();
        for (int i=0;i<4;i++){
            if (i==numberRightanser){
                variant.get(i).setText(Integer.toString(rightanser));

            }else{
                variant.get(i).setText(Integer.toString(genans()));
            }
        }
        textViewscor.setText(textScore);

     }

    public void next(View view) {
        if(!gameOver){
        TextView textView=(TextView)view;
        String res=textView.getText().toString();
        if(res==Integer.toString(rightanser)){
            Toast.makeText(this,"right",Toast.LENGTH_LONG).show(); score++;
        }else{
            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show();
        }
        count++;
        textScore=String.format("%s / %s",score,count);
        play();

    }}
}