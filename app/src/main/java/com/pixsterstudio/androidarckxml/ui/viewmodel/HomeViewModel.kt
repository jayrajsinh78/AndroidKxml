package com.pixsterstudio.androidarckxml.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pixsterstudio.androidarckxml.data.repo.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository:HomeRepository):ViewModel() {


}