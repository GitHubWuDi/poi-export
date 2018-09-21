package com.vrv.vap.exportAndImport.word.policy;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.vrv.vap.exportAndImport.util.StyleUtils;
import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.data.TextRenderData;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午6:11:31 
* 类说明  文本Render
*/
public class TextRenderPolicy implements RenderPolicy {

	static final String REGEX_LINE_CHARACTOR = "\\n";
	
	@Override
	public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
		RunTemplate runTemplate = (RunTemplate) eleTemplate;
		XWPFRun run = runTemplate.getRun();
		if (null == data) {
			// support String to set blank
			run.setText("", 0);
			return;
		}

		TextRenderData textRenderData = null;
		if (data instanceof TextRenderData) {
			textRenderData = (TextRenderData) data;
		} else {
			textRenderData = new TextRenderData(data.toString());
		}
		String textData = textRenderData.getText();
		StyleUtils.styleRun(run, textRenderData.getStyle());
		if (null == textData) textData = "";
		
		String[] split = textData.split(REGEX_LINE_CHARACTOR);
		if (null != split){
		    run.setText(split[0], 0); 
		    for (int i = 1; i < split.length; i++) {
                run.addBreak(); 
                run.setText(split[i]);
            }
		}
	
	}

}
