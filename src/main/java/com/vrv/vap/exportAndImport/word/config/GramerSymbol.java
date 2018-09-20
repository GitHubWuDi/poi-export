package com.vrv.vap.exportAndImport.word.config; 
/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午3:05:19 
* 类说明    默认模板语法
*/
public enum GramerSymbol {


    /**
     * 图片
     */
    IMAGE('@'),

    /**
     * 文本
     */
    TEXT('\0'),

    /**
     * 表格
     */
    TABLE('#'),

    /**
     * 列表
     */
    NUMBERIC('*'),

    /**
     * word文档模板
     */
    DOCX_TEMPLATE('+');

    private char symbol;

    private GramerSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String toString() {
        return String.valueOf(this.symbol);
    }

 
	
	
}
