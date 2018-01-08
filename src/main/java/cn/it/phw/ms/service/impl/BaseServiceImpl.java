package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    protected JsonResult jsonResult;
    protected Map<String, Object> data;

    /*private Class<?> clazz;

    private BaseExample example;*/

    public BaseServiceImpl() {

        jsonResult = new JsonResult();
        data = new HashMap<>();

        /*Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            clazz = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            clazz = Object.class;
            log.warn("Get Generic Super Class Failed, Use Default Object Class");
            throw new IllegalArgumentException("Get Generic Super Class Failed");
        }
        String exampleName = clazz.getSimpleName() + "Example";
        log.info(clazz.getName());
        try {
            Class<?> exampleClass = Class.forName("cn.it.phw.ms.pojo." + exampleName);
            example = (BaseExample) exampleClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }*/

    }

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public JsonResult selectByPrimaryKey(Object id) {
        T item = baseMapper.selectByPrimaryKey(id);
        if (item == null) {
            jsonResult.setMessage("无对应记录");
            jsonResult.setStatus(500);
        } else {
            jsonResult.setStatus(200);
            jsonResult.setMessage("查询成功");
            data.put(AppContext.KEY_DATA, item);
            jsonResult.setData(data);
        }
        return jsonResult;
    }

    @Override
    public JsonResult selectByExample(BaseExample example) {

        List<T> items = baseMapper.selectByExample(example);

        if (items.size() == 0) {
            jsonResult.setMessage("无对应记录");
            jsonResult.setStatus(500);
        } else {
            jsonResult.setStatus(200);
            jsonResult.setMessage("加载完成");
            data.put(AppContext.KEY_DATA, items);
            jsonResult.setData(data);
        }
        return jsonResult;
    }

    @Override
    public JsonResult updateByPrimaryKey(Object id) {

        return null;
    }

    @Override
    public JsonResult updateByExample(BaseExample example) {
        return null;
    }

    @Override
    public JsonResult deleteByPrimaryKey(Object id) {
        return null;
    }

    @Override
    public JsonResult deleteByExample(BaseExample example) {
        return null;
    }

    @Override
    public JsonResult insert(T item) {
        return null;
    }

}
