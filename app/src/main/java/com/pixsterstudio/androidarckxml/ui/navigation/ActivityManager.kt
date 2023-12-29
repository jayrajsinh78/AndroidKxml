package com.smartsociety.app.ui.manager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.pixsterstudio.androidarckxml.di.PerActivityScope
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import com.pixsterstudio.androidarckxml.ui.navigation.ActivityHandler


import javax.inject.Inject

@PerActivityScope
class ActivityManager @Inject
internal constructor(private val context: BaseActivity) {
    private var intent: Intent? = null
    private var activity: Class<out Activity>? = null
    private var shouldAnimate = true

    fun create(activityClass: Class<out BaseActivity>): ActivityHandler {
        activity = activityClass
        intent = Intent(context, activityClass)
        return Handler()
    }

    private inner class Handler : ActivityHandler {
        private var bundle: Bundle? = null
        private var activityOptionsBundle: Bundle? = null
        private var isToFinishCurrent: Boolean = false
        private var requestCode: Int = 0
        var startForResult: ActivityResultLauncher<Intent>? = null

        override fun start() {
            if (bundle != null)
                intent!!.putExtras(bundle!!)

            if (!shouldAnimate)
                intent!!.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            if (startForResult == null) {
                if (activityOptionsBundle == null)
                    context.startActivity(intent)
                else
                    context.startActivity(intent, activityOptionsBundle)
            } else {
                val currentFragment = context.getCurrentFragment<BaseFragment<*>>()
                startForResult!!.launch(intent)
            }

            if (isToFinishCurrent)
                context.finish()
        }

        override fun addBundle(bundle: Bundle): ActivityHandler {
            if (this.bundle != null)
                this.bundle!!.putAll(bundle)
            else
                this.bundle = bundle
            return this
        }

        override fun addSharedElements(pairs: List<Pair<View, String>>): ActivityHandler {
            val optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context, *pairs.toTypedArray())
            activityOptionsBundle = optionsCompat.toBundle()
            return this
        }

        override fun byFinishingCurrent(): ActivityHandler {
            isToFinishCurrent = true
            return this
        }

        override fun byFinishingAll(): ActivityHandler {
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            return this
        }


        override fun <T : BaseFragment<*>> setPage(page: Class<T>): ActivityHandler {
            intent!!.putExtra(ACTIVITY_FIRST_PAGE, page)
            return this
        }

        override fun forResult(requestCode: Int): ActivityHandler {
            this.requestCode = requestCode
            return this
        }

        override fun forResult(startForResult: ActivityResultLauncher<Intent>): ActivityHandler {
            this.startForResult = startForResult
            return this
        }

        override fun shouldAnimate(isAnimate: Boolean): ActivityHandler {
            shouldAnimate = isAnimate
            return this
        }
    }

    companion object {
        const val ACTIVITY_FIRST_PAGE = "first_page"
    }

}
