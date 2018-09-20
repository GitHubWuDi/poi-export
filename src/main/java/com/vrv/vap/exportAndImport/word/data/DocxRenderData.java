package com.vrv.vap.exportAndImport.word.data;

import java.io.File;
import java.util.List;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:14:52 
* 类说明    可以是另一个docx文档的合并，或者是数据集合针对同一个模板的不同渲染结果的合并
*/
@Data
public class DocxRenderData implements RenderData {
   
    /**
     * 合并文档
     */
    private File docx;

    /**
     * 渲染合并文档模板的数据集合，若合并文档不是个模板，可为空
     */
    private List<?> dataList;
    
    public DocxRenderData(File docx) {
        this.docx = docx;
    }

    public DocxRenderData(File docx, List<?> dataList) {
        this.docx = docx;
        this.dataList = dataList;
    }
    
}
