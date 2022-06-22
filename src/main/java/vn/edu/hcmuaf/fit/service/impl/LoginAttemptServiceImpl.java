package vn.edu.hcmuaf.fit.service.impl;

import com.google.common.cache.*;
import vn.edu.hcmuaf.fit.service.LoginAttemptService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LoginAttemptServiceImpl implements LoginAttemptService {
    private LoadingCache<String, Integer> loginAttemptCache;
    private static final int ATTEMPTS_INCREMENT = 1;
    private static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 5;

    public LoginAttemptServiceImpl() {
        loginAttemptCache = CacheBuilder.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    @Override
    public void addUserToLoginAttemptCache(String username) {
        int attempts;
        try {
            attempts = loginAttemptCache.get(username) + ATTEMPTS_INCREMENT;
            loginAttemptCache.put(username, attempts);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // evict if you login successful
    @Override
    public void evictUserFromLoginAttemptCache(String username) {
        loginAttemptCache.invalidate(username);
    }

    @Override
    public boolean hasExceededMaxAttempts(String username) throws ExecutionException {
        return loginAttemptCache.get(username) >= MAXIMUM_NUMBER_OF_ATTEMPTS;
    }
}
