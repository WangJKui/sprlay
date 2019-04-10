package com.wjk.sprlay.web;

import java.util.ArrayList;
import java.util.List;

import com.wjk.sprlay.web.vo.BaseVO;

/**
 * 列表响应数据对象，用于响应列表数据显示请求。
 * <p>
 * 在列表数据新增、数据删除、数据保存等处理器中会创建此对象并返回处理结果。
 * @ClassName  ListResponse
 * @Description 
 * @author WangJKui
 * @date   2019年4月10日 上午8:59:07
 */
public class ListResponse extends BaseResponse {
	/**
	 * 分页模式下的当前页号
	 */
	private int pno = 1;

	/**
	 * 分页模式下的每页记录数
	 */
	private int rpp = 10;

	/**
	 * 满足查询条件的记录总数。
	 * <p>
	 * 注意分页模式下“满足查询条件的记录总数”和“数据列表的长度”的区别
	 */
	private int total = 0;

	/**
	 * 数据列表
	 */
	private List<BaseVO> rows = null;


	/**
	 * 缺省构造器。
	 * 
	 * @param ecode
	 *            关联的实体编号
	 */
	public ListResponse() {
		super();
	}

	/**
	 * 获取当前页号
	 * 
	 * @return 当前页号
	 */
	public int getPno() {
		return pno;
	}

	/**
	 * 设置当前页号
	 * 
	 * @param pno
	 *            当前页号
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
	 * 获取满足查询条件的记录总数。
	 * <p>
	 * 注意分页模式下“满足查询条件的记录总数”和“数据列表的长度”的区别
	 * 
	 * @return 满足查询条件的记录总数
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 设置满足查询条件的记录总数
	 * <p>
	 * 注意分页模式下“满足查询条件的记录总数”和“数据列表的长度”的区别
	 * 
	 * @param total
	 *            满足查询条件的记录总数
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 获取数据列表
	 * 
	 * @return 数据列表
	 */
	public List<BaseVO> getRows() {
		return rows;
	}

	/**
	 * 设置数据列表
	 * 
	 * @param rows
	 *            数据列表
	 */
	public void setRows(List<BaseVO> rows) {
		this.rows = rows;
	}

	/**
	 * 把VO对象添加到数据列表
	 * 
	 * @param row
	 *            VO对象
	 */
	public  void addToRows(BaseVO row) {
		
		if (rows == null) {
			rows = new ArrayList<BaseVO>();
		}
		rows.add(row);
	}

	/**
	 * 把VO对象从数据列表中参数
	 * 
	 * @param row
	 *            需删除的VO对象
	 * @return 删除的VO对象
	 */
	public BaseVO deleteFromRows(BaseVO row) {
		
		if (rows == null) {
			return null;
		}

		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i) == row) {
				return rows.remove(i);
			}
		}
		return null;
	}

}
