package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentRespuestaPresupuestoBinding
import com.example.calculosdeobra20.viewModel.PresupuestoViewModel

class RespuestaPresupuestoFragment : Fragment() {

    lateinit var binding: FragmentRespuestaPresupuestoBinding
    val presupuestoViewModel by activityViewModels<PresupuestoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentRespuestaPresupuestoBinding.inflate(inflater, container, false)


        presupuestoViewModel.liveDevaluacion.observe(viewLifecycleOwner,{
            binding.presupuestoDevaluacion.text= it.toString()
        })
        presupuestoViewModel.livePresupuesto.observe(viewLifecycleOwner,{
            binding.tvPresuLimpio.text= it.toString()
        })
        presupuestoViewModel.liveCombustible.observe(viewLifecycleOwner,{
            binding.tvCombustible.text= it.toString()
        })
        presupuestoViewModel.livePresupuestoTotal.observe(viewLifecycleOwner,{
            binding.tvPresuFinal.text= it.toString()
        })

        return binding.root
    }

}