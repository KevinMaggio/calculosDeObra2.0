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
import com.example.calculosdeobra20.databinding.FragmentLadrillosBinding
import com.example.calculosdeobra20.viewModel.ParedesViewModel
import java.text.DecimalFormat

class LadrillosFragment : Fragment() {

    private lateinit var binding : FragmentLadrillosBinding
    val paredesViewModel by activityViewModels<ParedesViewModel>()
    var pase = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLadrillosBinding.inflate(inflater, container, false)
        paredesViewModel.liveVerifyError.postValue(false)
        clear()


        binding.btCalcular.setOnClickListener {
            checkNumber(binding.etMt2.text.toString())
            if (pase) {
                paredesViewModel.calculoParedHueco(binding.etMt2.text.toString().toDouble())
            }

        }
        binding.btCancel.setOnClickListener {
            clear()
        }
        paredesViewModel.livepared.observe(viewLifecycleOwner,{
            val limitador = DecimalFormat("#0.00")

            binding.twArena.text = limitador.format(it.arena) +"Mt3"
            binding.twCemento.text= limitador.format(it.cemento) +"Uni"
            binding.twCal.text= limitador.format(it.cal)+"Uni"
            binding.twLadrillo.text= it.ladrillo.toInt().toString()+"Uni"
        })
        paredesViewModel.liveVerifyError.observe(viewLifecycleOwner,{
            binding.btCalcular.isEnabled= it
        })
        binding.etMt2.doAfterTextChanged {
            paredesViewModel.validarBoton(it.toString())
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

    fun clear(){
        binding.etMt2.text.clear()
        binding.twLadrillo.text="0"
        binding.twCal.text="0"
        binding.twCemento.text="0"
        binding.twArena.text="0"

    }
}