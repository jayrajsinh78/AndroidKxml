package com.pixsterstudio.androidarckxml.data.livedata

import androidx.lifecycle.MutableLiveData
import com.pixsterstudio.androidarckxml.data.DataWrapper
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import retrofit2.Response

class LiveData<T> : MutableLiveData<DataWrapper<T>>() {

    fun observe(owner:BaseFragment<*>,onSuccess:(Response<T>)->Unit,onError:(Throwable)->Unit){
        super.observe(owner){
            if (it?.throwable !=null){
                onError.invoke(it.throwable)
            }else if (it?.responseBody !=null){
                onSuccess.invoke(it.responseBody)
            }
        }
    }


    fun observe(owner:BaseActivity,onSuccess:(Response<T>)->Unit,onError:(Throwable)->Unit){
        super.observe(owner){
            if (it?.throwable !=null){
                onError.invoke(it.throwable)
            }else if (it?.responseBody !=null){
                onSuccess.invoke(it.responseBody)
            }
        }
    }
}