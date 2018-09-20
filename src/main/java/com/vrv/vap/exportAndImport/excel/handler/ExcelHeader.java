package com.vrv.vap.exportAndImport.excel.handler;

import com.vrv.vap.exportAndImport.excel.converter.ReadConvertible;
import com.vrv.vap.exportAndImport.excel.converter.WriteConvertible;

import lombok.Data;

/**
 * * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  
 * @version 创建时间：2018年9月18日 下午2:31:20
 * 类说明:用来存储Excel标题的对象，通过该对象可以获取标题和方法的对应关系
 */
@Data
public class ExcelHeader implements Comparable<ExcelHeader> {

	public ExcelHeader() {
		super();
	}

	public ExcelHeader(String title, int order, WriteConvertible writeConverter, ReadConvertible readConverter,
			String filed, Class<?> filedClazz) {
		super();
		this.title = title;
		this.order = order;
		this.writeConverter = writeConverter;
		this.readConverter = readConverter;
		this.filed = filed;
		this.filedClazz = filedClazz;
	}

	/**
	 * excel的标题名称
	 */
	private String title;

	/**
	 * 每一个标题的顺序
	 */
	private int order;

	/**
	 * 写数据转换器
	 */
	private WriteConvertible writeConverter;

	/**
	 * 读数据转换器
	 */
	private ReadConvertible readConverter;

	/**
	 * 注解域
	 */
	private String filed;

	/**
	 * 属性类型
	 */
	private Class<?> filedClazz;

	@Override
	public int compareTo(ExcelHeader o) {
		return order - o.order;
	}
}
