package cn.it.phw.ms.common;

import cn.it.phw.ms.dao.BaseMapper;
import cn.it.phw.ms.pojo.BaseEntity;
import cn.it.phw.ms.pojo.BaseExample;
import cn.it.phw.ms.pojo.User;
import cn.it.phw.ms.pojo.UserExample;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.impl.BaseServiceImpl;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;

/**
 * 反射工具类
 *
 * @author phw
 */
public class ReflectionHelper<T> {

    private static final Logger logger = LogManager.getLogger(ReflectionHelper.class);

    /**
     * 根据传入的属性名来获取对象的属性
     *
     * @param obj       要操作的对象
     * @param fieldName 传入的属性名
     * @return 对象的属性
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) throws NoSuchFieldException {

        for (Class<?> superClass = obj.getClass();
                superClass != Object.class; superClass = superClass.getSuperclass()) {
            return superClass.getDeclaredField(fieldName);
        }
        return null;
    }

    /**
     * 根据传入的属性名来获取对象的属性值
     * @param obj 要操作的对象
     * @param fieldName 传入的属性名
     * @return 属性值
     * @throws SecurityException
     * @throws NoSuchFieldException 传入的属性未找到
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * 根据传入的属性来设置对象的属性值
     * @param obj 要操作的对象
     * @param fieldName 要操作的属性
     * @param value 要设置的属性值
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

        Field field = obj.getClass().getDeclaredField(fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

    /**
     * 获得定义类时声明的基类(父类)的泛型参数的类型
     * @param clazz 子类class
     * @param index 基类层级(继承了多少次)
     * @return 父类的类型
     */
    public static Class getSuperClassGenericType(final Class clazz, final int index){
        Type genericType = clazz.getGenericSuperclass();
        if(!(genericType instanceof ParameterizedType)){
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();

        if(index>=params.length || index < 0){
            logger.warn("Index:"+index+",size of "+clazz.getSimpleName()+"'s Parameterize Type:"+params.length);
            return Object.class;
        }
        if(!(params[index] instanceof Class)){
            return Object.class;
        }
        return (Class)params[index];
    }

    public static Class<?> getClassGenericType(final Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            return Object.class;
        }
    }

    public BaseExample getExampleByItem(Class<?> clazz, T record, BaseExampleOptions opt)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
                                            InstantiationException, InvocationTargetException {

        Class<?> cls = getClassGenericType(clazz);
        String exampleName = cls.getName() + "Example";
        String criteriaName = exampleName + ".Criteria";
        System.out.println(criteriaName);
        Class<?> exampleClass = Class.forName(exampleName);
        BaseExample example = (BaseExample) exampleClass.newInstance();
        Method method = exampleClass.getMethod("or");
        Object criteria = method.invoke(example);
        Class<?> criteriaClass = criteria.getClass();
        Field[] fieldsOfRecord = record.getClass().getDeclaredFields();
        Method[] methodsOfCriteria = criteriaClass.getMethods();

        for (Field field: fieldsOfRecord) {
            field.setAccessible(true);
            Object param = field.get(record);
            if (param != null) {
                String fieldName = (field.getName().substring(0, 1).toUpperCase()) + (field.getName().substring(1));
                StringBuilder methodName = new StringBuilder();
                methodName.append("and")
                        .append(fieldName)
                        .append(opt);
                System.out.println(methodName.toString());
                Method methodOfCriteria = criteria.getClass().getDeclaredMethod(methodName.toString());
                System.out.println(methodOfCriteria.getName());
            }
        }

        return example;
    }


    /**
     * Test
     *
     * @param args args
     */
    public static void main(String[] args) {

        BaseService<User> baseService = new BaseServiceImpl<User>() {
            @Override
            public BaseMapper<User> getBaseMapper() {
                return null;
            }
        };

        ReflectionHelper<User> helper = new ReflectionHelper<>();

        User user = new User();
        user.setId(1);
        user.setUsername("admin1");
        try {
            BaseExample example = helper.getExampleByItem(baseService.getClass(), user, BaseExampleOptions.EqualTo);
            System.out.println(example);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
