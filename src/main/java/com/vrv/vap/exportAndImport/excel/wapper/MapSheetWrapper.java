package com.vrv.vap.exportAndImport.excel.wapper;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * * 
 * 
 * @author wudi   E‐mail:wudi@vrvmail.com.cn  @version 创建时间：2018年9月18日 下午3:40:02
 *          类说明 基于模板、注解的Map数据导出的sheet包装类
 */
@Data
public class MapSheetWrapper {

	public MapSheetWrapper(Map<String, List<?>> data, Class clazz) {
		this.data = data;
		this.clazz = clazz;
	}

	public MapSheetWrapper(int sheetIndex, Map<String, List<?>> data, Class clazz) {
		this.sheetIndex = sheetIndex;
		this.data = data;
		this.clazz = clazz;
	}

	public MapSheetWrapper(Map<String, List<?>> data, Map<String, String> extendMap, Class clazz) {
		this.data = data;
		this.extendMap = extendMap;
		this.clazz = clazz;
	}

	public MapSheetWrapper(int sheetIndex, Map<String, List<?>> data, Map<String, String> extendMap, Class clazz,
			boolean isWriteHeader) {
		this.sheetIndex = sheetIndex;
		this.data = data;
		this.extendMap = extendMap;
		this.clazz = clazz;
		this.isWriteHeader = isWriteHeader;
	}

	/**
	 * sheet序号
	 */
	private int sheetIndex;

	/**
	 * 表格行数据
	 */
	private Map<String, List<?>> data;

	/**
	 * 扩展数据
	 */
	private Map<String, String> extendMap;

	/**
	 * 注解的class
	 */
	private Class clazz;

	/**
	 * 是否写表头
	 */
	private boolean isWriteHeader;
}
