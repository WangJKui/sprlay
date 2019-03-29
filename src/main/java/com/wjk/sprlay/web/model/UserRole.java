package com.wjk.sprlay.web.model;

import java.io.Serializable;

/**
 * @ClassName  UserRole
 * @Description 用户角色实体
 * @author WangJKui
 * @date   2019年3月28日 下午3:38:08
 */
public class UserRole implements Serializable{
    /**   
	 * @Fields serialVersionUID TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userid;

    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userid=" + userid + ", roleid=" + roleid + "]";
	}
    
    
}