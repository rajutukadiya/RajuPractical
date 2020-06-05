package com.rajutukadiya_practical.app.network


import com.rajutukadiya_practical.app.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface BackEndApi {
    @FormUrlEncoded
    @POST("Maid/maid_login")
    fun LOGIN(@Field("sEmailID") email: String,
              @Field("sPassword") password: String,
              @Field("sAuthenticationCode") device_type:String): Call<User>




}

