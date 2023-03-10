package shamsiddin.project.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.R
import shamsiddin.project.bookstore.databinding.ActivityMainBinding
import shamsiddin.project.bookstore.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsBinding
    lateinit var animation_menu: Animation
    lateinit var animation_menu_finish: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.menu.setOnClickListener {
            animation_menu = AnimationUtils.loadAnimation(this, R.anim.menu_animation)
            binding.menutask.startAnimation(animation_menu)
            binding.menutask.visibility = View.VISIBLE
        }
        binding.logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.backtomain.setOnClickListener {
            binding.menutask.visibility = View.GONE
            animation_menu_finish = AnimationUtils.loadAnimation(this, R.anim.menu_animtion_finish)
            binding.menutask.startAnimation(animation_menu_finish)
        }

        binding.home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.selected.setOnClickListener {
            val intent = Intent(this, SavedActivity::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
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
    }
}