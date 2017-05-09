package util;

import org.json.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/3.
 */
public class BeanMapJson {
	public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null) return null;
		Object obj = beanClass.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				setter.invoke(obj, map.get(property.getName()));
			}
		}
		return obj;
	}
	
	public static Map<String, Object> beanToMap(Object obj) throws Exception {
		if (obj == null) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}
		return map;
	}
	
	public static Map jsonToMap(JSONObject jsonObject) {
		Map map = new HashMap();
		Iterator<String> iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			map.put(key, jsonObject.opt(key));
		}
		return map;
	}
}
