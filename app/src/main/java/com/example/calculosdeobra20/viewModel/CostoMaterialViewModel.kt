package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculosdeobra20.model.Hormigon
import com.example.calculosdeobra20.model.Revoque

class CostoMaterialViewModel : ViewModel() {

    var liveHormigon = MutableLiveData<Hormigon>()
    var liveRevoque = MutableLiveData<Revoque>()
    var livePintura = MutableLiveData<Double>()

    val liveValidarHormigon = MutableLiveData<Boolean>()
    val liveValidarPintura = MutableLiveData<Boolean>()
    val liveValidarRevoque = MutableLiveData<Boolean>()

    fun calculoHormigon(ladoA: Double, ladoB: Double, alto: Double) {
        val mt3 = ladoA * ladoB * alto
        val cemento = mt3 * 7
        val arenaPiedra = mt3 * 0.66
        val matHormigon = Hormigon(cemento, arenaPiedra, arenaPiedra)
        liveHormigon.postValue(matHormigon)

    }

    fun calculoRevoque(mt2: Int) {
        val mt2Final = mt2 * 0.025
        val cemento= mt2Final * 5
        val arena= mt2Final * 0.9
        val cal= mt2Final * 9
        liveRevoque.postValue(Revoque(cemento,arena,cal))


    }

    fun calculoPintura(mt2: Int) {
        livePintura.postValue(mt2.toDouble() / 8)
    }

    fun validarHormigon(ladoA: String, ladoB: String, alto: String) {
        if (ladoA.isNotEmpty() && ladoB.isNotEmpty() && alto.isNotEmpty() && checkNumber(ladoA) && checkNumber(ladoB) && checkNumber(alto)) {
            liveValidarHormigon.postValue(true)
        } else {
            liveValidarHormigon.postValue(false)
        }
    }

    fun validarPintura(mt2: String) {
        if (mt2.isNotEmpty() && checkNumber(mt2)) {
            liveValidarPintura.postValue(true)
        } else {
            liveValidarPintura.postValue(false)
        }
    }

    fun validarRevoque(mt2: String) {
        if (mt2.isNotEmpty() && checkNumber(mt2)) {
            liveValidarRevoque.postValue(true)
        } else {
            liveValidarRevoque.postValue(false)
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
}