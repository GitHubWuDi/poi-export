package com.vrv.vap.exportAndImport.excel.wapper;

import java.util.List;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月18日 下午3:55:32 
* 类说明 无模板、基于注解导出的sheet包装类
*/
@Data
public class NoTemplateSheetWrapper {
	 /**
     * 待导出行数据
     */
    private List<?> data;

    /**
     * 基于注解的class
     */
    private Class clazz;

    /**
     * 是否写入表头
     */
    private boolean isWriteHeader;

    /**
     * sheet名
     */
    private String sheetName;
    
    public NoTemplateSheetWrapper(List<?> data, Class clazz) {
        this.data = data;
        this.clazz = clazz;
    }

    public NoTemplateSheetWrapper(List<?> data, Class clazz, boolean isWriteHeader) {
        this.data = data;
        this.clazz = clazz;
        this.isWriteHeader = isWriteHeader;
    }

    public NoTemplateSheetWrapper(List<?> data, Class clazz, boolean isWriteHeader, String sheetName) {
        this.data = data;
        this.clazz = clazz;
        this.isWriteHeader = isWriteHeader;
        this.sheetName = sheetName;
    }
}
