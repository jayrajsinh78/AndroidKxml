package com.pixsterstudio.androidarckxml.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pixsterstudio.androidarckxml.di.components.ActivityComponent
import com.pixsterstudio.androidarckxml.di.components.FragmentComponent
import com.pixsterstudio.androidarckxml.di.modules.FragmentModule
import com.pixsterstudio.androidarckxml.ui.navigation.Navigator
import com.google.android.material.snackbar.Snackbar
import com.pixsterstudio.androidarckxml.R
import com.pixsterstudio.androidarckxml.di.HasComponent
import com.pixsterstudio.androidarckxml.session.AppPreferences
import com.pixsterstudio.androidarckxml.session.Session
import com.pixsterstudio.androidarckxml.ui.auth.activity.AuthenticationActivity
import com.pixsterstudio.androidarckxml.utils.Constants
import javax.inject.Inject

abstract class BaseFragment<T: ViewBinding>:Fragment() , HasComponent<FragmentComponent> {

    private var _binding: T? = null

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val binding: T
        get() = _binding!!

    override val component: FragmentComponent
        get() {
            return getComponent(ActivityComponent::class.java)?.plus(FragmentModule(this))!!
        }

    @Inject
    lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        inject(component)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = createViewBinding(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated()
    }


    protected abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?,attachToRoot: Boolean): T

    protected abstract fun viewCreated()

    protected abstract fun inject(fragmentComponent: FragmentComponent)

    fun showMessage(message: String) {
        showSnackBar(message)
    }

    private fun showSnackBar(message: String) {
       // hideKeyBoard()
        if (view != null) {
            val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            snackbar.duration = 3000
            snackbar.setActionTextColor(Color.WHITE)
            snackbar.setAction("OK") { snackbar.dismiss() }
            val snackView = snackbar.getView()
            val textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.maxLines = 4

            snackView.setBackgroundColor(requireActivity().getResources().getColor(R.color.C_0B1C12))
            snackbar.show()
        }
    }

    open fun onBackActionPerform(): Boolean {
        return true
    }

    fun clearUser(){
        appPreferences.clearAll()
        session.clearSession()
        appPreferences.putBoolean(Constants.DONT_SHOW_ON_BOARDING,true)
        navigator.loadActivity(AuthenticationActivity::class.java).byFinishingAll().start()
    }
    protected fun <C> getComponent(componentType: Class<C>): C? {
        return componentType.cast((activity as HasComponent<C>).component)

    }
    fun hideKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideKeyboard()
        }
    }

    fun showKeyBoard() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showKeyboard()
        }
    }
}