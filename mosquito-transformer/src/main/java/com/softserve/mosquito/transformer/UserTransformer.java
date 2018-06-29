package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {

    private UserTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static User toEntity(UserDto userDto) {
        if (userDto == null)
            return null;
        else {
            return User.builder()
                    .id(userDto.getId())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .specializations(SpecializationTransformer.toEntityList(userDto.getSpecializations()))
                    .build();
        }
    }

    public static UserDto toDto(User user) {
        if (user == null)
            return null;
        else
            return UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .confirmPassword(user.getPassword())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .specializations(SpecializationTransformer.toDTOList(user.getSpecializations()))
                    .build();
    }

    public static List<User> toEntityList(List<UserDto> userDtos) {
        if (userDtos == null)
            return new ArrayList<>();
        else {
            List<User> users = new ArrayList<>();
            for (UserDto item : userDtos)
                users.add(toEntity(item));
            return users;
        }
    }

    public static List<UserDto> toDtoList(List<User> users) {
        if (users == null)
            return new ArrayList<>();
        else {
            List<UserDto> userDtos = new ArrayList<>();
            for (User item : users)
                userDtos.add(toDto(item));
            return userDtos;
        }
    }
}