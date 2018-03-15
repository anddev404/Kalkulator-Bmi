package com.anddev.kalkulatorbmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anddev.kalkulatorbmi.model.PobraneDane;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wiekEditText)
    EditText wiekEditText;

    @BindView(R.id.wzrostEditText)
    EditText wzrostEditText;

    @BindView(R.id.wagaEditText)
    EditText wagaEditText;

    @BindView(R.id.bmiTextView)
    TextView bmiTextView;

    @AfterViews
    void onCreate() {
        ButterKnife.bind(this);

        wiekEditText.addTextChangedListener(textWatcher);
        wzrostEditText.addTextChangedListener(textWatcher);
        wagaEditText.addTextChangedListener(textWatcher);

    }

    private final TextWatcher textWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {

            PobraneDane pobraneDane = pobierzDaneZWidokuDoModelu();
            Kalkulator kalkulator = new Kalkulator(pobraneDane);
            float bmi = kalkulator.obliczBmi();
            Toast.makeText(MainActivity.this, "Bmi wynosi: " + bmi, Toast.LENGTH_SHORT).show();

        }
    };

    public PobraneDane pobierzDaneZWidokuDoModelu() {
        
        try {

            Integer wiek = Integer.parseInt(wiekEditText.getText().toString());
            Float wzrost = Float.parseFloat(wzrostEditText.getText().toString());
            Float waga = Float.parseFloat(wagaEditText.getText().toString());

            return new PobraneDane(wiek, wzrost, waga);

        } catch (Exception e) {

            return new PobraneDane(0, 0, 0);

        }
    }
}
