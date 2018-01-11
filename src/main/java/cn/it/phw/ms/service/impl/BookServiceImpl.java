package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.BookMapper;
import cn.it.phw.ms.pojo.Book;
import cn.it.phw.ms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {

    @Autowired
    protected BookMapper bookMapper;

    @Override
    public BaseMapper<Book> getBaseMapper() {
        return bookMapper;
    }

}
