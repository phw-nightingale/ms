package cn.it.phw.ms.interceptor;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.common.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Gson gson;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        verifyLogin(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    private JsonResult verifyLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        JsonResult jsonResult = new JsonResult();

        String authorization = httpServletRequest.getHeader(AppContext.AUTHORIZATION);
        if (StringUtils.isEmpty(authorization)) {
            authorization = httpServletRequest.getParameter("token");
        }

        if (StringUtils.isEmpty(authorization)) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("Error: Login First Please");
            exportJsonResult(httpServletResponse, jsonResult);
        } else {
            try {
                Claims claims = JwtUtils.parseJWT(authorization);
                String uid = claims.getId();

                if (redisTemplate.opsForHash().hasKey(AppContext.USER_CACHE, uid)) {
                    httpServletRequest.setAttribute(AppContext.KEY_ID, uid);
                    //logger.info(httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURL().toString());
                    jsonResult.setStatus(200);
                    jsonResult.setMessage("OK");
                } else {
                    jsonResult.setStatus(500);
                    jsonResult.setMessage("Error: Login First Please");
                }
            } catch (SignatureException | MalformedJwtException e) {
                jsonResult.setStatus(500);
                jsonResult.setMessage("Error: Login Error, Retry Please");
                return jsonResult;
            } catch (ExpiredJwtException e) {
                jsonResult.setStatus(500);
                jsonResult.setMessage("Error: Login Info Timed out");
                return jsonResult;
            }

        }

        return jsonResult;
    }

    /**
     * Export Json Result
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
