package com.example.currencyconverter.viewmodel

import com.example.currencyconverter.model.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.net.UnknownHostException

class ViewModelCurrency {

    suspend fun getAllCurrencyBasedUSD(): Pair<String, Any> {
        val url = "https://api.exchangeratesapi.io/v1/latest?access_key=159c8815df3b32de38ffd3209b012e4d"
        val client = OkHttpClient()

        // Tạo yêu cầu GET tới URL
        val request = Request.Builder()
            .url(url)
            .build()

        // Thực hiện yêu cầu và xử lý kết quả
        return withContext(Dispatchers.IO) {
            try {
                val response: Response =
                    client.newCall(request).execute()
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    if(jsonResponse == null){
                        Pair("error", "API return empty result")
                    }
                    else {
                        try {
                            val jsonObject = JSONObject(jsonResponse)
                            val rates = jsonObject.getJSONObject("rates")
                            if (rates.length()<2) {
                                Pair("error", "API doesn't get enough data")
                            } else {
                                val currList = mutableListOf<Currency>()
                                for (currency in rates.keys()) {
                                    val rateValue = rates.getDouble(currency)
                                    if(rateValue > 0) {
                                        currList.add(Currency(currency,rateValue))
                                    }
                                }
                                Pair("ok", currList)
                            }
                        }
                        catch (e: Exception){
                            Pair("error", "Error while parsing Json")
                        }
                    }

                } else {

                    Pair("error", "Error: ${response.code}")
                }
            }
            catch (e: UnknownHostException) {
                Pair("error", "Cannot connect to API, please try again")
            }
            catch (e: Exception) {
                println("Error: ${e.message}")
                Pair("error", "Some thing went wrong! Please try again")
            }
        }
    }

}

