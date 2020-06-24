package com.phjethva.boilerplate.java.ui.activities.main;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.phjethva.boilerplate.java.data.main.callbacks.MainActivityCallback;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application app;
    private MainActivityCallback callback;

    public MainViewModelFactory(Application app, MainActivityCallback callback) {
        this.app = app;
        this.callback = callback;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainViewModel(app, callback);
    }

}
