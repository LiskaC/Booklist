package com.booklist.repository

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class BookRepository(private val dsl: DSLContext) {

  fun getBooks(): MutableList<String> {
    val table = DSL.table("book")
    val nameField = DSL.field("name", String::class.java)
    return dsl.select(nameField)
      .from(table)
      .fetch(nameField)
  }
}