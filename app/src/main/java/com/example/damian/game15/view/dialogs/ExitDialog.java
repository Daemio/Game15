package com.example.damian.game15.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.damian.game15.R;
import com.example.damian.game15.Utils;
import com.example.damian.game15.events.CallBackDialogExit;

/**
 * Created by Admin on 03.11.2015.
 */
public class ExitDialog extends Dialog implements View.OnClickListener {
    CallBackDialogExit callback;

    public void setCallback(CallBackDialogExit callback) {
        this.callback = callback;
    }

    Button btnCancel;
    Button btnSave;
    Button btnExit;
    public ExitDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int action=0;
        switch (v.getId()){
            case R.id.btnCancel:
                action = Utils.DIALOG_ACTION_CANCEL;
                break;
            case R.id.btnSave:
                action = Utils.DIALOG_ACTION_SAVE_AND_CLOSE;
                break;
            case R.id.btnExit:
                action = Utils.DIALOG_ACTION_CLOSE;
                break;
        }
        if(callback!=null){
            callback.onExitDialog(action);
        }
    }
}
