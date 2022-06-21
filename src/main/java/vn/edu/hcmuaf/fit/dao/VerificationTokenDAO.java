package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.VerificationToken;

public interface VerificationTokenDAO extends BaseDAO<VerificationToken> {
    VerificationToken findByUserId(Long userId);
}
