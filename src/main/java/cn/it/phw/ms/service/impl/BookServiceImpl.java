package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BookMapper;
import cn.it.phw.ms.pojo.Book;
import cn.it.phw.ms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl extends BaseServiceImpl implements BookService {

    @Autowired
    protected BookMapper bookMapper;

    @Override
    public JsonResult selectBookByPrimaryKey(Object id) {

        Book book = bookMapper.selectByPrimaryKey(id);
        if (book == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("没有查询到相应记录");
        } else {
            jsonResult.setStatus(200);
            jsonResult.setMessage("加载完成");
            data.put(AppContext.KEY_DATA, book);
            jsonResult.setData(data);
        }

        return jsonResult;
    }

}
