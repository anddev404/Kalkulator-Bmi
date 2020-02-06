package pl.anddev.kalkulatorbmi;

import android.content.ContextWrapper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.anddev.kalkulatorbmi.model.PobraneDane;
import pl.anddev.kalkulatorbmi.tools.FloatUtils;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    //region wzrost

    @BindView(R.id.wzrostEditText)
    EditText wzrostEditText;

    @BindView(R.id.wzrostFtEditText)
    EditText wzrostFtEditText;

    @BindView(R.id.wzrostInEditText)
    EditText wzrostInEditText;

    @BindView(R.id.wzrost_cm_layout)
    LinearLayout wzrostCmLayout;

    @BindView(R.id.wzrost_ft_layout)
    LinearLayout wzrostFtLayout;

    Spinner spinnerWzrost;

    int wybranaJednostkaWzrostu;

    //endregion

    //region waga
    @BindView(R.id.wagaEditText)
    EditText wagaEditText;

    @BindView(R.id.wagaLbsEditText)
    EditText wagaLbsEditText;

    @BindView(R.id.wagaStEditText)
    EditText wagaStoneEditText;

    @BindView(R.id.wagaLbWithStoneEditText)
    EditText wagaLbWithStoneEditText;

    @BindView(R.id.waga_kg_layout)
    LinearLayout wagaKgLayout;

    @BindView(R.id.waga_lbs_layout)
    LinearLayout wagaLbLayout;

    @BindView(R.id.waga_stone_and_lbs_layout)
    LinearLayout wagaStAndLbLayout;

    Spinner spinnerWaga;

    String jednostkaWagi;

    int wybranaJednostkaWagi;
    //endregion

    @BindView(R.id.bmiTextView)
    TextView bmiTextView;


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


    public void pokazWybranaJednostkeWzrostu(int position) {
        if (position == 0) {
            wzrostCmLayout.setVisibility(View.VISIBLE);
            wzrostFtLayout.setVisibility(View.GONE);

        }
        if (position == 1) {
            wzrostCmLayout.setVisibility(View.GONE);
            wzrostFtLayout.setVisibility(View.VISIBLE);
        }
    }

    public void pokazWybranaJednostkeWagi(int position) {
        if (position == 0) {
            wagaKgLayout.setVisibility(View.VISIBLE);
            wagaLbLayout.setVisibility(View.GONE);
            wagaStAndLbLayout.setVisibility(View.GONE);

        }
        if (position == 1) {
            wagaKgLayout.setVisibility(View.GONE);
            wagaLbLayout.setVisibility(View.VISIBLE);
            wagaStAndLbLayout.setVisibility(View.GONE);
        }
        if (position == 2) {
            wagaKgLayout.setVisibility(View.GONE);
            wagaLbLayout.setVisibility(View.GONE);
            wagaStAndLbLayout.setVisibility(View.VISIBLE);
        }

    }

    public void addItemsOnSpinnerWzrost() {

        spinnerWzrost = (Spinner) findViewById(R.id.spinnerWzrost);
        List<String> list = new ArrayList<String>();
        list.add("cm");
        list.add("ft+in");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWzrost.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerWaga() {

        spinnerWaga = (Spinner) findViewById(R.id.spinnerWaga);
        List<String> list = new ArrayList<String>();
        list.add("kg");
        list.add("lb");
        list.add("st+lb");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWaga.setAdapter(dataAdapter);
    }


    @AfterViews
    void onCreate() {
        ButterKnife.bind(this);

        inicjalizujSharePreference();
        odczytajWzrost();
        wybranaJednostkaWzrostu = odczytajJednostkeWzrostu();
        wybranaJednostkaWagi = odczytajJednostkeWagi();
        getSupportActionBar().hide();
        wzrostEditText.addTextChangedListener(textWatcher);
        wagaEditText.addTextChangedListener(textWatcher);
        wzrostFtEditText.addTextChangedListener(textWatcher);
        wzrostInEditText.addTextChangedListener(textWatcher);
        wagaEditText.addTextChangedListener(textWatcher);
        wagaLbsEditText.addTextChangedListener(textWatcher);
        wagaStoneEditText.addTextChangedListener(textWatcher);
        wagaLbWithStoneEditText.addTextChangedListener(textWatcher);

        seekBarBmi.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        addItemsOnSpinnerWzrost();
        addItemsOnSpinnerWaga();
        spinnerWzrost.setSelection(wybranaJednostkaWzrostu);
        spinnerWaga.setSelection(wybranaJednostkaWagi);

//nie zapomnac ze uzywamy seton item listener zamias t on click listener
        spinnerWzrost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    wybranaJednostkaWzrostu = 0;
                    pokazWybranaJednostkeWzrostu(0);

                } else if (position == 1) {
                    wybranaJednostkaWzrostu = 1;
                    pokazWybranaJednostkeWzrostu(1);
                } else {
                    wybranaJednostkaWzrostu = 0;
                    pokazWybranaJednostkeWzrostu(0);
                }

                obliczWszystko();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerWaga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    wybranaJednostkaWagi = 0;
                    pokazWybranaJednostkeWagi(0);
                    jednostkaWagi = "kg";

                } else if (position == 1) {
                    wybranaJednostkaWagi = 1;
                    pokazWybranaJednostkeWagi(1);
                    jednostkaWagi = "lb";

                } else if (position == 2) {
                    wybranaJednostkaWagi = 2;
                    pokazWybranaJednostkeWagi(2);
                    jednostkaWagi = "lb";

                } else {
                    wybranaJednostkaWagi = 0;
                    pokazWybranaJednostkeWagi(0);
                    jednostkaWagi = "kg";

                }
                obliczWszystko();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();

        zapiszWzrost();
        zapiszJednostkeWzrostu();
        zapiszJednostkeWagi();

    }

    private void inicjalizujSharePreference() {

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }


    private void zapiszWzrost() {

        Prefs.putString("wzrost", wzrostEditText.getText().toString());
        Prefs.putString("wzrostFt", wzrostFtEditText.getText().toString());
        Prefs.putString("wzrostIn", wzrostInEditText.getText().toString());

    }

    private void odczytajWzrost() {

        wzrostEditText.setText(Prefs.getString("wzrost", ""));
        wzrostFtEditText.setText(Prefs.getString("wzrostFt", ""));
        wzrostInEditText.setText(Prefs.getString("wzrostIn", ""));
        ustawKursorNakoncu(wzrostEditText);
        ustawKursorNakoncu(wzrostFtEditText);
        ustawKursorNakoncu(wzrostInEditText);

    }

    private void zapiszJednostkeWzrostu() {

        Prefs.putInt("wzrostJednostka", wybranaJednostkaWzrostu);
    }

    private int odczytajJednostkeWzrostu() {

        int wzrostJednostka = Prefs.getInt("wzrostJednostka", 0);

        return wzrostJednostka;
    }

    private void zapiszJednostkeWagi() {

        Prefs.putInt("wagaJednostka", wybranaJednostkaWagi);
    }

    private int odczytajJednostkeWagi() {

        int wagaJednostka = Prefs.getInt("wagaJednostka", 0);
        if (wagaJednostka == 0) {
            jednostkaWagi = "kg";
        } else if (wagaJednostka == 1) {
            jednostkaWagi = "lb";
        } else if (wagaJednostka == 2) {
            jednostkaWagi = "lb";
        } else {
            jednostkaWagi = "kg";
        }
        return wagaJednostka;
    }

    private final TextWatcher textWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {

            obliczWszystko();
        }
    };

    public void obliczWszystko() {

        PobraneDane pobraneDane = pobierzDaneZWidokuDoModelu();
        Kalkulator kalkulator = new Kalkulator(pobraneDane);
        float bmi = kalkulator.obliczBmi();
        zmienKolorWybranegoLinearLayout(bmi);
        zmienKolorTekstuBmi(bmi);
        zmienSeekBarWgBmi(bmi);
        wyswietlaWageIdealna(kalkulator.obliczWageIdealna(), jednostkaWagi);
        wyswietlaPrzedzialWagi(kalkulator.obliczWageMinimalna(), kalkulator.obliczWageMaksymalna(), jednostkaWagi);
        float nadwagaLubNiedowaga = pobraneDane.getWaga() - kalkulator.obliczWageIdealna();
        wyswietlNadwageLubNiedowage(nadwagaLubNiedowaga, jednostkaWagi);

        bmi = FloatUtils.zaokraglijFloata(bmi, 2);
        wyswietlBmiWWidoku(bmi);
    }

    public Float zamienFuntyICaleNaCm(String ft, String in) {
        Float wzrost = 0.0f;
        try {
            Float wzrostFt = Float.parseFloat(ft);
            wzrost = wzrostFt * 30.48f;
        } catch (Exception e) {

        }
        try {
            Float wzrostIn = Float.parseFloat(in);
            wzrost = wzrost + (wzrostIn * 2.54f);
        } catch (Exception e) {

        }

        return wzrost;
    }

    public Float zamienLbNaKg(String lb) {
        Float waga = 0.0f;
        try {
            Float wagaLb = Float.parseFloat(lb);
            waga = wagaLb * 0.45359237f;
        } catch (Exception e) {

        }

        return waga;
    }

    public Float zamienKgNaLb(Float kg) {
        Float waga = 0.0f;
        try {
            waga = kg * 2.20462262f;
        } catch (Exception e) {

        }

        return waga;
    }

    public Float zamienStAndLbNaKg(String st, String lb) {
        Float waga = 0.0f;
        try {
            Float wagaLb = Float.parseFloat(lb);
            waga = (wagaLb * 0.45359237f);
        } catch (Exception e) {

        }
        try {
            Float wagaSt = Float.parseFloat(st);
            waga = waga + (wagaSt * 6.35029f);
        } catch (Exception e) {

        }
        Log.d("St " + waga, "MARCIN");//filtrowanie w logCat po treści - nie tagu

        return waga;

    }

    public PobraneDane pobierzDaneZWidokuDoModelu() {

        try {
            Float wzrost = 0.0f;
            Float waga = 0.0f;

            if (wybranaJednostkaWzrostu == 0) {
                wzrost = Float.parseFloat(wzrostEditText.getText().toString());
            } else if (wybranaJednostkaWzrostu == 1) {
                wzrost = zamienFuntyICaleNaCm(wzrostFtEditText.getText().toString(), wzrostInEditText.getText().toString());

            }

            if (wybranaJednostkaWagi == 0) {
                waga = Float.parseFloat(wagaEditText.getText().toString());
            } else if (wybranaJednostkaWagi == 1) {
                waga = zamienLbNaKg(wagaLbsEditText.getText().toString());

            } else if (wybranaJednostkaWagi == 2) {
                waga = zamienStAndLbNaKg(wagaStoneEditText.getText().toString(), wagaLbWithStoneEditText.getText().toString());

            }

            return new PobraneDane(wzrost, waga);

        } catch (Exception e) {

            return new PobraneDane(0, 0);

        }
    }

    public void wyswietlBmiWWidoku(Float bmi) {
        bmiTextView.setText("" + bmi);
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

        } else if (bmi >= 30 && bmi < 35) {
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

        } else if (bmi >= 30 && bmi < 35) {
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

        } else if (bmi >= 30 && bmi < 35) {

            float wspolczynnik = 20 / 5f;
            float postep = ((float) bmi - 30f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 60);
        } else {

            float wspolczynnik = 20 / 20;
            float postep = ((float) bmi - 35f) * wspolczynnik;
            seekBarBmi.setProgress((int) postep + 80);
        }
    }

    public void wyswietlaWageIdealna(float wagaIdealnaFloat, String jednostkaWagi) {
        if (wybranaJednostkaWagi == 0) {
            wagaIdealna.setText("" + FloatUtils.zaokraglijFloata(wagaIdealnaFloat, 1) + " " + jednostkaWagi);

        } else if (wybranaJednostkaWagi == 1) {
            wagaIdealna.setText("" + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaIdealnaFloat), 1) + " " + jednostkaWagi);
        } else if (wybranaJednostkaWagi == 2) {
            wagaIdealna.setText("" + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaIdealnaFloat), 1) + " " + jednostkaWagi);
        } else {
            wagaIdealna.setText("" + FloatUtils.zaokraglijFloata(wagaIdealnaFloat, 1) + " " + jednostkaWagi);

        }
    }

    public void wyswietlaPrzedzialWagi(float wagaMinimalna, float wagaMaksymalna, String jednostkaWagi) {
        if (wybranaJednostkaWagi == 0) {
            przedzialWagi.setText("" + FloatUtils.zaokraglijFloata(wagaMinimalna, 1) + " " + jednostkaWagi + " - " + FloatUtils.zaokraglijFloata(wagaMaksymalna, 1) + " " + jednostkaWagi);

        } else if (wybranaJednostkaWagi == 1) {
            przedzialWagi.setText("" + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaMinimalna), 1) + " " + jednostkaWagi + " - " + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaMaksymalna), 1) + " " + jednostkaWagi);
        } else if (wybranaJednostkaWagi == 2) {
            przedzialWagi.setText("" + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaMinimalna), 1) + " " + jednostkaWagi + " - " + FloatUtils.zaokraglijFloata(zamienKgNaLb(wagaMaksymalna), 1) + " " + jednostkaWagi);
        } else {
            przedzialWagi.setText("" + FloatUtils.zaokraglijFloata(wagaMinimalna, 1) + " " + jednostkaWagi + " - " + FloatUtils.zaokraglijFloata(wagaMaksymalna, 1) + " " + jednostkaWagi);

        }
    }

    public void wyswietlNadwageLubNiedowage(float nadwagaFloat, String jednostkaWagi) {
        try {


            float waga = 0;
            if (wybranaJednostkaWagi == 0) {
                waga = Float.parseFloat(wagaEditText.getText().toString());
            } else if (wybranaJednostkaWagi == 1) {
                waga = zamienLbNaKg(wagaLbsEditText.getText().toString());

            } else if (wybranaJednostkaWagi == 2) {
                waga = zamienStAndLbNaKg(wagaStoneEditText.getText().toString(), wagaLbWithStoneEditText.getText().toString());

            }

            if (waga <= 0) {
                nadwaga.setText("... " + jednostkaWagi);
                return;
            }
        } catch (Exception e) {
            return;
        }

        Float niedowagaNadwaga = FloatUtils.zaokraglijFloata(nadwagaFloat, 1);

        if (wybranaJednostkaWagi == 1) {
            niedowagaNadwaga = FloatUtils.zaokraglijFloata(zamienKgNaLb(nadwagaFloat), 1);
        } else if (wybranaJednostkaWagi == 2) {
            niedowagaNadwaga = FloatUtils.zaokraglijFloata(zamienKgNaLb(nadwagaFloat), 1);
        }


        if (niedowagaNadwaga < 0) {
            nadwagaTextView.setText(getResources().getString(R.string.underweight));
            nadwaga.setText("" + (-niedowagaNadwaga) + " " + jednostkaWagi);
        } else {
            nadwagaTextView.setText(getResources().getString(R.string.overweight));
            nadwaga.setText("" + niedowagaNadwaga + " " + jednostkaWagi);
        }
    }

    public void ustawKursorNakoncu(EditText edittext) {
        edittext.setSelection(edittext.getText().length());

    }

}