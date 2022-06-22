package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.VerificationToken;

import java.util.UUID;

public interface VerificationTokenDAO extends BaseDAO<VerificationToken> {
    VerificationToken findByUserId(Long userId);
    VerificationToken findByToken(UUID token);
}
