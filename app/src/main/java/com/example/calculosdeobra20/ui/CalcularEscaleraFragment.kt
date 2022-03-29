package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        //desactivar boton al volver
        calcularEscaleraViewModel.liveVerificacionBoton.postValue(false)

        binding.btCalcular.setOnClickListener {
                calcularEscaleraViewModel.calcularEscalera(
                    binding.etAlto.text.toString().toDouble(),
                    binding.etLargo.text.toString().toDouble(),
                    binding.etAncho.text.toString().toDouble()
                )
                findNavController().navigate(R.id.action_calcularEscaleraFragment_to_resultadoEscaleraFragment)
        }

        calcularEscaleraViewModel.liveVerificacionBoton.observe(viewLifecycleOwner, {
            binding.btCalcular.isEnabled= it
        })
        binding.etAlto.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                it.toString(),
                binding.etAncho.text.toString(),
                binding.etLargo.text.toString()
            )
        }
        binding.etLargo.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                binding.etAlto.text.toString(),
                it.toString(),
                binding.etAncho.text.toString()
            )
        }
        binding.etAncho.doAfterTextChanged {
            calcularEscaleraViewModel.verificarBoton(
                binding.etAncho.text.toString(),
                binding.etAlto.text.toString(),
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