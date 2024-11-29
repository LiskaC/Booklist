# Booklist
___

This is a Kotlin with Spring Boot application to store and retrieve books, with information about them. The built tool is gradle, database is Postgres and the database queries are made using jOOQ.

So far this is just a project to carefully review my knowledge of Kotlin. Starting off without PRs since I'm working alone on the application, but as it becomes more complex I may start to make them anyway in order to keep a better record of changes. Until then this will be done purely via commit messages.

## TODO

- Have POST endpoint for book insertion reject if the book is already available
- Add swagger file for API documentation
- Configure environment variables to remove hardcoding from application.yml
- Add tests