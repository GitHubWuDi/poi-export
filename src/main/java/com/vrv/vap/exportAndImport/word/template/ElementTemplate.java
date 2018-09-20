
package com.vrv.vap.exportAndImport.word.template;

/**
 * 对应的元素模板
 * @author wd-pc
 *
 */
public class ElementTemplate {
	protected Character sign;
	protected String tagName;
	protected String source;

	public ElementTemplate() {}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Character getSign() {
		return sign;
	}

	public void setSign(Character sign) {
		this.sign = sign;
	}
}
