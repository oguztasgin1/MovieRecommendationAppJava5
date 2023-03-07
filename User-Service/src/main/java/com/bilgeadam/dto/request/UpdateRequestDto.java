package com.bilgeadam.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateRequestDto {
    Long authid;
    @NotBlank
    @Size
    String username;
    @Email
    String email;
    String avatar;
    String password;
    String phone;
    String address;
    String about;
}
