package com.pixsterstudio.androidarckxml.ui.auth.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.pixsterstudio.androidarckxml.R
import com.pixsterstudio.androidarckxml.databinding.AuthenticationActivityBinding
import com.pixsterstudio.androidarckxml.di.components.ActivityComponent
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.utils.Constants

class AuthenticationActivity:BaseActivity() {

    lateinit var binding: AuthenticationActivityBinding

    override fun createViewBinding(): View {
        binding = AuthenticationActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int {
        return R.id.placeHolder
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (appPreferences.getBoolean(Constants.DONT_SHOW_ON_BOARDING)){
        //    load(LoginFragment(), isReplace = true, isAddToBackStack = false)
        }else{
         //   load(OnBoardingMainFragment(), isReplace = true, isAddToBackStack = false)
        }


    }





    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}