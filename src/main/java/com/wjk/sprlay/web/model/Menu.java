package com.wjk.sprlay.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName:  Menu   
 * @Description:资源权限实体 
 * @author: WangJKui
 * @date:   2019年1月29日 下午2:31:39   
 *
 */
public class Menu implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer pid;

    private String name;

    private String uri;

    private String icon;

    private String permission;
    /*类型（1:目录，2：菜单，3：按钮）*/
    private Integer type;

    /* 状态（0：禁止，1：正常）   */
    private Integer status;

    /*排序*/
    private Integer orderno;

    private String ctime;

    private String remark;
    
    private List<Menu> child;
    
    public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", name=" + name + ", uri=" + uri + ", icon=" + icon
				+ ", permission=" + permission + ", type=" + type + ", status=" + status + ", orderno=" + orderno
				+ ", ctime=" + ctime + ", remark=" + remark + ", child=" + child + "]";
	}
    
    
}