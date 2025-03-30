package com.hubertasvin.library.mapper;

import com.hubertasvin.library.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Select("SELECT * FROM books WHERE id = #{id}")
    Book findById(Long id);

    @Select("SELECT * FROM books")
    List<Book> findAll();

    @Insert("INSERT INTO books(title, publisher_id) VALUES (#{title}, #{publisher.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Book book);

    @Update("UPDATE books SET title = #{title}, publisher_id = #{publisher.id} WHERE id = #{id}")
    void update(Book book);

    @Delete("DELETE FROM books WHERE id = #{id}")
    void delete(Long id);
}
