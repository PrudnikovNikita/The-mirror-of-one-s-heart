package com.example.prudnikovapp.models;

import android.app.AlarmManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.Serializable;


public class ActivityEntity implements Parcelable {
    private String name;
    private int time;
    protected ActivityEntity(Parcel in) {
        name = in.readString();
        time = in.readInt();
        click = in.readByte() != 0;
        count = in.readInt();
        boxTime = in.readInt();
        maxTime = in.readInt();
        testExit = in.readByte() != 0;
        second = in.readInt();
    }

    public static final Creator<ActivityEntity> CREATOR = new Creator<ActivityEntity>() {
        @Override
        public ActivityEntity createFromParcel(Parcel in) {
            return new ActivityEntity(in);
        }

        @Override
        public ActivityEntity[] newArray(int size) {
            return new ActivityEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(time);
        parcel.writeByte((byte) (click ? 1 : 0));
        parcel.writeInt(count);
        parcel.writeInt(boxTime);
        parcel.writeInt(maxTime);
        parcel.writeByte((byte) (testExit ? 1 : 0));
        parcel.writeInt(second);
    }

    private boolean click = false;
    private int count;

    private int boxTime = 0;

    public void setBoxTime(int boxTime) {
        this.boxTime = boxTime;
    }

    public int getBoxTime() {
        return boxTime;
    }

    private int maxTime;

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public ActivityEntity(int count, String name, int time, int maxTime){
        this.maxTime = maxTime;
        this.count = count;
        this.time = time;
        this.name = name;
    }
    public ActivityEntity(){

    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public void setSecond(int second) {
        this.second = second;
    }

    public int getSecond() {
        return second;
    }

    private int second = 0;
    public void start(ProgressBar progressBar, TextView textView, TextView textView2){
        int firstTime = time;
        if (isClick() == false){
            setClick(true);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (isClick() == true & time < maxTime){

                        try {
                            progressBar.post(new Runnable() {
                                @Override
                                public void run() {

                                    time++;
                                    boxTime++;
                                    progressBar.setProgress((int) getTime());

                                    second++;
                                    if (boxTime % 60 == 0){
                                        second = 0;
                                    }

                                    textView2.setText("Всего времени:\n"  + String.valueOf(boxTime / 3600) +" ч " +  String.valueOf(boxTime / 60) + " мин " +  String.valueOf(second) + " сек");
                                    textView.setText(String.valueOf(time / 3600) +" ч " +  String.valueOf(time / 60) + " мин " +  String.valueOf(second) + " сек");
                                }

                            });
                            new Thread().sleep(1000);

                        } catch (InterruptedException e){

                        }

                    }
                    if (time == maxTime){
                        time = 0;
                        progressBar.setProgress((int) getTime());

                        setClick(false);
                    }
                }
            }).start();




        }
    }
    public void start(ProgressBar progressBar){
        int firstTime = time;
        if (isClick() == false){
            setClick(true);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (isClick() == true & time < maxTime){

                        try {
                            progressBar.post(new Runnable() {
                                @Override
                                public void run() {

                                    time++;
                                    boxTime++;
                                    progressBar.setProgress((int) getTime());

                                    second++;
                                    if (boxTime % 60 == 0){
                                        second = 0;
                                    }
                                }

                            });
                            new Thread().sleep(1000);

                        } catch (InterruptedException e){

                        }

                    }
                    if (time == maxTime){
                        setClick(false);
                        time = 0;
                        progressBar.setProgress((int) getTime());
                    }
                }
            }).start();





        }
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


}
