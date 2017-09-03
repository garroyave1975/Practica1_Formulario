package com.example.gabrielarroyave.practica1_formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;



public class MainActivity extends AppCompatActivity {

    private String loggin, password, confirmar, email, sexo, ciudad, hobbies, nacimiento, vacios = "Campos obligatorios",
            vloggin, vpassword, vconfirmar, vemail, vhobbies, hobbievacio = "no", vfecha;
    private EditText eUsuario, ePassword, eConfirmar, eEmail;
    private RadioButton rMasculino, rFemenino;
    private TextView tLoggin, tPassword, tConfirmar, tEmail, tSwhodate, tHobbies, tInformacion, tInfoError;
    private CheckBox cLeer, cCine, cComer, cDeporte;
    private Spinner sCiudad;
    private Button bAceptar;

    private static final String TAG = "MainActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tLoggin = (TextView) findViewById(R.id.tLoggin);
        tPassword = (TextView) findViewById(R.id.tPassword);
        tConfirmar = (TextView) findViewById(R.id.tConfirmar);
        tEmail = (TextView) findViewById(R.id.tEmail);
        tSwhodate = (TextView) findViewById(R.id.tShowdate);
        tHobbies = (TextView) findViewById(R.id.tHobbies);
        tInformacion = (TextView) findViewById(R.id.tInformacion);
        tInfoError = (TextView) findViewById(R.id.tInfoError);
        eUsuario = (EditText) findViewById(R.id.eUsuario);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eConfirmar = (EditText) findViewById(R.id.eConfirmar);
        eEmail = (EditText) findViewById(R.id.eEmail);
        rMasculino = (RadioButton) findViewById(R.id.rMasculino);
        rFemenino = (RadioButton) findViewById(R.id.rFemenino);
        cCine = (CheckBox) findViewById(R.id.cCine);
        cLeer = (CheckBox) findViewById(R.id.cLeer);
        cComer = (CheckBox) findViewById(R.id.cComer);
        cDeporte = (CheckBox) findViewById(R.id.cDeporte);
        sCiudad = (Spinner) findViewById(R.id.sCiudad);
        bAceptar = (Button) findViewById(R.id.bAceptar);


        //Código para el datepicker
        mDisplayDate = (TextView) findViewById(R.id.tShowdate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: "
                        + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);

            }
        };
        //fin código date picker

        //Codigo para spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCiudad.setAdapter(adapter);
        sCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Fin de codigo para spinner

    }


    public void fAceptar(View view) {

        loggin = eUsuario.getText().toString();
        password = ePassword.getText().toString();
        confirmar = eConfirmar.getText().toString();
        email = eEmail.getText().toString();
        vloggin = tLoggin.getText().toString();
        vpassword = tPassword.getText().toString();
        vconfirmar = tConfirmar.getText().toString();
        vemail = tEmail.getText().toString();
        vhobbies = tHobbies.getText().toString();
        vfecha = tSwhodate.getText().toString();

        if (rMasculino.isChecked()) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }
        if (cCine.isChecked() || cComer.isChecked() || cLeer.isChecked() || cDeporte.isChecked()) {

            if (cCine.isChecked()) {
                hobbies = "Cine " + hobbies;
            }
            if (cComer.isChecked()) {
                hobbies = "Comer " + hobbies;
            }
            if (cLeer.isChecked()) {
                hobbies = "Leer " + hobbies;
            }
            if (cDeporte.isChecked()) {
                hobbies = "Deporte " + hobbies;
            }
            hobbievacio = "no";
        } else {
            vacios = vacios + " '" + vhobbies + "'";
            hobbievacio = "si";

        }


        if (loggin.isEmpty() || password.isEmpty() || confirmar.isEmpty() || vfecha.isEmpty() || email.isEmpty() || hobbievacio == "si") {
            tInformacion.setText("");
            if (email.isEmpty()) {
                vacios = vacios + " '" + vemail + "'";
            }
            if (loggin.isEmpty()) {
                vacios = vacios + " '" + vloggin + "'";
            }
            if (password.isEmpty()) {
                vacios = vacios + " '" + vpassword + "'";
            }
            if (confirmar.isEmpty()) {
                vacios = vacios + " '" + vconfirmar + "'";
            }
            if (vfecha.isEmpty()) {
                vacios = vacios + "Fecha de nacimiento";
            }
            tInfoError.setText(vacios);
            vacios = "Debe llenar los siguientes campos:";


        } else {
            if (password.equals(confirmar)) {
                tInfoError.setText(" ");
                tInformacion.setText("\nUsuario: " + loggin + "\nEmail: " + email + "\nSexo: " + sexo + "\nFecha: " + vfecha + "\nCiudad: " + ciudad + "\nHobbie: " + hobbies);
                loggin = "";
                email = "";
                sexo = "";
                hobbies = "";
            } else {
                tInfoError.setText("Password no coincide: \n");
                tInformacion.setText("");

            }


        }
    }

}