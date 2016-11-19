package biz.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * 反射的 Utils 函数集合 提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 * 
 */
public class ReflectionUtils {
	
	/**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型 如: public EmployeeDao extends
     * HiberanteBaseDao<Employee, String>
     * 
     * @param clazz
     * @param index
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class<?>) params[index];
    }
}
