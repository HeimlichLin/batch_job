package batchJob.been.vo.fpg;

import java.util.ArrayList;
import java.util.List;

public class FTZLFPGcXmlBagVo {

	private String fieldName;// 欄位名稱
	private String fieldValue;// 欄位值
	private String bagNo; // 袋序
	private String lastMark; // 最後一袋
	private String pageNo; // 該袋頁數
	private String qty; // 件數
	private String weight; // 重量
	private String otherFields; // 其他欄位
	private List<FTZLFPGcXmlItemVo> items = new ArrayList<FTZLFPGcXmlItemVo>();

	public String getBagNo() {
		return bagNo;
	}

	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}

	public String getLastMark() {
		return lastMark;
	}

	public void setLastMark(String lastMark) {
		this.lastMark = lastMark;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getOtherFields() {
		return otherFields;
	}

	public void setOtherFields(String otherFields) {
		this.otherFields = otherFields;
	}

	public List<FTZLFPGcXmlItemVo> getItems() {
		return items;
	}

	public void setItems(List<FTZLFPGcXmlItemVo> items) {
		this.items = items;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
