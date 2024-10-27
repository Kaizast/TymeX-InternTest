package com.example.currencyconverter.viewmodel
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.currencyconverter.R
import com.example.currencyconverter.model.Currency
import java.util.Locale

class FormHandler(
    private val spinner1: Spinner,
    private val spinner2: Spinner,
    private val editText1: EditText,
    private val editText2: EditText,
    private var currList: List<Currency>,
    private val context: Context
) {

    fun updateCurrList(newCurrList: List<Currency>) {
        currList = newCurrList
    }
    private var previousSelection1: Int = -1
    private var previousSelection2: Int = -1
    private var isEditing = false
    fun setupListeners() {

        unlockAllInput()
        previousSelection1 = spinner1.selectedItemPosition
        previousSelection2 = spinner2.selectedItemPosition
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem1 = spinner1.selectedItemPosition
                val selectedItem2 = spinner2.selectedItemPosition

                if (selectedItem1 == selectedItem2) {
                    // Đổi vị trí cho nhau
                    val temp = previousSelection1
                    spinner1.setSelection(selectedItem2)
                    spinner2.setSelection(temp)
                    previousSelection1 =spinner1.selectedItemPosition
                    previousSelection2 =spinner2.selectedItemPosition
                }
                if (!isEditing) {
                    isEditing = true // Đặt cờ lên
                    val amountChange = updateAmount(spinner1, editText1.text.toString(), spinner2)
                    editText1.setText(amountChange)
                    isEditing = false // Đặt cờ về lại
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem1 = spinner1.selectedItemPosition
                val selectedItem2 = spinner2.selectedItemPosition


                if (selectedItem1 == selectedItem2) {
                    val temp = previousSelection2
                    spinner2.setSelection(selectedItem1)
                    spinner1.setSelection(temp)
                    previousSelection1 =spinner1.selectedItemPosition
                    previousSelection2 =spinner2.selectedItemPosition
                }
                if (!isEditing) {
                    isEditing = true
                    val amountChange = updateAmount(spinner2, editText2.text.toString(), spinner1)
                    editText1.setText(amountChange)
                    isEditing = false
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        editText1.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    val text = s.toString()
                    if (text.startsWith("0") && text.length > 1 && text[1] != '.') {
                        s.delete(0, 1)
                    }
                    if (text.startsWith(".")) {
                        s.insert(0, "0")
                    }
                    else{
                        if (!isEditing) {
                            isEditing = true
                            val amountChange = updateAmount(spinner1,s.toString(),spinner2)
                            editText2.setText(amountChange)
                            isEditing = false
                        }
                    }
                } else {
                    editText1.setText("0")
                    editText1.setSelection(1)
                }

            }
        }
        )
        editText2.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    val text = s.toString()
                    if (text.startsWith("0") && text.length > 1 && text[1] != '.') {
                        s.delete(0, 1)
                    }
                    if (text.startsWith(".")) {
                        s.insert(0, "0")
                    }else{
                        if (!isEditing) {
                            isEditing = true
                            val amountChange = updateAmount(spinner2,s.toString(),spinner1)
                            editText1.setText(amountChange)
                            isEditing = false // Đặt cờ về lại
                        }
                    }
                } else {
                    editText1.setText("0")
                    editText1.setSelection(1)
                }

            }
        }
        )
    }  

    private fun unlockAllInput() {
        val currListName = mutableListOf<String>()

        for(curr in currList){
            currListName.add(curr.name)
        }
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, currListName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        adapter.notifyDataSetChanged()
        spinner1.isEnabled = true
        spinner2.isEnabled = true
        editText1.isEnabled = true
        editText2.isEnabled = true
        editText1.setText("1")
        selectMostPopularCurrency()
        if (!isEditing) {
            isEditing = true
            val amountChange = updateAmount(spinner1, editText1.text.toString(), spinner2)
            editText1.setText(amountChange)
            isEditing = false
        }

    }
    private fun selectMostPopularCurrency(){
        val xmlCurrencyList = context.resources.getStringArray(R.array.currency_array).toList()
        val matchingItems = xmlCurrencyList.intersect(currList.map { it.name }).toList()
        var pos1= 0
        var pos2= 1
        if(matchingItems.size >= 2){
            pos1 = currList.indexOfFirst{ it.name == (matchingItems[0])}
            pos2 = currList.indexOfFirst{ it.name == (matchingItems[1])}
        }
        if (matchingItems.size == 1) {
            pos1 = currList.indexOfFirst{ it.name == (matchingItems[0])}
            pos2 = 0
        }
        spinner1.setSelection(pos1)
        spinner2.setSelection(pos2)

    }
    fun lockAllInput(){
        val currListName = mutableListOf<String>()
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, currListName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter
        adapter.notifyDataSetChanged()
        spinner1.isEnabled = false
        spinner2.isEnabled = false
        editText1.isEnabled = false
        editText2.isEnabled = false
        editText1.setText("")
        editText2.setText("")
    }
    private fun updateAmount(currChoose: Spinner, amountChoose: String, currChange: Spinner) : String{
        val currChooseName = currChoose.selectedItem.toString()
        val currChangeName = currChange.selectedItem.toString()
        val setChoose = currList.find{it.name == currChooseName}
        val setChange = currList.find{it.name == currChangeName}
        val amount = amountChoose.toDouble()
        val result = (setChange!!.rate / setChoose!!.rate) * amount
        val stringResult = String.format(Locale.ENGLISH,"%.2f",result)
        return stringResult
        }

    }



