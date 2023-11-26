package com.cheesy.otapara.otaparaclient.viewmodel

import androidx.lifecycle.ViewModel
import com.cheesy.otapara.otaparaclient.repository.SettingsRepository
import com.cheesy.otapara.otaparaclient.viewmodel.states.GlobalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _state = MutableStateFlow(GlobalState())
    val state = _state.asStateFlow()
    init {
        _state.value = _state.value.copy(
            userMalToken = settingsRepository.getJWTToken()
        )
    }

    fun updateJWTFromSetting() {
        _state.value = _state.value.copy(
            userMalToken = settingsRepository.getJWTToken()
        )
    }

}