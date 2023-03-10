package shamsiddin.project.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.gson.Gson
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.R
import shamsiddin.project.bookstore.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var animation_menu: Animation
    lateinit var animation_menu_finish: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.menu.setOnClickListener {
            animation_menu = AnimationUtils.loadAnimation(this, R.anim.menu_animation)
            binding.menutask.startAnimation(animation_menu)
            binding.menutask.visibility = View.VISIBLE
        }
        binding.backtomain.setOnClickListener {
            animation_menu_finish = AnimationUtils.loadAnimation(this, R.anim.menu_animtion_finish)
            binding.menutask.startAnimation(animation_menu_finish)
            binding.menutask.visibility = View.GONE
        }
        binding.linerRename.setOnClickListener {
            binding.editName.visibility = View.VISIBLE
            binding.nameTextt.visibility = View.GONE
            binding.done.visibility = View.VISIBLE
        }
        binding.done.setOnClickListener {
            if (!binding.renamedName.text.isNullOrEmpty()){
                binding.personName.text = binding.renamedName.text
            }
            binding.done.visibility = View.GONE
            binding.editName.visibility = View.GONE
            binding.nameTextt.visibility = View.VISIBLE
        }
        binding.homeProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.newsProfile.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
        binding.selectedProfile.setOnClickListener {
            val intent = Intent(this, SavedActivity::class.java)
            startActivity(intent)
        }
        binding.linerMyBooks.setOnClickListener {
            val intent = Intent(this, SavedActivity::class.java)
            startActivity(intent)
        }
        binding.linerLogOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.linerMybooks.setOnClickListener {
            val intent = Intent(this, SavedActivity::class.java)
            startActivity(intent)
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
        binding.linerLang.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            val getpref = getSharedPreferences("Til", MODE_PRIVATE)
            val edit = getpref.edit()
            val gson = Gson()
            edit.putString("profil", "true").apply()
            startActivity(intent)
        }
    }
}