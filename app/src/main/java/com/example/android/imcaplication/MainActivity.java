package com.example.android.imcaplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    EditText edtPeso;
    EditText edtAltura;

    TextView txtDiag;

    Button btnCalcular;
    RadioButton rbMasc;
    RadioButton rbFen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtPeso = (EditText) findViewById(R.id.edtPeso);
        edtAltura = (EditText) findViewById(R.id.edtAltura);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        rbFen = (RadioButton) findViewById(R.id.rbFemenino);
        txtDiag = (TextView) findViewById(R.id.edtDiag);

        btnCalcular = (Button) findViewById(R.id.btnCalcular);


    }

    public void calcular(View view) {
        double peso = 0;
        double altura = 0;
        double edad = 0;
        double imc = 0;
        double pi = 0;
        double piTruncated = 0;
        double pM = 23;
        double pF = 21.5;



        String[] diagnosticos = {"Desnutricion", "Bajo Peso", "Normal", "Sobrepeso", "Obesidad", "Obes. Marcada", "Obs. Morv=bida"};

        String diagnostico = "";


        if (!edtPeso.getText().toString().equals("")) {
            peso = Double.parseDouble(edtPeso.getText().toString());
        } else {
            edtPeso.setError("Complete el peso!");
            return;
        }

        if (!edtAltura.getText().toString().equals("")) {
            altura = Double.parseDouble(edtAltura.getText().toString());
        } else {
            edtAltura.setError("Complete la altura!");
            return;
        }


        imc = peso / Math.pow(altura, 2) * 10000;

        Double imcTruncated = BigDecimal.valueOf(imc)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        if (rbMasc.isChecked()) {


            if (imc < 18) {
                diagnostico = diagnosticos[0];
            }
            if (imc >= 18 && imc < 20) {
                diagnostico = diagnosticos[1];
            }
            if (imc >= 20 && imc < 25) {
                diagnostico = diagnosticos[2];
            }
            if (imc >= 25 && imc < 30) {
                diagnostico = diagnosticos[3];
            }
            if (imc >= 30 && imc < 35) {
                diagnostico = diagnosticos[4];
            }
            if (imc >= 35 && imc < 40) {
                diagnostico = diagnosticos[5];
            }
            if (imc >= 40) {
                diagnostico = diagnosticos[6];
            }
            pi = pM * (altura*altura)/10000;



        } else if (rbFen.isChecked()) {

            rbMasc.setChecked(false);
            if (imc < 17) {
                diagnostico = diagnosticos[0];
            }
            if (imc >= 17 && imc < 20) {
                diagnostico = diagnosticos[1];
            }
            if (imc >= 20 && imc < 25) {
                diagnostico = diagnosticos[2];
            }
            if (imc >= 25 && imc < 30) {
                diagnostico = diagnosticos[3];
            }
            if (imc >= 30 && imc < 35) {
                diagnostico = diagnosticos[4];
            }
            if (imc >= 35 && imc < 40) {
                diagnostico = diagnosticos[5];
            }
            if (imc >= 40) {
                diagnostico = diagnosticos[6];
            }


            pi = pF * (altura*altura)/10000;


        } else {
            rbMasc.setError("Seleccione el Genero");
            rbFen.setError("Seleccione el Genero");
            return;
        }


        piTruncated = BigDecimal.valueOf(pi)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        txtDiag.setText(" IMC: " + imcTruncated + " Kg/mt²"+ " \n Diagnostico: " + diagnostico + " \n Peso Ideal: " + piTruncated + " Kg");


    }


    public void limpar(View view) {
        edtAltura.setText("");
        edtPeso.setText("");
        txtDiag.setText("");
        edtPeso.requestFocus();

    }

}