package com.hubertasvin.library.mapper;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select(
            "SELECT b.id AS book_id, b.title AS title, p.id AS publisher_id, p.publisher_name AS publisher_name, " +
                    "a.id AS author_id, a.full_name AS author_full_name FROM books b " +
                    "LEFT JOIN publishers p ON b.publisher_id = p.id " +
                    "LEFT JOIN book_authors ba ON b.id = ba.book_id " +
                    "LEFT JOIN authors a ON ba.author_id = a.id"
    )
    @Results({
            @Result(column = "book_id", property = "id", id = true),
            @Result(column = "title", property = "title"),
            @Result(property = "publisher",
                    association = @Association(
                            results = {
                                    @Result(column = "publisher_id", property = "id", id = true),
                                    @Result(column = "publisher_name", property = "name")
                            }
                    )
            ),
            @Result(property = "authors",
                    collection = @Collection(
                            results = {
                                    @Result(column = "author_id", property = "id", id = true),
                                    @Result(column = "author_full_name", property = "fullName")
                            }
                    )
            )
    })
    List<Book> selectAllBooksWithDetails();


    @Insert("INSERT INTO books(title, publisher_id) VALUES (#{title}, #{publisher.id})")
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(Book book);

    @Insert("INSERT INTO book_authors(book_id, author_id) VALUES (#{bookId}, #{authorId})")
    void insertBookAuthor(@Param("bookId") Long bookId, @Param("authorId") Long authorId);
}
