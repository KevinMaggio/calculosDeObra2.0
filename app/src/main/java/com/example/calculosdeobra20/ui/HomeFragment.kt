package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.presupuestos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_presupuestosFragment)
        }
        binding.calculosMt2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sacarMt2Fragment)
        }
        binding.calculoEscalera.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calcularEscaleraFragment)
        }
        binding.calculosParedes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_calcularParedesFragment)
        }
        binding.costoMaterial.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_costoMaterialFragment)
        }
        binding.misTrabajos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_misTrabajosFragment)
        }

        return binding.root
    }
}