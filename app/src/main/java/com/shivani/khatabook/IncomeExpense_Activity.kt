package com.shivani.khatabook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.shivani.khatabook.databinding.ActivityIncomeExpenseBinding

lateinit var binding1 : ActivityIncomeExpenseBinding

class IncomeExpense_Activity : AppCompatActivity() {
    var id :String? = null
    lateinit var db:DBhelper
    var categoryList= arrayListOf<Catogery_modelclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityIncomeExpenseBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        db = DBhelper(this)

        categoryList = db.getCategory()
        initbinding()
        getintentData()
    }
    private fun initbinding(){
        var nameList = arrayListOf<String>()
        categoryList.forEach {
            nameList.add(it.name)
        }
        var arrayadapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,nameList
        )
        binding1.Spinner.adapter = arrayadapter

        binding1.btnIncome.setOnClickListener {

            var index = binding1.Spinner.selectedItemPosition
            var finelId ="0"
            if (id!=null)
            {
                finelId = id!!
            }
            var model = IncomeExpense_model(
                finelId,
                binding1.edttitle.text.toString(),
                binding1.edtamounts.text.toString(),
                binding1.edtdate.text.toString(),
                binding1.edtnote.text.toString(),
                binding1.edttime.text.toString(),
                nameList[index])
            if (id!=null)
            {
                db.updateIncomeExpense(model)
            }
            else{
                db.addIncomeExpense(model)
            }
            finish()
        }
        binding1.btnExpense.setOnClickListener {
            var index = binding1.Spinner.selectedItemPosition
            var finelId ="0"
            if (id!=null){
                finelId = id!!
            }
            var model = IncomeExpense_model(
                finelId,
            binding1.edttitle.text.toString(),
            binding1.edtamounts.text.toString(),
            binding1.edtdate.text.toString(),
            binding1.edtnote.text.toString(),
            binding1.edttime.text.toString(),
                nameList[index])
            if (id!=null){
                db.updateIncomeExpense(model)
            }
            else{
                db.addIncomeExpense(model)
            }
            finish()
        }
        binding1.btnDelete.setOnClickListener {
            db.deleteIncomeExpense(id!!)
            finish()
        }
    }
    private fun getintentData(){

        var notes = intent.getStringExtra("notes")
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var date = intent.getStringExtra("date")
        var id = intent.getStringExtra("id")

        var category = intent.getStringExtra("category")
        var time =intent.getStringExtra("time")

        if (amount!=null)
        {
            binding1.btnDelete.visibility = View.VISIBLE
            binding1.edtamounts.setText("$amount")
            binding1.edtdate.setText("$date")
            binding1.edtnote.setText("$notes")
            binding1.edttitle.setText("$title")
            binding1.edttime.setText("$time")

            var i=0

            while (i<categoryList.size){
                if (categoryList[i].name.equals(category)){
                    binding1.Spinner.setSelection(i)
                }
                i++
            }
        }


    }
}