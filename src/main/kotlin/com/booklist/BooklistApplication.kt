package com.booklist

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooklistApplication: CommandLineRunner {

	@Autowired
	lateinit var dsl: DSLContext

	override fun run(vararg args: String?) {
		val table = DSL.table("book")
		var result = dsl.fetchCount(table)
		println("Book count is: $result")
	}
}


fun main(args: Array<String>) {
	runApplication<BooklistApplication>(*args)
}
