package com.bilgeadam.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateByEmailOrUserNameRequestDto {
    Long id;
    String username;
    String email;
}
