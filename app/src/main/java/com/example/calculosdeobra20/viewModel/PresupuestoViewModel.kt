package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresupuestoViewModel : ViewModel() {

    val liveCombustible = MutableLiveData<Int>()
    val livePresupuesto = MutableLiveData<Int>()
    val livePresupuestoTotal = MutableLiveData<Int>()
    val liveValidarBoton = MutableLiveData<Boolean>()
    val liveDevaluacion = MutableLiveData<Int>()

    private fun sacarPresupuesto(precio: Int, metros: Int): Int {
        livePresupuesto.postValue(precio * metros)
        return precio * metros
    }

    private fun evaluarCombustible(km: Int, precioCombustible: Int, dias: Int): Int {
        val resul = (((km * 2) * dias) * precioCombustible) / 7
        liveCombustible.postValue(resul)
        return resul
    }

    fun sacarTotal(precio: Int, metros: Int, km: Int, precioCombustible: Int, dias: Int) {
        livePresupuestoTotal.value =
            sacarPresupuesto(precio, metros) + evaluarCombustible(km, precioCombustible, dias)
    }

    fun validarBoton(
        precio: String,
        metros: String,
        km: String,
        precioCombustible: String,
        dias: String
    ) {
        if (precio.isNotEmpty() && metros.isNotEmpty() && km
                .isNotEmpty() && precioCombustible.isNotEmpty() && dias
                .isNotEmpty() && checkNumber(precio) && checkNumber(metros) && checkNumber(km) && checkNumber(
                precioCombustible
            ) && checkNumber(dias)
        ) {
            liveValidarBoton.postValue(true)
        } else {
            liveValidarBoton.postValue(false)
        }
    }

    fun presupuestoConDevaluacion(meses: Double) {
        val devaluacion = meses * 0.04

        livePresupuestoTotal.let { liveDevaluacion.postValue((devaluacion * it.value!!.toDouble() + it.value!!).toInt()) }
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