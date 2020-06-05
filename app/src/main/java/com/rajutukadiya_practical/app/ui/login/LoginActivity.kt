package com.rajutukadiya_practical.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rajutukadiya_practical.app.home.MainActivity
import com.rajutukadiya_practical.app.R
import com.rajutukadiya_practical.app.databinding.ActivityLoginBinding
import com.rajutukadiya_practical.app.ui.login.viewmodel.LoginViewModel
import com.rajutukadiya_practical.app.util.CustomeProgressDialog




class LoginActivity : AppCompatActivity() {

    var   binding: ActivityLoginBinding? = null
    var viewmodel: LoginViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(this)
        initObservables()


    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })

        viewmodel?.userLogin?.observe(this, Observer { user ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }


}
