package com.example.feli.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feli.R
import com.example.feli.viewModel.FeliInfoViewModel

class FeliInfoFragment : Fragment() {


    lateinit var v:View
    companion object {
        fun newInstance() = FeliInfoFragment()
    }

    private lateinit var viewModel: FeliInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.feli_info_fragment, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeliInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}