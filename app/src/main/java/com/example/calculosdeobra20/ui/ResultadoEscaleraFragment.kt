package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentResultadoEscaleraBinding
import com.example.calculosdeobra20.viewModel.CalcularEscaleraViewModel


class ResultadoEscaleraFragment : Fragment() {

    val calcularEscaleraViewModel by activityViewModels<CalcularEscaleraViewModel>()
    lateinit var binding: FragmentResultadoEscaleraBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultadoEscaleraBinding.inflate(inflater, container, false)


        calcularEscaleraViewModel.liveAlzada.observe(viewLifecycleOwner,{
            binding.twAlzada.text= it.toString()
        })
        calcularEscaleraViewModel.livePisada.observe(viewLifecycleOwner,{
            binding.twPisada.text= it.toString()
        })
        calcularEscaleraViewModel.liveEscalones.observe(viewLifecycleOwner,{
            binding.twEscalones.text=it.toString()
        })
        calcularEscaleraViewModel.liveHormigon.observe(viewLifecycleOwner,{
            binding.twArena.text="${it.arena} Mt3"
            binding.twCemento.text="${it.cemento} Uni"
            binding.twPiedra.text="${it.piedra} Mt3"
        })

        return binding.root
    }
}