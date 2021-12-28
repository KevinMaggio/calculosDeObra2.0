package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculosdeobra20.model.Hormigon

class CalcularEscaleraViewModel : ViewModel() {

    var liveVerificacionBoton = MutableLiveData<Boolean>()
    var liveAlzada = MutableLiveData<Int>()
    var livePisada = MutableLiveData<Int>()
    var liveEscalones = MutableLiveData<Int>()
    var liveHormigon = MutableLiveData<Hormigon>()


    fun calcularEscalera(alto: Double, largo: Double, ancho:Double) {

        var condicion = false
        var alzada = 19
        do {
            val resto = alto % alzada
            if (resto != 0.0) {
                liveAlzada.postValue(alzada)
                liveEscalones.value = (alto / alzada).toInt()
                condicion = true
            }else {
                alzada --
            }
        } while (!condicion)
        livePisada.value = (largo /liveEscalones.value!!).toInt()

        val mt3 = liveAlzada.value!! * livePisada.value!! * ancho + ((ancho+1) * ancho*0.7)
        val aridos = mt3 * 0.66
        val cement = mt3 * 7
        liveHormigon.postValue(Hormigon(cement,aridos,aridos))
    }

    fun verificarBoton (largo:String, alto : String, ancho:String){
        if (largo.isNullOrEmpty() && ancho.isNullOrEmpty() && alto.isNullOrEmpty()){
            liveVerificacionBoton.postValue(true)
        }
    }


    //calcular los escalones
    //consumo de materiales resultado en mt3 de concreto
    //pisada y alzada
}
