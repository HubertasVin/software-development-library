package com.hubertasvin.library.mapper;

import com.hubertasvin.library.domain.Author;
import com.hubertasvin.library.domain.Book;
import com.hubertasvin.library.domain.Publisher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

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
