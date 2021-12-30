package com.example.calculosdeobra20.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculosdeobra20.model.Hormigon
import java.text.DecimalFormat

class CalcularEscaleraViewModel : ViewModel() {

    var liveVerificacionBoton = MutableLiveData<Boolean>(false)
    var liveAlzada = MutableLiveData<Double>()
    var livePisada = MutableLiveData<Int>()
    var liveEscalones = MutableLiveData<Int>()
    var liveHormigon = MutableLiveData<Hormigon>()


    //Mejorar la calidad de codigo
    fun calcularEscalera(alto: Double, largo: Double, ancho: Double) {

        var condicion = false
        var alzada: Double = 19.0
        var escalones = 0
        var limitador = DecimalFormat("#00.0")
        var alzada2 = 0.0

        do {
            alzada2 = (limitador.format(alzada)).toString().replace(",", ".").toDouble()
            val resto = (alto % alzada2).toInt()
            if (resto == 0) {
                liveAlzada.postValue(
                    limitador.format(alzada2).toString().replace(",", ".").toDouble()
                )

                escalones = (alto / alzada2).toInt()
                liveEscalones.postValue(escalones)
                condicion = true
            } else {
                alzada = alzada - 0.10
            }
        } while (!condicion)
        val pisada = (largo / escalones).toInt()

        livePisada.postValue(pisada)
        val mt3 =
            (alzada2 / 100) * (pisada / 100) * (ancho / 100) + ((largo / 100 + 1) * (ancho / 100) * 0.10)
        val aridos = mt3 * 0.66
        val cement = mt3 * 7
        liveHormigon.postValue(
            Hormigon(
                limitador.format(cement).toString().replace(",", ".").toDouble(),
                limitador.format(aridos).toString().replace(",", ".").toDouble(),
                limitador.format(aridos).toString().replace(",", ".").toDouble()
            )
        )
    }

    fun verificarBoton(largo: String, alto: String, ancho: String) {
        if (largo.isNotEmpty() && ancho.isNotEmpty() && alto.isNotEmpty()) {
            liveVerificacionBoton.postValue(true)
        } else {
            liveVerificacionBoton.postValue(false)
        }
    }

}
