package com.acmel.proyecto.miscontactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acmel.proyecto.miscontactos.util.TextChangedListener;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
    }

    private void inicializarComponentesUI() {

        txtNombre = (EditText)findViewById(R.id.cmpNombre);
        txtTelefono = (EditText)findViewById(R.id.cmpTelefono);
        txtEmail = (EditText) findViewById(R.id.cmpEmail);
        txtDireccion = (EditText) findViewById(R.id.cmpDireccion);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setEnabled(false);

        txtNombre.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
                activarBoton();
            }
        });

        txtTelefono.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
                activarBoton();
            }
        });

        txtEmail.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
                activarBoton();
            }
        });

        txtDireccion.addTextChangedListener(new TextChangedListener() {
            @Override
            public void onTextChanged(CharSequence seq, int start, int before, int count) {
                //btnAgregar.setEnabled(!seq.toString().trim().isEmpty());
                activarBoton();
            }
        });


    }

    public void onClick(View view) {
        String mesg = String.format("%s ha sido agregado a la lista!",txtNombre.getText());
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpíarCampos();
    }

    private void limpíarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        txtNombre.requestFocus();
    }

    private void  activarBoton(){
        String nombre, telefono, email, direccion;
        nombre = txtNombre.getText().toString();
        telefono = txtTelefono.getText().toString();
        email = txtEmail.getText().toString();
        direccion = txtDireccion.getText().toString();

        if (nombre.length()> 0
                && telefono.length()>0
                && email.length()>0
                && direccion.length()>0){
                btnAgregar.setEnabled(true);
        }else {
            btnAgregar.setEnabled(false);
        }
    }
    }

