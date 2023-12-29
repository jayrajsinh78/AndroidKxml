package com.pixsterstudio.androidarckxml.ui.home.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.pixsterstudio.androidarckxml.databinding.WebviewFragmentBinding
import com.pixsterstudio.androidarckxml.di.components.FragmentComponent
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import com.pixsterstudio.androidarckxml.utils.Constants
import com.pixsterstudio.androidarckxml.utils.hide
import com.pixsterstudio.androidarckxml.utils.show

class WebViewFragment:BaseFragment<WebviewFragmentBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): WebviewFragmentBinding {
        return WebviewFragmentBinding.inflate(layoutInflater)
    }

    private val title by lazy {
        arguments?.getString(Constants.TITLE) ?: ""
    }

    private val url by lazy {
        arguments?.getString(Constants.WebViews.WEBVIEW_URL) ?: ""
    }

    override fun viewCreated() {

        Log.d("TAG Webview", "viewCreated: ")
        binding.imageViewBack.setOnClickListener { navigator.goBack() }
        binding.textViewTitle.text = title

        if (title == "FAQ") {
            binding.webViewTwo.show()
            binding.webView.hide()
            binding.webViewTwo.webViewClient = WebViewClient()
            binding.webViewTwo.settings.javaScriptEnabled = true
            binding.webViewTwo.loadUrl(url)
        } else {
            binding.webViewTwo.hide()
            binding.webView.show()
            binding.webView.loadUrl(url)
        }

    }

    override fun inject(fragmentComponent: FragmentComponent) {
       fragmentComponent.inject(this)
    }
}