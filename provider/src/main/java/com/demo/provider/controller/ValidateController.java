package com.demo.provider.controller;


import com.demo.common.HttpUtils.constant.status.BaseStatusCode;
import com.demo.common.HttpUtils.constant.status.IStatusCode;
import com.demo.common.HttpUtils.constant.target.ResponseDataBody;
import com.demo.common.HttpUtils.entity.request.UserRequestDto;
import com.demo.common.HttpUtils.entity.response.UserResponseDto;
import com.demo.common.HttpUtils.validate.group.UserRequestDtoAddValidate;
import com.demo.common.HttpUtils.validate.group.UserRequestDtoUpdateValidate;
import com.demo.provider.validate.group.UserRequestDtoSimpleValidate;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/8
 * Time: 14:55
 * Description: No Description
 */
@RestController
@RequestMapping("/validate")
@Slf4j
@ResponseDataBody
public class ValidateController {

    @PostMapping("simple")
    public IStatusCode simple(@Validated(UserRequestDtoSimpleValidate.class) @RequestBody UserRequestDto userRequestDto){
        log.info("userRequestDto: {}",userRequestDto);
//        ValidateUtil.beanValidate(userRequestDto);
        return BaseStatusCode.SUCCESS;
    }

    @PostMapping("update")
    public IStatusCode update(@Validated(UserRequestDtoUpdateValidate.class) @RequestBody UserRequestDto userRequestDto){
        log.info("userRequestDto: {}",userRequestDto);
        return BaseStatusCode.SUCCESS;
    }

    @PostMapping("add")
    public IStatusCode add(@Validated(UserRequestDtoAddValidate.class) @RequestBody UserRequestDto userRequestDto){
        log.info("userRequestDto: {}",userRequestDto);
        return BaseStatusCode.SUCCESS;
    }

    @GetMapping("/getSimple")
    // 指定JsonView的简单视图
    @JsonView(UserResponseDto.UserResponseSimpleDtoView.class)
    public UserResponseDto  getSimple(){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName("张三");
        userResponseDto.setAge(10);
        userResponseDto.setEmail("zhangsan@qq.com");
        userResponseDto.setGender(0);
        userResponseDto.setPhoneNum("13888888888");
        userResponseDto.setOptUser("admin");
        return userResponseDto ;
    }

    @GetMapping("getDetail")
    // 指定详细视图
    @JsonView(UserResponseDto.UserResponseDetailDtoView.class)
    public UserResponseDto getDatail(){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName("张三");
        userResponseDto.setAge(10);
        userResponseDto.setEmail("zhangsan@qq.com");
        userResponseDto.setGender(0);
        userResponseDto.setPhoneNum("13888888888");
        userResponseDto.setOptUser("admin");

        return userResponseDto;
    }
}
