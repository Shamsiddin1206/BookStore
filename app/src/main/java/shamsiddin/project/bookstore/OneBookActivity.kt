package shamsiddin.project.bookstore

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.databinding.ActivityOneBookBinding

class OneBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityOneBookBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bookkk:Book = intent.getSerializableExtra("book") as Book

        binding.mainrasm.setImageResource(bookkk.image)
        binding.authorname.text = (bookkk.name + " " +bookkk.name2)
        binding.mainname.text = bookkk.title

        binding.orqaga.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}