package com.example.kacpe.android;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView) findViewById(R.id.birthday);
        addListenerOnPassword();
        addListenerOnPasswordConfirm();
        addListenerOnSubmit();

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

    public void addListenerOnPassword() {
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText password_confirm = (EditText) findViewById(R.id.password_confirm);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final TextView wrongPass = (TextView) findViewById(R.id.passwd_wrong);

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                wrongPass.setVisibility(View.GONE);
                String pass = password.getText().toString();

                if(!hasFocus) {
                    if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                        wrongPass.setVisibility(View.VISIBLE);
                    }
                }
            }

        });

    }

    public void addListenerOnPasswordConfirm() {
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText password_confirm = (EditText) findViewById(R.id.password_confirm);
        final TextView password_match = (TextView) findViewById(R.id.passwd_match);
        final TextView confirmText = (TextView) findViewById(R.id.confirm_passwd_text);

        password_confirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                password_match.setVisibility(View.GONE);
                String pass_confirm = password_confirm.getText().toString();
                String pass = password.getText().toString();

                if (!hasFocus) {
                    if (!pass.equals(pass_confirm)) {
                        //Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                        password_match.setVisibility(View.VISIBLE);
                    }
                }
            }

        });
    }

    public void addListenerOnSubmit() {
                final EditText password = (EditText) findViewById(R.id.password);
        final EditText password_confirm = (EditText) findViewById(R.id.password_confirm);
        final TextView password_match = (TextView) findViewById(R.id.passwd_match);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final TextView passwd_text = (TextView) findViewById(R.id.passwd_text);
        final TextView passwd_conf_text = (TextView) findViewById(R.id.confirm_passwd_text);
        final TextView bday_text = (TextView) findViewById(R.id.bday_text);
        final TextView lname_text = (TextView) findViewById(R.id.lname_text);
        final TextView fname_text = (TextView) findViewById(R.id.fname_text);
        final EditText fname = (EditText) findViewById(R.id.firstName);
        final CheckBox terms = (CheckBox) findViewById(R.id.terms_accepted);
        final EditText lname = (EditText) findViewById(R.id.lastName);
        final EditText email = (EditText) findViewById(R.id.email);
        final TextView wrongPass = (TextView) findViewById(R.id.passwd_wrong);
        final TextView email_text = (TextView) findViewById(R.id.email_text);
        final TextView bday = (TextView) findViewById(R.id.birthday);
        final TextView confirmText = (TextView) findViewById(R.id.confirm_passwd_text);
        final ColorStateList oldColors =  confirmText.getTextColors();
        final ColorStateList oldColorsCheckBox = terms.getTextColors();

        terms.setMovementMethod(LinkMovementMethod.getInstance());

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fName = fname.getText().toString();
                String lName = lname.getText().toString();
                String eMail = email.getText().toString();
                String bDay = bday.getText().toString();
                String pass_confirm = password_confirm.getText().toString();
                String pass = password.getText().toString();

                if (!pass.equals(pass_confirm)) {
                    password_match.setVisibility(View.VISIBLE);
                    passwd_text.setTextColor(Color.RED);
                    passwd_conf_text.setTextColor(Color.RED);
                }
                else {
                    password_match.setVisibility(View.GONE);
                    passwd_text.setTextColor(oldColors);
                    passwd_conf_text.setTextColor(oldColors);
                }

                if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                    wrongPass.setVisibility(View.VISIBLE);
                    passwd_text.setTextColor(Color.RED);
                }
                else {passwd_text.setTextColor(oldColors);}

                if(fName.trim().isEmpty()) {
                    fname_text.setTextColor(Color.RED);
                }
                else {
                    fname_text.setTextColor(oldColors);
                }

                if(lName.trim().isEmpty()) {
                    lname_text.setTextColor(Color.RED);
                }
                else {
                    lname_text.setTextColor(oldColors);
                }

                if(eMail.trim().isEmpty()) {
                    email_text.setTextColor(Color.RED);
                }
                else {
                    email_text.setTextColor(oldColors);
                }

                if(bDay.trim().isEmpty()) {
                    bday_text.setTextColor(Color.RED);
                }
                else {
                    bday_text.setTextColor(oldColors);
                }

                if(!terms.isChecked()) {
                    terms.setTextColor(Color.RED);
                }
                else {terms.setTextColor(oldColorsCheckBox);}
            }
        });

    }
}
