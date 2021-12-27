package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCalcularParedesBinding

class CalcularParedesFragment : Fragment() {

    lateinit var binding: FragmentCalcularParedesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCalcularParedesBinding.inflate(inflater, container, false)

        binding.btLadrillo.setOnClickListener {
            findNavController().navigate(R.id.action_calcularParedesFragment_to_ladrillosFragment)
        }
        binding.btSeco.setOnClickListener {
            findNavController().navigate(R.id.action_calcularParedesFragment_to_construccionSecaFragment)
        }

    return binding.root
    }

}