package com.shivani.khatabook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivani.khatabook.databinding.ActivityCategoryBinding

lateinit var categoryBinding: ActivityCategoryBinding

class Category_Activity : AppCompatActivity() {
    var list = arrayListOf<Catogery_modelclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)
        var db = DBhelper(this)
        list = db.getCategory()
        initbinding()
    }
    private fun initbinding(){
        var adapter = Category_Adapter(this@Category_Activity,list)
        var lm = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        categoryBinding.rvcategory.layoutManager = lm
        categoryBinding.rvcategory.adapter = adapter

        categoryBinding.btncategory.setOnClickListener {

            var db = DBhelper(this@Category_Activity)

            if (categoryBinding.EdtCatogery.text!!.isEmpty())
            {
                categoryBinding.EdtCatogery.setError("Please add category")
            }
            else
            {
                db.addCategory(categoryBinding.EdtCatogery.text.toString())
                finish()
            }
        }
    }
}