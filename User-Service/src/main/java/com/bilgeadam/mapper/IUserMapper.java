package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {


    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final NewCreateUserRequestDto dto);
    @Mapping(source = "authid" ,target = "id")
    UpdateByEmailOrUserNameRequestDto toUpdateByEmailOrUserNameRequestDto(final UpdateRequestDto dto);
    UserProfile toUserProfile(final UpdateRequestDto dto);
}