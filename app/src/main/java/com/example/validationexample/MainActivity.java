package com.example.validationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.validationexample.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText user,phone,email;
    TextView tvcountchar;

    Button validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user= binding.etCountChar;
        tvcountchar= binding.tvCount;
        phone=binding.etPhone;
        email=binding.etEmail;
        validate=binding.btValidate;

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(getBaseContext(),"antes de cambiar el texto",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText= s.toString();
                int countChar= inputText.length();

                if (countChar<=50){
                    tvcountchar.setText(String.valueOf(countChar));
                }
                else {
                    tvcountchar.setText("el limite de caracteres son 50");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getBaseContext(),"Después de cambiar el texto",Toast.LENGTH_LONG).show();
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email Válido",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Email inválido",Toast.LENGTH_LONG).show();
                }

                if (isValidPhoneNumber(phone.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Teléfono Válido",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Teléfono Inválido",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean isValidPhoneNumber(String phone){
        return Patterns.PHONE.matcher(phone).matches();
    }
    public  boolean isValidEmail(String email){
        Pattern pattern= Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
        //return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}