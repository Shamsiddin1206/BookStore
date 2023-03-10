package shamsiddin.project.bookstore

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import shamsiddin.project.bookstore.Adapters.BookAdapter
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.DataClasses.User
import shamsiddin.project.bookstore.databinding.ActivityMainBinding
import shamsiddin.project.bookstore.databinding.ActivitySavedBinding

class SavedActivity : AppCompatActivity() {
    lateinit var binding: ActivitySavedBinding
    lateinit var animation_menu: Animation
    lateinit var animation_menu_finish: Animation
    lateinit var adapter: BookAdapter
    lateinit var list: ArrayList<Book>
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getpreferences = getSharedPreferences("Books", MODE_PRIVATE)
        val type = object : TypeToken<ArrayList<Book>>(){}.type
        val str = getpreferences.getString("booklist", "")
        val gson = Gson()
        list = gson.fromJson(str, type)
        if(list.isEmpty()){
            binding.hiddentext.visibility = View.VISIBLE
        }else{
            binding.savedbookListview.visibility = View.VISIBLE
            binding.hiddentext.visibility = View.GONE
            adapter = BookAdapter(this, list, object : BookAdapter.OnEditListener{
                override fun onSelect(book: Book) {
                    if (!book.holati){
                        list.remove(book)
                        adapter.notifyDataSetChanged()
                        if (list.size==0){
                            binding.hiddentext.visibility = View.VISIBLE
                            binding.savedbookListview.visibility = View.GONE
                        }
                    }
                }
            })
            binding.savedbookListview.adapter = adapter
        }
        binding.menu.setOnClickListener {
            binding.menutask.visibility = View.VISIBLE
            animation_menu = AnimationUtils.loadAnimation(this, R.anim.menu_animation)
            binding.menutask.startAnimation(animation_menu)
        }
        binding.backtomain.setOnClickListener {
            animation_menu_finish = AnimationUtils.loadAnimation(this, R.anim.menu_animtion_finish)
            binding.menutask.startAnimation(animation_menu_finish)
            binding.menutask.visibility = View.GONE
        }
        binding.home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.news.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        binding.linerMybooks.setOnClickListener {
            Toast.makeText(this, "You are already in this page", Toast.LENGTH_SHORT).show()
        }
        binding.linerNotification.setOnClickListener {
            Toast.makeText(this, getString(R.string.notes), Toast.LENGTH_SHORT).show()
        }
        binding.personProfileImg.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.help.setOnClickListener {
            Toast.makeText(this, getString(R.string.contactme), Toast.LENGTH_SHORT).show()
        }
    }
}