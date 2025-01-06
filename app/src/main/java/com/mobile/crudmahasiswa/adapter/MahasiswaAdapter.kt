package com.mobile.crudmahasiswa.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.crudmahasiswa.DetailPage
import com.mobile.crudmahasiswa.R
import com.mobile.crudmahasiswa.helper.DbHelper
import com.mobile.crudmahasiswa.model.ModelMahasiswa

class MahasiswaAdapter(
    private var listMahasiswa : List<ModelMahasiswa>,
    val context: Context
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    private val db : DbHelper = DbHelper(context)

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtjurusan: TextView = itemView.findViewById(R.id.txtJurusan)
        val txtNim: TextView = itemView.findViewById(R.id.txtNIM)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEditItem)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDeleteItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data_mahasiswa,
            parent, false
        )
        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val nMahasiswa = listMahasiswa[position]
        holder.txtNim.text = nMahasiswa.nim.toString()
        holder.txtNama.text = nMahasiswa.nama
        holder.txtjurusan.text = nMahasiswa.jurusan

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, DetailPage::class.java)
            intent.putExtra("Jurusan", nMahasiswa.jurusan)
            intent.putExtra("Nama", nMahasiswa.nama)
            intent.putExtra("NIM", nMahasiswa.nim.toString())
            holder.itemView.context.startActivity(intent)


        }

        holder.btnDelete.setOnClickListener(){
            db.deleteMahasiswa(nMahasiswa.id)
            refreshData(db.getAllDataMahasiswa())
            Toast.makeText(holder.itemView.context,
                "Berhasil Delete Data ${nMahasiswa.nama}", Toast.LENGTH_LONG
            ).show()
        }

        }
        //untuk refersh data
        fun refreshData(newMahasiswa: List<ModelMahasiswa>) {
            listMahasiswa = newMahasiswa
            notifyDataSetChanged()

    }
}