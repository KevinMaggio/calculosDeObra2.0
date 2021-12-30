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
import com.example.calculosdeobra20.databinding.FragmentSacarMt2Binding
import com.example.calculosdeobra20.viewModel.CalculosViewModel


class SacarMt2Fragment : Fragment() {

    lateinit var binding: FragmentSacarMt2Binding
    val calculosViewModel by activityViewModels<CalculosViewModel>()
    var pase = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSacarMt2Binding.inflate(inflater, container, false)

        //desactivar boton
        calculosViewModel.liveVerifiBoton.postValue(false)

        binding.btCalcular.setOnClickListener {
            checkNumber(binding.etLadoA.text.toString())
            checkNumber(binding.etLadoB.text.toString())
            checkNumber(binding.etAlto.text.toString())
            if (pase) {
                calculosViewModel.calculoMT(
                    binding.etLadoA.text.toString().toDouble(),
                    binding.etLadoB.text.toString().toDouble(),
                    binding.etAlto.text.toString().toDouble()
                )
            }
        }
        binding.btBorrar.setOnClickListener {
            borrarCampos()
        }
        // verificacion de boton
        calculosViewModel.liveResultMt.observe(viewLifecycleOwner, {
            binding.tvResultado.text = ("$it Metros de Trabajo")
        })

        binding.etAlto.doAfterTextChanged {
            calculosViewModel.verificacionBoton(
                binding.etLadoA.text.toString(),
                binding.etLadoB.text.toString(),
                it.toString()
            )
        }
        binding.etLadoA.doAfterTextChanged {
            calculosViewModel.verificacionBoton(
                it.toString(),
                binding.etLadoB.text.toString(),
                binding.etAlto.text.toString()
            )
        }
        binding.etLadoB.doAfterTextChanged {
            calculosViewModel.verificacionBoton(
                binding.etLadoA.text.toString(),
                it.toString(),
                binding.etAlto.text.toString()
            )
        }

        calculosViewModel.liveVerifiBoton.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled = it
        })


        return binding.root
    }

    fun checkNumber(valor: String) {
        try {
            valor.toInt()

        } catch (e: NumberFormatException) {
            mesageError()
            binding.btCalcular.isEnabled = false
            pase = false
        }
    }

    fun mesageError() {
        Toast.makeText(context, "Error en la escritura de Datos", Toast.LENGTH_LONG).show()
    }

    fun borrarCampos() {
        binding.etLadoB.text.clear()
        binding.etLadoA.text.clear()
        binding.etAlto.text.clear()
        calculosViewModel.resetResult()
    }
}