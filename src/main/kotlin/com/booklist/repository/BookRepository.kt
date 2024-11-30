package com.booklist.repository

import com.booklist.data.AddBookResult
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.dao.DuplicateKeyException
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

  fun addBook(name: String): AddBookResult {
    if (name.isBlank()) {
      return AddBookResult(false, "Book name can't be blank")
    }

    return try {
      dsl.insertInto(table)
        .set(nameField, name)
        .execute()
      AddBookResult(true, "$name added successfully!")
    } catch (e: DuplicateKeyException) {
      e.printStackTrace()
      AddBookResult(false, "'$name' already exists in the database")
    } catch (e: Exception) {
      e.printStackTrace()
      AddBookResult(false, "Failed to add $name to the database. ${e.message}")
    }
  }
}