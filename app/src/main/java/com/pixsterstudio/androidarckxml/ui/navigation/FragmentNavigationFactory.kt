package com.pixsterstudio.androidarckxml.ui.navigation

import android.os.Bundle
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import javax.inject.Inject

class FragmentNavigationFactory @Inject constructor(val fragmentManager: FragmentManager) {

    private var fragment:BaseFragment<*>?=null
    private var tag:String?=null


    fun <T:BaseFragment<*>> create(aClass: Class<T>):FragmentActionPerformer<T>{
        return make(getFragment(aClass))
    }

    fun <T :BaseFragment<*>> make(fragment: T?):FragmentActionPerformer<T>{
        this.fragment = fragment
        tag = fragment!!.javaClass.simpleName
        return Provider(fragment,this)
    }

    private inner class Provider<T : BaseFragment<*>>(private val fragment:T,private val navigationFactory: FragmentNavigationFactory):FragmentActionPerformer<T>{
        override fun add(toBackStack: Boolean) {
            navigationFactory.fragmentManager.openFragment(fragment,Option.ADD,toBackStack,tag!!)
        }


        override fun replace(toBackStack: Boolean) {
            navigationFactory.fragmentManager.openFragment(fragment,Option.REPLACE,toBackStack,tag!!)
        }


        override fun show(fragmentToShow: BaseFragment<*>, vararg fragmentToHide: BaseFragment<*>) {
            navigationFactory.fragmentManager.showFragment(fragmentToShow,fragmentToHide.iterator().next())
        }

        override fun setBundle(bundle: Bundle): FragmentActionPerformer<*> {
            fragment.arguments = bundle
            return this
        }


        override fun clearHistory(tag: String?): FragmentActionPerformer<*> {
            navigationFactory.fragmentManager.clearFragmentHistory(tag!!)
            return this
        }

        override fun hasData(passable: Passable<T>): FragmentActionPerformer<*> {
           passable.passData(fragment)
            return this
        }

    }

    fun <T : BaseFragment<*>> getFragment(aClass: Class<T>): T? {

        try {
            return aClass.getDeclaredConstructor().newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }


}