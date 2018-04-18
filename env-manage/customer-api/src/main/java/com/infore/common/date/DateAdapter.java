package com.infore.common.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @desc 日期转换类
 * @author XiaChengwei
 * @date 2017-07-05 15:21
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public String marshal(Date arg0) throws Exception {
		// TODO Auto-generated method stub
		return dateFormat.format(arg0);
	}

	@Override
	public Date unmarshal(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return dateFormat.parse(arg0);
	}

}
