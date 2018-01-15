package cn.it.phw.ms.dao;

import cn.it.phw.ms.BaseJunitTest;
import cn.it.phw.ms.pojo.Book;
import cn.it.phw.ms.pojo.BookExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestBookMapper extends BaseJunitTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testSelectByPrimaryKey() {
        Book book = bookMapper.selectByPrimaryKey(1);
        System.out.println(book.getBookName());
    }

    @Test
    public void testSelectByExample() {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.or();
        criteria.andIdEqualTo(1);
        List<Book> books = bookMapper.selectByExample(example);
        System.out.println(books.size());
        System.out.println(books.get(0).getBookName());
    }



}
