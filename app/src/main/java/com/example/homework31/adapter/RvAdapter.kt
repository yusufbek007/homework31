package com.example.homework31.adapter

import com.example.homework31.databinding.ItemDialogBinding
import com.example.homework31.databinding.RvItemBinding
import com.example.homework31.models.Currency
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog


class RvAdapter(var list: List<Currency>, var context: Context) : RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: RvItemBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(currency: Currency, position: Int ){
            rvItemBinding.tv1.text = currency.CcyNm_EN
            rvItemBinding.tv2.text = currency.Rate

            rvItemBinding.back.setOnClickListener {
                val dialog = AlertDialog.Builder(context).create()
                val dialogItemBinding = ItemDialogBinding.inflate(LayoutInflater.from(context))
                dialog.setView(dialogItemBinding.root)
                dialogItemBinding.name.text = list[position].Ccy
                dialogItemBinding.count.text = list[position].Rate + "so'm"
                dialogItemBinding.news.text  = "Oxirgi Ynagilanish:" + list[position].Date
                dialogItemBinding.btn.setOnClickListener {
                    dialog.cancel()
                }
                dialog.setCancelable(false)
                dialog.show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}
