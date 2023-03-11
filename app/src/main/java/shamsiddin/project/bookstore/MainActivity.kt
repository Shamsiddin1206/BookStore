package shamsiddin.project.bookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import shamsiddin.project.bookstore.Adapters.BookAdapter
import shamsiddin.project.bookstore.DataClasses.Book
import shamsiddin.project.bookstore.DataClasses.User
import shamsiddin.project.bookstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var animation_menu: Animation
    lateinit var animation_menu_finish: Animation
    lateinit var bookList:ArrayList<Book>
    lateinit var allbooks:ArrayList<Book>
    lateinit var currentAdapter: BookAdapter
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        allbooks = arrayListOf()
        val getPreferences = getSharedPreferences("Books", MODE_PRIVATE)
        val edit = getPreferences.edit()
        val type = object : TypeToken<ArrayList<Book>>(){}.type
        val gson = Gson()
        val str = getPreferences.getString("booklist", "-1")
        if (str == "-1"){
            bookList = arrayListOf()
        }else{
            bookList = gson.fromJson(str, type)
        }
        val s = gson.toJson(bookList)
        edit.putString("booklist", s).apply()

        binding.news.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
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
        val bookAdapter = BookAdapter(this, fantasyAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                    Log.d("SSS", "onSelect: ${bookList.joinToString()}")
                }else{
                    bookList.remove(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                }
            }
        })
        index = 0
        currentAdapter = bookAdapter
        binding.bookListview.adapter = bookAdapter


        val religionAdapter = BookAdapter(this, relifionAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                    Log.d("SSS", "onSelect: ${bookList.joinToString()}")
                }else{
                    bookList.remove(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                    Log.d("SSS", "onSelect: ${bookList.joinToString()}")
                }
            }
        })

        val literatureAdapter = BookAdapter(this, literatureAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                    Log.d("SSS", "onSelect: ${bookList.joinToString()}")
                }else{
                    bookList.remove(book)
                    val a = gson.toJson(bookList)
                    edit.putString("booklist", a).apply()
                    Log.d("SSS", "onSelect: ${bookList.joinToString()}")
                }
            }
        })

        val BioAdapter = BookAdapter(this, relifionAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                }else{
                    bookList.remove(book)
                }
            }
        })

        val NovelAdapter = BookAdapter(this, literatureAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                }else{
                    bookList.remove(book)
                }
            }
        })

        val ScienceAdapter = BookAdapter(this, fantasyAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                }else{
                    bookList.remove(book)
                }
            }
        })


        val BusinessAdapter = BookAdapter(this, fantasyAPI(), object : BookAdapter.OnEditListener {
            override fun onSelect(book: Book) {
                if (book.holati){
                    Check(book)
                }else{
                    bookList.remove(book)
                }
            }
        })




        //title kodlari//

        binding.fantasy.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 0
            currentAdapter = bookAdapter
            binding.bookListview.adapter = bookAdapter
        }

        binding.religion.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 1
            currentAdapter = religionAdapter
            binding.bookListview.adapter = religionAdapter
        }

        binding.literature.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 2
            currentAdapter = literatureAdapter
            binding.bookListview.adapter = literatureAdapter
        }

        binding.busLit.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 3
            currentAdapter = BusinessAdapter
            binding.bookListview.adapter = BusinessAdapter
        }

        binding.biography.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 4
            currentAdapter = BioAdapter
            binding.bookListview.adapter = BioAdapter
        }

        binding.novel.setOnClickListener {
            binding.fanLine.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline)
            binding.scienceLine.setBackgroundResource(R.drawable.underline2)
            index = 5
            currentAdapter = NovelAdapter
            binding.bookListview.adapter = NovelAdapter
        }

        binding.science.setOnClickListener {
            binding.science.setBackgroundResource(R.drawable.underline2)
            binding.relLine.setBackgroundResource(R.drawable.underline2)
            binding.litLine.setBackgroundResource(R.drawable.underline2)
            binding.busLine.setBackgroundResource(R.drawable.underline2)
            binding.biogLine.setBackgroundResource(R.drawable.underline2)
            binding.novelLine.setBackgroundResource(R.drawable.underline2)
            binding.scienceLine.setBackgroundResource(R.drawable.underline)
            index = 6
            currentAdapter = ScienceAdapter
            binding.bookListview.adapter = ScienceAdapter
        }

        binding.search.addTextChangedListener {
            val userFilter = arrayListOf<Book>()
            if (it!!.length>0 && it !=null){
                for (i in 0..allbooks.size-1){
                    if (allbooks[i].title.trim().toString().contains(it.trim().toString())){
                        userFilter.add(allbooks[i])
                    }
                }
                val adapter2 = BookAdapter(this, userFilter, object : BookAdapter.OnEditListener{
                    override fun onSelect(book: Book) {
                        TODO("Not yet implemented")
                    }
                })
                if (it.length==0){
                    binding.bookListview.adapter = bookAdapter
                }else{
                    binding.bookListview.adapter = adapter2
                }
            }
        }

        binding.bookListview.setOnItemClickListener { parent, view, position, id ->
            val bookk = SelectList().get(position)
            val intent = Intent(this, OneBookActivity::class.java)
            intent.putExtra("book", bookk)
            startActivity(intent)
        }
        binding.linerMybooks.setOnClickListener {
            val intent = Intent(this, SavedActivity::class.java)
            intent.putExtra("savedList", bookList)
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

        binding.cancell.setOnClickListener {
            binding.search.text.clear()
            binding.bookListview.adapter = bookAdapter
        }
    }

    private fun fantasyAPI(): ArrayList<Book> {
        val fantasylist = arrayListOf<Book>()
        fantasylist.add(Book("451 Fahrenheit", "Ray Bradbury", "", R.drawable.farenheit, "4.6", false))
        fantasylist.add(Book("Awakened darkness", "Evgeniy Gagloyev", "", R.drawable.pardus7, "5.0", false))
        fantasylist.add(Book("Lord Of The Rings", "J.R.R.Tolkien", "", R.drawable.kingofthering, "5.0", false))
        fantasylist.add(Book("Waking nightmare", "Carly Anne West", "", R.drawable.koshmarnayavu, "4.5", false))
        fantasylist.add(Book("Martian", "Ray Bradbury", "", R.drawable.marcianin, "4.7", false))
        fantasylist.add(Book("Take the Star Road", "Peter Grant", "", R.drawable.star_way, "4.7", false))
        fantasylist.add(Book("Star Wars", "Terry Brooks", "", R.drawable.starwars, "4.9", false))
        fantasylist.add(Book("451 Fahrenheit", "Rey Bredberi", "", R.drawable.farenheit, "4.9", false))
        AddBooks(fantasylist)
        Log.d("DDD", "fantasyAPI: ${fantasylist[0].holati}")
        CheckYurak(fantasylist)
        return fantasylist
    }

    private fun relifionAPI(): ArrayList<Book>{
        val userlist = arrayListOf<Book>()
        userlist.add(Book("Odoblar xazinasi", "Shayh Muhammad Sodiq","Muhammad Yusuf", R.drawable.odoblar_xazinasi, "5.0", false))
        userlist.add(Book("Saodat asri", "Axmad Lutfiy Qozonchi","", R.drawable.saodat_asri, "5.0", false))
        userlist.add(Book("Xadis va Hayot", "Shayh Muhammad Sodiq","Muhammad Yusuf", R.drawable.xadis_hayot, "5.0", false))
        userlist.add(Book("Tafsiri Hilol", "Shayh Muhammad Sodiq","Muhammad Yusuf", R.drawable.tafsiri_hilol, "5.0", false))
        userlist.add(Book("Ruhiy tarbiya", "Shayh Muhammad Sodiq","Muhammad Yusuf", R.drawable.ruhiy_tarbiya, "5.0", false))
        AddBooks(userlist)
        CheckYurak(userlist)
        return userlist
    }

    private fun literatureAPI(): ArrayList<Book>{
        val userlist = arrayListOf<Book>()
        userlist.add(Book("War and Piece", "Lev Tolstoy", "", R.drawable.voynaimir, "5.0", false))
        userlist.add(Book("Dead Souls", "Nikolay Gogol", "", R.drawable.mortviedushi, "4.8", false))
        userlist.add(Book("Three Musketeers", "Alexander Dumas", "", R.drawable.trimush, "4.9", false))
        userlist.add(Book("Player", "F. Dostoevsky", "", R.drawable.igrok, "4.7", false))
        userlist.add(Book("451 Fahrenheit", "Ray Bradbury", "", R.drawable.farenheit, "4.6", false))
        AddBooks(userlist)
        CheckYurak(userlist)
        return userlist
    }

    private fun AddBooks(arrayList: ArrayList<Book>){
        for (i in 0..arrayList.size-1){
            allbooks.add(arrayList[i])
        }
    }

    private fun SelectList():ArrayList<Book>{
        if (index==1){
            return relifionAPI()
        }
        if (index==2){
            return literatureAPI()
        }
        if (index==3){
            return fantasyAPI()
        }
        if (index==4){
            return relifionAPI()
        }
        if (index==5){
            return literatureAPI()
        }
        return fantasyAPI()
    }

    private fun Check(book: Book){
        if (book !in bookList){
            bookList.add(book)
        }
    }

    private fun CheckYurak(arrayList: ArrayList<Book>){
        for (i in 0..arrayList.size-1){
            if (arrayList[i] in bookList){
                arrayList[i].holati = true
            }
        }
    }
}