package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.entity.*;

import java.util.List;

public interface CommonService {
    AppServiceResult<List<Color>> getColors();

    AppServiceResult<List<Material>> getMaterials();

    AppServiceResult<List<Province>> getProvinces();

    AppServiceResult<List<District>> getDistricts();

    AppServiceResult<List<Ward>> getWards();
}
