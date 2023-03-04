package com.bilgeadam.manager;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-userprofilee", decode404 = true, url = "http://localhost:8091/api/v1/user")
public interface IUserManager {

    // UserServise attımız istekleri yöneten bir interfaced olacak.
    // İsteğimizi Auth' dan User' a atacağız.
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);
}
