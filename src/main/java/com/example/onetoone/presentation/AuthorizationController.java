package com.example.onetoone.presentation;

import com.example.onetoone.presentation.request.UserAuthorizationRequest;
import com.example.onetoone.presentation.view.JwtTokenView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthorizationController {


    @PostMapping("/jwt")
    public ResponseEntity<JwtTokenView> oneTimeAuthorization(@RequestBody UserAuthorizationRequest request) {
        log.info("Request to receive a jwt token");

        return ResponseEntity.ok()
                .body(JwtTokenView.builder()
                        .jwtToken("TEST")
                        .email("maksim.ananev.1994@mail.ru")
                        .id(3L)
                        .build());
    }

    @GetMapping("jwt/refresh")
    public ResponseEntity<JwtTokenView> refreshJwtToken(@CookieValue("refresh-jwt") String refreshJwt) {
        log.info("Request to update a jwt token");

        return ResponseEntity.ok()
                .body(JwtTokenView.builder()
                        .jwtToken("TEST")
                        .email("maksim.ananev.1994@mail.ru")
                        .id(3L)
                        .build());
    }
}
