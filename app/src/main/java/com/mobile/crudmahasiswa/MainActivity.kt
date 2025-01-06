package com.mobile.crudmahasiswa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.crudmahasiswa.adapter.MahasiswaAdapter
import com.mobile.crudmahasiswa.databinding.ActivityMainBinding
import com.mobile.crudmahasiswa.databinding.ActivityTambahDataMahasiswaBinding
import com.mobile.crudmahasiswa.helper.DbHelper
import com.mobile.crudmahasiswa.screen_page.TambahDataMahasiswa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : DbHelper
    private lateinit var mahasiswaAdapter: MahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DbHelper(this)
        mahasiswaAdapter = MahasiswaAdapter(db.getAllDataMahasiswa(),this)

        binding.rvDataMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.rvDataMahasiswa.adapter = mahasiswaAdapter

        //silahkan buat detail page
        //ketika diklik item nya akan pindah

        binding.btnPageTambah.setOnClickListener{
            val intent = Intent(this,TambahDataMahasiswa::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val newMahasiswa = db.getAllDataMahasiswa()
        mahasiswaAdapter.refreshData(newMahasiswa)

    }
}