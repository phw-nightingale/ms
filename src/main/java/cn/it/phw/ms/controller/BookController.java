package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.BookExample;
import cn.it.phw.ms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    protected BookExample bookExample = new BookExample();

    @ResponseBody
    @GetMapping("/book/{id}")
    public JsonResult selectBookByPrimaryKey(@PathVariable Integer id) {

        return bookService.selectBookByPrimaryKey(id);
    }

    @ResponseBody
    @GetMapping("/book")
    public JsonResult selectBookByExample(@RequestParam Integer id) {
        BookExample.Criteria criteria = bookExample.or();
        criteria.andIdEqualTo(id);
        return bookService.selectByExample(bookExample);
    }

}
