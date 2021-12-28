package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCalcularEscaleraBinding
import com.example.calculosdeobra20.viewModel.CalcularEscaleraViewModel

class CalcularEscaleraFragment : Fragment() {

    lateinit var binding: FragmentCalcularEscaleraBinding
    val calcularEscaleraViewModel by activityViewModels<CalcularEscaleraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalcularEscaleraBinding.inflate(inflater, container, false)


        binding.btCalcular.setOnClickListener {
            calcularEscaleraViewModel.calcularEscalera(
                binding.etAlto.toString().toDouble(),
                binding.etLargo.toString().toDouble(),
                binding.etAncho.toString().toDouble()
            )
            findNavController().navigate(R.id.action_calcularEscaleraFragment_to_resultadoEscaleraFragment)
        }
        calcularEscaleraViewModel.liveVerificacionBoton.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled= it
        })
        binding.etAlto.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                it.toString(),
                binding.etAncho.toString(),
                binding.etLargo.toString()
            )
        }
        binding.etLargo.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                binding.etAlto.toString(),
                it.toString(),
                binding.etAncho.toString()
            )
        }
        binding.etAncho.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                binding.etAncho.toString(),
                binding.etAlto.toString(),
                it.toString()
            )
        }
        binding.btBorrar.setOnClickListener {
            limpiar()
        }

        return binding.root
    }

    fun limpiar(){
        binding.etAncho.text.clear()
        binding.etLargo.text.clear()
        binding.etAlto.text.clear()
    }

}