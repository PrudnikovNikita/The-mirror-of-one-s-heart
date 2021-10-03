package com.example.prudnikovapp.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.prudnikovapp.R;
import com.example.prudnikovapp.adapters.ActivityAdapter;
import com.example.prudnikovapp.models.ActivityEntity;
import com.example.prudnikovapp.adapters.ActivityAdapter.OnStateClickListener;
import com.example.prudnikovapp.models.User;
import com.example.prudnikovapp.models.removable;
import com.example.prudnikovapp.repository.dbAdapter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements removable  {


    public static ArrayList<ActivityEntity> activityEntityList = new ArrayList<ActivityEntity>();
    private dbAdapter adapter;
    public static int x = 0;
    static final String AGE_KEY = "AGE";
    static final String ACCESS_NAME="ACCESS_NAME";
    static final String ACCESS_COUNT="ACCESS_COUNT";
    static final String ACCESS_TIME="ACCESS_TIME";
    static final String ACCESS_CLICK="ACCESS_CLICK";
    static final String ACCESS_BOXTIME="ACCESS_BOXTIME";
    static final String ACCESS_second="ACCESS_second";
    static final String ACCESS_MAXTIME="ACCESS_MAXTIME";
    static final String ACCESS_ARRAY="ACCESS_ARRAY";

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {


                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();

                        ActivityEntity activityEntity1 = (ActivityEntity) intent.getParcelableExtra(ACCESS_ARRAY);

                        activityEntityList.set(activityEntity1.getCount(), activityEntity1);
                        activityAdapter = new ActivityAdapter(MainActivity.this, activityEntityList, stateClickListener);
                        recyclerView.setAdapter(activityAdapter);

                    }
                    else{

                    }
                }
            });

  public static ActivityAdapter.OnStateClickListener stateClickListener;
   public static RecyclerView recyclerView;
    private int userId=0;
    public static ActivityAdapter activityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        stateClickListener = new ActivityAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ActivityEntity state, int position, View view) {
                Intent intent = new Intent(view.getContext(), WindowActivity.class);


                intent.putExtra(ActivityEntity.class.getSimpleName(), state);

                mStartForResult.launch(intent);

            }
        };

        Bundle arguments = getIntent().getExtras();
        recyclerView = (RecyclerView) findViewById(R.id.Activity);
        if (arguments != null){
            ActivityEntity activityEntity = (ActivityEntity) arguments.getParcelable(ActivityEntity.class.getSimpleName());
            activityEntityList.set(activityEntity.getCount(), activityEntity);
            activityAdapter = new ActivityAdapter(MainActivity.this, activityEntityList, stateClickListener);
            recyclerView.setAdapter(activityAdapter);
        }

            activityAdapter = new ActivityAdapter(this, activityEntityList, stateClickListener);
            recyclerView.setAdapter(activityAdapter);



    }



    public void profile(View view){

    }

    @Override
    public void onResume() {
        super.onResume();

    }



    public void send(View view){




    }

    private PendingIntent getAlarm(){
        Intent alarmGetInfo = new Intent(this, WindowActivity.class);
        alarmGetInfo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this,0, alarmGetInfo, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void del(View view){
        x--;
        activityEntityList.remove(0);
        ActivityEntity activityEntity = activityEntityList.get(0);
        activityEntity.setCount(activityEntity.getCount() - 1);
        activityEntityList.set(activityEntity.getCount(), activityEntity);
        activityAdapter = new ActivityAdapter(MainActivity.this, activityEntityList, stateClickListener);
        recyclerView.setAdapter(activityAdapter);



    }

    public void addActivity(View view){
        ActivityEntity activityEntity = new ActivityEntity();
        Context context;
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.set_activity, null);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.inputName);
        final Button button = (Button) promptsView.findViewById(R.id.setTimeActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context;
                LayoutInflater li = LayoutInflater.from(view.getContext());
                View promptsView = li.inflate(R.layout.set_time, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(view.getContext());

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                final TimePicker timePicker1 = (TimePicker) promptsView.findViewById(R.id.timepicker1);
                //TimePicker timePicker2 = (TimePicker) findViewById(R.id.timepicker2);


                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ок",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // Calendar calendar = Calendar.getInstance();
                                        // calendar.set(Calendar.SECOND, 0);
                                        // calendar.set(Calendar.MILLISECOND, 0);
                                        // calendar.set(Calendar.MINUTE, timePicker1.getMinute());
                                        // calendar.set(Calendar.HOUR, timePicker1.getHour());

                                        activityEntity.setMaxTime(timePicker1.getMinute() * 60);
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
        });
        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                activityEntity.setTime(0);
                                activityEntity.setCount(x);
                                activityEntity.setName(userInput.getText().toString());
                                activityEntityList.add(activityEntity);
                                activityAdapter = new ActivityAdapter(MainActivity.this, activityEntityList, stateClickListener);
                                recyclerView.setAdapter(activityAdapter);
                                x++;






                                Intent intent = new Intent(view.getContext(), WindowActivity.class);


                                intent.putExtra(ActivityEntity.class.getSimpleName(), activityEntity);

                                mStartForResult.launch(intent);
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

    @Override
    public void remove(int activity, Context context) {


    }
}