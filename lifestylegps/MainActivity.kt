package com.example.lifestylegps

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tempeui.R
import com.google.firebase.perf.util.Timer
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var setting : ImageView
    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var pressureTextView: TextView
    private lateinit var thermometerImageView: ImageView


    private var temperature: Int = 0
    private var humidity: Int = 0
    private var pressure: Int = 0
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setting = findViewById(R.id.setting)
        temperatureTextView = findViewById(R.id.txt3)
        humidityTextView = findViewById(R.id.humidity)
        pressureTextView = findViewById(R.id.Pressure)
        thermometerImageView = findViewById(R.id.thermometerImage)


        val temperature = generateRandomValue(15, 35)
        val humidity = generateRandomValue(40, 80)
        val pressure = generateRandomValue(980, 1020)

        temperatureTextView.text = "Temperature: $temperature°C"
        humidityTextView.text = "Humidity: $humidity%"
        pressureTextView.text = "Pressure: $pressure hPa"

        updateThermometerImage(temperature)


        setting.setOnClickListener {

            showPopupWindow(it)
        }

    }
    private fun generateRandomValue(min: Int, max: Int): Int {
        return Random().nextInt(max - min + 1) + min
    }

    private fun updateThermometerImage(temperature: Int) {
        when {
            temperature < 20 -> {
                thermometerImageView.setImageResource(R.drawable.thermometer_19)
            }
            temperature in 20..28 -> {
                thermometerImageView.setImageResource(R.drawable.thermometer_27)
            }
            temperature > 28 -> {
                thermometerImageView.setImageResource(R.drawable.thermometer_28)
            }
        }
    }


    private fun showPopupWindow(view: View) {

        val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.setting_popup, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        popupWindow.isFocusable = true

        val popupText: TextView = popupView.findViewById(R.id.popup_textbox)
        val popupButton: Button = popupView.findViewById(R.id.popup_button)
        popupButton.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.showAtLocation(view.rootView, Gravity.CENTER, 0, 0)
    }
}