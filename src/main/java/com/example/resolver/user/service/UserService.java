package com.example.resolver.user.service;

import com.example.resolver.auth.dto.AuthUser;
import com.example.resolver.user.dto.UserRequestDto;
import com.example.resolver.user.entity.User;
import com.example.resolver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void update(AuthUser authUser, UserRequestDto request) {
        User user = userRepository.findById(authUser.getId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );

        // 이메일은 유니크 하기 떄문에 업데이트를 해야한다면, 이미 존재하는 이메일인지 확인해야 한다
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일이기 때문에 업데이트 할 수 없습니다.");
        }

        user.update(request.getEmail(), request.getPassword());
    }
}
