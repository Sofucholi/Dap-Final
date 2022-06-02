package com.example.feli.fragments

import android.app.Person
import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feli.MainActivity
import com.example.feli.Persona
import com.example.feli.R
import com.example.feli.adapters.feliAdapter
import com.example.feli.viewModel.RecyclerViewViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerViewFragment : Fragment() {

    fun generateUrl(s: String): String {
        val p = s.split("/").toTypedArray()
        return "https://drive.google.com/uc?export=download&id=" + p[5]
    }


    lateinit var v: View
    lateinit var recyclerView: RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var ButtonPlus:com.google.android.material.floatingactionbutton.FloatingActionButton
    var PersonaList: MutableList<Persona> = mutableListOf(Persona("", "", "", "", ""))

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

    private lateinit var viewModel: RecyclerViewViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        PersonaList.clear()
        db = Firebase.firestore
        db.collection("personas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    PersonaList.add(
                        Persona(
                            document.data.get("nombre").toString(),
                            document.data.get("edad").toString(),
                            document.data.get("curso").toString(),
                            document.data.get("Descripcion").toString(),
                            generateUrl(document.data.get("url").toString())
                        )
                    )
                }
                recyclerView.adapter =
                    feliAdapter(PersonaList, context = requireContext(), Onclick = {
                        var action =
                            RecyclerViewFragmentDirections.actionRecyclerViewFragmentToFeliInfoFragment(
                                PersonaList[it]
                            )
                        findNavController().navigate(action)
                    })
                recyclerView.layoutManager = LinearLayoutManager(MainActivity())
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        v = inflater.inflate(R.layout.recycler_view_fragment, container, false)
        recyclerView = v.findViewById<RecyclerView>(R.id.feliRecyclerView)
        ButtonPlus = v.findViewById(R.id.floating_action_button)
        return v
    }

    override fun onStart() {
        recyclerView.adapter = feliAdapter(PersonaList, context = requireContext(), Onclick = {
            var action =
                RecyclerViewFragmentDirections.actionRecyclerViewFragmentToFeliInfoFragment(
                    PersonaList[it]
                )
            v.findNavController().navigate(action)
        })

        ButtonPlus.setOnClickListener {
            var action = RecyclerViewFragmentDirections.actionRecyclerViewFragmentToAddPersonFragment()
            v.findNavController().navigate(action)
        }
        recyclerView.layoutManager = LinearLayoutManager(MainActivity())
        super.onStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecyclerViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}