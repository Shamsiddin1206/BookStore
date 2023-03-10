package shamsiddin.project.bookstore.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.R
import shamsiddin.project.bookstore.databinding.BookItemBinding

class BookAdapter(context: Context, var books:ArrayList<Book>, var listner: OnEditListener):ArrayAdapter<Book>(context, R.layout.book_item, books) {
    override fun getCount(): Int {
        return books.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: BookItemBinding
        if (convertView==null){
            view = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }else{
            view = BookItemBinding.bind(convertView)
        }
        val item = books.get(position)
        view.name.text = item.title
        view.yozuvName.text = item.name
        view.reyting.text = item.baho
        view.photo.setImageResource(item.image)
        view.name2.text = item.name2
        if (item.holati){
            view.yurak.setImageResource(R.drawable.yurak2)
        }else{
            view.yurak.setImageResource(R.drawable.yurak1)
        }
        view.yurak.setOnClickListener {
            if (!item.holati){
                view.yurak.setImageResource(R.drawable.yurak2)
                item.holati = true
            }else{
                view.yurak.setImageResource(R.drawable.yurak1)
                item.holati = false
            }
            listner.onSelect(item)
        }
        return view.root
    }
    interface OnEditListener{
        fun onSelect(book:Book)
    }
}