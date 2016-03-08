package com.acmel.proyecto.miscontactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.acmel.proyecto.miscontactos.util.Contacto;
import com.acmel.proyecto.miscontactos.util.TextChangedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private List<Contacto> contactos = new ArrayList<Contacto>();
    private ListView contactsListView;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarTabs();
    }

    private void inicializarTabs() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Crear");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Lista");
        tabHost.addTab(spec);
    }

    private void inicializarComponentesUI() {

        txtNombre = (EditText)findViewById(R.id.cmpNombre);
        txtTelefono = (EditText)findViewById(R.id.cmpTelefono);
        txtEmail = (EditText) findViewById(R.id.cmpEmail);
        txtDireccion = (EditText) findViewById(R.id.cmpDireccion);
        contactsListView = (ListView)findViewById(R.id.listView);

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
        agregarContacto(
                txtNombre.getText().toString(),
                txtTelefono.getText().toString(),
                txtEmail.getText().toString(),
                txtDireccion.getText().toString()
        );
        String mesg = String.format("%s ha sido agregado a la lista!",txtNombre.getText());
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        inicializarListView();
        limpíarCampos();
    }

    private void inicializarListView() {

    }

    private void agregarContacto(String nombre, String telefono, String email, String direccion) {
        contactos.add(new Contacto(nombre,telefono,email,direccion));
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

