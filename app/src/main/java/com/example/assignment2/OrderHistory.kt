package com.example.assignment2

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_order_history.*
import java.util.ArrayList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OrderHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Order History"
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val i = intent
        var order = arrayListOf<OrderItems>()
        var stringOrders = arrayListOf<String>()

        order = i.getSerializableExtra("Key") as ArrayList<OrderItems>
        Log.d("DEBUG", order.toString())

        stringOrders = ArrayList()
        for (item in order) {
            stringOrders.add(item.toString())
        }

        val adapter = ArrayAdapter<String>(
            this@OrderHistory,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            stringOrders
        )


        listView.adapter = adapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
