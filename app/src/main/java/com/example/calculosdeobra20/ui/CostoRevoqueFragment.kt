package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.databinding.FragmentCostoRevoqueBinding
import com.example.calculosdeobra20.viewModel.CostoMaterialViewModel
import java.text.DecimalFormat


class CostoRevoqueFragment : Fragment() {

    lateinit var binding: FragmentCostoRevoqueBinding
    val costoMaterialViewModel by activityViewModels<CostoMaterialViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostoRevoqueBinding.inflate(inflater, container, false)

        //desactivar boton
        costoMaterialViewModel.liveValidarRevoque.postValue(false)

        costoMaterialViewModel.liveRevoque.observe(viewLifecycleOwner,{
            val limitador = DecimalFormat("#0.00")
            binding.twArena.text= limitador.format(it.arena) +"Mt3"
            binding.twCemento.text= limitador.format(it.cemento)+"Uni"
            binding.twCal.text= limitador.format(it.cal)+"Uni"
        })

        binding.btCalcular.setOnClickListener {
                costoMaterialViewModel.calculoRevoque(binding.etMt2.text.toString().toDouble().toInt())
        }

        costoMaterialViewModel.liveValidarRevoque.observe(viewLifecycleOwner,{
            binding.btCalcular.isEnabled= it
        })

        binding.btBorrar.setOnClickListener {
            limpiarCampos()
        }

        binding.etMt2.doAfterTextChanged {
            costoMaterialViewModel.validarRevoque(it.toString())
        }

        return binding.root
    }

    fun limpiarCampos() {
        binding.etMt2.text.clear()
        binding.twArena.text= "0"
        binding.twCemento.text= "0"
        binding.twCal.text= "0"
    }
}