package com.phjethva.boilerplate.java.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.phjethva.boilerplate.R;
import com.phjethva.boilerplate.databinding.LayDialogBinding;

public class UtilDialog extends AlertDialog implements View.OnClickListener {

    private LayDialogBinding binding;
    private int type;
    private int icn;
    private String ttl, msg, yes, no;
    private String result;
    private int action;
    public DialogActionListen clickDialogAction;

    public interface DialogActionListen {
        void callbackDialogActionPrmListen(UtilDialog dialog, boolean bolAction, int type, int action);

        void callbackDialogActionListen(UtilDialog dialog, boolean bolAction, String result, int action);
    }

    public UtilDialog(Activity act, DialogActionListen clickDialogAction,
                      int type,
                      int icn,
                      String ttl,
                      String msg,
                      String yes, String no,
                      String result, int action) {
        super(act);
        this.clickDialogAction = clickDialogAction;
        this.type = type;
        this.icn = icn;
        this.ttl = ttl;
        this.msg = msg;
        this.yes = yes;
        this.no = no;
        this.result = result;
        this.action = action;
    }

    /*public MyDialogs(Activity act, DialogActionListen clickDialogAction,
                     int icn,
                     String ttl,
                     String msg,
                     String yes, String no,
                     String result, int action) {
        super(act);
        this.clickDialogAction = clickDialogAction;
        this.icn = icn;
        this.ttl = ttl;
        this.msg = msg;
        this.yes = yes;
        this.no = no;
        this.result = result;
        this.action = action;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.colorNull);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.lay_dialogs, null, false);
        setContentView(binding.getRoot());

        binding.ivDialogIcn.setBackgroundResource(icn);
        binding.tvDialogTtl.setText(ttl);
        binding.tvDialogMsg.setText(msg);
        binding.btnDialogYes.setText(yes);
        binding.btnDialogNo.setText(no);

        binding.btnDialogYes.setOnClickListener(this);
        binding.btnDialogNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View viw) {
        int id = viw.getId();
        switch (id) {
            case R.id.btn_dialog_yes:
                dismiss();
                if (clickDialogAction != null) {
                    if (result.equalsIgnoreCase("prm")) {
                        clickDialogAction.callbackDialogActionPrmListen(this, true, type, action);
                    } else {
                        clickDialogAction.callbackDialogActionListen(this, true, result, action);
                    }
                }
                break;
            case R.id.btn_dialog_no:
                dismiss();
                if (clickDialogAction != null) {
                    if (result.equalsIgnoreCase("prm")) {
                        clickDialogAction.callbackDialogActionPrmListen(this, false, type, action);
                    } else {
                        clickDialogAction.callbackDialogActionListen(this, false, result, action);
                    }
                }
                break;
        }
    }

}
