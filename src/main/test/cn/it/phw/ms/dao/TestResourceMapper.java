package cn.it.phw.ms.dao;

import cn.it.phw.ms.BaseJunitTest;
import cn.it.phw.ms.pojo.Resource;
import cn.it.phw.ms.pojo.ResourceExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestResourceMapper extends BaseJunitTest {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void testSelectByExample() {
        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.or();
        criteria.andNameEqualTo("book");
        List<Resource> resources = resourceMapper.selectByExample(example);
        System.out.println(resources.size());
    }

}
