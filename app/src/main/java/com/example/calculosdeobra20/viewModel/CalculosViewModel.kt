package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculosViewModel : ViewModel() {

    val liveResultMt = MutableLiveData<Double>()
    val liveVerifiBoton = MutableLiveData<Boolean>()

    fun calculoMT(ladoA:Double, ladoB:Double, alto: Double){
        val result = ((ladoA*2) + (ladoB*2))* alto
        liveResultMt.postValue(result)
    }
    fun verificacionBoton(lado: String, lado2: String, alto:String){
        if(lado.isNotEmpty() && lado2.isNotEmpty() && alto.isNotEmpty() && checkNumber(lado) && checkNumber(lado2) && checkNumber(alto)){
            liveVerifiBoton.postValue(true)
        }else{
            liveVerifiBoton.postValue(false)
        }
    }
    fun checkNumber(valor: String): Boolean {
        return try {
            valor.toDouble()
            valor.toDouble() >= 0
        } catch (e: NumberFormatException) {
            false
        }
    }
    fun resetResult(){
        liveResultMt.postValue(0.0)
    }
}