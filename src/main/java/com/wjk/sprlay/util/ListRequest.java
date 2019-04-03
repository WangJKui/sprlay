package com.wjk.sprlay.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wjk.sprlay.web.model.Role;

/**
 * 列表请求数据对象，用于接收视图层的列表请求参数
 * <p>
 * 通常在列表处理器申明一个该类型的参数，在接收和处理"json"参数后，可根据需要调用 {@link WAFService#parseForList(javax.servlet.http.HttpServletRequest, ListRequest, ListCache)}、
 * {@link WAFService#parseForSave(ListCache)}和{@link WAFService#parseForDelete(ListRequest, ListCache)}
 * 等方法对请求参数做进一步解析。
 * @author WangJKui
 * @date   2019年4月3日 上午11:14:29
 */
public class ListRequest extends BaseRequest {

	/**
	 * 列表数据动态查询条件。
	 * <p>
	 * 列表数据查询条件分为静态和动态两部分：<br>
	 * （1）静态查询条件由实体配置中的“结果集限制条件”设定；<br>
	 * （2）动态查询条件通常表现为视图层用户可设置的查询条件。
	 * <p>
	 * 结果集的实际查询条件为静态查询条件和动态查询条件的“逻辑与（and）”
	 */
	private String where = null;

	/**
	 * 排序列名
	 */
	private String sortName = null;

	/**
	 * 排序方式（升序asc或降序desc）
	 */
	private String sortType = null;
	
	/**
	 * 列表新增时新增的行数
	 */
	private int listNewCount = 1;

	/**
	 * 页号
	 */
	private int pno = -1;

	/**
	 * 每页记录数
	 */
	private int rpp = -1;

	/**
	 * 待删除的VO对象列表。
	 * <p>
	 * 由{@link WAFService#parseForDelete(ListRequest, ListCache)}解析而成
	 */
	private List<Role> deletes = null;

	/**
	 * 被修改了的VO对象列
	 * <p>
	 * 由{@link WAFService#parseForSave(ListRequest, ListCache)}解析而成
	 */
	private List<Role> modifys = null;

	/**
	 * 新增的VO对象列表。
	 * <p>
	 * 由{@link WAFService#parseForSave(ListRequest, ListCache)}解析而成
	 */
	private List<Role> news = null;
	
	/**
	 * 新增的VO对象列表中对应的动态实体项值。
	 * <p>
	 * 由{@link WAFService#parseDynEntityItemData(ListRequest, ListCache)}解析而成
	 */
	private Map<String, Map<String,Object>> dynNews = null;
	

	/**
	 * 修改的VO对象列表中对应的动态实体项值。
	 * <p>
	 * 由{@link WAFService#parseDynEntityItemData(ListRequest, ListCache)}解析而成
	 */
	private Map<String, Map<String,Object>> dynModifys = null;

	/**
	 * 查询项处理器
	 */
//	private QueryItemsHandler qisHandler = null;

	/**
	 * 获取列表数据动态查询条件
	 * 
	 * @return 动态查询条件
	 */
	public String getWhere() {
		return where;
	}

	/**
	 * 设置列表数据动态查询条件。
	 * 
	 * @param where
	 *            动态查询条件
	 */
	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * 获取排序列名。
	 * 
	 * @return 排序列名
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * 设置排序列名。
	 * 
	 * @param sortName
	 *            排序列名
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**获取列表新增时一次新增的行数
	 * @return
	 */
	public int getListNewCount() {
		return listNewCount;
	}

	/**
	 * 设置列表新增一次返回的记录数
	 * @param listNewCount
	 *        新增行数
	 */
	public void setListNewCount(int listNewCount) {
		this.listNewCount = listNewCount;
	}

	/**
	 * 获取排序类型。
	 * 
	 * @return 排序类型。asc-升序；desc-降序
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * 设置排序类型。
	 * 
	 * @param sortType
	 *            排序类型。asc-升序；desc-降序
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * 获取页号
	 * 
	 * @return 页号
	 */
	public int getPno() {
		return pno;
	}

	/**
	 * 设置页号
	 * 
	 * @param pno
	 *            页号
	 */
	public void setPno(int pno) {
		this.pno = pno;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getRpp() {
		return rpp;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param rpp
	 *            每页记录数
	 */
	public void setRpp(int rpp) {
		this.rpp = rpp;
	}

	/**
	 * 获取待删除的VO对象列表
	 * 
	 * @return 待删除的VO对象列表
	 */
	public List<Role> getDeletes() {
		return deletes;
	}

	/**
	 * 设置待删除的VO对象列表
	 * 
	 * @param deletes
	 *            待删除的VO对象列表
	 */
	public void setDeletes(List<Role> deletes) {
		this.deletes = deletes;
	}

	/**
	 * 获取被修改了的VO对象列表
	 * 
	 * @return 被修改了的VO对象列
	 */
	public List<Role> getModifys() {
		return modifys;
	}

	/**
	 * 设置被修改了的VO对象列
	 * 
	 * @param modifys
	 *            被修改了的VO对象列
	 */
	public void setModifys(List<Role> modifys) {
		this.modifys = modifys;
	}

	/**
	 * 获取新增的VO对象列表
	 * 
	 * @return 新增的VO对象列表
	 */
	public List<Role> getNews() {
		return news;
	}

	/**
	 * 设置新增的VO对象列表
	 * 
	 * @param news
	 *            新增的VO对象列表
	 */
	public void setNews(List<Role> news) {
		this.news = news;
	}

	/**
	 * 获取查询项处理器
	 * 
	 * @return 查询项处理器
	 */
	/*public QueryItemsHandler getQueryItemHandler() {
		return qisHandler;
	}*/

	/**
	 * 设置查询项处理器
	 * 
	 * @param qisHandler
	 *            查询项处理器
	 */
	/*public void setQueryItemHandler(QueryItemsHandler qisHandler) {
		this.qisHandler = qisHandler;
	}*/

	public static String buildWhere() {
		return null;
	}

	/**
	 * 获取新增的动态实体项值
	 * 
	 * @return 新增的动态实体项值
	 */
	public Map<String, Map<String, Object>> getDynNews() {
		if (dynNews == null) {
			dynNews = new HashMap<String, Map<String, Object>>();
		}
		
		return dynNews;
	}

	/**
	 * 设置新增的VO对象动态实体项值
	 * 
	 * @param dynNews
	 *            新增的VO对象动态实体项值
	 */
	public void setDynNews(Map<String, Map<String, Object>> dynNews) {
		this.dynNews = dynNews;
	}

	/**
	 * 获取修改的动态实体项值
	 * 
	 * @return 修改的动态实体项值
	 */
	public Map<String, Map<String, Object>> getDynModifys() {
		if (dynModifys == null) {
			dynModifys = new HashMap<String, Map<String, Object>>();
		}
		
		return dynModifys;
	}
	/**
	 * 设置修改的VO对象动态实体项值
	 * 
	 * @param dynModifys
	 *            修改的VO对象动态实体项值
	 */
	public void setDynModifys(Map<String, Map<String, Object>> dynModifys) {
		this.dynModifys = dynModifys;
	}

	/*public QueryItemsHandler getQisHandler() {
		if (qisHandler == null) {
			qisHandler = new QueryItemsHandler();
		}
		
		return qisHandler;
	}

	public void setQisHandler(QueryItemsHandler qisHandler) {
		this.qisHandler = qisHandler;
	}*/
}