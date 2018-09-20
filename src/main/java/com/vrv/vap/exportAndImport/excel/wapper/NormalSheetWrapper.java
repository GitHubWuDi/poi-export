package com.vrv.vap.exportAndImport.excel.wapper;

import java.util.List;
import java.util.Map;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月18日 下午3:45:26 
* 类说明    基于模板、注解的通用sheet包装类
*/
@Data
public class NormalSheetWrapper {

	/**
     * sheet的序号
     */
    private int sheetIndex;

    /**
     * 表格行数据
     */
    private List<?> data;

    /**
     * 扩张数据
     */
    private Map<String, String> extendMap;

    /**
     * 基于注解的class
     */
    private Class clazz;

    /**
     * 是否写入表头
     */
    private boolean isWriteHeader;
	
    public NormalSheetWrapper(int sheetIndex, List<?> data, Class clazz) {
        this.sheetIndex = sheetIndex;
        this.data = data;
        this.clazz = clazz;
    }

    public NormalSheetWrapper(List<?> data, Class clazz) {
        this.data = data;
        this.clazz = clazz;
    }

    public NormalSheetWrapper(int sheetIndex, List<?> data, Map<String, String> extendMap, Class clazz,
                              boolean isWriteHeader) {
        this.sheetIndex = sheetIndex;
        this.data = data;
        this.extendMap = extendMap;
        this.clazz = clazz;
        this.isWriteHeader = isWriteHeader;
    }
    
}
