package batchJob.common.reader;

import java.util.List;

import com.tradevan.common.db.utils.BigDecimalUtils;

import batchJob.been.EventCRecvADo;
import batchJob.been.EventCRecvBDo;
import batchJob.been.EventCRecvCDo;
import batchJob.been.vo.cpg.EventCRecvVo;
import batchJob.common.xml.Book;
import batchJob.common.xml.ContextFactory;
import batchJob.common.xml.TxtBookData;
import batchJob.common.xml.XmlOut;
import batchJob.common.xml.impl.AbstractReaderComponent;

public class EcReader extends AbstractReaderComponent {
	
	public EcReader(final ContextFactory contextFactory) {
		super(contextFactory);
	}
	
	@Override
	protected XmlOut read(final Book context) {
		final TxtBookData bookData = (TxtBookData) context.read();

		final EventCRecvVo eventCRecvVo = this.read(bookData.getLines());
		return eventCRecvVo;
	}

	private EventCRecvVo read(final List<String> lines) {
		final EventCRecvVo eventCRecvVo = new EventCRecvVo();
		for (final String line : lines) {
			final String[] ec = line.split(",");
			if (ec.length < 3 || ec.length == 0) {
				continue;
			}
			final String type = ec[2];
			if (type.equals("A")) {
				final EventCRecvADo eventCRecvADo = new EventCRecvADo();
				eventCRecvADo.setPostspecialaccount(ec[0]); // 特約戶號
				eventCRecvADo.setPosttype(ec[1]); // 郵件類別
				eventCRecvADo.setType(ec[2]); // 格 式
				eventCRecvADo.setYear(ec[3]); // 年 度
				eventCRecvADo.setCountrycode(ec[4]); // 國名代碼
				eventCRecvADo.setExchangeagency(ec[5]); // 互換局名
				eventCRecvADo.setTotalpackageno(BigDecimalUtils.formObj(ec[6])); // 總包號碼
				eventCRecvADo.setGoodtotalpackageyear(ec[7]); // 貨總包年度
				eventCRecvADo.setGoodtotalpackageno(ec[8]); // 貨總包號碼
				eventCRecvADo.setPostmblno(ec[9]);// 貨提單號碼
				eventCRecvADo.setOldfilename(ec[10]);// 原接收檔名
				eventCRecvADo.setTakeoffflight(ec[11]);// 起飛班機
				eventCRecvADo.setSendtime(ec[12]);// 起飛時間
				eventCRecvADo.setSendtime(ec[13]);// 封發時間
				eventCRecvADo.setTotalweight(BigDecimalUtils.formObj(ec[14]));// 總件數
				eventCRecvADo.setTotalpagecount(BigDecimalUtils.formObj(ec[15]));// 總頁數
				eventCRecvADo.setTotalbagnumber(BigDecimalUtils.formObj(ec[16]));// 總袋數
				eventCRecvADo.setTotalweight(BigDecimalUtils.formObj(ec[17]));// 總重
																				// 量
				if (ec.length >= 19) {
					eventCRecvADo.setArrivaltime(ec[18]);// 預定到達時間
				}
				eventCRecvVo.getEventCRecvADos().add(eventCRecvADo);
			} else if (type.equals("B")) {
				final EventCRecvBDo eventCRecvBDo = new EventCRecvBDo();
				eventCRecvBDo.setPostspecialaccount(ec[0]); // 特約戶號
				eventCRecvBDo.setPosttype(ec[1]); // 郵件類別
				eventCRecvBDo.setType(ec[2]); // 格 式
				eventCRecvBDo.setYear(ec[3]); // 年 度
				eventCRecvBDo.setCountrycode(ec[4]); // 國名代碼
				eventCRecvBDo.setExchangeagency(ec[5]); // 互換局名
				eventCRecvBDo.setTotalpackageno(BigDecimalUtils.formObj(ec[6])); // 總包號碼
				eventCRecvBDo.setGoodtotalpackageyear(ec[7]); // 貨總包年度
				eventCRecvBDo.setGoodtotalpackageno(ec[8]); // 貨總包號碼
				eventCRecvBDo.setBagno(BigDecimalUtils.formObj(ec[9]));// 袋 序
				eventCRecvBDo.setLastmark(ec[10]);// 最後一袋
				eventCRecvBDo.setPageno(BigDecimalUtils.formObj(ec[11]));// 該袋頁數
				eventCRecvBDo.setQty(BigDecimalUtils.formObj(ec[12]));// 件 數
				eventCRecvBDo.setWeight(BigDecimalUtils.formObj(ec[13]));// 重 量
				eventCRecvBDo.setSealdatetime(ec[14]);// 封發時間
				eventCRecvBDo.setUpu(ec[15]);// UPU 29碼
				eventCRecvVo.getEventCRecvBDos().add(eventCRecvBDo);
			} else {
				final EventCRecvCDo eventCRecvCDo = new EventCRecvCDo();
				eventCRecvCDo.setPostspecialaccount(ec[0]); // 特約戶號
				eventCRecvCDo.setPosttype(ec[1]); // 郵件類別
				eventCRecvCDo.setType(ec[2]); // 格 式
				eventCRecvCDo.setYear(ec[3]); // 年 度
				eventCRecvCDo.setCountrycode(ec[4]); // 國名代碼
				eventCRecvCDo.setExchangeagency(ec[5]); // 互換局名
				eventCRecvCDo.setTotalpackageno(BigDecimalUtils.formObj(ec[6])); // 總包號碼
				eventCRecvCDo.setGoodtotalpackageyear(ec[7]); // 貨總包年度
				eventCRecvCDo.setGoodtotalpackageno(ec[8]); // 貨總包號碼
				eventCRecvCDo.setBagno(BigDecimalUtils.formObj(ec[9]));// 袋 序
				eventCRecvCDo.setPageno(BigDecimalUtils.formObj(ec[10]));// 頁 序
				eventCRecvCDo.setCellno(BigDecimalUtils.formObj(ec[11]));// 格 次
				eventCRecvCDo.setPostno(ec[12]);// 郵件號碼
				eventCRecvCDo.setWeight(BigDecimalUtils.formObj(ec[13]));// 重 量
				eventCRecvCDo.setSealdatetime(ec[14]);// 封發時間
				eventCRecvCDo.setProvincetype(ec[15]);// 省 別
				eventCRecvCDo.setBoxtype(ec[16]);// 箱 別
				eventCRecvVo.getEventCRecvCDos().add(eventCRecvCDo);
			}

		}
		return eventCRecvVo;
	}

}
