package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.training.adapters.ActivityStateAdapter;
import com.example.training.models.ActivityState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<ActivityState> activityStates = new ArrayList<>();
    ActivityStateAdapter stateAdapter;
    ListView ActivityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityList = (ListView) findViewById(R.id.Activity);




    }



    private void setInitialData(){

        activityStates.add(new ActivityState ("Бег"));

    }

    public void send(View view){
        EditText editText = (EditText) findViewById(R.id.search);

        String text = editText.getText().toString();


        activityStates.add(new ActivityState(text));
        ActivityStateAdapter stateAdapter = new ActivityStateAdapter(this, R.layout.list_activity, activityStates);
        ActivityList.setAdapter(stateAdapter);
        editText.setText("");
    }
}