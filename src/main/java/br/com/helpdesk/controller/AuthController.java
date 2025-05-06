package br.com.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpdesk.security.JwtUtil;
import jakarta.validation.Valid;

/**
 * Controller responsável pelo endpoint de autenticação (/login).
 */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * DTO para requisição de login.
     */
    public static class LoginRequest {
        @Valid
        private String username;
        @Valid
        private String password;
        // getters e setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    /**
     * Autentica o usuário e gera um token JWT se as credenciais estiverem corretas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
