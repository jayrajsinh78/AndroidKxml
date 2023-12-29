package com.pixsterstudio.androidarckxml.data.livedata

import com.pixsterstudio.androidarckxml.data.repo.HomeRepository
import com.pixsterstudio.androidarckxml.data.service.HomeService
import com.pixsterstudio.androidarckxml.ui.base.BaseDataSource
import javax.inject.Inject

class HomeLiveDataSource @Inject constructor(val homeService:HomeService): BaseDataSource(),HomeRepository {


}