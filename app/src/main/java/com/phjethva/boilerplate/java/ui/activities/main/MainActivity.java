package com.phjethva.boilerplate.java.ui.activities.main;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.phjethva.boilerplate.R;
import com.phjethva.boilerplate.databinding.MainActivityBinding;
import com.phjethva.boilerplate.java.data.main.callbacks.MainActivityCallback;
import com.phjethva.boilerplate.java.ui.activities.BaseActivity;

import static com.phjethva.boilerplate.toast.java.UtilToast.pjToastSquare;

public class MainActivity extends BaseActivity implements MainActivityCallback {

    private MainActivityBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUp(savedInstanceState);

        binding.btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pjToastSquare(getApplicationContext(), "HelloWorld");
            }
        });

    }

    private void setUp(Bundle instance) {
        viewModel = new ViewModelProvider(this, new MainViewModelFactory(getApplication(), this)).get(MainViewModel.class);
        if (instance == null) {
            viewModel.init();
        }
        binding.setViewModel(viewModel);

        setUI();
    }

    private void setUI() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick() {

    }

}
