package com.example.ilya.smartcap_v31b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button conformButton = (Button) this.findViewById(R.id.conformButton);
        Button cancelButton= (Button) this.findViewById(R.id.cancelButton);
        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Login(arg0);
            }
        };
        conformButton.setOnClickListener(ocl);
        cancelButton.setOnClickListener(ocl);
    }

    public void Login(View arg0){
        EditText name=(EditText)findViewById(R.id.username);
        EditText pwd=(EditText)findViewById(R.id.pwd);
        Button bt=(Button)findViewById(arg0.getId());
        String text=bt.getText().toString();
        if(text.equals("login")){
            String name1=name.getText().toString();
            String pwd1=pwd.getText().toString();
            if(name1.equals("1") && pwd1.equals("2")){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
            }
            else{
                Toast toast=Toast.makeText(this,"Wrong account or password, please input again",Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else{
            name.setText("");
            pwd.setText("");
        }
    }
}
