package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;

import java.util.List;

public interface AddressService {
    AppServiceResult<List<AddressDto>> getAddresses();
    AppServiceResult<List<AddressDto>> getAddressByTrademarkId(Long trademarkId);
	AppServiceResult<List<AddressDto>> getAddressByUserId(Long userId);
	AppServiceResult<AddressDto> getAddress(Long id);
	AppServiceResult<AddressDto> getAddressByPath(String path);
	AppServiceResult<AddressDto> createAddress(AddressCreate address);
	AppBaseResult updateAddress(AddressUpdate address);
	AppBaseResult deleteAddress(Long id);
}
