package com.example.ajapp.Menu.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajapp.Login.viewModel.LoginViewModel
import com.example.ajapp.Menu.ui.Adapter.ListAdapter
import com.example.ajapp.R

class Attend : Fragment() {
    lateinit var mUserViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter= ListAdapter()

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_attend, container, false)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.adapter =adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context)
        mUserViewModel = ViewModelProvider(this).get(
            LoginViewModel::class.java
        )
        mUserViewModel.readAllData1.observe(viewLifecycleOwner, Observer { user1 ->
            adapter.setData(user1)

        })

         return view
    }

}