package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentCalcularEstructuraBinding

class CalcularEstructuraFragment : Fragment() {


    lateinit var binding : FragmentCalcularEstructuraBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalcularEstructuraBinding.inflate(inflater, container, false)


        binding.btColumnas.setOnClickListener {
            findNavController().navigate(R.id.action_calcularEstructuraFragment_to_columnasFragment)
        }
        binding.btLoza.setOnClickListener {
            findNavController().navigate(R.id.action_calcularEstructuraFragment_to_lozaFragment)

        }
        binding.btVigas.setOnClickListener {
            findNavController().navigate(R.id.action_calcularEstructuraFragment_to_vigasFragment)
        }
        return binding.root
    }


}