package com.vrv.vap.exportAndImport.word.policy;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午5:57:42 
* 类说明          SelfRender
*/
public class SelfRenderPolicy implements RenderPolicy {

	@Override
	public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
		RunTemplate runTemplate = (RunTemplate) eleTemplate;
		XWPFRun run = runTemplate.getRun();
		run.setText(runTemplate.getSource(), 0);
	
	}

}
