package com.talhakara.yemeksepeti.Domain

import java.io.Serializable


class FoodDomain() : Serializable {
    var title: String = ""
        get() = field
        set(value) {
            field = value
        }

    var pic: String = ""
        get() = field
        set(value) {
            field = value
        }

    var description: String = ""
        get() = field
        set(value) {
            field = value
        }

    var fee: Double = 0.0
        get() = field
        set(value) {
            field = value
        }

    var numberInCard: Int = 0
        get() = field
        set(value) {
            field = value
        }

    constructor(
        title: String,
        pic: String,
        description: String,
        fee: Double,
        numberInCard: Int
    ) : this() {
        this.title = title
        this.pic = pic
        this.description = description
        this.fee = fee
        this.numberInCard = numberInCard
    }

    constructor(
        title: String,
        pic: String,
        description: String,
        fee: Double
    ) : this(title, pic, description, fee, 0)



}
