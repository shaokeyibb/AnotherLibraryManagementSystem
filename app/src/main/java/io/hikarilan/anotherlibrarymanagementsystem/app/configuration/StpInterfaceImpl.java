package io.hikarilan.anotherlibrarymanagementsystem.app.configuration;

import cn.dev33.satoken.stp.StpInterface;
import io.hikarilan.anotherlibrarymanagementsystem.app.data.Role;
import io.hikarilan.anotherlibrarymanagementsystem.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class StpInterfaceImpl implements StpInterface {

    private final UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return List.of(userService.getUser((Long) loginId).map(it -> it.getRole().toString()).orElse(Role.GUEST.toString()));
    }
}
