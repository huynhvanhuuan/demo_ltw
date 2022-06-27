package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.ProvinceDto;
import vn.edu.hcmuaf.fit.dto.product.ColorDto;
import vn.edu.hcmuaf.fit.dto.product.MaterialDto;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.CommonService;

import java.util.ArrayList;
import java.util.List;

public class CommonServiceImpl implements CommonService {
    private final ColorDAO colorDAO;
    private final MaterialDAO materialDAO;
    private final ProvinceDAO provinceDAO;

    public CommonServiceImpl() {
        colorDAO = ColorDAOImpl.getInstance();
        materialDAO = MaterialDAOImpl.getInstance();
        provinceDAO = ProvinceDAOImpl.getInstance();

        ((ProvinceDAOImpl) provinceDAO).setDistrictDAO(DistrictDAOImpl.getInstance());
    }

    @Override
    public AppServiceResult<List<ColorDto>> getColors() {
        try {
            List<Color> colors = colorDAO.findAll();
            List<ColorDto> result = new ArrayList<>();

            colors.forEach(color -> result.add(ColorDto.createFromEntity(color)));

            return new AppServiceResult<>(true, 0, "Success", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<List<MaterialDto>> getMaterials() {
        try {
            List<Material> materials = materialDAO.findAll();
            List<MaterialDto> result = new ArrayList<>();

            materials.forEach(material -> result.add(MaterialDto.createFromEntity(material)));

            return new AppServiceResult<>(true, 0, "Success", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<List<ProvinceDto>> getProvinces() {
        try {
            List<Province> provinces = provinceDAO.findAll();
            List<ProvinceDto> result = new ArrayList<>();

            provinces.forEach(province -> result.add(ProvinceDto.createFromEntity(province)));

            return new AppServiceResult<>(true, 0, "Success", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }
}
