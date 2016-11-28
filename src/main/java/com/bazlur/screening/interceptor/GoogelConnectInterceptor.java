package com.bazlur.screening.interceptor;

import com.bazlur.screening.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.google.api.Google;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class GoogelConnectInterceptor implements ConnectInterceptor<Google> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogelConnectInterceptor.class);
    private SignupService signupService;

    public GoogelConnectInterceptor(SignupService signupService) {
        this.signupService = signupService;
    }

    @Override
    public void preConnect(ConnectionFactory<Google> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
    }

    @Override
    public void postConnect(Connection<Google> connection, WebRequest request) {
        UserProfile userProfile = connection.fetchUserProfile();
        String displayName = connection.getDisplayName();

        String email = userProfile.getEmail();
        String id = userProfile.getId();
        String name = userProfile.getName();

        LOGGER.info("email:{}, id:{}, name:{}, displayName:{}", email, id, name, displayName);
    }
}
