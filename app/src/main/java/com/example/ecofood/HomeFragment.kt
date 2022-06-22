package com.example.ecofood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    var productList = ArrayList<Product>()
    val product1 = Product(R.drawable.p1, "Food 1", "1€")
    val product2 = Product(R.drawable.p2, "Food 1", "0.50€")
    val product3 = Product(R.drawable.p3, "Food 1", "1.10€")
    val product4 = Product(R.drawable.p4, "Food 1", "1.50€")
    val product5 = Product(R.drawable.p5, "Food 1", "1.70€")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context,
            2,
            GridLayoutManager.VERTICAL,
            false)


        productList.add(product1)
        productList.add(product2)
        productList.add(product3)
        productList.add(product4)
        productList.add(product5)

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter



//        view.findViewById<Button>(R.id.btn_log_out).setOnClickListener{
//            Firebase.auth.signOut()
//            var navLogin = activity as FragmentNavigation
//            navLogin.navigateFrag(LoginFragment(), addToStack = true)
//        }
        return view
    }
}