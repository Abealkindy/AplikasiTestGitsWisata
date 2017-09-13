package com.abraham24.beautyofindonesia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.abraham24.beautyofindonesia.Fonts.Fonts;
import com.abraham24.beautyofindonesia.Presenter.PresenterModel;
import com.abraham24.beautyofindonesia.Presenter.PresenterRegister;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener, PresenterModel {

    private static final String DIALOG_DATE = "MainActivity.DateDialog";

    PresenterRegister register = null;
    String nilai, dates;

    @BindView(R.id.edit_text_first_name)
    EditText editTextFirstName;
    @BindView(R.id.edit_text_last_name)
    EditText editTextLastName;
    @BindView(R.id.edit_text_username_register)
    EditText editTextUsernameRegister;
    @BindView(R.id.edit_text_password_register)
    EditText editTextPasswordRegister;
    @BindView(R.id.edit_birth_date)
    EditText editBirthDate;
    @BindView(R.id.radio_button_male)
    RadioButton radioButtonMale;
    @BindView(R.id.radio_button_female)
    RadioButton radioButtonFemale;
    @BindView(R.id.edit_text_phone_number)
    EditText editTextPhoneNumber;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.textViewReg)
    TextView textViewReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Fonts.MontserratExtraLight(this, textViewReg);
        register = new PresenterRegister(this);

    }


    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }

    @Override
    public void onFinishDialog(Date date) {
        dates = formatDate(date);
        editBirthDate.setText(dates);
    }


    @Override
    public void error(String errors) {

    }

    @Override
    public void hasil(boolean result) {

        if (result == true) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }


    @OnClick({R.id.edit_birth_date, R.id.radio_button_male, R.id.radio_button_female, R.id.button_sign_up})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_birth_date:
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_DATE);
                break;
            case R.id.radio_button_male:
                nilai = "1";
                break;
            case R.id.radio_button_female:
                nilai = "2";
                break;
            case R.id.button_sign_up:

                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String username = editTextUsernameRegister.getText().toString();
                String password = editTextPasswordRegister.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();


                if (firstName.isEmpty()) {
                    editTextFirstName.setError("First name kosong!");
                    editTextFirstName.requestFocus();
                } else if (lastName.isEmpty()) {
                    editTextLastName.requestFocus();
                    editTextLastName.setError("Last name kosong!");
                } else if (username.isEmpty()) {
                    editTextUsernameRegister.requestFocus();
                    editTextUsernameRegister.setError("Username kosong!");
                } else if (password.isEmpty()) {
                    editTextPasswordRegister.requestFocus();
                    editTextPasswordRegister.setError("Password kosong!");
                } else if (phoneNumber.isEmpty()) {
                    editTextPhoneNumber.requestFocus();
                    editTextPhoneNumber.setError("Phone number kosong!");
                } else {
//
                    register.Input(firstName, lastName, username, password, dates, nilai, phoneNumber);

                }
                break;
        }
    }
}
