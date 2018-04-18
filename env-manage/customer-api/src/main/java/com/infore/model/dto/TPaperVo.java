/**
 * 
 */
package com.infore.model.dto;

import com.infore.model.TPaper;

/**
 * @desc   
 * @class  TPaperDto
 * @author  create author by deer
 * @date  2018年3月29日下午8:21:28
 */
public class TPaperVo extends TPaper{
	
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
