package com.example.currencyconverter.model

class Currency {
    var name: String
    var rate: Double

    constructor() {
        name = ""
        rate = 0.0
    }

    // Constructor có cả hai tham số (tên và tỉ lệ)
    constructor(name: String, rate: Double) {
        this.name = name
        this.rate = rate
    }
}