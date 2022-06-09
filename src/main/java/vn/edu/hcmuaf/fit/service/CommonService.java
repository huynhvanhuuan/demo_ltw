package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;
import vn.edu.hcmuaf.fit.dto.product.ColorDto;
import vn.edu.hcmuaf.fit.dto.product.MaterialDto;
import vn.edu.hcmuaf.fit.entity.*;

import java.util.List;

public interface CommonService {
    AppServiceResult<List<ColorDto>> getColors();
    AppServiceResult<ColorDto> getColor(Long id);

    AppServiceResult<List<MaterialDto>> getMaterials();
    AppServiceResult<MaterialDto> getMaterial(Long id);

    AppServiceResult<List<ProvinceDto>> getProvinces();
    AppServiceResult<ProvinceDto> getProvince(Long id);

    AppServiceResult<List<DistrictDto>> getDistricts();
    AppServiceResult<DistrictDto> getDistrict(Long id);

    AppServiceResult<List<WardDto>> getWards();
    AppServiceResult<WardDto> getWard(Long id);
}
