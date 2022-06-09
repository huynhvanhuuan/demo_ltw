package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;
import vn.edu.hcmuaf.fit.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.dto.product.ColorDto;
import vn.edu.hcmuaf.fit.dto.product.MaterialDto;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.CommonService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonServiceImpl implements CommonService {
    private final ColorDAO colorDAO;
    private final MaterialDAO materialDAO;
    private final ProvinceDAO provinceDAO;
    private final DistrictDAO districtDAO;
    private final WardDAO wardDAO;

    public CommonServiceImpl() {
        this.colorDAO = new ColorDAOImpl();
        this.materialDAO = new MaterialDAOImpl();
        this.provinceDAO = new ProvinceDAOImpl();
        this.districtDAO = new DistrictDAOImpl();
        this.wardDAO = new WardDAOImpl();
    }

    @Override
    public AppServiceResult<List<ColorDto>> getColors() {
        try {
            List<Color> colors = colorDAO.findAll();
            List<ColorDto> result = new ArrayList<>();

            colors.forEach(color -> result.add(ColorDto.createFromEntity(color)));

            return new AppServiceResult<>(true, 0, "Succeed!", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<ColorDto> getColor(Long id) {
        try {
            Color color = colorDAO.findById(id);

            if (color == null)
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Color id is not exist: " + id, null);

            return new AppServiceResult<>(true, 0, "Succeed!", ColorDto.createFromEntity(color));
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

            return new AppServiceResult<>(true, 0, "Succeed!", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<MaterialDto> getMaterial(Long id) {
        try {
            Material material = materialDAO.findById(id);

            if (material == null)
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Material id is not exist: " + id, null);

            return new AppServiceResult<>(true, 0, "Succeed!", MaterialDto.createFromEntity(material));
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

            return new AppServiceResult<>(true, 0, "Succeed!", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<ProvinceDto> getProvince(Long id) {
        try {
            Province province = provinceDAO.findById(id);

            if (province == null)
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Province id is not exist: " + id, null);

            return new AppServiceResult<>(true, 0, "Succeed!", ProvinceDto.createFromEntity(province));
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<List<DistrictDto>> getDistricts() {
        try {
            List<District> districts = districtDAO.findAll();
            List<DistrictDto> result = new ArrayList<>();

            districts.forEach(district -> result.add(DistrictDto.createFromEntity(district)));

            return new AppServiceResult<>(true, 0, "Succeed!", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<DistrictDto> getDistrict(Long id) {
        try {
            District district = districtDAO.findById(id);

            if (district == null)
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "District id is not exist: " + id, null);

            return new AppServiceResult<>(true, 0, "Succeed!", DistrictDto.createFromEntity(district));
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<List<WardDto>> getWards() {
        try {
            List<Ward> wards = wardDAO.findAll();
            List<WardDto> result = new ArrayList<>();

            wards.forEach(ward -> result.add(WardDto.createFromEntity(ward)));

            return new AppServiceResult<>(true, 0, "Succeed!", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<WardDto> getWard(Long id) {
        try {
            Ward ward = wardDAO.findById(id);

            if (ward == null)
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Ward id is not exist: " + id, null);

            return new AppServiceResult<>(true, 0, "Succeed!", WardDto.createFromEntity(ward));
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }
}
