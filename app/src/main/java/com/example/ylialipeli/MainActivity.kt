package com.example.ylialipeli

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import android.widget.GridLayout



class MainActivity : ComponentActivity() {

    private lateinit var peli: YliAliPeli
    private lateinit var currentStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peli = YliAliPeli(1, 16)

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)
        currentStatus = findViewById(R.id.currentStatus)

        for (i in 1..16) {
            val button = Button(this).apply {
                text = i.toString()
                setOnClickListener { tarkistaArvaus(i) }
            }
            gridLayout.addView(button)
        }
    }

    private fun tarkistaArvaus(arvaus: Int) {
        val tulos = peli.arvaa(arvaus)
        when (tulos) {
            Arvaustulos.Low -> currentStatus.text = "Liian pieni"
            Arvaustulos.High -> currentStatus.text = "Liian suuri"
            Arvaustulos.Hit -> currentStatus.text = "Oikein. Arvauksia: ${peli.guesses}"
        }
    }
}



    class YliAliPeli(val low: Int, val high: Int) {
        val secret = (low..high).random()
        var guesses = 0
        fun arvaa(arvaus: Int): Arvaustulos {
            guesses++
            return if (arvaus > secret)
                Arvaustulos.High
            else if (arvaus < secret)
                Arvaustulos.Low
            else Arvaustulos.Hit
        }
    }

    enum class Arvaustulos {
        Low, Hit, High
    }