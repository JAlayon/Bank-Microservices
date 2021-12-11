package com.alayon.msbank.security.controller;

import com.alayon.msbank.security.cross.JwtTokenCross;
import com.alayon.msbank.security.dto.AuthRequest;
import com.alayon.msbank.security.dto.AuthResponse;
import com.alayon.msbank.security.model.AccessModel;
import com.alayon.msbank.security.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenCross jwtTokenCross;

    @GetMapping()
    public List<AccessModel> get() {
        return authService.getAccess();
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody AuthRequest request) throws Exception {
        logger.info("Post: UserName {} - Password {}", request.getUserName(), request.getPassword());

        if (!authService.validatedCredentials(request.getUserName(), request.getPassword())) {
            return new ResponseEntity<String>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
        }
        AuthResponse response = new AuthResponse(jwtTokenCross.generateToken(request), request.getUserName(), "1d");
        return ResponseEntity.ok(response);
    }
}
