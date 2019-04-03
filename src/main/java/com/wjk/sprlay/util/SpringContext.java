package com.wjk.sprlay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName  SpringContext
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author WangJKui
 * @date   2019年4月3日 下午5:34:09
 */
public class SpringContext implements ApplicationContextAware {

	protected static Logger log = LoggerFactory.getLogger(SpringContext.class);

	private static ApplicationContext applicationContext;

	/**
	 * 从Spring上下文中获取BEAN
	 * 
	 * @param beanName
	 *            BEAN名称
	 * @return BEAN
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {

		if (applicationContext == null) {
			return null;
		}

		Object bean = applicationContext.getBean(beanName);

		if (log.isDebugEnabled()) {
			log.debug("bean with name '" + beanName + "' was got: " + (bean == null ? null : bean.getClass().getName()));
		}

		if (bean == null) {
			return null;
		} else {
			return (T) bean;
		}
	}

	public static BeanFactory getBeanFactory(){
		return applicationContext.getAutowireCapableBeanFactory();
	}

	/////////////////////////////////////////////////////////////

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}