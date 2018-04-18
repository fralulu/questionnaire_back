/**
 * 
 */
package com.infore.common.enums;

/**
 * @desc   
 * @class  SiteOperTypeEnums
 * @author  create author by deer
 * @date  2017年11月30日上午9:06:17
 */
public enum SiteOperTypeEnums {

	SITE_OPERATOR_TYPE_1("站点",1),SITE_OPERATOR_TYPE_2("区域",2);
	
	
	// 构造方法
    private SiteOperTypeEnums(String name, int value) {
        this.name = name;
        this.value = value;
    }
    
    // 普通方法
    public static String getName(int value) {
        for (SiteOperTypeEnums s : SiteOperTypeEnums.values()) {
            if (s.getValue() == value) {
                return s.name;
            }
        }
        return null;
    }
    
	private String name;
    private int value;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
    
    
}
