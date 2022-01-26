package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentPresupuestosBinding
import com.example.calculosdeobra20.viewModel.PresupuestoViewModel


class PresupuestosFragment : Fragment() {

    lateinit var binding: FragmentPresupuestosBinding
    val presupuestoViewModel by activityViewModels<PresupuestoViewModel>()
    var pase = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPresupuestosBinding.inflate(inflater, container, false)

        //desactivar boton al volver
        presupuestoViewModel.liveValidarBoton.postValue(false)

        binding.btCalcular.setOnClickListener {
            checkNumber(binding.precioMt.text.toString())
            checkNumber(binding.mtRealizar.text.toString())
            checkNumber(binding.viaje.text.toString())
            checkNumber(binding.precioCombustible.text.toString())
            checkNumber(binding.tiempo.text.toString())
            if (pase) {
                presupuesto(
                    binding.precioMt.text.toString().toDouble().toInt(),
                    binding.mtRealizar.text.toString().toDouble().toInt(),
                    binding.viaje.text.toString().toDouble().toInt(),
                    binding.precioCombustible.text.toString().toDouble().toInt(),
                    binding.tiempo.text.toString().toDouble().toInt()
                )

                pasarRespuestaPresupuesto()
                presupuestoViewModel.presupuestoConDevaluacion(meses())
            }
        }
        binding.btBorrar.setOnClickListener {
            borrarCampos()
        }

        //validacion de boton a continuacion

        presupuestoViewModel.liveValidarBoton.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled = it
        })

        binding.viaje.doAfterTextChanged {
            presupuestoViewModel.validarBoton(
                binding.precioMt.text.toString(),
                binding.mtRealizar.text.toString(),
                it.toString(),
                binding.precioCombustible.text.toString(),
                binding.tiempo.text.toString()
            )
        }
        binding.precioMt.doAfterTextChanged {
            presupuestoViewModel.validarBoton(
                it.toString(),
                binding.mtRealizar.text.toString(),
                binding.viaje.text.toString(),
                binding.precioCombustible.text.toString(),
                binding.tiempo.text.toString()
            )
        }
        binding.mtRealizar.doAfterTextChanged {
            presupuestoViewModel.validarBoton(
                binding.precioMt.text.toString(),
                it.toString(),
                binding.viaje.text.toString(),
                binding.precioCombustible.text.toString(),
                binding.tiempo.text.toString()
            )
        }
        binding.precioCombustible.doAfterTextChanged {
            presupuestoViewModel.validarBoton(
                binding.precioMt.text.toString(),
                binding.mtRealizar.text.toString(),
                binding.viaje.text.toString(),
                it.toString(),
                binding.tiempo.text.toString()
            )
        }
        binding.tiempo.doAfterTextChanged {
            presupuestoViewModel.validarBoton(
                binding.precioMt.text.toString(),
                binding.mtRealizar.text.toString(),
                binding.viaje.text.toString(),
                binding.precioCombustible.text.toString(),
                it.toString()
            )
        }

        // seleccion de checkBox

        binding.checkDias.setOnClickListener {
            binding.checkAOs.isChecked = false
            binding.checkMeses.isChecked = false
            binding.checkSemanas.isChecked = false
        }
        binding.checkSemanas.setOnClickListener {
            binding.checkDias.isChecked = false
            binding.checkMeses.isChecked = false
            binding.checkAOs.isChecked = false
        }
        binding.checkMeses.setOnClickListener {
            binding.checkDias.isChecked = false
            binding.checkSemanas.isChecked = false
            binding.checkAOs.isChecked = false
        }
        binding.checkAOs.setOnClickListener {
            binding.checkDias.isChecked = false
            binding.checkSemanas.isChecked = false
            binding.checkMeses.isChecked = false
        }

        return binding.root
    }

    fun presupuesto(precio: Int, metros: Int, km: Int, precioCombustible: Int, dato: Int) {
        presupuestoViewModel.sacarTotal(precio, metros, km, precioCombustible, calculosDias(dato))
    }

    fun calculosDias(dato: Int): Int {
        var salida = 0
        when {
            binding.checkAOs.isChecked -> salida = dato * 365
            binding.checkDias.isChecked -> salida = dato * 1
            binding.checkSemanas.isChecked -> salida = dato * 7
            binding.checkMeses.isChecked -> salida = dato * 30
        }
        return salida
    }

    fun pasarRespuestaPresupuesto() {
        findNavController().navigate(R.id.action_presupuestosFragment_to_respuestaPresupuestoFragment)
    }

    fun borrarCampos() {
        binding.mtRealizar.text.clear()
        binding.precioCombustible.text.clear()
        binding.precioMt.text.clear()
        binding.tiempo.text.clear()
        binding.viaje.text.clear()
        binding.checkAOs.isChecked = false
        binding.checkMeses.isChecked = false
        binding.checkSemanas.isChecked = false
        binding.checkDias.isChecked = false
    }

    fun meses(): Double {
        var resto = calculosDias(binding.tiempo.text.toString().toDouble().toInt()) / 30
        return resto.toDouble()
    }

    fun checkNumber(valor: String) {
        try {
            valor.toDouble()
            if (valor.toDouble() > 0) {
                pase = true
            } else {
                pase = false
                mesageError()
                binding.btCalcular.isEnabled = false
            }
        } catch (e: NumberFormatException) {
            mesageError()
            binding.btCalcular.isEnabled = false
            pase = false
        }
    }

    fun mesageError() {
        Toast.makeText(context, "Error en la escritura de Datos", Toast.LENGTH_LONG).show()
    }

}