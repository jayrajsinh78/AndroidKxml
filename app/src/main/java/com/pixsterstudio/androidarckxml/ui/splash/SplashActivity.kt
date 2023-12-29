package com.pixsterstudio.androidarckxml.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.pixsterstudio.androidarckxml.databinding.SplashActivityBinding
import com.pixsterstudio.androidarckxml.di.components.ActivityComponent
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.home.activity.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var splashActivityBinding: SplashActivityBinding

    override fun createViewBinding(): View {
        splashActivityBinding = SplashActivityBinding.inflate(layoutInflater)
        return splashActivityBinding.root
    }


    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun findFragmentPlaceHolder(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //   Log.d("Time Frame", "onCreate: ${parseTime(2.78798)}")

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            loadActivity(HomeActivity::class.java).byFinishingAll().start()
            /* if (appPreferences.getBoolean(Constants.IS_LOGIN)){
                  loadActivity(HomeActivity::class.java).byFinishingAll().start()
             }else{
                  loadActivity(AuthenticationActivity::class.java).byFinishingAll().start()
             }*/
        }
    }


}