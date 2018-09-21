package com.vrv.vap.exportAndImport.word.policy;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.NumberingWrapper;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat.Enum;

import com.vrv.vap.exportAndImport.util.StyleUtils;
import com.vrv.vap.exportAndImport.word.NiceXWPFDocument;
import com.vrv.vap.exportAndImport.word.XWPFTemplate;
import com.vrv.vap.exportAndImport.word.data.NumbericRenderData;
import com.vrv.vap.exportAndImport.word.data.TextRenderData;
import com.vrv.vap.exportAndImport.word.data.style.Style;
import com.vrv.vap.exportAndImport.word.template.ElementTemplate;
import com.vrv.vap.exportAndImport.word.template.RunTemplate;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午5:44:54 
* 类说明  列表展示类
*/
public class NumbericRenderPolicy implements RenderPolicy {

	@Override
	public void render(ElementTemplate eleTemplate, Object data, XWPFTemplate template) {
        NiceXWPFDocument doc = template.getXWPFDocument();
        RunTemplate runTemplate = (RunTemplate) eleTemplate;
        XWPFRun run = runTemplate.getRun();
        if (null == data) return;

        NumbericRenderData numbericData = (NumbericRenderData) data;
        List<TextRenderData> datas = numbericData.getNumbers();
        Pair<Enum, String> numFmt = numbericData.getNumFmt();
        Style fmtStyle = numbericData.getFmtStyle();
        if (datas == null || datas.isEmpty()) {
            run.setText("", 0);
            return;
        } else {

            XWPFNumbering numbering = doc.getNumbering();
            if (null == numbering) {
                numbering = doc.createNumbering();
            }

            NumberingWrapper numberingWrapper = new NumberingWrapper(numbering);
            CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
            // if we have an existing document, we must determine the next
            // free number first.
            cTAbstractNum.setAbstractNumId(
                    BigInteger.valueOf(numberingWrapper.getAbstractNumsSize() + 10));

            Enum fmt = numFmt.getLeft();
            String val = numFmt.getRight();
            CTLvl cTLvl = cTAbstractNum.addNewLvl();
            cTLvl.addNewNumFmt().setVal(fmt);
            cTLvl.addNewLvlText().setVal(val);
            cTLvl.addNewStart().setVal(BigInteger.valueOf(1));
            cTLvl.setIlvl(BigInteger.valueOf(0));
            if (fmt == STNumberFormat.BULLET) {
                cTLvl.addNewLvlJc().setVal(STJc.LEFT);
            } else {
                // cTLvl.setIlvl(BigInteger.valueOf(0));
            }

            XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
            BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);

            BigInteger numID = numbering.addNum(abstractNumID);
            // doc.insertNewParagraph(run);
            XWPFRun newRun;
            for (TextRenderData line : datas) {
                XWPFParagraph paragraph = doc.insertNewParagraph(run);
                paragraph.setNumID(numID);
                CTP ctp = paragraph.getCTP();
                CTPPr pPr = ctp.isSetPPr() ? ctp.getPPr() : ctp.addNewPPr();
                CTParaRPr pr = pPr.isSetRPr() ? pPr.getRPr() : pPr.addNewRPr();
                StyleUtils.styleRpr(pr, fmtStyle);
                newRun = paragraph.createRun();
                StyleUtils.styleRun(newRun, line.getStyle());
                newRun.setText(line.getText());
            }
            // doc.insertNewParagraph(run);
        }
        run.setText("", 0);
        IRunBody parent = run.getParent();
        if (parent instanceof XWPFParagraph) {
            ((XWPFParagraph) parent).removeRun(runTemplate.getRunPos());
            // To do: 更好的列表样式
            // ((XWPFParagraph) parent).setSpacingBetween(0,
            // LineSpacingRule.AUTO);
        }
    		
	}

}
