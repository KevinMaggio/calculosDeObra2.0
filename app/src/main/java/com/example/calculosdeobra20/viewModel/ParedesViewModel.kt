package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculosdeobra20.model.Pared

class ParedesViewModel : ViewModel(){

    val livepared = MutableLiveData<Pared>()
    val liveVerifyError = MutableLiveData<Boolean>()



    fun calculoParedHueco(mt2: Double) {
        val material = mt2 * 0.009
        val ladrillos = 12 * mt2
        livepared.postValue( Pared(material *3,material *2, material * 7, ladrillos ))
    }
    fun validarBoton(valor: String){
        liveVerifyError.postValue(!valor.isNullOrEmpty())
    }
}