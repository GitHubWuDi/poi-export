package com.vrv.vap.exportAndImport.excel.exception; 
/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月18日 上午11:53:34 
* Excel相关异常说明 
*/
public class ExcelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcelException() {}
	
	public ExcelException(String message,Throwable t) {
		super(message, t);
	}
	
	public ExcelException(Throwable t){
		super(t);
	}
	
	public ExcelException(String message){
		super(message);
	}
}
