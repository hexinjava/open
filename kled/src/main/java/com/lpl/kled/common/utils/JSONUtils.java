package com.lpl.kled.common.utils;

/**
 * Copyright 2010 .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


/**
 * 包含操作 {@code JSON} 数据的常用方法的工具类。
 * <p />
 * 该工具类使用的 {@code JSON} 转换引擎是
 * <a href="http://code.google.com/p/google-gson/" mce_href=
 * "http://code.google.com/p/google-gson/" target="_blank"> {@code
 * Google Gson}</a>。 下面是工具类的使用案例：
 * 
 * <pre>
 * public class User {
 *     &#64;SerializedName("pwd")
 *     private String password;
 *     &#64;Expose
 *     &#64;SerializedName("uname")
 *     private String username;
 *     &#64;Expose
 *     &#64;Since(1.1)
 *     private String gender;
 *     &#64;Expose
 *     &#64;Since(1.0)
 *     private String sex;
 *     
 *     public User() {}
 *     public User(String username, String password, String gender) {
 *         // user constructor code... ... ...
 *     }
 *     
 *     public String getUsername()
 *     ... ... ...
 * }
 * List<User> userList = new LinkedList<User>();
 * User jack = new User("Jack", "123456", "Male");
 * User marry = new User("Marry", "888888", "Female");
 * userList.add(jack);
 * userList.add(marry);
 * Type targetType = new TypeToken<List<User>>(){}.getType();
 * String sUserList1 = JSONUtils.toJson(userList, targetType);
 * sUserList1 ----> [{"uname":"jack","gender":"Male","sex":"Male"},{"uname":"marry","gender":"Female","sex":"Female"}]
 * String sUserList2 = JSONUtils.toJson(userList, targetType, false);
 * sUserList2 ----> [{"uname":"jack","pwd":"123456","gender":"Male","sex":"Male"},{"uname":"marry","pwd":"888888","gender":"Female","sex":"Female"}]
 * String sUserList3 = JSONUtils.toJson(userList, targetType, 1.0d, true);
 * sUserList3 ----> [{"uname":"jack","sex":"Male"},{"uname":"marry","sex":"Female"}]
 * </pre>
 * 
 * @author 
 * @since ay-commons-lang 1.0
 * @version 1.1.0
 */
@SuppressWarnings("unchecked")
public class JSONUtils {

	/** 空的 {@code JSON} 数据 - <code>"{}"</code>。 */
	public static final String EMPTY_JSON = "{}";

	/** 空的 {@code JSON} 数组(集合)数据 - {@code "[]"}。 */
	public static final String EMPTY_JSON_ARRAY = "[]";

	/** 默认的 {@code JSON} 日期/时间字段的格式化模式。 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** {@code Google Gson} 的 <code>@Since</code> 注解常用的版本号常量 - {@code 1.0}。 */
	public static final double SINCE_VERSION_10 = 1.0d;

	/** {@code Google Gson} 的 <code>@Since</code> 注解常用的版本号常量 - {@code 1.1}。 */
	public static final double SINCE_VERSION_11 = 1.1d;

	/** {@code Google Gson} 的 <code>@Since</code> 注解常用的版本号常量 - {@code 1.2}。 */
	public static final double SINCE_VERSION_12 = 1.2d;

	/** {@code Google Gson} 的 <code>@Until</code> 注解常用的版本号常量 - {@code 1.0}。 */
	public static final double UNTIL_VERSION_10 = SINCE_VERSION_10;

	/** {@code Google Gson} 的 <code>@Until</code> 注解常用的版本号常量 - {@code 1.1}。 */
	public static final double UNTIL_VERSION_11 = SINCE_VERSION_11;

	/** {@code Google Gson} 的 <code>@Until</code> 注解常用的版本号常量 - {@code 1.2}。 */
	public static final double UNTIL_VERSION_12 = SINCE_VERSION_12;

