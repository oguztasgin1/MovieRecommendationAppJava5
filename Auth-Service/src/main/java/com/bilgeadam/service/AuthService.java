package com.bilgeadam.service;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.bilgeadam.dto.response.LoginResponseDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.exception.AuthException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.EStatus;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final IUserManager userManager;
    public AuthService(IAuthRepository repository, IUserManager userManager) {
        super(repository);
        this.repository = repository;
        this.userManager = userManager;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        try {
            Auth auth= IAuthMapper.INSTANCE.toAuth(dto);
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            userManager.createUser(IAuthMapper.INSTANCE.toNewCreateUserRequestDto(auth));
            return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        }catch (Exception e){

            throw new AuthException(ErrorType.USER_NOT_CREATED);
        }
    }

    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()) {
            throw new AuthException(ErrorType.USER_NOT_FOUND);
        }
        if (dto.getActivationCode().equals(auth.get().getActivationCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            save(auth.get());
            return true;
        } else {
            throw new AuthException(ErrorType.ACTIVATE_CODE_ERROR);

        }
    }

    public LoginResponseDto login (LoginRequestDto dto){
        Optional<Auth> auth=repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()){
            throw  new AuthException(ErrorType.LOGIN_ERROR);
        }
        if(!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw  new AuthException(ErrorType.LOGIN_ERROR_STATUS);
        }
        return IAuthMapper.INSTANCE.toLoginResponseDto(auth.get());

        }

    public Boolean updateUsernameOrEmail(UpdateByEmailOrUserNameRequestDto dto) {
        Optional<Auth> auth = repository.findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthException(ErrorType.USER_NOT_FOUND);
        }

        auth.get().setEmail(dto.getEmail());
        auth.get().setUsername(dto.getUsername());
        update(auth.get());
        return true;
    }
}
