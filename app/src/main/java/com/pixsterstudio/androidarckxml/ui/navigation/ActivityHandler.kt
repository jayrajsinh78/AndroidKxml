package com.pixsterstudio.androidarckxml.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.core.util.Pair
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment


@UiThread
interface ActivityHandler {

    fun start()

    fun addBundle(bundle: Bundle): ActivityHandler

    fun addSharedElements(pairs: List<Pair<View, String>>): ActivityHandler

    fun byFinishingCurrent(): ActivityHandler

    fun byFinishingAll(): ActivityHandler

    fun <T : BaseFragment<*>> setPage(page: Class<T>): ActivityHandler

    fun forResult(requestCode: Int): ActivityHandler

    fun forResult(startForResult: ActivityResultLauncher<Intent>): ActivityHandler

    fun shouldAnimate(isAnimate: Boolean): ActivityHandler


}
