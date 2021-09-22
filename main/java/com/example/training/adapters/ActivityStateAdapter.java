package com.example.training.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training.R;
import com.example.training.models.ActivityState;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

public class ActivityStateAdapter extends RecyclerView.Adapter<ActivityStateAdapter.ViewHolder> {

    private final LayoutInflater inflater;

    private final List<ActivityState> states;



    public ActivityStateAdapter(Context context, List<ActivityState> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityStateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityStateAdapter.ViewHolder viewHolder, int position) {
        ActivityState activityState = states.get(position);

        viewHolder.nameView.setText(activityState.getName());
        viewHolder.progressBar.setProgress( (int)activityState.getTime());



        viewHolder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {



                activityState.start(viewHolder.progressBar);



            }
        });
        viewHolder.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityState.setClick(false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }


    public void addBust(ActivityState activityState, ProgressBar progressBar, Thread thread){


        Thread finalThread = thread;
        Runnable runnable = new Runnable() {
                @Override
                public void run() {

                   /** new Timer().scheduleAtFixedRate(new TimerTask(){
                        @Override
                        public void run(){
                            activityState.bustTime();

                            progressBar.post(new Runnable() {
                                public void run() {

                                    activityState.bustTime();
                                    progressBar.setProgress((int) activityState.getTime());

                                }
                            });
                        }
                    },0,1000);**/

                }
            };
            // Определяем объект Thread - новый поток
            thread = new Thread(runnable);
            // Запускаем поток
            thread.start();



    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        final Button startButton, stopButton;
        final TextView nameView;

        final ProgressBar progressBar;

        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            progressBar = (ProgressBar) view.findViewById(R.id.chro);
            startButton = (Button) view.findViewById(R.id.startButton);
            stopButton = (Button) view.findViewById(R.id.stopButton);
        }

    }





}
