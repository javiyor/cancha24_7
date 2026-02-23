package com.example.cancha24_7.data.source.network

import com.example.cancha24_7.network.HorarioApiService
import com.example.cancha24_7.viewmodel.network.CanchaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Usamos 'object' para implementar el patrón Singleton.
// Esto asegura que solo haya una instancia de Retrofit y de los servicios en toda la app.
object RetrofitClient {

    // IMPORTANTE: Reemplaza esta URL con la URL base real de tu API.
    // Debe terminar con una barra diagonal "/".
    private const val BASE_URL = "https://5amsoftware.com.ar/cancha/"

    // Creamos la instancia de Retrofit de forma "perezosa" (lazy).
    // Solo se construirá la primera vez que se necesite, ahorrando recursos.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Usamos Gson para convertir JSON a objetos Kotlin
            .build()
    }

    // Creamos una instancia "lazy" para tu servicio de canchas.
    // Retrofit implementará automáticamente la interfaz 'CanchaApiService'.
    val canchaApi: CanchaApiService by lazy {
        retrofit.create(CanchaApiService::class.java)
    }

    // Creamos una instancia "lazy" para tu servicio de horarios.
    // Retrofit implementará automáticamente la interfaz 'HorarioApiService'.
    val horarioApi: HorarioApiService by lazy {
        retrofit.create(HorarioApiService::class.java)
    }
}

