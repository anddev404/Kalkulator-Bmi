package com.anddev.kalkulatorbmi;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anddev.kalkulatorbmi.model.PobraneDane;
import com.anddev.kalkulatorbmi.tools.FloatUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SeekBarProgressChange;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.buttonKobieta)
    ImageButton buttonKobieta;

    @BindView(R.id.buttonMezczyzna)
    ImageButton buttonMezczyzna;


    @BindView(R.id.wagaIdealna)
    TextView wagaIdealna;

    @BindView(R.id.przedzialWagi)
    TextView przedzialWagi;

    @BindView(R.id.nadwaga)
    TextView nadwaga;

    @BindView(R.id.underOverWeightTextView)
    TextView nadwagaTextView;

    @BindView(R.id.seekBarBmi)
    SeekBar seekBarBmi;

    @BindView(R.id.linearLayoutNiedowaga)
    LinearLayout linearLayoutNiedowaga;

    @BindView(R.id.linearLayoutWagaPrawidlowa)
    LinearLayout linearLayoutWagaPrawidlowa;

    @BindView(R.id.linearLayoutNadwaga)
    LinearLayout linearLayoutNadwaga;

    @BindView(R.id.linearLayoutOtylosc1)
    LinearLayout linearLayoutOtylosc1;

    @BindView(R.id.linearLayoutOtylosc2)
    LinearLayout linearLayoutOtylosc2;

    @AfterViews
    void onCreate() {
        ButterKnife.bind(this);

        wiekEditText.addTextChangedListener(textWatcher);
        wzrostEditText.addTextChangedListener(textWatcher);
        wagaEditText.addTextChangedListener(textWatcher);
        getSupportActionBar().hide();

        seekBarBmi.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
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
            zmienKolorWybranegoLinearLayout(bmi);
            zmienKolorTekstuBmi(bmi);
            zmienSeekBarWgBmi(bmi);
            wyswietlaWageIdealna(kalkulator.obliczWageIdealna());
            wyswietlaPrzedzialWagi(kalkulator.obliczWageMinimalna(), kalkulator.obliczWageMaksymalna());
            float nadwaga = pobraneDane.getWaga() - kalkulator.obliczWageMaksymalna();
            wyswietlaNadwage(nadwaga);

            bmi = FloatUtils.zaokraglijFloata(bmi, 2);
            wyswietlBmiWWidoku(bmi);
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

    public void wyswietlBmiWWidoku(Float bmi) {
        bmiTextView.setText("" + bmi);
    }


    @OnClick({R.id.buttonKobieta, R.id.buttonMezczyzna})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.buttonKobieta:

                buttonKobieta.setImageResource(R.drawable.kobieta_aktywny);
                buttonMezczyzna.setImageResource(R.drawable.mezczyzna);

                break;

            case R.id.buttonMezczyzna:

                buttonKobieta.setImageResource(R.drawable.kobieta);
                buttonMezczyzna.setImageResource(R.drawable.mezczyzna_aktywny);

                break;
        }
    }


    public void usunKolorWszystkichLinearLayout() {

        linearLayoutNiedowaga.setBackground(null);
        linearLayoutWagaPrawidlowa.setBackground(null);
        linearLayoutNadwaga.setBackground(null);
        linearLayoutOtylosc1.setBackground(null);
        linearLayoutOtylosc2.setBackground(null);
    }

    public void zmienKolorWybranegoLinearLayout(float bmi) {

        if (bmi <= 0) {
            usunKolorWszystkichLinearLayout();

        } else if (bmi < 18.5) {
            usunKolorWszystkichLinearLayout();
            linearLayoutNiedowaga.setBackgroundColor(getResources().getColor(R.color.kolorNiedowagaSlaby));

        } else if (bmi >= 18.5 && bmi < 25) {
            usunKolorWszystkichLinearLayout();
            linearLayoutWagaPrawidlowa.setBackgroundColor(getResources().getColor(R.color.kolorWagaWlasciwaSlaby));

        } else if (bmi >= 25 && bmi < 30) {
            usunKolorWszystkichLinearLayout();
            linearLayoutNadwaga.setBackgroundColor(getResources().getColor(R.color.kolorNadwagaSlaby));

        } else if (bmi >= 30 && bmi < 40) {
            usunKolorWszystkichLinearLayout();
            linearLayoutOtylosc1.setBackgroundColor(getResources().getColor(R.color.kolorOtylosc1Slaby));

        } else {
            usunKolorWszystkichLinearLayout();
            linearLayoutOtylosc2.setBackgroundColor(getResources().getColor(R.color.kolorOtylosc2Slaby));
        }

    }

    public void zmienKolorTekstuBmi(float bmi) {

        if (bmi <= 0) {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorTekstu));

        } else if (bmi < 18.5) {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorNiedowaga));

        } else if (bmi >= 18.5 && bmi < 25) {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorWagaWlasciwa));

        } else if (bmi >= 25 && bmi < 30) {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorNadwaga));

        } else if (bmi >= 30 && bmi < 40) {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorOtylosc1));

        } else {
            bmiTextView.setTextColor(getResources().getColor(R.color.kolorOtylosc2));

        }

    }

    public void zmienSeekBarWgBmi(float bmi) {


        if (bmi <= 0) {

            seekBarBmi.setProgress(0);

        } else if (bmi < 18.5) {

            float wspolczynnik = 20 / 18.5f;
            float postep = bmi * wspolczynnik;
            seekBarBmi.setProgress((int) postep);

        } else if (bmi >= 18.5 && bmi < 25) {

            float wspolczynnik = 20 / 6.5f;
            float postep = ((float) bmi - 18.5f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 20);

        } else if (bmi >= 25 && bmi < 30) {

            float wspolczynnik = 20 / 5f;
            float postep = ((float) bmi - 25f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 40);

        } else if (bmi >= 30 && bmi < 40) {

            float wspolczynnik = 20 / 10f;
            float postep = ((float) bmi - 30f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 60);
        } else {

            float wspolczynnik = 20 / 20;
            float postep = ((float) bmi - 40f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 80);
        }
    }

    public void wyswietlaWageIdealna(float wagaIdealnaFloat) {

        wagaIdealna.setText("" + FloatUtils.zaokraglijFloata(wagaIdealnaFloat, 1));
    }

    public void wyswietlaPrzedzialWagi(float wagaMinimalna, float wagaMaksymalna) {

        przedzialWagi.setText("" + FloatUtils.zaokraglijFloata(wagaMinimalna, 1) + " - " + FloatUtils.zaokraglijFloata(wagaMaksymalna, 1));
    }

    public void wyswietlaNadwage(float nadwagaFloat) {
        Float niedowagaNadwaga = FloatUtils.zaokraglijFloata(nadwagaFloat, 1);

        if (niedowagaNadwaga < 0) {
            nadwagaTextView.setText(getResources().getString(R.string.underweight));
            nadwaga.setText("" + (-niedowagaNadwaga));
        } else {
            nadwagaTextView.setText(getResources().getString(R.string.overweight));
            nadwaga.setText("" + niedowagaNadwaga);
        }
    }

}