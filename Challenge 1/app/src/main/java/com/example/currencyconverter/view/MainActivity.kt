package com.example.currencyconverter.view

import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.model.Currency
import com.example.currencyconverter.viewmodel.FormHandler
import com.example.currencyconverter.viewmodel.ViewModelCurrency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private var status: String = "error" // Trạng thái ban đầu
    private var hasRunFormHandler: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val errorText :TextView = findViewById(R.id.errorText)
        val currency1 :Spinner = findViewById(R.id.currency1)
        val currency2 :Spinner = findViewById(R.id.currency2)
        val amount1 :EditText = findViewById(R.id.amount1)
        val amount2 :EditText = findViewById(R.id.amount2)
        handler = Handler()
        CoroutineScope(Dispatchers.Main).launch {checkCurrencyStatus(errorText,currency1,currency2,amount1,amount2)}
    }
    private suspend fun checkCurrencyStatus(errorText :TextView, currency1 :Spinner, currency2 :Spinner, amount1 :EditText, amount2 :EditText) {
        val formHandler = FormHandler(currency1, currency2, amount1, amount2, emptyList(), applicationContext)
        val vmc = ViewModelCurrency()
            while (status == "error") {
                val (result, content) = vmc.getAllCurrencyBasedUSD()
                if (result == "error") {
                    errorText.setText(content.toString())
                    formHandler.lockAllInput()
                    delay(1000)
                    checkCurrencyStatus(errorText,currency1,currency2,amount1,amount2)

                }
                else{
                    if(content is List<*>){
                        status = "ok"
                        errorText.setText("")
                        val currList = content as List<Currency>
                        formHandler.updateCurrList(currList)
                        formHandler.setupListeners()
                        delay(3600000)
                        checkCurrencyStatus(errorText,currency1,currency2,amount1,amount2)
                    }
                    else{
                        errorText.setText("API doesn't return the right Currency rate list")
                        delay(1000)
                        checkCurrencyStatus(errorText,currency1,currency2,amount1,amount2)

                    }
                }
            }



    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Dừng mọi callback khi Activity bị hủy
    }
}


