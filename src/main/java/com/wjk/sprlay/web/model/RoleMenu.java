package com.wjk.sprlay.web.model;

import java.io.Serializable;

/**
 * @ClassName  RoleMenu
 * @Description 角色资源实体
 * @author WangJKui
 * @date   2019年3月28日 下午3:37:32
 */
public class RoleMenu implements Serializable{
    /**   
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleid;

    private Integer menuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

	@Override
	public String toString() {
		return "RoleMenu [id=" + id + ", roleid=" + roleid + ", menuid=" + menuid + "]";
	}
    
    
}