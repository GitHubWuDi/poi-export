package com.vrv.vap; 
/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月20日 下午4:05:00 
* 类说明 
*/

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.data.PictureRenderData;
import com.vrv.vap.model.NetSiteVO;

public class NetSiteTest {

	private NetSiteVO netSiteVO;
	
	
	//BufferedImage bufferImage;
	
	@Before
	public void init(){
		netSiteVO = new NetSiteVO();
		netSiteVO.setAccess_name("办公资产");
		netSiteVO.setAccess_use("用于办公");
		netSiteVO.setArea("江夏");
		netSiteVO.setCity("武汉");
		netSiteVO.setIp("127.0.0.1");
		netSiteVO.setPolice_name("wudi");
		netSiteVO.setPolice_tel("18995805092");
		netSiteVO.setPolice_type("警员");
		netSiteVO.setTerminal_count("10");
		netSiteVO.setUnit("公安局");
		netSiteVO.setEr("√");
//		bufferImage = BytePictureUtils.newBufferImage(100, 100);
//		Graphics2D g = (Graphics2D)bufferImage.getGraphics();
//		g.setColor(Color.red);
//		g.fillRect(0, 0, 100, 100);
//		g.dispose();
//		bufferImage.flush();
		netSiteVO.setPic(new PictureRenderData(120,120, "src/main/resources/image/picture.jpg"));
	} 
	
	@Test
	public void testWordTable() throws IOException{
		XWPFTemplate template = XWPFTemplate.compile("src/main/resources/word_template/福建省公安网边界接入（网中网）拆除表.docx").render(netSiteVO);
		FileOutputStream out = new FileOutputStream("福建省公安网边界接入（网中网）拆除表.docx");
		template.write(out);
		out.flush();
		out.close();
		template.close();
		
	}
	
	
	
	
}
