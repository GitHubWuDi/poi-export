package com.vrv.vap.exportAndImport.word.data;

import com.vrv.vap.exportAndImport.util.BytePictureUtils;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月19日 下午4:43:37 
* 类说明 
*/
@Data
public class PictureRenderData implements RenderData {

	/**
	 * 图片宽度
	 */
	private int width;
	/**
	 * 图片高度
	 */
	private int height;
	/**
	 * 图片路径
	 */
	private String path;

	/**
	 * 图片二进制数据
	 */
	private transient byte[] data;
	
	/**
	 * @param width 宽度
	 * @param height 高度
	 * @param path  本地图片路径
	 */
	public PictureRenderData(int width, int height, String path) {
		this.width = width;
		this.height = height;
		this.path = path;
	}

	/**
	 * @param width 宽度
	 * @param height 高度
	 * @param path 标识图片后缀，如.png、.jpg等
	 * @param data 图片byte[]数据，可以通过工具类{@link BytePictureUtils}生成
	 */
	public PictureRenderData(int width, int height, String path, byte[] data) {
		this.width = width;
		this.height = height;
		this.path = path;
		this.data = data;
	}
	
}
