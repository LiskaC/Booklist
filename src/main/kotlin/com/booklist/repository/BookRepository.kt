package com.booklist.repository

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class BookRepository(private val dsl: DSLContext) {

  val table = DSL.table("book")
  val nameField = DSL.field("name", String::class.java)

  fun getAllBooks(): MutableList<String> {
    return dsl.select(nameField)
      .from(table)
      .fetch(nameField)
  }

  fun getBookByName(name: String): String? {
    return dsl.select(nameField)
      .from(table)
      .where(nameField.eq(name))
      .fetchOne(nameField)
  }
}