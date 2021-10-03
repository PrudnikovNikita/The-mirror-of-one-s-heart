package com.example.prudnikovapp.adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prudnikovapp.R;
import com.example.prudnikovapp.activities.MainActivity;
import com.example.prudnikovapp.models.ActivityEntity;
import com.example.prudnikovapp.models.removable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(ActivityEntity state, int position, View view);
    }

    private final OnStateClickListener onClickListener;

    com.example.prudnikovapp.models.removable removable;

    private final LayoutInflater inflater;

    private final ArrayList<ActivityEntity> states ;

    public void deleteItemByPosition(int position){
        states.remove(position);
        notifyDataSetChanged();
    }

    public ActivityAdapter(Context context, List<ActivityEntity> states,OnStateClickListener onClickListener) {
        this.states = (ArrayList<ActivityEntity>) states;
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_activity, parent, false);
        return new ViewHolder(view);
    }

    public void remove(int position){
        states.remove(position);
        notifyItemRemoved(position);
        for (int i = position; i < states.size(); i++){
            ActivityEntity activityEntity1 = states.get(i);
            activityEntity1.setCount(i);
            states.set(i, activityEntity1);
        }

        MainActivity.x--;
        MainActivity.recyclerView.setAdapter(this);
    }

    @Override
    public void onBindViewHolder(ActivityAdapter.ViewHolder viewHolder, int position) {
        ActivityEntity activityEntity = states.get(position);

        viewHolder.nameView.setText(activityEntity.getName());
        viewHolder.progressBar.setMax(activityEntity.getMaxTime());
        viewHolder.progressBar.setProgress( (int)activityEntity.getTime());

        if (activityEntity.isClick() == true){
            activityEntity.setClick(false);
            activityEntity.start(viewHolder.progressBar);
        }
        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                remove(position);


            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(activityEntity, position, viewHolder.itemView);

            }
        });

        viewHolder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {

                activityEntity.start(viewHolder.progressBar);

            }
        });
        viewHolder.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityEntity.setClick(false);

            }
        });
    }



    @Override
    public int getItemCount() {
        return states.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        final Button remove;
        final Button startButton, stopButton;
        final TextView nameView;

        final ProgressBar progressBar;

        ViewHolder(View view) {
            super(view);
            remove = (Button) view.findViewById(R.id.b4);
            nameView = (TextView) view.findViewById(R.id.name);
            progressBar = (ProgressBar) view.findViewById(R.id.chro);
            startButton = (Button) view.findViewById(R.id.startButton);
            stopButton = (Button) view.findViewById(R.id.stopButton);
        }
    }
}
