package vn.edu.hcmuaf.fit.dto.appuser;

import vn.edu.hcmuaf.fit.entity.AppRole;

public class AppRoleDto {
    private Long id;
    private String name;

    public static AppRoleDto createFromEntity(AppRole src) {
        AppRoleDto dest = new AppRoleDto();

        dest.id = src.getId();
        dest.name = src.getName();

        return dest;
    }
}
