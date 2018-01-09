package cn.it.phw.ms.service;

import cn.it.phw.ms.BaseJunitTest;
import cn.it.phw.ms.pojo.Book;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBookService extends BaseJunitTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private Gson gson;

    @Test
    public void testUpdateByPrimaryKey() {
        Book book = new Book();
        book.setId(1);
        book.setBookName("龙族 III");
        jsonResult = bookService.updateByPrimaryKey(book);
        System.out.println(gson.toJson(jsonResult));
    }

}
