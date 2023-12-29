package com.pixsterstudio.androidarckxml.ui.navigation

import android.os.Bundle
import androidx.annotation.UiThread
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment

@UiThread
interface FragmentActionPerformer<T> {

    fun add(toBackStack: Boolean)

    fun replace(toBackStack: Boolean)

    fun show(fragmentToShow: BaseFragment<*>, vararg fragmentToHide: BaseFragment<*>)

    fun setBundle(bundle: Bundle): FragmentActionPerformer<*>

    fun clearHistory(tag: String?): FragmentActionPerformer<*>

    fun hasData(passable: Passable<T>): FragmentActionPerformer<*>

}