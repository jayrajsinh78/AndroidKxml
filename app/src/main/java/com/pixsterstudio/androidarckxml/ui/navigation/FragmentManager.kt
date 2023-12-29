package com.pixsterstudio.androidarckxml.ui.navigation

import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

class FragmentManager
@Inject constructor(private val fragmentManager: androidx.fragment.app.FragmentManager,
    @Named("placeholder") private val placeHolder: Int
) : FragmentHandler {
    override fun openFragment(
        baseFragment: BaseFragment<*>,
        option: Option,
        isAddToBackStack: Boolean,
        tag: String
    ) {

        val fragmentTransaction = fragmentManager.beginTransaction()

        when (option) {
            Option.ADD -> fragmentTransaction.add(placeHolder, baseFragment, tag)
            Option.REPLACE -> fragmentTransaction.replace(placeHolder, baseFragment, tag)
            Option.SHOW -> {
                if (baseFragment.isAdded)
                    fragmentTransaction.show(baseFragment)
            }

            Option.HIDE -> {
                if (baseFragment.isAdded)
                    fragmentTransaction.hide(baseFragment)
            }
        }

        if (isAddToBackStack)
            fragmentTransaction.addToBackStack(tag)

        fragmentTransaction.commit()
    }

    override fun showFragment(
        fragmentToShow: BaseFragment<*>,
        vararg fragmentToHide: BaseFragment<*>
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragmentToShow.isAdded) {
            fragmentTransaction.show(fragmentToShow)
        } else
            openFragment(fragmentToShow, Option.ADD, false, "home")

        for (fragment in fragmentToHide) {
            if (fragment.isAdded)
                fragmentTransaction.hide(fragment)
        }
        fragmentTransaction.commit()
    }

    override fun clearFragmentHistory(tag: String) {
        fragmentManager.popBackStackImmediate(
            tag,
            androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }


}