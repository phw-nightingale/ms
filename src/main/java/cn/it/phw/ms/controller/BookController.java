package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Book;
import cn.it.phw.ms.pojo.BookExample;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @GetMapping("/book/{id}")
    public JsonResult findBookById(@PathVariable Integer id) {
        return bookService.selectByPrimaryKey(id);
    }

    @ResponseBody
    @GetMapping("/books")
    public JsonResult findAllBooks() {
        return bookService.selectByExample(null);
    }

    @ResponseBody
    @PostMapping("/book")
    public JsonResult updateBookById(Book book) {
        return bookService.updateByPrimaryKey(book);
    }

    @ResponseBody
    @DeleteMapping("/book/{id}")
    public JsonResult deleteBookById(@PathVariable Integer id) {
        return bookService.deleteByPrimaryKey(id);
    }

}
