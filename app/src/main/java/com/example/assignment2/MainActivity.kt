package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main. *
import java.text.DecimalFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var spinnerItems = ArrayList<String>();
    var spinnerItems2 = ArrayList<String>();
    var ordercount = 0
    var df = DecimalFormat("#,##0.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculateTotal()

        spinnerItems = arrayListOf("Small", "Medium", "Large")
        spinnerItems2 = arrayListOf("Vanilla", "Chocolate", "Strawberry")

        var spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems)
        var spinnerAdapter2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems2)

        spinner.adapter = spinnerAdapter;
        spinner2.adapter = spinnerAdapter2;

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTotal()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                calculateTotal()
            }

        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            calculateTotal()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            calculateTotal()
        }

    }

    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            calculateTotal()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }

    })
    }

        var orders = arrayListOf<OrderItems>()

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu_main, menu)
            return true
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this@MainActivity, AboutActivity2::class.java)
//                intent.putExtra("Datakey", orders)
                startActivity(intent)
                return true
            }
            R.id.menu_order -> {
                val intent2 = Intent(this@MainActivity, OrderHistory::class.java)
                intent2.putExtra("Key", orders)
                startActivity(intent2)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun process_checkBoxes(view: View) {
        calculateTotal()
    }

    fun reset_default(view: View?) {
        spinner.setSelection(0)
        spinner2.setSelection(0)
        seekBar.progress = 1
        checkBox9.isChecked = false
        checkBox10.isChecked = false
        checkBox11.isChecked = false
        checkBox12.isChecked = false
        checkBox13.isChecked = false
        checkBox14.isChecked = false
        checkBox15.isChecked = false
        checkBox16.isChecked = false
        calculateTotal()
    }
    fun process_theworks(view: View) {
        spinner.setSelection(2)
        seekBar.progress = 3
        checkBox9.isChecked = true
        checkBox10.isChecked = true
        checkBox11.isChecked = true
        checkBox12.isChecked = true
        checkBox13.isChecked = true
        checkBox14.isChecked = true
        checkBox15.isChecked = true
        checkBox16.isChecked = true
        calculateTotal()
    }
    fun process_order(view: View) {
        ordercount++
        val orderInfo = "$ordercount"
        val flavorInfo = spinner2.selectedItem as String
        val sizeInfo = spinner.selectedItem as String
        val dateInfo = Date(System.currentTimeMillis()).toString()
        val costInfo = textView.text as String
        var toppingInfo = ""
        val fudgeInfo = seekBar.progress.toString() + " oz"
        if (checkBox9.isChecked)
            toppingInfo += "Peanuts, "
        if (checkBox10.isChecked)
            toppingInfo += "Almonds, "
        if (checkBox11.isChecked)
            toppingInfo += "Strawberries, "
        if (checkBox12.isChecked)
            toppingInfo += "Gummy Bears, "
        if (checkBox13.isChecked)
            toppingInfo += "M&M's, "
        if (checkBox14.isChecked)
            toppingInfo += "Brownies, "
        if (checkBox15.isChecked)
            toppingInfo += "Oreo's, "
        if (checkBox16.isChecked)
            toppingInfo += "Marshmallows, "

        if (toppingInfo != "")
            toppingInfo = toppingInfo.substring(0, toppingInfo.length - 2)
        else
            toppingInfo = "None"



        orders.add(
            OrderItems(
                orderInfo,
                flavorInfo,
                sizeInfo,
                dateInfo,
                costInfo,
                toppingInfo,
                fudgeInfo
            )
        )
        reset_default(view = null)

    }
    fun calculateTotal() {
        var total = 0.00
        var price = "$"
        if (spinner.selectedItemPosition == 0)
            total += 2.99
        if (spinner.selectedItemPosition == 1)
            total += 3.99
        if (spinner.selectedItemPosition == 2)
            total += 4.99


        if (checkBox9.isChecked)
            total += 0.15
        if (checkBox10.isChecked)
            total += 0.15
        if (checkBox11.isChecked)
            total += 0.20
        if (checkBox12.isChecked)
            total += 0.20
        if (checkBox13.isChecked)
            total += 0.25
        if (checkBox14.isChecked)
            total += 0.20
        if (checkBox15.isChecked)
            total += 0.20
        if (checkBox16.isChecked)
            total += 0.15

        if (seekBar.progress == 1)
            total += 0.15
        if (seekBar.progress == 2)
            total += 0.25
        if (seekBar.progress == 3)
            total += 0.30


        total = java.lang.Double.valueOf(df.format(total))
        price += (total).toString()
        textView.text = price
    }


}