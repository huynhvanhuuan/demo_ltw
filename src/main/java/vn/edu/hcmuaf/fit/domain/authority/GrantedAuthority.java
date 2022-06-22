package vn.edu.hcmuaf.fit.domain.authority;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {
    String getAuthority();
}
