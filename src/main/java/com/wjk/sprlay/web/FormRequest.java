package com.wjk.sprlay.web;

import java.util.HashMap;
import java.util.Map;

import com.wjk.sprlay.web.vo.BaseVO;

/**
 * 表单请求数据对象，用于接收视图层的表单请求参数。
 * <p>
 * 通常在表单处理器中申明一个该类型的参数，在接收和处理"json"参数后， 可根据需要调用{@link #parseForForm(FormCache)}、
 * {@link #parseForSave(FormCache)}等方法对请求参数做进一步解析。
 * @author WangJKui
 * @date   2019年4月3日 上午11:09:05
 */
public class FormRequest extends BaseRequest {

	/**
	 * 表单类型。
	 * <p>
	 * 可选值：<br>
	 * 新增：{@link WafConstants#FORMTYPE_NEW}, <br>
	 * 修改：{@link WafConstants#FORMTYPE_MODIFY},<br>
	 * 浏览：{@link WafConstants#FORMTYPE_BROWSE}
	 */
	private String formType = null;

	/**
	 * 表单保存后的处理方式：
	 * <p>
	 * 可选值：<br>
	 * 继续（新增或修改）：{@link WafConstants#FORMBRANCH_AGAIN}, <br>
	 * 退出：{@link WafConstants#FORMBRANCH_EXIT}
	 */
	private String formBranch = null;

	/**
	 * 表单数据记录。
	 */
	private BaseVO row = null;

	/**
	 * 新增的VO对象表单中对应的动态实体项值。
	 * <p>
	 * 由{@link WAFService#parseDynEntityItemData(javax.servlet.http.HttpServletRequest, FormRequest, FormCache)}解析而成
	 */
	private Map<String, Object> dynNews = null;


	/**
	 * 修改的VO对象表单中对应的动态实体项值。
	 * <p>
	 * 由{@link WAFService#parseDynEntityItemData(javax.servlet.http.HttpServletRequest, FormRequest, FormCache)}解析而成
	 */
	private Map<String,Object> dynModifys = null;

	/**
	 * 获取表单类型
	 * 
	 * @return 表单类型
	 */
	public String getFormType() {
		return formType;
	}

	/**
	 * 设置表单类型
	 * 
	 * @param formType
	 *            表单类型。可选值：<br>
	 *            新增：{@link WafConstants#FORMTYPE_NEW}, <br>
	 *            修改：{@link WafConstants#FORMTYPE_MODIFY},<br>
	 *            浏览：{@link WafConstants#FORMTYPE_BROWSE}
	 */
	public void setFormType(String formType) {
		this.formType = formType;
	}

	/**
	 * 获取表单保存后的处理方式
	 * 
	 * @return 处理处理方式
	 */
	public String getFormBranch() {
		return formBranch;
	}

	/**
	 * 设置表单保存后的处理方式
	 * 
	 * @param formBranch
	 *            表单保存后的处理方式。可选值：<br>
	 *            继续（新增或修改）：{@link WafConstants#FORMBRANCH_AGAIN}, <br>
	 *            退出：{@link WafConstants#FORMBRANCH_EXIT}
	 */
	public void setFormBranch(String formBranch) {
		this.formBranch = formBranch;
	}

	/**
	 * 获取表单数据记录
	 * 
	 * @return 表单数据记录
	 */
	public BaseVO getRow() {
		return row;
	}

	/**
	 * 设置表单数据记录
	 * 
	 * @param row
	 *            表单数据记录
	 */
	public void setRow(BaseVO row) {
		this.row = row;
	}

	/**
	 * 获取新增的动态实体项值
	 * 
	 * @return 新增的动态实体项值
	 */
	public Map<String, Object> getDynNews() {
		if (dynNews == null) {
			dynNews = new HashMap<String, Object>();
		}

		return dynNews;
	}

	/**
	 * 设置新增的VO对象动态实体项值
	 * 
	 * @param dynNews
	 *            新增的VO对象动态实体项值
	 */
	public void setDynNews(Map<String, Object> dynNews) {
		this.dynNews = dynNews;
	}

	/**
	 * 获取修改的动态实体项值
	 * 
	 * @return 修改的动态实体项值
	 */
	public Map<String, Object> getDynModifys() {
		if (dynModifys == null) {
			dynModifys = new HashMap<String, Object>();
		}

		return dynModifys;
	}

	/**
	 * 设置修改的VO对象动态实体项值
	 * 
	 * @param dynModifys
	 *            修改的VO对象动态实体项值
	 */
	public void setDynModifys(Map<String, Object> dynModifys) {
		this.dynModifys = dynModifys;
	}

}
