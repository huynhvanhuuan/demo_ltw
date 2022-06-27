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
		this.addressDAO = AddressDAOImpl.getInstance();
		this.districtDAO = DistrictDAOImpl.getInstance();
		this.wardDAO = WardDAOImpl.getInstance();

		((AddressDAOImpl) addressDAO).setDistrictDAO(districtDAO);
		((AddressDAOImpl) addressDAO).setWardDAO(wardDAO);

		((DistrictDAOImpl) districtDAO).setProvinceDAO(ProvinceDAOImpl.getInstance());
		((DistrictDAOImpl) districtDAO).setWardDAO(wardDAO);

		((WardDAOImpl) wardDAO).setDistrictDAO(districtDAO);
	}

	@Override
	public AppServiceResult<List<AddressDto>> getAddressByTrademarkId(Long trademarkId) {
		try {
			List<Address> entities = addressDAO.findByTrademarkId(trademarkId);

			List<AddressDto> result = new ArrayList<>();

			entities.forEach(entity -> result.add(AddressDto.createFromEntity(entity)));

			return new AppServiceResult<>(true, 0, "Success", result);
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

			return new AppServiceResult<>(true, 0, "Success", result);
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

			return new AppServiceResult<>(true, 0, "Success", AddressDto.createFromEntity(address));
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

			return new AppServiceResult<>(true, 0, "Success", AddressDto.createFromEntity(address));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<AddressDto> createAddressForTrademark(AddressCreate address) {
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

			List<Ward> wards = wardDAO.findByDistrictId(address.getDistrictId());
			if (wards.size() > 0) {
				if (address.getWardId() == 0) {
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Ward is required!", null);
				} else {
					if (address.getNumber() == null || address.getNumber().equals("")) {
						return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Number is required!", null);
					}

					if (address.getStreet() == null || address.getStreet().equals("")) {
						return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Street is required!", null);
					}
				}
			}

			Ward ward = wardDAO.findById(address.getWardId());

			String path = ward == null ? AddressUtil.formatAddressWithoutWard(address.getNumber(), address.getStreet(), district)
					: AddressUtil.formatAddress(address.getNumber(), address.getStreet(), ward, district);

			List<Address> addresses = addressDAO.findByTrademarkId(address.getId());
			for (Address item : addresses) {
				if (item.getPath().equals(path))
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Địa chỉ đã tồn tại", null);
			}

			newAddress.setId(0L);
			newAddress.setNumber(address.getNumber());
			newAddress.setStreet(address.getStreet());
			newAddress.setWard(ward);
			newAddress.setDistrict(district);
			newAddress.setPath(path);

			addressDAO.saveForTrademark(newAddress, address.getId());

			return new AppServiceResult<>(true,0,"Thêm địa chỉ thành công", AddressDto.createFromEntity(newAddress));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(),null);
		}
	}

	@Override
	public AppBaseResult createAddressForUser(AddressCreate address) {
		try {
			Address newAddress = new Address();

			if (address.getProvinceId() == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Province is required!");
			}

			if (address.getDistrictId() == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"District is required!");
			}

			District district = districtDAO.findById(address.getDistrictId());

			List<Ward> wards = wardDAO.findByDistrictId(address.getDistrictId());
			if (wards.size() > 0) {
				if (address.getWardId() == null) {
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Ward is required!");
				}

				if (address.getWardId() != 0) {
					if (address.getNumber() == null && address.getNumber().equals("")) {
						return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Number is required!");
					}

					if (address.getStreet() == null && address.getNumber().equals("")) {
						return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Street is required!");
					}
				}
			}

			Ward ward = wardDAO.findById(address.getWardId());

			String path = ward == null ? AddressUtil.formatAddressWithoutWard(address.getNumber(), address.getStreet(), district)
					: AddressUtil.formatAddress(address.getNumber(), address.getStreet(), ward, district);

			List<Address> addresses = addressDAO.findByUserId(address.getId());
			for (Address item : addresses) {
				if (item.getPath().equals(path))
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Địa chỉ đã tồn tại");
			}

			newAddress.setId(0L);
			newAddress.setNumber(address.getNumber());
			newAddress.setStreet(address.getStreet());
			newAddress.setWard(ward);
			newAddress.setDistrict(district);
			newAddress.setPath(path);

			addressDAO.saveForUser(newAddress, address.getId());

			return new AppBaseResult(true,0,"Thêm địa chỉ thành công");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(),"Không thể thêm địa chỉ");
		}
	}

	@Override
	public AppBaseResult updateAddress(AddressUpdate address) {
		try {
			Address updateAddress = addressDAO.findById(address.getAddressId());

			if (updateAddress == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Địa chỉ không tồn tại: " + address.getAddressId());
			}

			if (address.getProvinceId() == null)
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Province is required!");

			if (address.getDistrictId() == null)
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"District is required!");

			District district = districtDAO.findById(address.getDistrictId());

			List<Ward> wards = wardDAO.findByDistrictId(address.getDistrictId());
			if (wards.size() > 0) {
				if (address.getWardId() == null)
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Ward is required!");

				if (address.getWardId() != 0) {
					if (address.getNumber() == null)
						return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Number is required!");

					if (address.getStreet() == null)
						return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Street is required!");
				}
			}

			Ward ward = wardDAO.findById(address.getWardId());

			String path = ward == null ? AddressUtil.formatAddressWithoutWard(address.getNumber(), address.getStreet(), district)
					: AddressUtil.formatAddress(address.getNumber(), address.getStreet(), ward, district);

			List<Address> addresses = addressDAO.findByUserId(address.getId());
			for (Address item : addresses) {
				if (item.getPath().equalsIgnoreCase(path))
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Địa chỉ đã tồn tại");
			}

			updateAddress.setId(address.getAddressId());
			updateAddress.setNumber(address.getNumber());
			updateAddress.setStreet(address.getStreet());
			updateAddress.setWard(ward);
			updateAddress.setDistrict(district);
			updateAddress.setPath(path);
			updateAddress.setDefaultAddress(address.getDefaultAddress());

			addressDAO.save(updateAddress);

			return new AppBaseResult(true,0,"Cập nhật thành công");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(),"Cập nhật địa chỉ thất bại");
		}
	}

	@Override
	public AppBaseResult changeDefaultAddress(Long id, Long userId) {
		try {
			Address address = addressDAO.findById(id);

			if (address == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Địa chỉ không tồn tại: " + id);
			}

			List<Address> addresses = addressDAO.findByUserId(userId);

			for (Address item : addresses) {
				item.setDefaultAddress(false);
				addressDAO.save(item);
			}

			address.setDefaultAddress(true);
			addressDAO.save(address);

			return new AppBaseResult(true,0,"Đổi địa chỉ mặc định thành công");
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(),null);
		}
	}

	@Override
	public AppBaseResult deleteAddress(Long id) {
		try {
			Address address = addressDAO.findById(id);

			if (address != null) {
				addressDAO.remove(id);
				return new AppBaseResult(true, 0,"Xóa địa chỉ thành công");
			} else {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Id không tồn tại: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(),"Xóa địa chỉ thất bại");
		}
	}
}
