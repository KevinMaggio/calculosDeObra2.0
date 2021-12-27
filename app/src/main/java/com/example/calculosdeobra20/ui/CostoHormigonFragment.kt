package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCostoHormigonBinding
import com.example.calculosdeobra20.viewModel.CostoMaterialViewModel
import java.text.DecimalFormat


class CostoHormigonFragment : Fragment() {

    val costoMaterialViewModel by activityViewModels<CostoMaterialViewModel>()
    lateinit var binding: FragmentCostoHormigonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCostoHormigonBinding.inflate(inflater, container, false)

        //desactivar boton
        costoMaterialViewModel.liveValidarHormigon.postValue(false)

        costoMaterialViewModel.liveHormigon.observe(viewLifecycleOwner, {
            val limitador = DecimalFormat("#0.00")
            binding.twArena.text = limitador.format(it.arena) + " Mt3"
            binding.twCemento.text = limitador.format(it.cemento) + " Uni"
            binding.twPiedra.text = limitador.format(it.piedra) + " Mt3"
        })

        binding.btCalcular.setOnClickListener {
            costoMaterialViewModel.calculoHormigon(
                binding.etLadoA.text.toString().toDouble(),
                binding.etLadoB.text.toString().toDouble(),
                binding.twAlto.text.toString().toDouble()
            )
        }
        binding.btBorrar.setOnClickListener {
            limpiarPantalla()
        }
        // validar boton

        costoMaterialViewModel.liveValidarHormigon.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled = it
        })

        binding.etLadoB.doAfterTextChanged {
            costoMaterialViewModel.validarHormigon(
                binding.etLadoA.text.toString(),
                it.toString(),
                binding.twAlto.text.toString()
            )
        }
        binding.etLadoA.doAfterTextChanged {
            costoMaterialViewModel.validarHormigon(
                it.toString(),
                binding.etLadoB.toString(),
                binding.twAlto.text.toString()
            )
        }
        binding.twAlto.doAfterTextChanged {
            costoMaterialViewModel.validarHormigon(
                binding.etLadoA.text.toString(),
                binding.etLadoB.text.toString(),
                it.toString()
            )
        }

        return binding.root

    }

    fun limpiarPantalla() {
        binding.twPiedra.text = "0"
        binding.twCemento.text = "0"
        binding.twArena.text = "0"
        binding.twAlto.text.clear()
        binding.etLadoA.text.clear()
        binding.etLadoB.text.clear()
    }
}