package com.vrv.vap.exportAndImport.word.policy;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

import com.vrv.vap.exportAndImport.word.NiceXWPFDocument;
import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午5:26:14 
* 类说明 
*/
public abstract class DynamicTableRenderPolicy implements RenderPolicy {

	private Logger logger = Logger.getLogger(DynamicTableRenderPolicy.class);
	
	@Override
	public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
		NiceXWPFDocument doc = template.getXWPFDocument();
		RunTemplate runTemplate = (RunTemplate) eleTemplate;
		XWPFRun run = runTemplate.getRun();
		run.setText("", 0);
		try {
		    //w:tbl-w:tr-w:tc-w:p-w:tr
			XmlCursor newCursor = ((XWPFParagraph)run.getParent()).getCTP().newCursor();
			newCursor.toParent();
			//if (newCursor.getObject() instanceof CTTc) 
			newCursor.toParent();
			newCursor.toParent();
			XmlObject object = newCursor.getObject();
			XWPFTable table = doc.getTable((CTTbl) object);
			render(table, data);
		} catch (Exception e) {
			logger.error("dynamic table error:" + e.getMessage(), e);
		}
	
	}
	
	/**
	 * @param table 表格
	 * @param data 数据
	 */
	public abstract void render(XWPFTable table, Object data);

}
