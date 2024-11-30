# Booklist

___

This is a Kotlin with Spring Boot application to store and retrieve books, with information about them. The build tool
is Gradle, database is Postgres and the database queries are made using jOOQ.

So far this is just a project to carefully review my knowledge of Kotlin. Starting off without PRs since I'm working
alone on the application, but as it becomes more complex I may start to make them anyway in order to keep a better
record of changes. Until then this will be done purely via commit messages.

## TODO

- Add swagger file for API documentation
- Configure environment variables to remove hardcoding from application.yml
- Add tests

I'm thinking about the documentation for this project. So far the code is pretty explicit and the test cases I think
additionally describe the expected behaviour pretty well. Writing code docs for very explicit properties / values feels
a bit extra. I'm interested to see to what extent I can keep the code clean, and how far this can stretch before code
comments feel necessary. Expecting to discover that I should have been adding them from the get-go.