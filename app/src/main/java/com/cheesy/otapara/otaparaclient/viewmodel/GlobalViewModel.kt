package com.cheesy.otapara.otaparaclient.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.cheesy.otapara.otaparaclient.viewmodel.states.GlobalState
import com.cheesy.otapara.otaparaclient.viewmodel.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(GlobalState())
    val state = _state.asStateFlow()
}