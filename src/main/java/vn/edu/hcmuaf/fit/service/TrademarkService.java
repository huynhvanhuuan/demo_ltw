package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.trademark.*;

import java.util.List;

public interface TrademarkService {
	AppServiceResult<List<TrademarkDto>> getTrademarks();
	AppServiceResult<TrademarkDto> getTrademark(Long id);
	AppServiceResult<TrademarkDto> createTrademark(TrademarkCreate trademark);
	AppBaseResult updateTrademark(TrademarkUpdate trademark);
	AppBaseResult deleteTrademark(Long id);
}
