package com.wjk.sprlay.web.vo;

import java.io.Serializable;

/**
 * @ClassName  BaseVO
 * @Description 实体VO，与某个实体关联
 * @author WangJKui
 * @date   2019年4月10日 上午9:08:20
 */
public abstract class BaseVO implements VO, Cloneable, Serializable {
	
	/**   
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 569536256649599478L;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
