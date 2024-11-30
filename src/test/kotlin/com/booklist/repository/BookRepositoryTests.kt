package com.booklist.repository

import io.mockk.every
import io.mockk.mockk
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

@SpringBootTest
@ActiveProfiles("test")
class BookRepositoryTests {

  @Autowired
  private lateinit var bookRepository: BookRepository

  @Autowired
  private lateinit var dsl: DSLContext

  @BeforeEach
  fun setup() {
    dsl.execute("CREATE TABLE IF NOT EXISTS book (id SERIAL PRIMARY KEY, name VARCHAR(255))")
    dsl.execute("DELETE FROM book") // Clear the table before each test
  }

  @Test
  fun `should retrieve all books`() {
    dsl.execute("INSERT INTO book (name) VALUES ('Book 1'), ('Book 2')")
    val books = bookRepository.getAllBooks()
    assertEquals(2, books.size)
    assertEquals(listOf("Book 1", "Book 2"), books)
  }

  @Test
  fun `should return empty array if no books are found`() {
    val books = bookRepository.getAllBooks()
    assertEquals(0, books.size)
    assertEquals(listOf(), books)
  }

  @Test
  fun `should retrieve a book by it's name`() {
    dsl.execute("INSERT INTO book (name) VALUES ('Book 1')")
    val book = bookRepository.getBookByName("Book 1")
    assertEquals("Book 1", book)
  }

  @Test
  fun `should return null if no book in the DB corresponds to the name`() {
    dsl.execute("INSERT INTO book (name) VALUES ('Book 1')")
    val book = bookRepository.getBookByName("Book 2")
    assertNull(book)
  }

  @Test
  fun `should insert a single book`() {
    bookRepository.addBook("The Penguin")

    val book = dsl.fetchOne("SELECT name FROM book WHERE name = ?", "The Penguin")
    val title = book?.getValue("name", String::class.java)
    assertNotNull(book)
    assertEquals("The Penguin", title)
  }

  @Test
  fun `should not insert a record if the book name is a blank string`() {
    bookRepository.addBook("")
    bookRepository.addBook("  ")

    val tableSize = dsl.fetchCount(DSL.table("book"))
    assertEquals(0, tableSize)
  }

  @Test
  fun `should returns 'false' if adding a book fails at the database connection point`() {
    val mockDsl = mockk<DSLContext>()
    val bookRepository = BookRepository(mockDsl)
    val bookTable = DSL.table("book")
    every { mockDsl.insertInto(bookTable) } throws RuntimeException("Database connection failed")

    val result = bookRepository.addBook("Sad Book")
    assertFalse(result)
  }
}