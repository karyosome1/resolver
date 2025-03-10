package com.example.resolver.auth.service;

import com.example.resolver.auth.dto.AuthRequestDto;
import com.example.resolver.auth.dto.AuthResponseDto;
import com.example.resolver.common.config.JwtUtil;
import com.example.resolver.user.entity.User;
import com.example.resolver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(AuthRequestDto request) {
        // 이미 존재하는 이메일이므로 에러를 던짐
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다!");
        }

        // 회언가입 가능한 이메일
        // 필요한 경우 passwordEncoder를 사용해서 비밀번호를 암호화하면 된다
        User user = new User(request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public AuthResponseDto signin(AuthRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 회원입니다.")
        );

        String password = request.getPassword();
        if (!password.equals(user.getPassword())) {
            throw new IllegalStateException("비밀번호가 잘못되었습니다.");
        }

        // 비밀번호가 일치한 경우
        String bearerJwt = jwtUtil.createToken(user.getId(), user.getEmail());
        return new AuthResponseDto(bearerJwt);
    }

}
