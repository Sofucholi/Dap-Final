package com.example.feli.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feli.MainActivity
import com.example.feli.R
import com.example.feli.adapters.feliAdapter
import com.example.feli.feli
import com.example.feli.viewModel.RecyclerViewViewModel

class RecyclerViewFragment : Fragment() {

    lateinit var v:View
    lateinit var recyclerView:RecyclerView
    var FeliList: MutableList<feli> = mutableListOf(feli(90,"Feli Detroit", listOf("volar"),""))

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

    private lateinit var viewModel: RecyclerViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        recyclerView = v.findViewById<RecyclerView>(R.id.feliRecyclerView)
        FeliList.add(feli(100,"feli dorado", listOf("brillar"),""))
        return v
    }

    override fun onStart() {
        recyclerView.adapter = feliAdapter(FeliList, context = requireContext(), Onclick = {
            RecyclerViewFragmentDirections.actionRecyclerViewFragmentToFeliInfoFragment(FeliList[it])

        })
        recyclerView.layoutManager = LinearLayoutManager(MainActivity())
        super.onStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecyclerViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}