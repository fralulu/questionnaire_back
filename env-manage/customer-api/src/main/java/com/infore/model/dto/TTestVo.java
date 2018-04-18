/**
 * 
 */
package com.infore.model.dto;

import com.infore.model.TTest;

/**
 * @desc   
 * @class  TTestVo
 * @author  create author by deer
 * @date  2018年4月4日下午4:00:41
 */
public class TTestVo extends TTest{
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
