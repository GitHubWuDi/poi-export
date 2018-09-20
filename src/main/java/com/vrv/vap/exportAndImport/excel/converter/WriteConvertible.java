package com.vrv.vap.exportAndImport.excel.converter;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月18日 下午4:33:20 
* 类说明  写入excel内容转换器
*/
public interface WriteConvertible {

    /**
     * 写入Excel列内容转换
     *
     * @param object 待转换数据
     * @return 转换完成的结果
     */
    Object execWrite(Object object);
}
