package com.vrv.vap.exportAndImport.word.policy;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.vrv.vap.exportAndImport.word.NiceXWPFDocument;
import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.config.Configure;
import com.vrv.vap.exportAndImport.word.data.DocxRenderData;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午5:09:39 
* 类说明  合并文档
*/
public class DocxRenderPolicy implements RenderPolicy {
   
	private Logger logger = Logger.getLogger(DocxRenderPolicy.class);
	
	@Override
	public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        NiceXWPFDocument doc = template.getXWPFDocument();
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        if (null == data) return;

        XWPFRun run = runTemplate.getRun();
        run.setText("", 0);

        List<NiceXWPFDocument> docMerges = getMergedDocxs((DocxRenderData) data, template.getConfig());
        try {
            doc = doc.merge(docMerges, run);
        } catch (Exception e) {
            logger.error("merge docx error", e);
        }

        template.reload(doc);
    		
	}

	 private List<NiceXWPFDocument> getMergedDocxs(DocxRenderData data, Configure configure) {
	        List<NiceXWPFDocument> docs = new ArrayList<NiceXWPFDocument>();
	        File docx = data.getDocx();
	        List<?> dataList = data.getDataList();
	        if (null == dataList || dataList.isEmpty()) {
	            try {
	                // 待合并的文档不是模板
	                docs.add(new NiceXWPFDocument(new FileInputStream(docx)));
	            } catch (Exception e) {
	                logger.error("Cannot get the merged docx.", e);
	            }
	        } else {
	            for (int i = 0; i < dataList.size(); i++) {
	                XWPFTemplate temp = XWPFTemplate.compile(docx, configure);
	                temp.render(dataList.get(i));
	                docs.add(temp.getXWPFDocument());
	            }
	        }
	        return docs;
	    }
	
	
}
