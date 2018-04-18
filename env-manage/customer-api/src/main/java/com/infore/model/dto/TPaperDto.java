/**
 * 
 */
package com.infore.model.dto;

import java.util.List;

import com.infore.model.TOption;
import com.infore.model.TPaper;
import com.infore.model.TTest;

/**
 * @desc   
 * @class  TPaperDto
 * @author  create author by deer
 * @date  2018年4月11日下午2:25:55
 */
public class TPaperDto extends TPaper{
	
	private List<TTestDto> testList;
	
	
	/**
	 * @return the testList
	 */
	public List<TTestDto> getTestList() {
		return testList;
	}

	/**
	 * @param testList the testList to set
	 */
	public void setTestList(List<TTestDto> testList) {
		this.testList = testList;
	}

	public  class TTestDto extends TTest{
		private List<TOption> optionList;

		/**
		 * @return the optionList
		 */
		public List<TOption> getOptionList() {
			return optionList;
		}

		/**
		 * @param optionList the optionList to set
		 */
		public void setOptionList(List<TOption> optionList) {
			this.optionList = optionList;
		}
		
	};
	

}

