package com.markoapps.sweetchex1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.markoapps.sweetchex1.R;
import com.markoapps.sweetchex1.viewmodels.MainViewModel;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.imageUrlLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String imageUrl) {
                if(imageUrl != null) {
                    Picasso.get().load(imageUrl).into(imageView);
                }
            }
        });

        viewModel.updateImage();

    }
}