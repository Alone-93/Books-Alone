package com.alone.booksalone.dao;

import com.alone.booksalone.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookDAO {
    @Insert({"insert into book (name,author,price) values(#{book.name},#{book.author},#{book.price})"})
    int addBook(Book book);
    @Delete({"delete from book where id=#{id}"})
    int deleteBook(@Param("id") int id);
    @Update({"update book set status=#{status} where id=#{id}"})
    int updateBookStatus(@Param("id") int id,@Param("status") int status);
    Book selectById(int id);
    @Select({"select name,author,price from book"})
    List<Book> selectAll();
}
