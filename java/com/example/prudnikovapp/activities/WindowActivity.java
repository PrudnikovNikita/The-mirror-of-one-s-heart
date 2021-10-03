package com.example.prudnikovapp.activities;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.prudnikovapp.R;
import com.example.prudnikovapp.fragments.RenameFragment;
import com.example.prudnikovapp.models.ActivityEntity;
import com.example.prudnikovapp.models.WindowChangeActivity;
import com.example.prudnikovapp.models.removable;

public class WindowActivity extends AppCompatActivity implements WindowChangeActivity {

    ProgressBar progressBar;
    ActivityEntity activityEntity;


    TextView textView;
    TextView textView2, textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        Bundle arguments = getIntent().getExtras();

        progressBar = (ProgressBar) findViewById(R.id.textView2);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.calendarView);
        textView3 = (TextView) findViewById(R.id.state);
        activityEntity = (ActivityEntity) arguments.getParcelable(ActivityEntity.class.getSimpleName());

        textView2.setText(String.valueOf(activityEntity.getBoxTime() / 3600) +" ч " +  String.valueOf(activityEntity.getBoxTime() / 60) + " мин " +  String.valueOf(activityEntity.getSecond()) + " сек");
        progressBar.setMax(activityEntity.getMaxTime());
        progressBar.setMin(0);
        progressBar.setProgress((int) activityEntity.getTime());

        textView.setText(activityEntity.getName());

        if (activityEntity.isClick() == true){
            activityEntity.setClick(false);
            activityEntity.start(progressBar, textView2, textView3);

        }



    }


    @Override
    public void onBackPressed() {


        Intent data = new Intent(this, MainActivity.class);


        data.putExtra(MainActivity.ACCESS_ARRAY, activityEntity);
        setResult(RESULT_OK, data);
        super.onBackPressed();


    }

    removable removable;
    public void rename(String name){
        activityEntity.setName(name);
    }

    public void delete(View view){


    }
    public void changeGoal(View view){
        Context context;
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.set_time, null);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:

        final TimePicker timePicker = (TimePicker) promptsView.findViewById(R.id.timepicker1);
        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                activityEntity.setMaxTime(timePicker.getMinute() * 60);
                                progressBar.setMax(timePicker.getMinute() * 60);
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();

    }


    public void showDialog(View v) {

        Context context;
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.renamefragment, null);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.edit);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                activityEntity.setName(userInput.getText().toString());
                                textView.setText(activityEntity.getName());


                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();



    }





    public void stop(View view){
        activityEntity.setClick(false);
    }
    public void start(View view){



        activityEntity.start(progressBar, textView2, textView3);

    }


}



