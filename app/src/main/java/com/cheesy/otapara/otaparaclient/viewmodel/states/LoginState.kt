package com.cheesy.otapara.otaparaclient.viewmodel.states

data class LoginState(
    val name : String = "OTA-PARA",
    val isLoginOnGoing : Boolean = false,
    val loginUrl : String = "",
    val isLoadingToken : Boolean = false,
    val isLoadingTimePassed : Boolean = false,

)
