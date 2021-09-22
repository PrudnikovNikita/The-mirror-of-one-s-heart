package com.example.training.models;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityState  {

  private String name;
  private int time;
  private boolean click = false;


    private boolean testExit = false;

    public boolean isTestExit() {
        return testExit;
    }

    public void setTestExit(boolean testExit) {
        this.testExit = testExit;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public boolean isClick() {
        return click;
    }

    public void minusTime(){
        time--;
    }

    public void start(ProgressBar progressBar){
        if (isClick() == false){
            setClick(true);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (isClick() == true){

                        try {
                            progressBar.post(new Runnable() {
                                @Override
                                public void run() {
                                    bustTime();
                                    progressBar.setProgress((int) getTime());
                                }

                            });
                            new Thread().sleep(1000);

                        } catch (InterruptedException e){

                        }
                    }
                }
            }).start();


        }
    }


    public void addBust( ProgressBar progressBar, Thread thread){

        if (isClick() == true){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    new Timer().scheduleAtFixedRate(new TimerTask(){
                        @Override
                        public void run(){
                            bustTime();

                            progressBar.post(new Runnable() {
                                public void run() {

                                    bustTime();
                                    progressBar.setProgress((int) getTime());

                                }
                            });
                        }
                    },0,1000);

                }
            };
            // Определяем объект Thread - новый поток
            thread = new Thread(runnable);
            // Запускаем поток
            thread.start();
        }
        else thread.interrupt();




    }
    public void ret(){
        return;
    }
    public void bustTime(){
      this.time++;
  }
    public void setTime(int time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ActivityState(String name, int time) {
        this.name = name;
        this.time = time;
    }


}
