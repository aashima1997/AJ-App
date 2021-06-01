package com.example.ajapp.Menu.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ajapp.Menu.ui.Adapter.Tab2Adapter
import com.example.ajapp.R

class Tab2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tab2, container, false)
        val recyclerView: RecyclerView
        recyclerView = view!!.findViewById(R.id.tab2recycle)

        val ar1 = ArrayList<String>()
        ar1.add("lOVE YOURSELF")
        ar1.add("MAKE OTHERS HAPPY")
        ar1.add("DO SMILE")
        ar1.add("SPREAD LOVE")
        ar1.add(" BE HAPPY WITH WHAT YOU HAVE")




        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = Tab2Adapter(ar1)

        return view



    }

}