package com.phjethva.boilerplate.java.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.phjethva.boilerplate.R;
import com.phjethva.boilerplate.databinding.LayAlertBinding;

public class UtilAlert extends AlertDialog implements View.OnClickListener {

    private LayAlertBinding binding;
    String ttl, msg, ok;
    String result;
    int action;
    public AlertActionListen clickAlertAction;

    public interface AlertActionListen {
        void callbackAlertActionListen(UtilAlert alert, String result, int action);
    }

    public UtilAlert(Activity act,
                     String ttl,
                     String msg) {
        super(act);
        this.ttl = ttl;
        this.msg = msg;
    }

    public UtilAlert(Activity act, AlertActionListen clickAlertAction,
                     String ttl,
                     String msg,
                     String result, int action) {
        super(act);
        this.clickAlertAction = clickAlertAction;
        this.ttl = ttl;
        this.msg = msg;
        this.result = result;
        this.action = action;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.colorNull);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.lay_alerts, null, false);
        setContentView(binding.getRoot());

        binding.tvAlertTtl.setText(ttl);
        binding.tvAlertMsg.setText(msg);

        binding.btnAlertOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View viw) {
        int id = viw.getId();
        switch (id) {
            case R.id.btn_alert_ok:
                dismiss();
                if (clickAlertAction != null) {
                    clickAlertAction.callbackAlertActionListen(this, result, action);
                }
                break;
        }
    }

}
