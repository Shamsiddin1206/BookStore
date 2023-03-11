package shamsiddin.project.bookstore

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import shamsiddin.project.bookstore.databinding.ActivitySplashBinding
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var userList = ArrayList<String>()
        var name:String = ""
        val type2 = object : TypeToken<String>(){}.type
        val type = object : TypeToken<ArrayList<String>>(){}.type
        val gson = Gson()
        val gson2 = Gson()
        val getPreferences2 = getSharedPreferences("Til", MODE_PRIVATE)
        val str2 = getPreferences2.getString("lang", "-2")
        val getPreferences = getSharedPreferences("Status", MODE_PRIVATE)
        val str = getPreferences.getString("status", "-1")

        binding.splashLogo.alpha = 0f
        binding.splashLogo.animate().setDuration(3000).alpha(1f).withEndAction{
            if (str =="-1"){
                val intent = Intent(this, LanguageActivity::class.java)
                startActivity(intent)
                return@withEndAction
            }else{
                userList = gson.fromJson(str, type)
            }
            name = gson2.fromJson(str2, type2)
            var a = 0
            for (i in 0..userList.size-1){
                if (userList[i]=="true"){
                    a=1
                }
            }
            if (name.isEmpty()){
                if (a==1){
                    val intent = Intent(this, LanguageActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Find(name)
                if (a==1){
                    val intent = Intent(this, CheckPrivacyActivity::class.java)
                    startActivity(intent)
                }else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

    private fun Find(s: String){
        if(s == "Uzbek"){
            val a = "uz"
            val locale = Locale(a)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }else if (s == "Russian"){
            val a = "ru"
            val locale = Locale(a)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }else{
            val locale = Locale.ENGLISH
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }

    }
}