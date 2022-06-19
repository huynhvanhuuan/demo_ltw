package vn.edu.hcmuaf.fit.dto.role;

import vn.edu.hcmuaf.fit.entity.AppRole;

public class RoleDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleDto createFromEntity(AppRole src) {
        RoleDto dest = new RoleDto();

        dest.setId(src.getId());
        dest.setName(src.getName());

        return dest;
    }
}
