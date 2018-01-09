package cn.it.phw.ms;

import cn.it.phw.ms.common.JsonResult;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base Spring Junit Test Class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/spring/applicationContext.xml")
public class BaseJunitTest {

    protected JsonResult jsonResult;

    public BaseJunitTest() {
        jsonResult = new JsonResult();
    }

}
