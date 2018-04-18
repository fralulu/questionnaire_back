/**
 * 
 */
package com.infore.model.dto;

import com.infore.model.TOption;

/**
 * @desc   
 * @class  TOptionVo
 * @author  create author by deer
 * @date  2018年4月4日下午4:20:41
 */
public class TOptionVo extends TOption{
	private String queryLike;

	/**
	 * @return the queryLike
	 */
	public String getQueryLike() {
		return queryLike;
	}

	/**
	 * @param queryLike the queryLike to set
	 */
	public void setQueryLike(String queryLike) {
		this.queryLike = queryLike;
	}
}
