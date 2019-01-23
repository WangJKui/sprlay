package com.wjk.sprlay.web.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName:  Role   
 * @Description:角色实体 
 * @author: WangJKui
 * @date:   2019年1月22日 下午3:38:50   
 *
 */
public class Role implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = -4312514915455459320L;

	private String id;
	private String code;
	private String name;
	private String sort;
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", code=" + code + ", name=" + name + ", sort=" + sort + "]";
	}
	
	
}
