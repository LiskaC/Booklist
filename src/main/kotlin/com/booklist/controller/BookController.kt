package com.booklist.controller

import com.booklist.repository.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookRepository: BookRepository) {

  @GetMapping("/books")
    fun getBooks(): List<String> {
      return bookRepository.getBooks()
    }
}