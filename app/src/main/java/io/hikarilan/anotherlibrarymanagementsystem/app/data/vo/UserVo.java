package io.hikarilan.anotherlibrarymanagementsystem.app.data.vo;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;

public record UserVo(
        long id,
        String username,
        Role role
) {

    public static UserVo fromEntity(User user) {
        return new UserVo(user.getId(), user.getUsername(), user.getRole());
    }

}
