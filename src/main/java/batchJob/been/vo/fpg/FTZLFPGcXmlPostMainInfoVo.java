package batchJob.been.vo.fpg;

import java.util.ArrayList;
import java.util.List;

public class FTZLFPGcXmlPostMainInfoVo {
	
	private String postSpecialAccount; // 特約戶號
	private String year; // 年度
	private String countryCode; // 國名代碼
	private String exchangeAgency; // 互換局名
	private String totalPackageNo; // 總包號碼
	private String sealDatetime; // 封發時間
	private String postMBLNo; // 提單號碼
	private String lastYearTotalPackageNo; // 去年最後總包
	private String totalQty; // 總件數
	private String totalPageCount; // 總頁數
	private String totalBagNumber; // 總袋數
	private String totalWeight; // 總重量
	private String otherFields; // 其他欄位
	private String postType; // 郵件類別
	private List<FTZLFPGcXmlBagVo> bags = new ArrayList<FTZLFPGcXmlBagVo>();

	public String getPostSpecialAccount() {
		return postSpecialAccount;
	}

	public void setPostSpecialAccount(String postSpecialAccount) {
		this.postSpecialAccount = postSpecialAccount;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getExchangeAgency() {
		return exchangeAgency;
	}

	public void setExchangeAgency(String exchangeAgency) {
		this.exchangeAgency = exchangeAgency;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getTotalPackageNo() {
		return totalPackageNo;
	}

	public void setTotalPackageNo(String totalPackageNo) {
		this.totalPackageNo = totalPackageNo;
	}

	public String getSealDatetime() {
		return sealDatetime;
	}

	public void setSealDatetime(String sealDatetime) {
		this.sealDatetime = sealDatetime;
	}

	public String getPostMBLNo() {
		return postMBLNo;
	}

	public void setPostMBLNo(String postMBLNo) {
		this.postMBLNo = postMBLNo;
	}

	public String getLastYearTotalPackageNo() {
		return lastYearTotalPackageNo;
	}

	public void setLastYearTotalPackageNo(String lastYearTotalPackageNo) {
		this.lastYearTotalPackageNo = lastYearTotalPackageNo;
	}

	public String getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	public String getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(String totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public String getTotalBagNumber() {
		return totalBagNumber;
	}

	public void setTotalBagNumber(String totalBagNumber) {
		this.totalBagNumber = totalBagNumber;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getOtherFields() {
		return otherFields;
	}

	public void setOtherFields(String otherFields) {
		this.otherFields = otherFields;
	}

	public List<FTZLFPGcXmlBagVo> getBags() {
		return bags;
	}

	public void setBags(List<FTZLFPGcXmlBagVo> bags) {
		this.bags = bags;
	}

}
