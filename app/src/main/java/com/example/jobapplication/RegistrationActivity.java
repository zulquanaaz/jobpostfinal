package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Callback;
import java.io.IOException;
import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    EditText name,email,sid,password,phone;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email=findViewById(R.id.email);

        password=findViewById(R.id.password);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder retrofitBuilder =
                        new Retrofit.Builder()
                                .baseUrl("http://192.168.2.13:4000/")
                                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = retrofitBuilder.build();
                api ap=retrofit.create(api.class);
                Call<ResponseBody> reg=ap.register(email.getText().toString(),password.getText().toString());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("test",response.body().string());
                            Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG);
                    }
                });

            }
        });

    }
}