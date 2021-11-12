package com.example.basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre,edtCedula,edtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCedula=(EditText) findViewById(R.id.edtCedula);
        edtNombre=(EditText) findViewById(R.id.edtNombre);
        edtTelefono=(EditText) findViewById(R.id.edtTelefono);
    }

    public void registrar (View view){
        //crear objeto para referir a la base de datos
        AdminBD admin=new AdminBD(this,"BaseDatos",null,1);
        //se abre para escritura y lectura
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        //tomar lo que el usuario digita
        String cedula =edtCedula.getText().toString();
        String nombre =edtNombre.getText().toString();
        String telefono = edtTelefono.getText().toString();

        //validacion
        if (!cedula.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()){
            //crear variable registro tipo contentvalue
            ContentValues registro=new ContentValues();

            registro.put("cedula",cedula);
            registro.put("nombre",nombre);
            registro.put("telefono",telefono);

            //insertar registro
            BaseDatos.insert("usuario",null,registro);
            //cerrar bd
            BaseDatos.close();

            //limpiar campos
            edtTelefono.setText("");
            edtNombre.setText("");
            edtCedula.setText("");

            Toast.makeText(this,"Datos registrados correctamente",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Debe ingresar todos los datos",Toast.LENGTH_LONG).show();
        }
    }

    public void consultar (View view){
        AdminBD adminBD=new AdminBD(this,"BaseDatos",null,1);
        SQLiteDatabase BaseDatos=adminBD.getWritableDatabase();

        //ver si existe el usuario
        String cedula1=edtCedula.getText().toString();

        if(!cedula1.isEmpty()){
            //para moverse en el registro, hacer la busqueda con variable de tipo cursor

            Cursor fila=BaseDatos.rawQuery("select nombre,telefono from usuario where cedula="+cedula1,null);//consulta

            //para mover cursor hasta el primer registro
            if (fila.moveToFirst()){
                edtNombre.setText(fila.getString(0));
                edtTelefono.setText(fila.getString(1));
                BaseDatos.close();
            }else{
                Toast.makeText(this,"No se encontro el usuario",Toast.LENGTH_LONG).show();
            }
        }
    }
}