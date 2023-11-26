package com.cheesy.otapara.otaparaclient.viewmodel

import androidx.lifecycle.ViewModel
import com.cheesy.otapara.otaparaclient.repository.ApiRepository
import com.cheesy.otapara.otaparaclient.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

}