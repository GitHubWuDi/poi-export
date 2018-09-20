package com.vrv.vap.exportAndImport.word.data;

import com.vrv.vap.exportAndImport.word.data.style.Style;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:19:13 
* 类说明    文本模板类型
*/
@Data
public class TextRenderData implements RenderData {

    private Style style;
	
	/**
	 * \n 表示换行
	 */
	private String text;
	
	
	public TextRenderData() {
	}

	public TextRenderData(String text) {
		this.text = text;
	}

	public TextRenderData(String color, String text) {
		this.style = new Style(color);
		this.text = text;
	}
	
	public TextRenderData(String text, Style style) {
        this.style = style;
        this.text = text;
    }
	
}
