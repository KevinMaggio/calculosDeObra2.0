package com.example.calculosdeobra20.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.example.calculosdeobra20.R
import com.example.calculosdeobra20.databinding.FragmentMisTrabajosBinding
import java.io.File


class MisTrabajosFragment : Fragment() {

    private val FILE_NAME = "photo.jpg"
    lateinit var binding: FragmentMisTrabajosBinding
    private val REQUEST_CODE = 13

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMisTrabajosBinding.inflate(inflater, container, false)

      /*  binding.btPhoto.setOnClickListener {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           // val filePhoto = getPhotoFile(FILE_NAME)
            val providerFile =
                FileProvider.getUriForFile(this,"com.example.androidcamera.fileprovider", filePhoto)
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
        }*/


        return binding.root
    }
/*
    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
            viewImage.setImageBitmap(takenPhoto)
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }*/
}