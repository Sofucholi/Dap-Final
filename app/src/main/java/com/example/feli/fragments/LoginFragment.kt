package com.example.feli.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.feli.R
import com.example.feli.viewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {


    lateinit var v: View
    lateinit var EdtTxtEmail: EditText
    lateinit var EdtTxtPassword: EditText
    lateinit var BtnContinuar: Button
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        BtnContinuar.setOnClickListener {
            LogIn()
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)
        EdtTxtEmail = v.findViewById(R.id.EdtTxtEmail)
        EdtTxtPassword = v.findViewById(R.id.EdtTxtPassword)
        BtnContinuar = v.findViewById(R.id.Btn)
        auth = Firebase.auth
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun LogIn() {
        var Email = EdtTxtEmail.text.toString()
        var Password = EdtTxtPassword.text.toString()
        if (Email.isNotEmpty() && Password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var user = auth.currentUser
                    var email = user?.email ?: ""
                    var uid = user?.uid ?: ""
                    if (email.isNotEmpty() && uid.isNotEmpty()) {
                        var action = LoginFragmentDirections.actionLoginFragmentToRecyclerViewFragment()
                        v.findNavController().navigate(action)
                    }
                } else {
                    Snackbar.make(v, "Usuario Incorrecto", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}