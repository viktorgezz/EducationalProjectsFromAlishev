# Система управления библиотекой

Проект является продолжением предыдущей системы управления библиотекой, теперь использующей Hibernate и Spring Data JPA для всех взаимодействий с базой данных, исключая прямые SQL-запросы.

## Новые функции
- **Пагинация для книг**: Метод контроллера должен поддерживать пагинацию для обработки большого количества книг.
- **Сортировка книг по году**: Метод контроллера должен поддерживать сортировку книг по году публикации.
- **Страница поиска книг**: Поиск книг по начальным буквам названия, возвращая полное название книги и имя автора. Если книга взята, отображается имя читателя.
- **Проверка просроченных книг**: Автоматическое определение просроченных книг, если они были взяты более чем на 10 дней.

---

# Library Management System

The project is a continuation of the previous library management system, now using Hibernate and Spring Data JPA for all database interactions, excluding direct SQL queries.

## New Features
- **Book Pagination**: The controller method should support pagination to handle a large number of books.
- **Book Sorting by Year**: The controller method should support sorting books by their publication year.
- **Book Search Page**: Search for books by the initial letters of the title, returning the book's full title and author's name. If the book is borrowed, the borrower's name is displayed.
- **Overdue Book Check**: Automatic detection of overdue books if they have been borrowed for more than 10 days.
