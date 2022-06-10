package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.TrademarkDAO;
import vn.edu.hcmuaf.fit.dao.impl.AddressDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.TrademarkDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.trademark.*;
import vn.edu.hcmuaf.fit.entity.Trademark;
import vn.edu.hcmuaf.fit.service.TrademarkService;

import java.util.*;

public class TrademarkServiceImpl implements TrademarkService {
	private final TrademarkDAO trademarkDAO;

	public TrademarkServiceImpl() {
		this.trademarkDAO = new TrademarkDAOImpl();

		((TrademarkDAOImpl) trademarkDAO).setAddressDAO(new AddressDAOImpl());
	}

	@Override
	public AppServiceResult<List<TrademarkDto>> getTrademarks() {
		try {
			List<Trademark> trademarks = trademarkDAO.findAll();
			List<TrademarkDto> result = new ArrayList<>();

			trademarks.forEach(category -> result.add(TrademarkDto.createFromEntity(category)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<TrademarkDto> getTrademark(Long id) {
		try {
			Trademark trademark = trademarkDAO.findById(id);

			if (trademark == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Trademark id is not exist: " + id, null);

			return new AppServiceResult<>(true, 0, "Succeed!", TrademarkDto.createFromEntity(trademark));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<TrademarkDto> createTrademark(TrademarkCreate trademark) {
		try {
			Trademark newTrademark = new Trademark();

			if (trademark.getName() == null && trademark.getName().isEmpty()) {
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Name is required!", null);
			}

			List<Trademark> trademarks = trademarkDAO.findAll();
			for (Trademark t : trademarks) {
				if (t.getName().equals(trademark.getName())) {
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Name is exist!", null);
				}

				if (t.getWebsite().equals(trademark.getWebsite())) {
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Website is exist!", null);
				}
			}

			newTrademark.setId(0L);
			newTrademark.setName(trademark.getName());
			newTrademark.setWebsite(trademark.getWebsite());

			trademarkDAO.save(newTrademark);

			return new AppServiceResult<>(true, 0, "Succeed!", TrademarkDto.createFromEntity(newTrademark));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppBaseResult updateTrademark(TrademarkUpdate trademark) {
		try {
			Trademark updatedTrademark = trademarkDAO.findById(trademark.getId());

			if (updatedTrademark == null)
				return new AppBaseResult(false, AppError.Validation.errorCode(), "Trademark id is not exist: " + trademark.getId());

			if (trademark.getName() == null && trademark.getName().isEmpty())
				return new AppBaseResult(false, AppError.Validation.errorCode(), "Name is required!");

			List<Trademark> trademarks = trademarkDAO.findAll();
			for (Trademark t : trademarks) {
				if (t.getName().equals(trademark.getName()) && !Objects.equals(t.getId(), trademark.getId())) {
					return new AppBaseResult(false, AppError.Validation.errorCode(), "Name is exist!");
				}
			}

			updatedTrademark.setName(trademark.getName());
			updatedTrademark.setWebsite(trademark.getWebsite());

			trademarkDAO.save(updatedTrademark);

			return AppBaseResult.GenarateIsSucceed();
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}

	@Override
	public AppBaseResult deleteTrademark(Long id) {
		try {
			Trademark trademark = trademarkDAO.findById(id);

			if (trademark != null) {
				trademarkDAO.remove(id);
				return AppBaseResult.GenarateIsSucceed();
			} else {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Tradmark id is not exist: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}
}
