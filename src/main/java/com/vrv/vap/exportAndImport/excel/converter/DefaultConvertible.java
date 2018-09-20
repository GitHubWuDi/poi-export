package com.vrv.vap.exportAndImport.excel.converter;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月18日 上午11:49:59 
* 类说明 抽象默认转换器, 实现了{@link WriteConvertible} 与 {@link ReadConvertible}接口
*/
public class DefaultConvertible implements ReadConvertible,WriteConvertible {

	@Override
	public Object execWrite(Object object) {
		 throw new UnsupportedOperationException();
	}

	@Override
	public Object execRead(String object) {
		 throw new UnsupportedOperationException();
	}

}
