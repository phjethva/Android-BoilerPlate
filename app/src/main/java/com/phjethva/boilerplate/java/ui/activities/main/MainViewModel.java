package com.phjethva.boilerplate.java.ui.activities.main;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.phjethva.boilerplate.java.data.main.callbacks.MainActivityCallback;

public class MainViewModel extends AndroidViewModel {

    private Context ctx;
    private MainActivityCallback callback;

    public MainViewModel(Application app, MainActivityCallback callback) {
        super(app);
        this.ctx = app.getApplicationContext();
        this.callback = callback;
    }

    public void init() {

    }

}
