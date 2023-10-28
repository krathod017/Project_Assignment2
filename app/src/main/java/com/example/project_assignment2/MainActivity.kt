package com.example.project_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var input: EditText
    private lateinit var unit: Spinner
    private lateinit var km: TextView
    private lateinit var m: TextView
    private lateinit var cm: TextView
    private lateinit var mm: TextView
    private lateinit var microm: TextView
    private lateinit var nm: TextView
    private lateinit var mile: TextView
    private lateinit var yard: TextView
    private lateinit var foot: TextView
    private lateinit var inch: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.unit)
        km = findViewById(R.id.km)
        m = findViewById(R.id.m)
        cm = findViewById(R.id.cm)
        mm = findViewById(R.id.mm)
        microm = findViewById(R.id.microm)
        nm = findViewById(R.id.nm)
        mile = findViewById(R.id.mile)
        yard = findViewById(R.id.yard)
        foot = findViewById(R.id.foot)
        inch = findViewById(R.id.inch)

        val arr = arrayOf("km","m","cm","mm","microm","nm","mile","yard","foot","inch"
        )
        unit.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,arr)

        unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                i: Int,
                l: Long
            ) {
                update()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                update()
            }
        })
    }

    private fun update() {
        if (!input.text.toString().isEmpty() && unit.selectedItem.toString() != "") {
            val inValue = input.text.toString().toDouble()
            when (unit.selectedItem.toString()) {
                "km" -> setKm(inValue)
                "m" -> setKm(inValue / 1000)
                "cm" -> setKm(inValue / 100000)
                "mm" -> setKm(inValue / 1000000)
                "microm" -> setKm(inValue / 1000000000)
                "nm" -> {
                    val d = 1000000 * 1000000
                    setKm(inValue / d)
                }
                "mile" -> setKm(inValue * 1.609)
                "yard" -> setKm(inValue / 1094)
                "foot" -> setKm(inValue / 3281)
                "inch" -> setKm(inValue / 39370)
            }
        }
    }

    private fun setKm(kmIn: Double) {
        km.text = kmIn.toString()
        m.text = (kmIn * 1000).toString()
        cm.text = (kmIn * 100000).toString()
        mm.text = (kmIn * 1000000).toString()
        microm.text = (kmIn * 1000000000).toString()
        nm.text = (kmIn * 1000000 * 1000000).toString()
        mile.text = (kmIn / 1.609).toString()
        yard.text = (kmIn * 1094).toString()
        foot.text = (kmIn * 3281).toString()
        inch.text = (kmIn * 39370).toString()
    }
}
