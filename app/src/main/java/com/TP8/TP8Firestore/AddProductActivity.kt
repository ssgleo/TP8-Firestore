package com.yourdomain.yourproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddProductActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        firestore = FirebaseFirestore.getInstance()

        val edtName = findViewById<EditText>(R.id.edt_name)
        val edtCategory = findViewById<EditText>(R.id.edt_category)
        val edtPrice = findViewById<EditText>(R.id.edt_price)
        val btnSave = findViewById<Button>(R.id.btn_save)

        btnSave.setOnClickListener {
            val name = edtName.text.toString()
            val category = edtCategory.text.toString()
            val price = edtPrice.text.toString()

            if (name.isEmpty() || category.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val product = hashMapOf(
                "Nome" to name,
                "Categoria" to category,
                "Preço" to price.toDouble()
            )

            firestore.collection("Produtos")
                .add(product)
                .addOnSuccessListener {
                    Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao salvar produto", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
