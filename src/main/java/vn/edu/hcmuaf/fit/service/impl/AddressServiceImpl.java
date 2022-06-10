package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.AddressService;
import vn.edu.hcmuaf.fit.util.AddressUtil;

import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
	private final AddressDAO addressDAO;
	private final DistrictDAO districtDAO;
	private final WardDAO wardDAO;
	
	public AddressServiceImpl() {

		this.addressDAO = new AddressDAOImpl();
		this.districtDAO = new DistrictDAOImpl();
		this.wardDAO = new WardDAOImpl();

		((AddressDAOImpl) addressDAO).setDistrictDAO(districtDAO);
		((AddressDAOImpl) addressDAO).setWardDAO(wardDAO);

		((DistrictDAOImpl) districtDAO).setProvinceDAO(new ProvinceDAOImpl());
		((DistrictDAOImpl) districtDAO).setWardDAO(wardDAO);
		((WardDAOImpl) wardDAO).setDistrictDAO(districtDAO);
	}

	@Override
	public AppServiceResult<List<AddressDto>> getAddresses() {
		try {
			List<Address> entities = addressDAO.findAll();

			List<AddressDto> result = new ArrayList<>();

			entities.forEach(entity -> result.add(AddressDto.createFromEntity(entity)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<List<AddressDto>> getAddressByTrademarkId(Long trademarkId) {
		try {
			List<Address> entities = addressDAO.findByTrademarkId(trademarkId);

			List<AddressDto> result = new ArrayList<>();

			entities.forEach(entity -> result.add(AddressDto.createFromEntity(entity)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<List<AddressDto>> getAddressByUserId(Long userId) {
		try {
			List<Address> entities = addressDAO.findByUserId(userId);

			List<AddressDto> result = new ArrayList<>();

			entities.forEach(entity -> result.add(AddressDto.createFromEntity(entity)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<AddressDto> getAddress(Long id) {
		try {
			Address address = addressDAO.findById(id);

			if (address == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Address id is not exist: " + id, null);

			return new AppServiceResult<>(true, 0, "Succeed!", AddressDto.createFromEntity(address));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<AddressDto> getAddressByPath(String path) {
		try {
			Address address = addressDAO.findByPath(path);

			if (address == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Address path is not exist: " + path, null);

			return new AppServiceResult<>(true, 0, "Succeed!", AddressDto.createFromEntity(address));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<AddressDto> createAddress(AddressCreate address) {
		try {
			Address newAddress = new Address();

			if (address.getProvinceId() == null) {
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Province is required!", null);
			}

			if (address.getDistrictId() == null) {
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"District is required!", null);
			}

			District district = districtDAO.findById(address.getDistrictId());

			if (district.getWards().size() > 0) {
				if (address.getWardId() == null) {
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Ward is required!", null);
				}

				if (address.getWardId() != 0) {
					if (address.getNumber() == null && address.getNumber().equals("")) {
						return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Number is required!", null);
					}

					if (address.getStreet() == null && address.getNumber().equals("")) {
						return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Street is required!", null);
					}
				}
			}

			Ward ward = wardDAO.findById(address.getWardId());

			String path = ward == null ? AddressUtil.formatAddressWithoutWard(address.getNumber(), address.getStreet(), district)
					: AddressUtil.formatAddress(address.getNumber(), address.getStreet(), ward, district);

			List<Address> addresses = addressDAO.findAll();
			for (Address item : addresses) {
				if (item.getPath().equals(path))
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Address is existed", null);
			}

			newAddress.setId(0L);
			newAddress.setNumber(address.getNumber());
			newAddress.setStreet(address.getStreet());
			newAddress.setWard(ward);
			newAddress.setDistrict(district);
			newAddress.setPath(path);

			addressDAO.save(newAddress);

			return new AppServiceResult<>(true, 0, "Succeed!", AddressDto.createFromEntity(newAddress));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppBaseResult updateAddress(AddressUpdate address) {
		try {
			Address updateAddress = addressDAO.findById(address.getId());

			if (updateAddress == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Address id is not exist: " + address.getId());
			}

			if (address.getProvinceId() == null)
				return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(),
						"Province is required!", null);

			if (address.getDistrictId() == null)
				return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(),
						"District is required!", null);

			District district = districtDAO.findById(address.getDistrictId());

			if (district.getWards().size() > 0) {
				if (address.getWardId() == null)
					return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(), "Ward is required!", null);

				if (address.getWardId() != 0) {
					if (address.getNumber() == null)
						return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(), "Number is required!", null);

					if (address.getStreet() == null)
						return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(), "Street is required!", null);
				}
			}

			Ward ward = wardDAO.findById(address.getWardId());

			String path = ward == null ? AddressUtil.formatAddressWithoutWard(address.getNumber(), address.getStreet(), district)
					: AddressUtil.formatAddress(address.getNumber(), address.getStreet(), ward, district);

			List<Address> addresses = addressDAO.findAll();
			for (Address item : addresses) {
				if (item.getPath().equals(path))
					return new AppServiceResult<AddressDto>(false, AppError.Validation.errorCode(), "Address is existed", null);
			}

			updateAddress.setId(address.getId());
			updateAddress.setNumber(address.getNumber());
			updateAddress.setStreet(address.getStreet());
			updateAddress.setWard(ward);
			updateAddress.setDistrict(district);
			updateAddress.setPath(path);

			addressDAO.save(updateAddress);

			return new AppServiceResult<>(true, 0, "Succeed!", AddressDto.createFromEntity(updateAddress));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<AddressDto>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppBaseResult deleteAddress(Long id) {
		try {
			Address address = addressDAO.findById(id);

			if (address != null) {
				addressDAO.remove(id);
				return AppBaseResult.GenarateIsSucceed();
			} else {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Address id is not exist: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}
}
