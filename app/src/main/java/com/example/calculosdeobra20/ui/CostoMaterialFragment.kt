package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCostoMaterialBinding


class CostoMaterialFragment : Fragment() {


    lateinit var binding:FragmentCostoMaterialBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCostoMaterialBinding.inflate(inflater, container, false)


        binding.iwEstructuras.setOnClickListener {
            findNavController().navigate(R.id.action_costoMaterialFragment_to_costoHormigonFragment)
        }
        binding.iwPintura.setOnClickListener {
            findNavController().navigate(R.id.action_costoMaterialFragment_to_costoPinturaFragment)
        }
        binding.iwRevoque.setOnClickListener {
            findNavController().navigate(R.id.action_costoMaterialFragment_to_costoRevoqueFragment)
        }
        return binding.root
    }
}