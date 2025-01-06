package com.mobile.crudmahasiswa

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPage : AppCompatActivity() {

    private lateinit var txtJurusan : TextView
    private lateinit var txtNama : TextView
    private lateinit var txtNim : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page)

        txtJurusan = findViewById(R.id.txtJurusan)
        txtNama = findViewById(R.id.txtNama)
        txtNim = findViewById(R.id.txtNIM)

        val detailJurusan = intent.getStringExtra("Jurusan")
        val detailNama = intent.getStringExtra("Nama")
        val detilNim = intent.getStringExtra("NIM")

        txtJurusan.setText(detailJurusan)
        txtNama.setText(detailNama)
        txtNim.setText(detilNim)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}