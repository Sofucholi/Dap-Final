package com.example.feli.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.feli.Persona
import com.example.feli.R
import com.example.feli.viewModel.FeliInfoViewModel

class FeliInfoFragment : Fragment() {


    lateinit var v:View
    lateinit var NameTxt:TextView
    lateinit var EdadTxt:TextView
    lateinit var CursoTxt:TextView
    lateinit var DescriptionTxt:TextView
    lateinit var MainImageView:ImageView
    lateinit var userInfo:Persona

    companion object {
        fun newInstance() = FeliInfoFragment()
    }

    private lateinit var viewModel: FeliInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.feli_info_fragment, container, false)
        NameTxt = v.findViewById(R.id.NameTxt)
        EdadTxt = v.findViewById(R.id.EdadTxt)
        CursoTxt = v.findViewById(R.id.CursoTxt)
        DescriptionTxt = v.findViewById(R.id.DescriptionTxt)
        MainImageView = v.findViewById(R.id.MainImageView)
        userInfo = FeliInfoFragmentArgs.fromBundle(requireArguments()).feliInfo
        return v
    }

    override fun onStart() {
        NameTxt.text = userInfo.Nombre
        EdadTxt.text = "Edad: " + userInfo.Edad
        CursoTxt.text = "Curso: " + userInfo.curso
        DescriptionTxt.text = userInfo.descripcion
        Glide.with(this).load(userInfo.url).centerInside().circleCrop().into(MainImageView)
        super.onStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeliInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}