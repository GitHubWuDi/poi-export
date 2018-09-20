package com.vrv.vap.exportAndImport.word.policy;

import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:56:56 
* 类说明  自定义插件
*/
public interface RenderPolicy {

	public  void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template);
}
