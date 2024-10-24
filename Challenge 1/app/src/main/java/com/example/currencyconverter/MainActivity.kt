package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editTextNumber :EditText = findViewById(R.id.amount1)
        editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrEmpty()) {
                    val text = s.toString()
                    if (text.startsWith("0") && text.length > 1 && text[1] != '.') {
                        s.delete(0, 1) // Xóa số 0 nếu không có dấu chấm phía sau
                    }
                    if(text.startsWith(".")){
                        s.insert(0,"0")
                    }
                }
                else{
                    editTextNumber.setText("0")
                    editTextNumber.setSelection(1);
                }
            }
        })

    }
}

