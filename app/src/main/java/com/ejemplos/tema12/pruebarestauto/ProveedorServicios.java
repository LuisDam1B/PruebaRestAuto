package com.ejemplos.tema12.pruebarestauto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by xusa on 28/12/2017.
 */

public interface ProveedorServicios {
    @GET("autos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Autos>> getAutos();//@Query("search") String search);

    @GET("autos/{id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Autos>> getAuto(@Path("id") int id);//el idGame se sustituye por el {id}

    @POST("autos")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestJSon> insertarAuto(@Body Autos auto);//@body permite pasar los parametros

    @PUT("autos/{id}")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<RespuestJSon> modificarAuto(@Path("id") int id, @Body Autos auto);//@body permite pasar los parametros

     @DELETE("autos/{id}")
     @Headers({"Accept: application/json","Content-Type: application/json"})
     Call<RespuestJSon> delAuto(@Path("id") int id);//el idGame se sustituye por el {id}
}
