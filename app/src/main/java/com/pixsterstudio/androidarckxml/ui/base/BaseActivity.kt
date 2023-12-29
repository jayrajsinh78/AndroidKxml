package com.pixsterstudio.androidarckxml.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pixsterstudio.androidarckxml.di.HasComponent
import com.pixsterstudio.androidarckxml.di.Injector
import com.pixsterstudio.androidarckxml.di.components.ActivityComponent
import com.pixsterstudio.androidarckxml.di.components.DaggerActivityComponent
import com.pixsterstudio.androidarckxml.session.AppPreferences
import com.pixsterstudio.androidarckxml.session.Session
import com.pixsterstudio.androidarckxml.ui.auth.activity.AuthenticationActivity
import com.pixsterstudio.androidarckxml.ui.navigation.ActivityHandler
import com.pixsterstudio.androidarckxml.ui.navigation.FragmentActionPerformer
import com.pixsterstudio.androidarckxml.ui.navigation.FragmentNavigationFactory
import com.pixsterstudio.androidarckxml.ui.navigation.Navigator
import com.pixsterstudio.androidarckxml.utils.Constants
import com.smartsociety.app.ui.manager.ActivityManager
import javax.inject.Inject

abstract class BaseActivity:AppCompatActivity(),Navigator,HasComponent<ActivityComponent>{
    override val component: ActivityComponent
        get() = activityComponent

    private lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationFactory: FragmentNavigationFactory

    @Inject
    lateinit var activityManager: ActivityManager

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = DaggerActivityComponent.builder()
            .bindApplicationComponent(Injector.INSTANCE.appComponent)
            .bindActivity(this).build()
        inject(activityComponent)
        super.onCreate(savedInstanceState)
        setContentView(createViewBinding())
    }
    abstract fun createViewBinding(): View

    abstract fun findFragmentPlaceHolder(): Int

    abstract fun inject(activityComponent: ActivityComponent)



    fun <F : BaseFragment<*>> getCurrentFragment(): F? {
        return if (findFragmentPlaceHolder() == 0) null else supportFragmentManager.findFragmentById(
            findFragmentPlaceHolder()
        ) as F
    }


    override fun onBackPressed() {
        Log.d("Pressing Back", " load onBackPressed: ")
       // hideKeyboard()
        val currentFragment = getCurrentFragment<BaseFragment<*>>()
        if (currentFragment == null)
            super.onBackPressed()
        else if (currentFragment.onBackActionPerform() && shouldGoBack())
            super.onBackPressed()

    }

    protected fun shouldGoBack(): Boolean {
        return true
    }


    override fun <T:BaseFragment<*>> load(tClass: Class<T>): FragmentActionPerformer<T> {
       return navigationFactory.create(tClass)
    }

    override fun loadActivity(aClass: Class<out BaseActivity>): ActivityHandler {
        return activityManager.create(aClass)
    }


    override fun goBack() {
       onBackPressed()
    }

    override fun finish() {
        super.finish()
    }

    fun clearUser(){
        appPreferences.clearAll()
        session.clearSession()
        appPreferences.putBoolean(Constants.DONT_SHOW_ON_BOARDING,true)
        loadActivity(AuthenticationActivity::class.java).byFinishingAll().start()
    }


    fun showKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun hideKeyboard() {
        // Check if no view has focus:
        val view = this.currentFocus
        if (view != null) {
            val inputManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

    }
}