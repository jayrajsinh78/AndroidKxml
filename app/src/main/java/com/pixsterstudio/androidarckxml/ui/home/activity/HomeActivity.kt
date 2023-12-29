package com.pixsterstudio.androidarckxml.ui.home.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pixsterstudio.androidarckxml.R
import com.pixsterstudio.androidarckxml.databinding.HomeActivityBinding
import com.pixsterstudio.androidarckxml.di.components.ActivityComponent
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.home.fragment.WebViewFragment
import com.pixsterstudio.androidarckxml.ui.viewmodel.HomeViewModel

class HomeActivity:BaseActivity(){

    lateinit var binding: HomeActivityBinding

    private val homeViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]
    }

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun findFragmentPlaceHolder(): Int {
        return R.id.placeHolder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(WebViewFragment::class.java).replace(true)
    }

    override fun onPause() {
        super.onPause()
    }


}