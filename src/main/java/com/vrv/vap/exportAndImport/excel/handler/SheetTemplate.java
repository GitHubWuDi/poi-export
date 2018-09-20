package com.vrv.vap.exportAndImport.excel.handler;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.vrv.vap.exportAndImport.excel.exception.ExcelException;

/**
 * * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  
 * @version 创建时间：2018年9月18日 下午2:53:31
 * 类说明 Excel模板
 */
public class SheetTemplate implements Closeable {
	/**
	 * 当前工作簿
	 */
	Workbook workbook;
	/**
	 * 当前工作sheet表
	 */
	Sheet sheet;
	/**
	 * 当前表编号
	 */
	int sheetIndex;
	/**
	 * 当前行
	 */
	Row currentRow;
	/**
	 * 当前列数
	 */
	int currentColumnIndex;
	/**
	 * 当前行数
	 */
	int currentRowIndex;
	/**
	 * 默认样式
	 */
	CellStyle defaultStyle;
	/**
	 * 指定行样式
	 */
	Map<Integer, CellStyle> appointLineStyle = new HashMap<>();
	/**
	 * 分类样式模板
	 */
	Map<String, CellStyle> classifyStyle = new HashMap<>();
	/**
	 * 单数行样式
	 */
	CellStyle singleLineStyle;
	/**
	 * 双数行样式
	 */
	CellStyle doubleLineStyle;
	/**
	 * 数据的初始化列数
	 */
	int initColumnIndex;
	/**
	 * 数据的初始化行数
	 */
	int initRowIndex;

	/**
	 * 最后一行的数据
	 */
	int lastRowIndex;
	/**
	 * 默认行高
	 */
	float rowHeight;
	/**
	 * 序号坐标点
	 */
	int serialNumberColumnIndex = -1;
	/**
	 * 当前序号
	 */
	int serialNumber;

	/**
	 * 将文件写到相应的路径下
	 * 
	 * @param filePath
	 * @throws ExcelException
	 */
	public void write2File(String filePath) throws ExcelException {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			this.workbook.write(fos);
		} catch (IOException e) {
			throw new ExcelException(e);
		}
	}

	/**
	 * 将文件写到某个输出流中
	 * 
	 * @param os
	 * 输出流
	 */
	public void write2Stream(OutputStream os) throws ExcelException {

		try {
			this.workbook.write(os);
		} catch (IOException e) {
			throw new ExcelException(e);
		}
	}

	@Override
	public void close() throws IOException {
		if (null != this.workbook) {
			this.workbook.close();
		}
	}

}
