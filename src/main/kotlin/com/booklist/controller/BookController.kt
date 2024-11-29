package com.booklist.controller

import com.booklist.repository.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookRepository: BookRepository) {

  @GetMapping("/books")
    fun getAllBooks(): List<String> {
      return bookRepository.getAllBooks()
    }

  @GetMapping("/book")
    fun getBookByName(@RequestParam(name = "name") name: String): ResponseEntity<String> {
      val book = bookRepository.getBookByName(name)
      return if (book != null) {
        ResponseEntity.ok(book)
      } else {
        ResponseEntity.notFound().build()
      }
    }
}