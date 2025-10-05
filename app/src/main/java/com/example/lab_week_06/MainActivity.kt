package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    private val swipeToDeleteCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            // ✅ gunakan fungsi yang benar
            catAdapter.removeItem(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Female, CatBreed.BalineseJavanese, "Luna", "Playful and loving", "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Simba", "King of the couch", "https://cdn2.thecatapi.com/images/MTY3ODIyMQ2.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Milo", "Always curious", "https://cdn2.thecatapi.com/images/ckn.jpg"),
                CatModel(Gender.Female, CatBreed.BalineseJavanese, "Chloe", "Gentle soul", "https://cdn2.thecatapi.com/images/MTg0NzY5MA.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Oliver", "Loves naps", "https://cdn2.thecatapi.com/images/MTg0NzY5MA2.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Daisy", "Loves belly rubs", "https://cdn2.thecatapi.com/images/bpc.jpg"),
                CatModel(Gender.Unknown, CatBreed.BalineseJavanese, "Charlie", "Mysterious observer", "https://cdn2.thecatapi.com/images/6qi.jpg")
            )
        )


    }

    // ✅ Pindahkan ke dalam class MainActivity
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK", null)
            .show()
    }
}
