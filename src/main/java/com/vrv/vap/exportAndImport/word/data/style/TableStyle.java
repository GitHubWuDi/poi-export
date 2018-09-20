package com.vrv.vap.exportAndImport.word.data.style;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午3:53:08 
* 类说明   表格样式
*/
@Data
public class TableStyle {

	/**
	 * 背景色
	 */
	private String backgroundColor;

	/**
	 * 对齐方式：STJc.LEFT、STJc.CENTER、STJc.RIGHT
	 */
	private STJc.Enum align;
	
	
}
