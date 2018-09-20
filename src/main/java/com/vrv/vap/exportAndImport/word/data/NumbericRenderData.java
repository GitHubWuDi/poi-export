package com.vrv.vap.exportAndImport.word.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat.Enum;

import com.vrv.vap.exportAndImport.word.data.style.Style;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:35:26 
* 类说明  列表模型
*/
@Data
public class NumbericRenderData implements RenderData {

	/**
     * 1. 2. 3.
     */
    public static final Pair<Enum, String> FMT_DECIMAL = Pair.of(STNumberFormat.DECIMAL, "%1.");
    /**
     * 1) 2) 3)
     */
    public static final Pair<Enum, String> FMT_DECIMAL_PARENTHESES = Pair.of(STNumberFormat.DECIMAL,
            "%1)");
    /**
     * ● ● ●
     */
    public static final Pair<Enum, String> FMT_BULLET = Pair.of(STNumberFormat.BULLET, "●");
    /**
     * a. b. c.
     */
    public static final Pair<Enum, String> FMT_LOWER_LETTER = Pair.of(STNumberFormat.LOWER_LETTER,
            "%1.");
    /**
     * i ⅱ ⅲ
     */
    public static final Pair<Enum, String> FMT_LOWER_ROMAN = Pair.of(STNumberFormat.LOWER_ROMAN,
            "%1.");
    /**
     * A. B. C.
     */
    public static final Pair<Enum, String> FMT_UPPER_LETTER = Pair.of(STNumberFormat.UPPER_LETTER,
            "%1.");
    /**
     * Ⅰ Ⅱ Ⅲ
     */
    public static final Pair<Enum, String> FMT_UPPER_ROMAN = Pair.of(STNumberFormat.UPPER_ROMAN,
            "%1.");
    // /**
    // * 一、 二、 三、
    // */
    // public static final Pair<Enum, String> FMT_CHINESE_COUNTING_THOUSAND =
    // Pair
    // .of(STNumberFormat.CHINESE_COUNTING_THOUSAND, "%1、");
    // /**
    // * (一) (二) (三)
    // */
    // public static final Pair<Enum, String>
    // FMT_CHINESE_COUNTING_THOUSAND_PARENTHESES = Pair
    // .of(STNumberFormat.CHINESE_COUNTING_THOUSAND, "(%1)");

    private List<TextRenderData> numbers;

    private Pair<Enum, String> numFmt;

    private Style fmtStyle;

    public NumbericRenderData(Pair<Enum, String> numFmt, List<TextRenderData> numbers) {
        this(numFmt, null, numbers);
    }

    /**
     * @param numFmt 编号字符
     * @param fmtStyle 编号样式
     * @param numbers 列表内容
     */
    public NumbericRenderData(Pair<Enum, String> numFmt, Style fmtStyle, List<TextRenderData> numbers) {
        this.numFmt = numFmt;
        this.numbers = numbers;
        this.fmtStyle = fmtStyle;
    }

    public NumbericRenderData(List<TextRenderData> numbers) {
        this(FMT_BULLET, numbers);
    }
    
    public static NumbericRenderData build(String... text) {
        if (null == text) return null;
        List<TextRenderData> numbers = new ArrayList<TextRenderData>();
        for (String txt : text) {
            numbers.add(new TextRenderData(txt));
        }
        return new NumbericRenderData(numbers);
    }
    public static NumbericRenderData build(TextRenderData... data) {
        return new NumbericRenderData(null == data ? null : Arrays.asList(data));
    }
	
}
