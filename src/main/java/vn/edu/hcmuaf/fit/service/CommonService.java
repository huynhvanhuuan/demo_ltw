package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.ProvinceDto;
import vn.edu.hcmuaf.fit.dto.product.ColorDto;
import vn.edu.hcmuaf.fit.dto.product.MaterialDto;

import java.util.List;

public interface CommonService {
    AppServiceResult<List<ColorDto>> getColors();
    AppServiceResult<List<MaterialDto>> getMaterials();
    AppServiceResult<List<ProvinceDto>> getProvinces();
}
