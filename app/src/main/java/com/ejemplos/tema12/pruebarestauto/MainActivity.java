package com.ejemplos.tema12.pruebarestauto;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


enum Servicios{POST,GET,PUT,DELETE}
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Autos> autos;
    Button b1, b3, b4, b5, b6;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autos = new ArrayList<>();
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.texto);
        b1 = findViewById(R.id.button);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b1.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        textView.setText("");
        switch (v.getId()) {
            case R.id.button:

                cargarAutos();
                break;
            case R.id.button3:
                anyadirModificarAuto(new Autos("SEAT", "ALTEA", "sds", "AFAF"),Servicios.POST);
                break;
            case R.id.button4:
                cargarAuto(21);
                break;
            case R.id.button5:
                anyadirModificarAuto(new Autos("PEPEITA", "xxxs", "sds", "AFAF", 24),Servicios.PUT);
                break;
            case R.id.button6:
                eliminarAuto(24);
                break;
        }
    }

    private void anyadirModificarAuto(Autos auto, Servicios servicio)
    {
        ProveedorServicios proveedorServicios = crearRetrofit();
        Call<RespuestJSon> responseCall;
        if(servicio==Servicios.POST) responseCall=proveedorServicios.insertarAuto(auto);
        else  responseCall=proveedorServicios.modificarAuto(auto.id, auto);
        //Llamada asíncrona gestionada por Retrofit y para ahorrar hilos
        responseCall.enqueue(new Callback<RespuestJSon>() {
            @Override
            public void onResponse(Call<RespuestJSon> call, Response<RespuestJSon> response) {
                RespuestJSon autosResponse = response.body();
                if (autosResponse != null) {
                    textView.setText(autosResponse.mensaje.toString());
                } else textView.setText(response.message().toString());
            }

            @Override
            public void onFailure(Call<RespuestJSon> call, Throwable t) {
                Log.e("Error", t.toString());
                Toast.makeText(MainActivity.this, "Error" + t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void cargarAuto(int id) {
        ProveedorServicios proveedorServicios = crearRetrofit();

        Call<List<Autos>> responseCall = proveedorServicios.getAuto(id);

        //Llamada asíncrona gestionada por Retrofit y para ahorrar hilos
        responseCall.enqueue(new Callback<List<Autos>>() {
            @Override
            public void onResponse(Call<List<Autos>> call, Response<List<Autos>> response) {
                List<Autos> autosResponse = response.body();

                if (autosResponse != null) {
                    for (Autos a : autosResponse) {

                        textView.setText(textView.getText()+"\n"+a.toString());
                    }
                }else textView.setText("No encontrado autos");

            }
            @Override
            public void onFailure(Call<List<Autos>> call, Throwable t) {
                Log.e("Error", t.toString());
                Toast.makeText(MainActivity.this, "Error" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void eliminarAuto(int id) {
        ProveedorServicios proveedorServicios = crearRetrofit();
        Call<RespuestJSon> responseCall = proveedorServicios.delAuto(id);

        //Llamada asíncrona gestionada por Retrofit y para ahorrar hilos
        responseCall.enqueue(new Callback<RespuestJSon>() {
            @Override
            public void onResponse(Call<RespuestJSon> call, Response<RespuestJSon> response) {
                RespuestJSon autosResponse = response.body();

                if (autosResponse != null) {

                        textView.setText("Se han eliminado " + autosResponse.mensaje);

                }
                else textView.setText(response.message());
            }

            @Override
            public void onFailure(Call<RespuestJSon> call, Throwable t) {
                Log.e("Error", t.toString());
                Toast.makeText(MainActivity.this, "Error" + t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void cargarAutos() {
        ProveedorServicios proveedorServicios = crearRetrofit();

        Call<List<Autos>> responseCall = proveedorServicios.getAutos();

        //Llamada asíncrona gestionada por Retrofit y para ahorrar hilos
        responseCall.enqueue(new Callback<List<Autos>>() {
            @Override
            public void onResponse(Call<List<Autos>> call, Response<List<Autos>> response) {
                List<Autos> autosResponse = response.body();

                if (autosResponse != null) {
                    List<Autos> autos = autosResponse;
                    for (Autos auto : autos)
                        textView.setText(textView.getText() + "\n" + auto.toString());
                } else textView.setText(response.message());
            }

            @Override
            public void onFailure(Call<List<Autos>> call, Throwable t) {
                Log.e("Error", t.toString());
                Toast.makeText(MainActivity.this, "Error" + t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private ProveedorServicios crearRetrofit() {

        String url = "http://10.0.2.2/autosdb/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ProveedorServicios.class);
    }
}
