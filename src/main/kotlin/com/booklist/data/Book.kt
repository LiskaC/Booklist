package com.booklist.data

data class BookRequest(val name: String)

data class AddBookResult(val success: Boolean, val message: String)