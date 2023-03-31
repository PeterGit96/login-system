package co.develhope.loginsystem.security;

import co.develhope.loginsystem.auth.services.LoginService;
import co.develhope.loginsystem.user.entities.User;
import co.develhope.loginsystem.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get authorization header and validate

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate

        final String token;
        try {
            token = header.split("\\s")[1].trim();
        } catch(JWTVerificationException e) {
            filterChain.doFilter(request, response);
            return;
        }

        DecodedJWT decoded;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(LoginService.JWT_SECRET)).withIssuer("login-system").build();
            decoded = verifier.verify(token);
        } catch(JWTVerificationException ex) {
            filterChain.doFilter(request, response);
            return;
        }

        if(decoded == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context

        Optional<User> userDetails = userRepository.findById(decoded.getClaim("id").asLong());
        if(userDetails.isEmpty() || !userDetails.get().isActive()) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = userDetails.get();
        user.setPassword(null);
        user.setActivationCode(null);
        user.setPasswordResetCode(null);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, List.of());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }

}
