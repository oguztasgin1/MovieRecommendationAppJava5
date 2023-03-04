package com.bilgeadam.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewCreateUserRequestDto {
    Long authid;
    Long username;
    Long email;
}
