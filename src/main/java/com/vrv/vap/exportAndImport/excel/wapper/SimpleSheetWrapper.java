package com.vrv.vap.exportAndImport.excel.wapper;

import java.util.List;

import lombok.Data;

/**
 * * 
 * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  @version 
 * 创建时间：2018年9月18日 下午4:08:56
 * 功能说明：类说明 无模板，无注解的简单sheet包装类
 */
@Data
public class SimpleSheetWrapper {

	/**
	 * 每个sheet的列表数据
	 */
	private List<?> data;

	/**
	 * 每个sheet的表头
	 */
	private List<String> header;

	/**
	 * 每个sheet的名字
	 */
	private String sheetName;

	public SimpleSheetWrapper(List<?> data, List<String> header, String sheetName) {
		this.data = data;
		this.header = header;
		this.sheetName = sheetName;
	}

	public SimpleSheetWrapper(List<?> data, List<String> header) {
		this.data = data;
		this.header = header;
	}

	public SimpleSheetWrapper(List<?> data, String sheetName) {
		this.data = data;
		this.sheetName = sheetName;
	}

	public SimpleSheetWrapper(List<?> data) {
		this.data = data;
	}
}
