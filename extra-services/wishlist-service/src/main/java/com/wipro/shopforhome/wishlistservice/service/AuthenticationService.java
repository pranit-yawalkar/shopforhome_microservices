package com.wipro.shopforhome.wishlistservice.service;


import com.wipro.shopforhome.wishlistservice.exception.AuthenticationFailException;
import com.wipro.shopforhome.wishlistservice.model.AuthenticationToken;
import com.wipro.shopforhome.wishlistservice.model.User;
import com.wipro.shopforhome.wishlistservice.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        this.tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return this.tokenRepository.findByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = this.tokenRepository.findByToken(token);
        if(authenticationToken==null) {
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) {
        if(token==null) {
            throw new AuthenticationFailException("Token not found");
        }

        if(getUser(token)==null) {
            throw new AuthenticationFailException("Invalid Token");
        }
    }
}
