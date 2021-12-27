package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentLozaBinding


class LozaFragment : Fragment() {

lateinit var binding:FragmentLozaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLozaBinding.inflate(inflater, container, false)


        binding.btCalcular.setOnClickListener {
            // cargar el mt2 en el livedata
            // y aca se mueve
            check()
        }

        return binding.root
    }

    fun check(){
        when {
            binding.cbLozaClasica.isChecked -> {findNavController().navigate(R.id.action_lozaFragment_to_lozaClasicaFragment)}
            binding.cbLozaVigueta.isChecked -> {findNavController().navigate(R.id.action_lozaFragment_to_lozaViguetasFragment)}
        }
    }
}