package com.bilgeadam.service;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserException;
import com.bilgeadam.manager.AuthManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserProfileRepositroy;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.repository.enums.EStatus;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepositroy userProfileRepositroy;

    private final AuthManager authManager;

    public UserProfileService(IUserProfileRepositroy userProfileRepositroy, AuthManager authManager) {
        super(userProfileRepositroy);
        this.userProfileRepositroy = userProfileRepositroy;
        this.authManager = authManager;
    }

    public Boolean createUser(NewCreateUserRequestDto dto) {
        try {
            UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);

            save(userProfile);
            return  true;
        }catch (Exception e){
            throw  new UserException(ErrorType.USER_NOT_CREATED);
        }

    }

    public Boolean updateUser(UpdateRequestDto dto) {
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthid(dto.getAuthid());

        if (userProfile.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }

        if (!dto.getEmail().equals(userProfile.get().getEmail())||!dto.getUsername().equals(userProfile.get().getUsername())){
            userProfile.get().setUsername(dto.getUsername());
            userProfile.get().setEmail(dto.getEmail());
            authManager.updateByUsernameOrEmail(IUserMapper.INSTANCE.toUpdateByEmailOrUserNameRequestDto(dto));
        }
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAddress(dto.getAddress());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAvatar(dto.getAvatar());
        update(userProfile.get());
        return true;

    }

    public Boolean activateStatus(Long id) {
        Optional<UserProfile> userProfile = userProfileRepositroy.findOptionalByAuthid(id);
        if (userProfile.isEmpty()){
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;
    }
}