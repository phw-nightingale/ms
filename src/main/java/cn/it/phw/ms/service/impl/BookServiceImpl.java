package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.dao.BookMapper;
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
    public BaseMapper getBaseMapper() {
        return bookMapper;
    }

}
