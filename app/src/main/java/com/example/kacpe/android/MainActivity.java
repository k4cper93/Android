package com.example.kacpe.android;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView wrongPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView) findViewById(R.id.birthday);
        addListenerOnSubmit();
        addListenerOnPassword();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_NoActionBar,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/YYYY" + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

    public final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^\\w{8,16}$"
    );

    public void addListenerOnSubmit() {
        EditText password = (EditText) findViewById(R.id.password);
        final String pass = password.getText().toString();
        EditText password_confirm = (EditText) findViewById(R.id.password_confirm);
        String pass_confirm = password_confirm.getText().toString();
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        wrongPass = (TextView) findViewById(R.id.passwd_wrong);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wrongPass.setVisibility(View.INVISIBLE);
                if(!PASSWORD_PATTERN.matcher(pass).matches()){
                    wrongPass.setVisibility(View.VISIBLE);
                }
                else if(!pass.equals(pass_confirm)){
                    Toast.makeText(MainActivity.this,"Password not matching", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void addListenerOnPassword() {
        EditText password = (EditText) findViewById(R.id.password);
        wrongPass = (TextView) findViewById(R.id.passwd_wrong);

        password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wrongPass.setVisibility(View.INVISIBLE);
        }});
    }
}