	/**
	 * <p>
	 * <code>JSONUtils</code> instances should NOT be constructed in standard
	 * programming. Instead, the class should be used as
	 * <code>JSONUtils.fromJson("foo");</code>.
	 * </p>
	 * <p>
	 * This constructor is public to permit tools that require a JavaBean
	 * instance to operate.
	 * </p>
	 */
	public JSONUtils() {
		super();
	}

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * <strong>该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 <code>"{}"</code>； 集合或数组对象返回
	 * <code>"[]"</code> </strong>
	 * 
	 * @param target
	 *            目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param isSerializeNulls
	 *            是否序列化 {@code null} 值字段。
	 * @param version
	 *            字段的版本号注解。
	 * @param datePattern
	 *            日期字段的格式化模式。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType, boolean isSerializeNulls, Double version,
			String datePattern, boolean excludesFieldsWithoutExpose) {
		if (target == null)
			return EMPTY_JSON;
		GsonBuilder builder = new GsonBuilder();
		if (isSerializeNulls)
			builder.serializeNulls();
		if (version != null)
			builder.setVersion(version.doubleValue());
		if (StringUtil.isNull(datePattern))
			datePattern = DEFAULT_DATE_PATTERN;
		builder.setDateFormat(datePattern);
		if (excludesFieldsWithoutExpose)
			builder.excludeFieldsWithoutExposeAnnotation();
		return toJson(target, targetType, builder);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @throws Exception
	 * @since 1.0
	 */
	public static String toJson(Object target) throws Exception {
		return toJson(target, null, false, null, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @throws Exception
	 * @since 1.0
	 */
	public static String toJsonWithNull(Object target) throws Exception {
		return toJson(target, null, true, null, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @throws Exception
	 * @since 1.0
	 */
	public static String toJsonWithNull(Object target, String datePattern) throws Exception {
		return toJson(target, null, true, null, datePattern, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param datePattern
	 *            日期字段的格式化模式。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, String datePattern) {
		return toJson(target, null, false, null, datePattern, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Double version) {
		return toJson(target, null, false, version, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, null, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法只用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Double version, boolean excludesFieldsWithoutExpose) {
		return toJson(target, null, false, version, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType) {
		return toJson(target, targetType, false, null, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法只会转换标有 {@literal @Expose} 注解的字段；</li>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType, Double version) {
		return toJson(target, targetType, false, version, null, true);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法会转换所有未标注或已标注 {@literal @Since} 的字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType, boolean excludesFieldsWithoutExpose) {
		return toJson(target, targetType, false, null, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的目标对象转换成 {@code JSON} 格式的字符串。<strong>此方法通常用来转换使用泛型的对象。</strong>
	 * <ul>
	 * <li>该方法不会转换 {@code null} 值字段；</li>
	 * <li>该方法转换时使用默认的 日期/时间 格式化模式 - {@code yyyy-MM-dd HH:mm:ss}；</li>
	 * </ul>
	 * 
	 * @param target
	 *            要转换成 {@code JSON} 的目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param version
	 *            字段的版本号注解({@literal @Since})。
	 * @param excludesFieldsWithoutExpose
	 *            是否排除未标注 {@literal @Expose} 注解的字段。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.0
	 */
	public static String toJson(Object target, Type targetType, Double version, boolean excludesFieldsWithoutExpose) {
		return toJson(target, targetType, false, version, null, excludesFieldsWithoutExpose);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param token
	 *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
	 * @param datePattern
	 *            日期格式模式。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, TypeToken<T> token, String datePattern) {
		if (StringUtil.isNull(json)) {
			return null;
		}
		GsonBuilder builder = new GsonBuilder();
		if (StringUtil.isNull(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		Gson gson = builder.create();
		try {
			return (T) gson.fromJson(json, token.getType());
		} catch (Exception ex) {
			System.out.println(json + " 无法转换为 " + token.getRawType().getName() + " 对象!");
			return null;
		}
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param token
	 *            {@code com.google.gson.reflect.TypeToken} 的类型指示类对象。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, TypeToken<T> token) {
		return fromJson(json, token, null);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clazz
	 *            要转换的目标类。
	 * @param datePattern
	 *            日期格式模式。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
		if (StringUtil.isNull(json)) {
			return null;
		}
		GsonBuilder builder = new GsonBuilder();
		if (StringUtil.isNull(datePattern)) {
			datePattern = DEFAULT_DATE_PATTERN;
		}
		Gson gson = builder.setDateFormat(datePattern).create();
		try {
			return gson.fromJson(json, clazz);
		} catch (Exception ex) {
			System.out.println(json + " 无法转换为 " + clazz.getName() + " 对象!");
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 * 
	 * @param <T>
	 *            要转换的目标类型。
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clazz
	 *            要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @since 1.0
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {	    
		return fromJson(json, clazz, null);
	}
	
	/**
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
     * 对象。</strong>
     * 
     * @param <T>
     *            要转换的目标类型。
     * @param json
     *            给定的 {@code JSON} 字符串。
     * @param clazz
     *            要转换的目标类。
     * @param datePattern
     *            日期格式模式。
     *            
     * @param exclude
     *            排除的属性数组
     *               
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     * @since 1.0
     */
    public static <T> T fromJsonWithExclude(String json, Class<T> clazz, String datePattern,final String[] exclude) {
        if (StringUtil.isNull(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (StringUtil.isNull(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.setDateFormat(datePattern).setExclusionStrategies(new ExclusionStrategy() {            
            /** 
             * 设置要过滤的属性 
             */ 
            @Override
            public boolean shouldSkipField(FieldAttributes arg0) {
                for (String key : exclude) {  
                    if (key.equals(arg0.getName())) {  
                        return true;  
                    }  
                }  
                //这里，如果返回true就表示此属性要过滤，否则就输出
                return false;
            }           
            /** 
             * 设置要过滤的类 
             */
            @Override
            public boolean shouldSkipClass(Class<?> arg0) {
                //这里，如果返回true就表示此类要过滤，否则就输出
                return false;
            }
        }).create();
        
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception ex) {
            System.out.println(json + " 无法转换为 " + clazz.getName() + " 对象!");
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
     * 对象。</strong>
     * 
     * @param <T>
     *            要转换的目标类型。
     * @param json
     *            给定的 {@code JSON} 字符串。
     * @param clazz
     *            要转换的目标类。
     * @param exclude
     *            排除的属性数组           
     *          
     * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
     * @since 1.0
     */
    public static <T> T fromJsonWithExclude(String json, Class<T> clazz,String[] exclude) {
        return fromJsonWithExclude(json, clazz, null,exclude);
    }

	/**
	 * 将json转换成list
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromJsonToList(String json, Class<T> clazz) throws Exception {
		List<T> returnList = new ArrayList<T>();
		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++){
			T fromJsonWithMap = JSONUtils.fromJsonWithMap(array.getString(i), clazz);
			returnList.add(fromJsonWithMap);
		}
		return returnList;
	}

	/**
	 * 将给定的json字符串转换为对象,支持对象中含有Map和Collection类型
	 * 
	 * @param <T>
	 * @param json
	 *            json字符
	 * @param clazz
	 *            要转换的对象的class
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T fromJsonWithMap(String json, Class<T> clazz) throws Exception {
		JSONObject fromObject = JSONObject.fromObject(json);

		Map classMap = null;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Type genericType = field.getGenericType();
			if (genericType instanceof ParameterizedType) {
				Type rawType = ((ParameterizedType) genericType).getRawType();
				if (Collection.class.isAssignableFrom(((Class) rawType))) {
					classMap = new HashMap();
					Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
					if (types == null || types.length == 0) {
						throw new Exception("没有指定List的泛型,不能从json转换为对象");
					}
					classMap.put(field.getName(), types[0]);
				}
			}
		}
		if (classMap == null) {
			return (T) JSONObject.toBean(fromObject, clazz);
		} else {
			return (T) fromJsonWithMap(json, clazz, classMap);
		}
	}

	/**
	 * 将给定的json字符串转换为对象,支持对象中含有Map和Collection类型
	 * 
	 * @param <T>
	 * @param json
	 *            json字符
	 * @param clazz
	 *            要转换的对象的class
	 * @param classMap
	 *            指定Collection类型的key和class
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T fromJsonWithMap(String json, Class<T> clazz, Map<String, Class> classMap) {
		JSONObject fromObject = JSONObject.fromObject(json);
		return (T) JSONObject.toBean(fromObject, clazz, classMap);
	}

	/**
	 * 将给定的目标对象根据{@code GsonBuilder} 所指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * <p />
	 * 该方法转换发生错误时，不会抛出任何异常。若发生错误时，{@code JavaBean} 对象返回 <code>"{}"</code>；
	 * 集合或数组对象返回 <code>"[]"</code>。 其本基本类型，返回相应的基本值。
	 * 
	 * @param target
	 *            目标对象。
	 * @param targetType
	 *            目标对象的类型。
	 * @param builder
	 *            可定制的{@code Gson} 构建器。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @since 1.1
	 */
	public static String toJson(Object target, Type targetType, GsonBuilder builder) {
		if (target == null)
			return EMPTY_JSON;
		Gson gson = null;
		if (builder == null) {
			gson = new Gson();
		} else {
			gson = builder.create();
		}
		String result = EMPTY_JSON;
		try {
			if (targetType == null) {
				result = gson.toJson(target);
			} else {
				result = gson.toJson(target, targetType);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("目标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发生异常！");
			if (target instanceof Collection<?> || target instanceof Iterator<?> || target instanceof Enumeration<?>
					|| target.getClass().isArray()) {
				result = EMPTY_JSON_ARRAY;
			}
		}
		return result;
	}

	public static List<Object> fromJson(String json, Type type) {
		int start = json.indexOf("[");
		int end = json.lastIndexOf("]");
		if (start > -1 && end > -1) {
			String formatted = json.substring(start, end + 1);
			Gson gson = new Gson();
			return gson.fromJson(formatted, type);
		}
		return null;
	}

	public static void mergeJSONArray(JSONArray target, JSONArray origin) throws Exception {
		if (origin == null || origin.length() == 0) {
			return;
		}
		if (target == null) {
			target = new JSONArray();
		}
		for (int i = 0; i < origin.length(); i++) {
			target.put(origin.get(i));
		}
	}

	public static boolean validate(String json) {
		try {
			JSONObject.fromObject(json);
			return true;
		} catch (Exception e) {
			try {
				new JSONArray(json);
				return true;
			} catch (JSONException e1) {
				System.out.println("非法JSON: " + json);
			}

			return false;
		}
	}

	public static JSON fromXMLSerializer(String xmlContent) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.read(xmlContent);
	}

	/**
	 * 读取文件JSON文件中的数据
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getJsonbyFile(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		String laststr = "";
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}

	/**
	 * 将json转为map
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> getMapFromJson(String json) {

		JSONObject root = JSONObject.fromObject(json);
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator<String> iterator = root.keys(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = root.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 将json转为map
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String> getStrMapFromJson(String json) {
		JSONObject root = JSONObject.fromObject(json);
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<String> iterator = root.keys(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = root.getString(key);
			map.put(key, value);
		}
		return map;
	}
}