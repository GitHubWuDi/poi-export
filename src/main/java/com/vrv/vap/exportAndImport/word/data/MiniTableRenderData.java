package com.vrv.vap.exportAndImport.word.data;

import java.util.List;

import com.vrv.vap.exportAndImport.word.data.style.TableStyle;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:28:52 
* 类说明 
*/
@Data
public class MiniTableRenderData implements RenderData {

	/**
	 * 通用边距的表格宽度：A4(20.99*29.6),页边距为3.17*2.54
	 */
	public static final float WIDTH_A4_FULL = 14.65f;
	/**
	 * 窄边距的表格宽度：A4(20.99*29.6),页边距为1.27*1.27
	 */
	public static final float WIDTH_A4_NARROW_FULL = 18.45f;
	/**
	 * 适中边距的表格宽度：A4(20.99*29.6),页边距为1.91*2.54
	 */
	public static final float WIDTH_A4_MEDIUM_FULL = 17.17f;
	/**
	 * 宽边距的表格宽度：A4(20.99*29.6),页边距为5.08*2.54
	 */
	public static final float WIDTH_A4_EXTEND_FULL = 10.83f;
	
	private RowRenderData headers;
	private List<RowRenderData> datas;
	private String noDatadesc;
    private TableStyle style;
	
    
    /**
     * 最大宽度为：页面宽度-页边距宽度*2
     * 单位：cm
     */
    private float width;
	
    public MiniTableRenderData(List<RowRenderData> datas) {
        this(null, datas);
    }
    
    public MiniTableRenderData(RowRenderData headers, List<RowRenderData> datas) {
        this(headers, datas, WIDTH_A4_FULL);
    }
    
    public MiniTableRenderData(RowRenderData headers, List<RowRenderData> datas, float width) {
        this(headers, datas, null, width);
    }
    
    public MiniTableRenderData(RowRenderData headers, String noDatadesc) {
        this(headers, null, noDatadesc, WIDTH_A4_FULL);
    }
    

    /**
     * @param headers
     *            表格头
     * @param datas
     *            表格数据
     * @param noDatadesc
     *            没有数据显示的文案
     * @param width
     *            宽度
     */
    public MiniTableRenderData(RowRenderData headers, List<RowRenderData> datas,
            String noDatadesc, float width) {
        this.headers = headers;
        this.datas = datas;
        this.noDatadesc = noDatadesc;
        this.width = width;
    }
    
    
    public boolean isSetHeader(){
        return null != headers && headers.size() > 0;
    }
    public boolean isSetBody(){
        return null != datas && datas.size() > 0;
    }
    
    
}
