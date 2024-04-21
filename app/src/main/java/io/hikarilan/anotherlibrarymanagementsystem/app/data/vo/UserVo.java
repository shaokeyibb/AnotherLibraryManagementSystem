package io.hikarilan.anotherlibrarymanagementsystem.app.data.vo;

import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import io.hikarilan.anotherlibrarymanagementsystem.app.entity.User;

import java.util.List;

public record UserVo(long id, String username, Role role, List<BorrowRecordVo> borrowRecords) {

    public static UserVo fromEntity(User user) {
        return new UserVo(user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getBorrowRecords().stream().map(BorrowRecordVo::fromEntity).toList()
        );
    }

}
