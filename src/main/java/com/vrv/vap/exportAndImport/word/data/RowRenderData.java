package com.vrv.vap.exportAndImport.word.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vrv.vap.exportAndImport.word.data.style.TableStyle;

import lombok.Data;

/**
 * * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  
 * @version 创建时间：2018年9月19日 下午4:23:43
 * 类说明 行数据
 */
@Data
public class RowRenderData implements RenderData {

	
	public static final String check_arrow = "√";
	
	private List<TextRenderData> rowData;

	private TableStyle style;

	public RowRenderData() {
	}

	public RowRenderData(List<TextRenderData> rowData) {
		this.rowData = rowData;
	}

	public static RowRenderData build(String... row) {
		RowRenderData instance = new RowRenderData();
		instance.rowData = new ArrayList<TextRenderData>();
		for (String col : row) {
			instance.rowData.add(new TextRenderData(col));
		}
		return instance;
	}

	public static RowRenderData build(TextRenderData... row) {
		RowRenderData instance = new RowRenderData();
		instance.rowData = null == row ? null : Arrays.asList(row);
		return instance;
	}

	public RowRenderData(List<TextRenderData> rowData, String backgroundColor) {
		this.rowData = rowData;
		TableStyle style = new TableStyle();
		style.setBackgroundColor(backgroundColor);
		this.style = style;
	}
	
	 public int size() {
	        return null == rowData ? 0 : rowData.size();
	    }

}
