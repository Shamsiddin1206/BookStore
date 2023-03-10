package shamsiddin.project.bookstore.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shamsiddin.project.bookstore.DataClasses.TitlesItemData
import shamsiddin.project.bookstore.R
import shamsiddin.project.bookstore.databinding.TitleItemBinding

class TitleAdapter(var list:MutableList<TitlesItemData>): RecyclerView.Adapter<TitleAdapter.MyHolder>() {
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.title_text)
        var line = itemView.findViewById<ImageView>(R.id.line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var myholder = MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false))
        return myholder
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var a = list.get(position)
        holder.title.text = a.textt
        holder.line.setBackgroundResource(R.drawable.underline2)
    }
    override fun getItemCount(): Int {
        return list.size
    }
}