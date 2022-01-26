package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCostoPinturaBinding
import com.example.calculosdeobra20.viewModel.CostoMaterialViewModel
import java.text.DecimalFormat


class CostoPinturaFragment : Fragment() {

    lateinit var binding: FragmentCostoPinturaBinding
    val costoMaterialViewModel by activityViewModels<CostoMaterialViewModel>()
    var pase= true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCostoPinturaBinding.inflate(inflater, container, false)

        //desactivar boton
        costoMaterialViewModel.liveValidarPintura.postValue(false)

        costoMaterialViewModel.livePintura.observe(viewLifecycleOwner, {

            val limitador = DecimalFormat("#0.00")
            binding.twPintura.text = limitador.format(it) + "Lts"
        })

        binding.btCalcular.setOnClickListener {
            checkNumber(binding.etMt2.text.toString())

            if (pase) {
                costoMaterialViewModel.calculoPintura(
                    binding.etMt2.text.toString().toDouble().toInt()
                )
            }
        }

        costoMaterialViewModel.liveValidarPintura.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled = it
        })
        binding.btBorrar.setOnClickListener {
            limpiarCampos()
        }

        //validar boton
        binding.etMt2.doAfterTextChanged {
            costoMaterialViewModel.validarPintura(
                it.toString()
            )
        }
        return binding.root
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

    fun limpiarCampos() {
        binding.etMt2.text.clear()
        binding.twPintura.text= "0"
    }
}