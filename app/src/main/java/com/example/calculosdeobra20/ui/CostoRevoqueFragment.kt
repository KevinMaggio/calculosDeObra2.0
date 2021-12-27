package com.example.calculosdeobra20.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculosdeobra20.databinding.FragmentCostoRevoqueBinding
import com.example.calculosdeobra20.viewModel.CostoMaterialViewModel


class CostoRevoqueFragment : Fragment() {

    lateinit var binding: FragmentCostoRevoqueBinding
    val costoMaterialViewModel by activityViewModels<CostoMaterialViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostoRevoqueBinding.inflate(inflater, container, false)



        return binding.root
    }
}