package com.rajutukadiya_practical.app.ui.login.viewmodel

import android.app.Application
import android.content.Intent
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rajutukadiya_practical.app.model.User
import com.rajutukadiya_practical.app.network.BackEndApi
import com.rajutukadiya_practical.app.network.WebServiceClient
import com.rajutukadiya_practical.app.util.SingleLiveEvent
import com.rajutukadiya_practical.app.util.Util


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application), Callback<User> {


    var btnSelected: ObservableBoolean? = null
    var email: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        email = ObservableField("")
        password = ObservableField("")
        userLogin = MutableLiveData<User>()
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 8)


    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(email?.get()!!) && s.toString().length >= 8)


    }

    fun login() {
        progressDialog?.setValue(true)
        WebServiceClient.client.create(BackEndApi::class.java).LOGIN(
            email = email?.get()!!
            , password = password?.get()!!, device_type = "jkshfjk"
        )
            .enqueue(this)

    }

    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.setValue(false)

        userLogin?.value = response?.body()



    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.setValue(false)

    }

}