package com.booklist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooklistApplication

fun main(args: Array<String>) {
	runApplication<BooklistApplication>(*args)
}
