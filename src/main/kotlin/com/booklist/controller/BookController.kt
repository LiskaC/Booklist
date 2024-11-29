package com.booklist.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {

  @GetMapping("/books")
    fun getBooks(): List<String> {
      return listOf("Book 1", "Book 2", "Book 3")
    }
}