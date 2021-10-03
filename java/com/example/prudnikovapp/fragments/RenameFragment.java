package com.example.prudnikovapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.prudnikovapp.R;
import com.example.prudnikovapp.activities.MainActivity;
import com.example.prudnikovapp.models.WindowChangeActivity;

public class RenameFragment extends DialogFragment {

    WindowChangeActivity windowChangeActivity;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        windowChangeActivity = (WindowChangeActivity) context;
    }



    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

       final EditText editText = new EditText(this.getContext());



        return builder
                .setView(R.layout.renamefragment)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        windowChangeActivity.rename(editText.getText().toString());
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
