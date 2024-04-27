package com.example.examplesharedpreferences.fragments

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThirdViewModel : ViewModel() {

    private var email: String = ""
    private var password: String = ""

    private var number: MutableLiveData<Int> = MutableLiveData(0)

    var viewState = MutableLiveData<ThirdStates>()

    //init {
    //number.value = 0
    //}

    private fun validateButtons() {
        if ((PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty())
            && (password.length > 3 && password.isNotEmpty())) {

            viewState.value = ThirdStates.SuccessButton
        } else {

            viewState.value = ThirdStates.ErrorButton
        }
    }

    fun validateEmail(email: String) {
        this.email = email
        if (email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {

            viewState.value = ThirdStates.SuccessEmail

        } else {
            viewState.value = ThirdStates.ErrorEmail
        }
        validateButtons()
    }



    fun validatePassword(password: String) {
        this.password = password
        if (password.isNotEmpty() && password.length > 3) {

            viewState.value = ThirdStates.SuccessPassword

        } else {
            viewState.value = ThirdStates.ErrorPassword(password)
        }
        validateButtons()
    }

    fun setNumberValue() {
        number.value = number.value!! + 1
    }

    fun getNumberValue(): Int {
        return number.value!!
    }


    fun getNumberLiveData(): MutableLiveData<Int> {
        return number
    }
}

sealed class ThirdStates() {
    object SuccessEmail: ThirdStates()
    object ErrorEmail: ThirdStates()
    object SuccessPassword: ThirdStates()
    data class ErrorPassword(val password: String): ThirdStates()
    object SuccessButton: ThirdStates()
    object ErrorButton: ThirdStates()
}

enum class ThirdEnums {
    SUCCESS_EMAIL, ERROR_EMAIL
}