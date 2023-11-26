package com.cheesy.otapara.otaparaclient.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheesy.otapara.otaparaclient.util.Utils.TOKEN_FETCH_ERROR_MESSAGE
import com.cheesy.otapara.otaparaclient.repository.ApiRepository
import com.cheesy.otapara.otaparaclient.repository.SettingsRepository
import com.cheesy.otapara.otaparaclient.util.custom_exception.ApiRequestFailedException
import com.cheesy.otapara.otaparaclient.viewmodel.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun loginThroughApi(context: Context) {
        // call get link from here
        val intentLink =
            "https://myanimelist.net/v1/oauth2/authorize?response_type=code&client_id=31ad1c17f3c101c3add5efa974e1e05c&code_challenge=10fY4H32CqULCannm5F6sYgYJaxo12Ptr65rEIPLJ4Y&code_challenge_method=plain"
        _state.value = _state.value.copy(
            isLoginOnGoing = true,
            loginUrl = intentLink,
        )
    }

    fun fetchTokenBasedOnCode(
        context: Context,
        code: String?,
        globalViewModel: GlobalViewModel
    ) {
        _state.value = _state.value.copy(
            isLoadingToken = true,
        )
        Log.i(TAG, "fetchTokenBasedOnCode: $code")
        if (!code.isNullOrEmpty()) {
            viewModelScope.launch {
                delay(30000)
                _state.value = _state.value.copy(
                    isLoadingTimePassed = true
                )
            }
            viewModelScope.launch {
                try {
                    //Call API
                    val authResponse = apiRepository.getMalToken(code)
                    settingsRepository.setJWTToken(authResponse.token)
                    settingsRepository.setJWTRefreshToken(authResponse.refreshToken)
                    globalViewModel.updateJWTFromSetting()
                    _state.value = _state.value.copy(
                        isLoginOnGoing = false,
                    )
                } catch (e: ApiRequestFailedException) {
                    Toast.makeText(context, TOKEN_FETCH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                    _state.value = _state.value.copy(
                        isLoginOnGoing = false,
                    )
                } catch (e: Exception) {
                    Toast.makeText(context, TOKEN_FETCH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                    _state.value = _state.value.copy(
                        isLoginOnGoing = false,
                    )
                }
            }
        } else {
            Toast.makeText(context, TOKEN_FETCH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            _state.value = _state.value.copy(
                isLoginOnGoing = false,
            )
        }

    }

    fun tryAgain() {
        _state.value = _state.value.copy(
            isLoadingToken = false,
            isLoadingTimePassed = false,
        )
    }

}