package com.vrv.vap.exportAndImport.word.policy;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.vrv.vap.exportAndImport.word.NiceXWPFDocument;
import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.data.MiniTableRenderData;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午6:01:02 
* 类说明  简单表格没有加对应的样式
*/
public class SimpleTableRenderPolicy implements RenderPolicy {

    
    private MiniTableRenderPolicy miniTableRenderPolicy = new MiniTableRenderPolicy();

    @Override
    public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        NiceXWPFDocument doc = template.getXWPFDocument();
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();
        if (null == data) return;
        
        //兼容新的数据结构体
        if (data instanceof MiniTableRenderData){
            miniTableRenderPolicy.render(eleTemplate, data, template);
            return;
        }
        runTemplate.getRun().setText("", 0);
    }
}
