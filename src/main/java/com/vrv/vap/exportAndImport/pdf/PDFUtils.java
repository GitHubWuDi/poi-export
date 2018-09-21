package com.vrv.vap.exportAndImport.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.vrv.vap.exportAndImport.pdf.annotation.PdfField;

/**
 * pdf导出工具工具类
 * @author wd-pc
 *
 */
public class PDFUtils {
      
	
	private static Logger logger = Logger.getLogger(PDFUtils.class);
	
	private static final String pdf_font = "STSong-Light";
	
	private static final String pdf_format = "UniGB-UCS2-H";
	
	
	private static volatile PDFUtils pdfUtils;
	
	
	private PDFUtils() {}

	
	/**
	 * 双重锁单例
	 * @return
	 */
	public PDFUtils getInstance() {
		if (pdfUtils == null) {
			synchronized (PDFUtils.class) {
				if (null == pdfUtils) {
					pdfUtils = new PDFUtils();
				}
			}
		}
		return pdfUtils;
	}
	
	
	/**
	 * 有模板无对象生成Pdf
	 * @param templatePath //模板路径
	 * @param map //传输参数
	 * @param outFilePath //输出路径
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public void createPdf(String templatePath,Map<String,Object> map,String outFilePath) throws IOException, DocumentException{
		PdfReader reader = new PdfReader(templatePath);
		createPdfByMap(map, outFilePath, reader);  
	}
	
	/**
	 * 有模板无对象生成Pdf
	 * @param inputStream
	 * @param map
	 * @param outFilePath
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPdf(InputStream inputStream,Map<String,Object> map,String outFilePath)throws IOException, DocumentException{
		PdfReader reader = new PdfReader(inputStream);
		createPdfByMap(map, outFilePath, reader);  
	}


	private void createPdfByMap(Map<String, Object> map, String outFilePath, PdfReader reader)
			throws DocumentException, IOException, FileNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper ps = new PdfStamper(reader, bos);
		BaseFont bf =BaseFont.createFont(pdf_font,pdf_format,BaseFont.NOT_EMBEDDED); 
		AcroFields af = ps.getAcroFields();
		af.addSubstitutionFont(bf);
		for (Map.Entry<String, Object> entry:map.entrySet()){
			af.setField(entry.getKey(), entry.getValue().toString());
		}
		ps.setFormFlattening(true);
		ps.close();
		FileOutputStream out = new FileOutputStream(outFilePath);
		out.write(bos.toByteArray());  
		out.close();
	}
	
	
	public void createPdf(String templatePath,Object obj,String outFilePath){
		try {
			PdfReader reader = new PdfReader(templatePath);
			createPdfByObj(reader, obj, outFilePath);
		} catch (IOException | IllegalArgumentException | IllegalAccessException | DocumentException t) {
			logger.info("生成pdf", t);
		}
	}
	
	public void createPdf(InputStream inputStream,Object obj,String outFilePath){
		try {
			PdfReader reader = new PdfReader(inputStream);
			createPdfByObj(reader, obj, outFilePath);
		} catch (IOException | IllegalArgumentException | IllegalAccessException | DocumentException t) {
			logger.info("生成pdf", t);
		}
	}
	
	
	/**
	 * 通过对象完成完成pdf的产生
	 * @param templatePath
	 * @param data
	 * @param outFilePath
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void createPdfByObj(PdfReader reader,Object data,String outFilePath) throws IOException, DocumentException, IllegalArgumentException, IllegalAccessException{
		String key = null;
		String value = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper ps = new PdfStamper(reader, bos);
		BaseFont bf =BaseFont.createFont(pdf_font,pdf_format,BaseFont.NOT_EMBEDDED); 
		AcroFields af = ps.getAcroFields();
		af.addSubstitutionFont(bf);
		Field[] fields = data.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			PdfField pdfField = field.getAnnotation(PdfField.class);
			if(pdfField!=null){
				key = pdfField.name();
			}else{
				key = field.getName();
			}
			value = (String) field.get(data);
			af.setField(key, value);
		}
		ps.setFormFlattening(true);
		ps.close();
		FileOutputStream out = new FileOutputStream(outFilePath);
		out.write(bos.toByteArray());  
		out.close();
	}
	
	
}
