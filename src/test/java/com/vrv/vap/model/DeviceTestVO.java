package com.vrv.vap.model;

import com.vrv.vap.exportAndImport.pdf.annotation.PdfField;

import lombok.Data;

/** * 
* @author wudi 
* E‐mail:wudi@vrvmail.com.cn 
* @version 创建时间：2018年9月21日 下午4:04:11 
* 类说明 
*/
@Data
public class DeviceTestVO {
	@PdfField(name="applicantUnit")
	private String applicantUnit; 
	private String levelonedepartment;
	private String leveltwodepartment;
	@PdfField(name="isBasic")
	private String isBasic; //是否基层所队
	@PdfField(name="mainName")
	private String mainName;//责任人姓名
	@PdfField(name="mainCard")
	private String mainCard; //责任人身份证号
	private String isSelf;//是否本人使用
	private String userName;//使用人姓名
	private String userCard;//使用人身份证号
	private String telephone;//联系电话
	private String alarmBell;//警种
	private String depertment;
	private String depertmentid;
	private String ipAddress; //IP地 址
	private String ipGuid;//IPGuid
	private String deviceAddress; //设备所在地
	private String devicePurpose; //设备用途
	private String deviceType; //设备类型
	private String mac;
	private String twoLevelTicketNum; 
	private String dealAdvice;
	private String checkerAdvice;//工单中存储的审核建议字段,提供页面dealAdvice使用
	private String startTime; //开始时间
	private String endTime; //结束时间
	private String cancelDetail; //注销使用情况
	private String tmpDetail;//临时使用说明
	private String pkiUnit;//证书单位
	private String select; //选择情况  草稿（空值）  线上 线下
	private String isProject;//是否保护
}
