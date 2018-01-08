package cn.it.phw.ms.dao;

import cn.it.phw.ms.BaseJunitTest;
import cn.it.phw.ms.pojo.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBookMapper extends BaseJunitTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testSelectByPrimaryKey() {
        Book book = bookMapper.selectByPrimaryKey(1);
        System.out.println(book.getBookName());
    }

}
