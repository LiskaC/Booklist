package com.booklist.booklist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class BooklistApplication: CommandLineRunner {

	@Autowired
	lateinit var jdbcTemplate: JdbcTemplate

	override fun run(vararg args: String?) {
		val result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.book", Int::class.java)
		println("Book count is: $result")
	}
}


fun main(args: Array<String>) {
	runApplication<BooklistApplication>(*args)
}
