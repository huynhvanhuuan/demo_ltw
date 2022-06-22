package vn.edu.hcmuaf.fit.service;

import java.util.concurrent.ExecutionException;

public interface LoginAttemptService {
    void addUserToLoginAttemptCache(String username);
    void evictUserFromLoginAttemptCache(String username);
    boolean hasExceededMaxAttempts(String username) throws ExecutionException;
}
