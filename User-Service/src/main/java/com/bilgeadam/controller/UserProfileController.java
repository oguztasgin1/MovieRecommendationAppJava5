package com.bilgeadam.controller;

import static com.bilgeadam.constant.ApiUrls.*;


import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {
    private final UserProfileService userProfileService;


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));

    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateUser(dto));
    }

    @PostMapping(ACTIVATESTATUS + "/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid){
       return ResponseEntity.ok(userProfileService.activateStatus(authid));
    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus2(@RequestParam Long authid){
        return ResponseEntity.ok(userProfileService.activateStatus(authid));
    }

}