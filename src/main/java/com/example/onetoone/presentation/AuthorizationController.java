package com.example.onetoone.presentation;

import com.example.onetoone.config.security.JwtTokenService;
import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final JwtTokenService jwtTokenService;


    @PostMapping("/jwt")
    public ResponseEntity<JwtTokenView> oneTimeAuthorization(@RequestBody UserAuthorizationRequest request) {
        log.info("Request to receive a jwt token");

        var result = jwtTokenService.authenticate(request);
        ResponseCookie jwtCookie = ResponseCookie
                .from("refresh-jwt", result.getRefreshToken())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(JwtTokenView.builder()
                        .jwtToken(result.getJwtToken())
                        .email(result.getEmail())
                        .id(result.getId())
                        .build());
    }

    @GetMapping("jwt/refresh")
    public ResponseEntity<JwtTokenView> refreshJwtToken(@RequestHeader("refresh-jwt") String refreshJwt) {
        log.info("Request to update a jwt token");

        var result = jwtTokenService.refreshAccessToken(refreshJwt);
        ResponseCookie jwtCookie = ResponseCookie
                .from("refresh-jwt", result.getRefreshToken())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(JwtTokenView.builder()
                        .jwtToken(result.getJwtToken())
                        .email(result.getEmail())
                        .id(result.getId())
                        .build());
    }
}
