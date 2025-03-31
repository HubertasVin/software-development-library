package com.hubertasvin.library.mapper;

import com.hubertasvin.library.domain.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface AuthorMapper {
    @Select("SELECT * FROM authors WHERE full_name = #{fullName}")
    Author findByFullName(String fullName);

    @Insert("INSERT INTO authors(full_name) VALUES (#{fullName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Author author);
}
