package com.shivani.khatabook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.BaseProgressIndicator
import com.shivani.khatabook.databinding.ActivityMainBinding

lateinit var  binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var db: DBhelper
    lateinit var dataList: ArrayList<IncomeExpense_model>

    override fun onResume() {
        super.onResume()
        dataList = DBhelper(this).getIncomeExpense()
        rvsetup()

    }
        override fun onCreate(savedInstanceState: Bundle?) {

            binding = ActivityMainBinding.inflate(layoutInflater)
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            db = DBhelper(this)
            dataList = db.getIncomeExpense()
            rvsetup()

            binding.btnadd.setOnClickListener {
                var intent = Intent(this, Category_Activity::class.java)
                startActivity(intent)
            }
            binding.btnfloating.setOnClickListener {
                var intent = Intent(this, IncomeExpense_Activity::class.java)
                startActivity(intent)
            }
        }

        private fun rvsetup() {
            var adapter = Data_adapter(this, dataList)
            var lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.rvdata.layoutManager = lm
            binding.rvdata.adapter = adapter
        }
    }
