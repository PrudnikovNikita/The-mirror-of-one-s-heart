package com.example.training.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.training.R;
import com.example.training.models.ActivityState;

import java.util.List;

public class ActivityStateAdapter extends ArrayAdapter<ActivityState> {

    private LayoutInflater inflater;
    private int layout;
    private List<ActivityState> states;

    public ActivityStateAdapter(Context context, int resource, List<ActivityState> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);


        TextView nameView = (TextView) view.findViewById(R.id.name);


        ActivityState state = states.get(position);


        nameView.setText(state.getName());


        return view;
    }
}
