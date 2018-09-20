package com.vrv.vap.model;

import com.vrv.vap.exportAndImport.word.data.PictureRenderData;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月20日 下午3:59:26 
* 类说明 
*/
@Data
public class NetSiteVO {

	private String city;
	private String area;
	private String access_name;
	private String unit;
	private String police_type;
	private String police_name;
	private String police_tel;
	private String access_use;
	private String ip;
	private String terminal_count;
	private String er;
	private PictureRenderData pic;
	
	
}
