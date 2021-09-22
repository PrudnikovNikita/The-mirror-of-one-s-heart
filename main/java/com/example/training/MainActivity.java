package com.example.training;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;

import android.widget.EditText;


import com.example.training.adapters.ActivityStateAdapter;
import com.example.training.models.ActivityState;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ArrayList<ActivityState> activityStates = new ArrayList<ActivityState>();
    ActivityStateAdapter stateAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Activity);

        recyclerView = (RecyclerView) findViewById(R.id.Activity);

        stateAdapter = new ActivityStateAdapter(this, activityStates);

        recyclerView.setAdapter(stateAdapter);





    }





    public void send(View view){
                EditText editText = (EditText) findViewById(R.id.search);

                String text = editText.getText().toString();

                activityStates.add(new ActivityState(text, 0));

                stateAdapter.notifyDataSetChanged();

        editText.setText("");


    }
}