package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.bilgeadam.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;


    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }


    @PostMapping(ACTIVATESTATUS)
    public  ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){

        return   ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(ACTIVATESTATUS + "2")
    public  ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateRequestDto dto){

        return   ResponseEntity.ok(authService.activateStatus2(dto));
    }

    @PostMapping(LOGIN)
    public  ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));

    }

    @PutMapping (UPDATEBYUSERNAMEOREMAIL)
    ResponseEntity<Boolean> updateBuUsernameOrEmail(@RequestBody UpdateByEmailOrUserNameRequestDto dto){
        return ResponseEntity.ok((authService.updateUsernameOrEmail(dto)));
    }

    @GetMapping("/getRoleFromToken")
    public ResponseEntity<String> getRoleFromToken(String token){
        return ResponseEntity.ok(jwtTokenManager.getRoleFromToken(token).get());
    }

    @GetMapping("/getIdFromToken")
    public ResponseEntity<Long> getIdFromToken(String token){
        return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).get());
    }

}