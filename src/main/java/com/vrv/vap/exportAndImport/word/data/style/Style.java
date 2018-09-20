package com.vrv.vap.exportAndImport.word.data.style;

import lombok.Data;

/**
 * * 
 * 
 * @author wudi   
 * E‐mail:wudi@vrvmail.com.cn  
 * @version 创建时间：2018年9月19日 下午3:43:47
 * 类说明 样式说明
 */
public class Style {

	public Style() {
	}

	public Style(String color) {
		this.color = color;
	}

	public Style(String fontFamily, int fontSize) {
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}

	/**
	 * 文字颜色
	 */
	private String color;
	/**
	 * 字体
	 */
	private String fontFamily;
	/**
	 * 字体大小
	 */
	private int fontSize;
	/**
	 * 粗体
	 */
	private Boolean isBold;
	/**
	 * 斜体
	 */
	private Boolean isItalic;
	/**
	 * 删除线
	 */
	private Boolean isStrike;
	/**
	 * 下划线
	 */
	private Boolean isUnderLine;

	
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean isBold() {
        return isBold;
    }

    public void setBold(Boolean isBold) {
        this.isBold = isBold;
    }

    public Boolean isItalic() {
        return isItalic;
    }

    public void setItalic(Boolean isItalic) {
        this.isItalic = isItalic;
    }

    public Boolean isStrike() {
        return isStrike;
    }

    public void setStrike(Boolean isStrike) {
        this.isStrike = isStrike;
    }

    public Boolean isUnderLine() {
        return isUnderLine;
    }
	
	
}
