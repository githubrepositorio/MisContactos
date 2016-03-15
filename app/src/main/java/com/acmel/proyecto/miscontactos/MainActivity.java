package com.acmel.proyecto.miscontactos;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.acmel.proyecto.miscontactos.util.ContactListAdapter;
import com.acmel.proyecto.miscontactos.util.Contacto;
import com.acmel.proyecto.miscontactos.util.TextChangedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtTelefono, txtEmail, txtDireccion;
    private ArrayAdapter<Contacto> adapter;
    private ListView contactsListView;
    private ImageView imgViewContacto;
    private Button btnAgregar;
    private int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentesUI();
        inicializarListaContactos();
        inicializarTabs();
    }

    private void inicializarListaContactos() {
        adapter = new ContactListAdapter(this,new ArrayList<Contacto>());
        contactsListView.setAdapter(adapter);

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
        imgViewContacto = (ImageView) findViewById(R.id.imgViewContacto);
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
                txtDireccion.getText().toString(),
                (String)imgViewContacto.getTag() //

        );
        String mesg = String.format("%s ha sido agregado a la lista!",txtNombre.getText());
        Toast.makeText(this, mesg, Toast.LENGTH_SHORT).show();
        btnAgregar.setEnabled(false);
        limpíarCampos();
    }



    private void agregarContacto(String nombre, String telefono, String email, String direccion, String imageUri) {
        Contacto nuevo = new Contacto(nombre,telefono,email,direccion,imageUri);
        adapter.add(nuevo);
    }

    private void limpíarCampos() {
        txtNombre.getText().clear();
        txtTelefono.getText().clear();
        txtEmail.getText().clear();
        txtDireccion.getText().clear();
        // Restablecer la imagen predeterminada del contacto
        imgViewContacto.setImageResource(R.drawable.contacto);
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

    public void onImgClik(View view) {
        Intent intent = null;
        //Verificamos la version de la plataforma
        if(Build.VERSION.SDK_INT < 19 ){
            //android JellyBean 4.3 o anteriores
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }else{
            //android KitKat  4.4 o superiores
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(intent,request_code);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode == RESULT_OK && requestCode == request_code){
           imgViewContacto.setImageURI(data.getData());
           // Utilizamos el atributo TAG para almacenar la Uri al archivo seleccionado
           imgViewContacto.setTag(data.getData());


       }
    }
}

