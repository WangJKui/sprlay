package com.wjk.sprlay.web;

import com.wjk.sprlay.util.SprUtil;
import com.wjk.sprlay.web.vo.BaseVO;

/**
 * 表单响应数据对象，用于响应表单数据显示请求。
 * <p>
 * 在表单数据显示和数据保存等处理器中会创建此对象并返回处理结果。
 * @ClassName  FormResponse
 * @Description 
 * @author WangJKui
 * @date   2019年4月10日 上午8:53:49
 */
public class FormResponse extends BaseResponse {
	/**
	 * 表单类型。
	 * <p>
	 * 表单类型可选值：<br>
	 * 新增：{@link WafConstants#FORMTYPE_NEW}, <br>
	 * 修改：{@link WafConstants#FORMTYPE_MODIFY},<br>
	 * 浏览：{@link WafConstants#FORMTYPE_BROWSE}
	 */
	private String formType = SprUtil.FORMTYPE_BROWSE;

	/**
	 * 表单保存后的处理方式。
	 * <p>
	 * 表单保存后的处理方式可选值：<br>
	 * 继续（新增或修改）：{@link WafConstants#FORMBRANCH_AGAIN}, <br>
	 * 退出：{@link WafConstants#FORMBRANCH_EXIT}
	 */
	private String formBranch = SprUtil.FORMBRANCH_EXIT;

	/**
	 * 表单数据记录。
	 */
	private BaseVO row = null;

	
	/**
	 * 缺省构造器。
	 * 
	 */
	public FormResponse() {
		super();
	}

	/**
	 * 获取表单类型。
	 * 
	 * @return 表单类型
	 */
	public String getFormType() {
		return formType;
	}

	/**
	 * 设置表单类型。
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
	 * 设置表单保存后的处理方式。
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
	 * 获取表单数据记录。
	 * 
	 * @return 表单数据记录
	 */
	public BaseVO getRow() {
		return row;
	}

	/**
	 * 设置表单数据记录。
	 * 
	 * @param row
	 *            表单数据记录
	 */
	public void setRow(BaseVO row) {
		this.row = row;
	}


}
