package cn.it.phw.ms.service.impl;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.common.ReflectionHelper;
import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    JsonResult jsonResult;
    Map<String, Object> data;

    public BaseServiceImpl() {
        jsonResult = new JsonResult();
        data = new HashMap<>();
    }

    /**
     * 获取对应的数据持久层
     * 由业务类实现
     *
     * @return
     */
    public abstract BaseMapper<T> getBaseMapper();

    @Override
    public JsonResult selectByPrimaryKey(Object id) {
        if (id == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("参数错误");
        }

        if (!(id instanceof String)) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("参数类型错误");
        } else {
            id = Integer.valueOf((String) id);
        }

        T item = getBaseMapper().selectByPrimaryKey(id);
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

        List<T> items = getBaseMapper().selectByExample(example);

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
    public JsonResult updateByPrimaryKey(T item) {
        if (item == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("错误：参数为空");
        } else {
            //更新属性
            try {
                Class<?> cls = item.getClass();

                Field id_field = cls.getDeclaredField("id");

                if (id_field == null) {
                    jsonResult.setStatus(500);
                    jsonResult.setMessage("错误：没有找到id字段");
                } else {
                    //设置private属性的可访问性
                    id_field.setAccessible(true);
                    Object id = id_field.get(item);
                    if (id == null) {
                        jsonResult.setStatus(500);
                        jsonResult.setMessage("错误：id值为空");
                    }
                    T oldItem = getBaseMapper().selectByPrimaryKey(id_field.get(item));

                    Field[] fields = cls.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.get(item) != null) {
                            field.set(oldItem, field.get(item));
                        }
                    }

                    getBaseMapper().updateByPrimaryKey(oldItem);

                    jsonResult.setStatus(200);
                    jsonResult.setMessage("更新成功");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                jsonResult.setMessage("错误：" + e.getMessage());
                jsonResult.setStatus(500);
                return jsonResult;
            }
        }
        return jsonResult;
    }

    @Override
    public JsonResult updateByExample(BaseExample example) {
        if (example == null) {
            jsonResult.setMessage("错误：参数为空");
            jsonResult.setStatus(500);
        } else {
            getBaseMapper().updateByExample(example);
            jsonResult.setStatus(200);
            jsonResult.setMessage("更新成功");
        }
        return jsonResult;
    }

    @Override
    public JsonResult deleteByPrimaryKey(Object id) {
        if (id == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("错误：参数为空");
        } else {
            getBaseMapper().deleteByPrimaryKey(id);
            jsonResult.setStatus(200);
            jsonResult.setMessage("删除成功");
        }
        return jsonResult;
    }

    @Override
    public JsonResult deleteByExample(BaseExample example) {
        if (example == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("错误：参数为空");
        } else {
            getBaseMapper().deleteByExample(example);
            jsonResult.setStatus(200);
            jsonResult.setMessage("删除成功");
        }
        return jsonResult;
    }

    @Override
    public JsonResult insert(T item) {
        if (item == null) {
            jsonResult.setStatus(500);
            jsonResult.setMessage("错误：参数为空");
        } else {
            getBaseMapper().insert(item);
            jsonResult.setStatus(200);
            jsonResult.setMessage("添加成功");
        }
        return jsonResult;
    }

}
