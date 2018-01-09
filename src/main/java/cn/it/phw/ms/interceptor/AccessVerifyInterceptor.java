package cn.it.phw.ms.interceptor;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.Authority;
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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AccessVerifyInterceptor implements HandlerInterceptor {

    @Autowired
    private Gson gson;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String uid = "";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> clazz = handlerMethod.getBeanType(),
                superClazz = handlerMethod.getBeanType().getSuperclass();

        Method method = handlerMethod.getMethod();
        if (clazz != null && method != null) {
            boolean isClassAnnotation = clazz.isAnnotationPresent(Authority.class);
            boolean isMethodAnnotation = method.isAnnotationPresent(Authority.class);
            Authority authority = null;
            if (isClassAnnotation) {
                authority = clazz.getAnnotation(Authority.class);
            } else if (isMethodAnnotation) {
                authority = method.getAnnotation(Authority.class);
            } else {
                authority = superClazz.getAnnotation(Authority.class);
            }
            switch (authority.value()) {

                case Validate: {
                    JsonResult loginResult = verifyLogin(httpServletRequest, httpServletResponse);
                    if (loginResult.getStatus() == 500) {
                        exportJsonResult(httpServletResponse, loginResult);
                        return false;
                    }
                    JsonResult actionResult = verifyAction();
                    if (true) {
                        exportJsonResult(httpServletResponse, actionResult);
                        return false;
                    }
                    break;
                }
                case NoAuthority: {
                    JsonResult loginResult = verifyLogin(httpServletRequest, httpServletResponse);
                    if (loginResult.getStatus() == 500) {
                        exportJsonResult(httpServletResponse, loginResult);
                        return false;
                    }
                    break;
                }
                case NoValidate: {

                    break;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

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
                uid = claims.getId();

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
     *
     * @return
     */
    private JsonResult verifyAction() {

        return null;
    }

}
