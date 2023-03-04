package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ERole;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDto {
    Long id;
    String username;
    String email;
    ERole role;
}
