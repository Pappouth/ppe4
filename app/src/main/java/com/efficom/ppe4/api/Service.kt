package com.efficom.ppe4.api

import com.efficom.ppe4.Terminal
import com.efficom.ppe4.TerminalReservation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface Service {
    @GET("login.php")
    fun login(@Query("login") login: String, @Query("mdp") password: String): Call<ResponseBody>

    @GET("reservations_list.php")
    fun reservationsList(@Query("token") token: String): Call<List<TerminalReservation>>

    @GET("terminals_list.php")
    fun terminalsList(): Call<List<Terminal>>

    @GET("duree_reservation.php")
    fun resaDates(@Query("token") token: String, @Query("prod") prod: String, @Query("quantite") qtt: Int, @Query("debut_resa") debutResa: String, @Query("fin_resa") finResa: String, @Query("prix_total") prixTotal: Double, @Query("prix_prod") prixProd: Double): Call<ResponseBody>

    @GET("info_compte.php")
    fun infoCompte(@Query("token") token: String): Call<ResponseBody>
}