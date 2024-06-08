package com.example.mvvm_retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViewModel();
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
    }


    public void createNewUser() {
        String name  = ((EditText)findViewById(R.id.etName)).getText().toString();
        String email  = ((EditText)findViewById(R.id.etEmail)).getText().toString();

        System.out.println(name);
        System.out.println(email);
        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill the form", Toast.LENGTH_SHORT).show();
            return;
        }
        UserModel userModel = new UserModel("",name , email, "Active", "Male");
        viewModel.createNewUser(userModel);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getUserObserver().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userResponse) {
                if (userResponse == null) {
                    Toast.makeText(MainActivity.this, "Error, Please try later", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Successfully created new user : " + userResponse.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}