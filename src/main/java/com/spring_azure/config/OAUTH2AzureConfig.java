package com.spring_azure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OAUTH2AzureConfig {
	
//	@Value("${spring.security.oauth2.client.provider.azure.authorization-uri}")
//	private String authorizationURI;

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		    .antMatchers("/oauth2/**", "/login/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.oauth2Login()
			.defaultSuccessUrl("http://localhost:8080/welcome");
		return http.build();
	}
	
	@Bean
	ClientRegistrationRepository clientRepository() {
		ClientRegistration clientReg = clientRegistration();
		return new InMemoryClientRegistrationRepository(clientReg);
	}

	private ClientRegistration clientRegistration() {
		return ClientRegistration.withRegistrationId("azure")
				.clientId("7db2e2c1-a094-4e25-be0b-36ca18094c9c")
				.clientSecret("5928Q~1aMVNZlk3zubTMf1S1H4-y3R-uaDXYnc~s")
	            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
	            .scope("openid")
	            .authorizationUri("https://login.microsoftonline.com/common/oauth2/v2.0/authorize")
	            .tokenUri("https://login.microsoftonline.com/common/oauth2/v2.0/token")
	            .userInfoUri("https://graph.microsoft.com/oidc/userinfo")
	            .jwkSetUri("https://login.microsoftonline.com/common/discovery/v2.0/keys")
	            .clientName("Azure")
	            .build();
	 }
}