package cn.it.phw.ms.interceptor;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.Resource;
import cn.it.phw.ms.pojo.ResourceExample;
import cn.it.phw.ms.service.ResourceService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 */
@Component
public class AccessResourceInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AccessResourceInterceptor.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private Gson gson;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        String resourceName = (uri.split("/"))[2];
        log.info(resourceName);
        String lastItem = resourceName.substring(resourceName.length() - 1);
        log.info(lastItem);
        if (lastItem.equals("s")) {
            resourceName = resourceName.substring(0, resourceName.length() - 1);
        }
        log.info(resourceName);

        ResourceExample example = new ResourceExample();
        ResourceExample.Criteria criteria = example.or();
        criteria.andNameEqualTo(resourceName);

        JsonResult jsonResult = resourceService.selectByExample(example);
        if (jsonResult.getStatus() == 200) {
            List<Resource> resources = (List<Resource>) jsonResult.getData().get(AppContext.KEY_DATA);
            Resource resource = resources.get(0);
            Integer res_id = resource.getId();
            httpServletRequest.setAttribute("resId", res_id);
            return true;
        } else {
            jsonResult.setStatus(500);
            jsonResult.setMessage("No Such Resource");
            exportJsonResult(httpServletResponse, jsonResult);
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * Export Json Result
     *
     * @param httpServletResponse
     * @param jsonResult
     * @throws IOException
     */
    private void exportJsonResult(HttpServletResponse httpServletResponse, JsonResult jsonResult) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(gson.toJson(jsonResult));
        out.flush();
        out.close();
    }
}
