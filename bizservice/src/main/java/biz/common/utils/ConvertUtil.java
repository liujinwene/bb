package biz.common.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * 对象转换工具
 * @author xs
 *
 */
public class ConvertUtil {

	public static <T> T convert(Object obj,Class<T> c) {
		try {
			if(obj==null)
				return null;
			T t = c.newInstance();
			Field[] fields = c.getDeclaredFields();
			int length = fields.length;

			for (int i = 0; i < length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				Field objField = getDeclaredField(obj, fieldName);
				if(objField != null && objField.getType().getName().equals(field.getType().getName())){
					Object fieldValue = getFieldValue(obj, fieldName);
					setFieldValueByFieldType(t, fieldName, fieldValue);
				}
			}
			return t;
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> void convertOnlyNotNullField(Object source,Object target) throws Exception {
		if(source==null)
			return;
		if(target==null)
			return;

		Field[] sourceFields = source.getClass().getDeclaredFields();
		int length = sourceFields.length;

		for (int i = 0; i < length; i++) {
			Field sourceField = sourceFields[i];
			String fieldName = sourceField.getName();
			Field targetField = getDeclaredField(target, fieldName);
			if(targetField!=null&&targetField.getType().getName().equals(sourceField.getType().getName())){
				Object sourceFieldValue = getFieldValue(source, fieldName);
				if(sourceFieldValue!=null)
					setFieldValueByFieldType(target, fieldName, sourceFieldValue);
			}
		}
	}

	public static Map<String,String> convertObjToMap(Object obj,Class<?> c) throws Exception {
		if(obj==null)
			return null;

		Map<String,String> params = new HashMap<String, String>();

		Field[] fields = c.getDeclaredFields();
		int length = fields.length;

		for (int i = 0; i < length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			Object fieldValue = getFieldValue(obj, fieldName);
			if(fieldValue != null) {
				params.put(fieldName, fieldValue.toString());
			}
		}
		return params;
	}

	public static <T> T convertMapToObj(Map<String,Object> map, Class<T> c) throws Exception {
		if(map == null || map.isEmpty())
			return c.newInstance();

		T t = c.newInstance();

		Field[] fields = c.getDeclaredFields();
		int length = fields.length;

		for (int i = 0; i < length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			Object mapValue = map.get(fieldName);
			if(mapValue != null && mapValue.getClass().getName().equals(field.getType().getName())) {
				setFieldValueByFieldType(t, fieldName, mapValue);
			}
		}
		return t;
	}

	public static <T> List<T> convertForSampleTypeList(List<?> objs,Class<T> c) throws Exception {
		if(objs==null||objs.isEmpty())
			return null;

		List<T> list = new ArrayList<T>();

		Field[] fields = c.getDeclaredFields();
		int length = fields.length;

		for(Object obj:objs){
			T t = c.newInstance();
			for (int i = 0; i < length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				Field objField = getDeclaredField(obj, fieldName);
				if(objField!=null&&objField.getType().getName().equals(field.getType().getName())){
					Object fieldValue = getFieldValue(obj, fieldName);
					setFieldValueByFieldType(t, fieldName, fieldValue);
				}
			}
			list.add(t);
		}
		return list;
	}

	public static <T> T convert2(Object obj,Class<T> c) throws Exception {
		if(obj==null)
			return null;
		T t = c.newInstance();
		Field[] fields = c.getDeclaredFields();
		int length = fields.length;

		for (int i = 0; i < length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			Object fieldValue = getFieldValue(obj, fieldName);
			setFieldValueByFieldType(t, fieldName, fieldValue);
		}
		return t;
	}

	/**
	 * 将VO对象转成PO(包含null值)
	 * @param vo
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static Object convertVoToPo(Object vo,Object po) throws Exception {
		if (null == vo)
			return vo;

		Class<?> classType = vo.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(vo, fieldName);
			setFieldValueByFieldType(po, fieldName, fieldValue);
		}
		return po;
	}
	/**
	 * 将VO对象转成PO(包含null值)
	 * @param po
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static Object convertPoToVo(Object po,Object vo) throws Exception {
		if (null == po)
			return po;

		Class<?> classType = po.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(po, fieldName);
			setFieldValueByFieldType(vo, fieldName, fieldValue);
		}
		return vo;
	}
	/**
	 * 将VO对象转成PO（不包含null值）
	 * @param vo
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public static Object convertVoToPoNoNull(Object vo,Object po) throws Exception {
		if (null == vo)
			return vo;

		Class<?> classType = vo.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Object fieldValue = getFieldValue(vo, fieldName);
			if(fieldValue!=null){
				setFieldValueByFieldType(po, fieldName, fieldValue);
			}
		}
		return po;
	}
	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValueByFieldType(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		//		if (field == null) {
		//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		//		}
		if(null!=field){
			setFieldValue(object, fieldName, value);
		}
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		//		if (field == null) {
		//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		//		}

		if(null!=field){
			makeAccessible(field);
			try {
				field.set(object, value);
			} catch (IllegalAccessException e) {

			}
		}
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {

		}
		return result;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Field getDeclaredField(final Object object, final String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {//NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强行设置Field可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	public static List<String> convertLongToString(List<Long> modelIds) {
		if(modelIds == null ||modelIds.isEmpty()) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		for(Long r : modelIds) {
			list.add(r.toString());
		}
		return list;
	}
}
