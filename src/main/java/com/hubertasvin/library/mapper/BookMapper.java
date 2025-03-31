package com.hubertasvin.library.mapper;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("SELECT b.id, b.title FROM books b")
    @Results(
            id = "bookResultMap",
            value = {@Result(
                    column = "id",
                    property = "id",
                    id = true
            ), @Result(
                    column = "title",
                    property = "title"
            ), @Result(
                    property = "publisher",
                    column = "id",
                    one = @One(
                            select = "com.hubertasvin.library.mapper.BookMapper" +
                                    ".selectPublisherByBookId"
                    )
            ), @Result(
                    property = "authors",
                    column = "id",
                    many = @Many(
                            select = "com.hubertasvin.library.mapper.BookMapper" +
                                    ".selectAuthorsByBookId"
                    )
            )}
    )
    List<Book> selectAllBooksWithDetails();

    @Insert("INSERT INTO books(title, publisher_id) VALUES (#{title}, #{publisher.id})")
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(Book book);

    @Insert("INSERT INTO book_authors(book_id, author_id) VALUES (#{bookId}, #{authorId})")
    void insertBookAuthor(@Param("bookId") Long bookId, @Param("authorId") Long authorId);

    @Select(
            "SELECT p.id, p.publisher_name AS name FROM publishers p " + "JOIN books b ON b" +
                    ".publisher_id = p.id WHERE b.id = #{bookId}"
    )
    Publisher selectPublisherByBookId(@Param("bookId") Long bookId);

    @Select(
            "SELECT a.id, a.full_name AS fullName FROM authors a " + "JOIN book_authors ba ON a" +
                    ".id = ba.author_id WHERE ba.book_id = #{bookId}"
    )
    List<Author> selectAuthorsByBookId(
            @Param("bookId") Long bookId
    );

}
