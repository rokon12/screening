package com.bazlur.screening.interceptor;

import com.bazlur.screening.dto.FacebookDTO;
import com.bazlur.screening.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Bazlur Rahman Rokon
 * @since 11/28/16.
 */
public class FacebookConnectInterceptor implements ConnectInterceptor<Facebook> {
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookConnectInterceptor.class);

	private SignupService signupService;

	public FacebookConnectInterceptor(SignupService signupService) {
		this.signupService = signupService;
	}

	@Override
	public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
	}

	@Override
	public void postConnect(Connection<Facebook> connection, WebRequest request) {
		UserProfile userProfile = connection.fetchUserProfile();

		FacebookDTO facebookDTO = FacebookDTO.builder()
			.firstName(userProfile.getFirstName())
			.lastName(userProfile.getLastName())
			.username(userProfile.getUsername())
			.displayName(connection.getDisplayName())
			.email(userProfile.getEmail())
			.id(extractId(connection.getProfileUrl()))
			.imageURL(getImageUrl(connection, 285, 285))
			.build();

		signupService.loginOrCreateFacebookUser(facebookDTO);
	}

	private String getImageUrl(Connection<Facebook> connection, int height, int width) {
		return connection.getImageUrl() + "?height=x&width=y".replaceAll("x", Integer.toString(height)).replace("y", Integer.toString(width));
	}

	private String extractId(String profileUrl) {
		profileUrl = profileUrl.replace("https://www.facebook.com/app_scoped_user_id/", "");
		profileUrl = profileUrl.replace("/", "");
		return profileUrl;
	}
}
