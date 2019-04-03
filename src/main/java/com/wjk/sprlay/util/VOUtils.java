package com.wjk.sprlay.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.wjk.sprlay.exception.SprException;

/**
 * @ClassName  VOUtils
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author WangJKui
 * @date   2019年4月3日 上午9:56:36
 */
public abstract class VOUtils {

	//
	private VOUtils() {
	}
	

	/**
	 * 将Map中的所有key与VO的属性对应，将value赋值给该属性
	 * 
	 * @param map
	 * @param vo
	 */
	public static <T> void setMap2VO(Map<String, Object> map, T vo){
	    
	    if (!SprUtil.isEmpty(map) && vo != null){

	        try {
	            
	            setMap2NormalVO(map, vo);
	            
	        } catch (Exception e) {
	            throw new SprException(e);
	        }
	    }
	}
	
	/**
	 * 将Map中的所有key与VO的属性对应，将value赋值给该属性
	 * 
	 * @param map
	 * @param vo
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private static <T> void setMap2NormalVO(Map<String, Object> map, T vo) 
			throws IllegalAccessException, InvocationTargetException{
	    
    	for (Iterator<Map.Entry<String, Object>> iter = 
    			 map.entrySet().iterator(); 
			 iter.hasNext();) {
            
            Map.Entry<String, Object> entry = iter.next();
            
            String code = entry.getKey();

            Object value = entry.getValue();
            
            BeanUtils.setProperty(vo, code, value);
        }
	}
}
