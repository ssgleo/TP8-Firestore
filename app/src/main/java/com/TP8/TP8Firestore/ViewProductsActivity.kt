package com.yourdomain.yourproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ViewProductsActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_products)

        firestore = FirebaseFirestore.getInstance()
        val listView = findViewById<ListView>(R.id.list_view)

        firestore.collection("Produtos")
            .get()
            .addOnSuccessListener { documents ->
                val productList = ArrayList<String>()
                for (document in documents) {
                    val name = document.getString("Nome")
                    val category = document.getString("Categoria")
                    val price = document.getDouble("Preço")
                    productList.add("Nome: $name\nCategoria: $category\nPreço: $price")
                }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productList)
                listView.adapter = adapter
            }
            .addOnFailureListener {
                Toast.makeText(this, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show()
            }
    }
}
