package com.example.registro_post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUser, txtNames, txtPwd;
    Button btnReg;

    String url="http://santacruza.proyectosutd.com/login/registrar_post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNames = findViewById(R.id.txtNamesReg);
        txtUser = findViewById(R.id.txtUserReg);
        txtPwd = findViewById(R.id.txtPwdReg);
        btnReg = findViewById(R.id.btnRegistrarReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    void registrar() {

        StringRequest sr = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),
                            "Error de autentificación", Toast.LENGTH_SHORT).show();
                }
            },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error instanceof AuthFailureError) {

                        Toast.makeText(getApplicationContext(),
                                "Error de autentificación", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof NetworkError) {

                        Toast.makeText(getApplicationContext(),
                                "No hay internet", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof NoConnectionError) {

                        Toast.makeText(getApplicationContext(),
                                "No se pudo establecer una conexión", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof ParseError) {

                        Toast.makeText(getApplicationContext(),
                                "No se ha recibido una respuesta valida", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof ServerError) {

                        Toast.makeText(getApplicationContext(),
                                "El servidor ha emitido un error como respuesta", Toast.LENGTH_SHORT).show();

                    } else if (error instanceof TimeoutError) {

                        Toast.makeText(getApplicationContext(),
                                "Tiempo de espera agotado", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(),
                                "No se ha podido registrar"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String user = txtUser.getText().toString();
                    String names = txtNames.getText().toString();
                    String pwd = txtPwd.getText().toString();

                    Map<String, String> params = new HashMap<>();

                    params.put("user", user);
                    params.put("names", names);
                    params.put("pwd", pwd);

                    return params;
                }
            };
    }
}