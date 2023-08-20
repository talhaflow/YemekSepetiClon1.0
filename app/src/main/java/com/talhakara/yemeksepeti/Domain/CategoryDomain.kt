package com.talhakara.yemeksepeti.domain

class CategoryDomain(private val title: String, private val pic: String) {

    init {
        require(title.isNotBlank()) { "Title cannot be blank" }
        require(pic.isNotBlank()) { "Pic URL cannot be blank" }
    }

    fun getTitle(): String {
        return title
    }

    fun getPic(): String {
        return pic
    }
}
