package com.moneyTracker.services;

import com.moneyTracker.dtos.ProfilePatchDto;
import com.moneyTracker.dtos.ProfileTokenPostDto;
import com.moneyTracker.entities.ProfileEntity;
import com.moneyTracker.repositories.ProfileJpaRepository;
import com.moneyTracker.token.TokenRepository;
import com.moneyTracker.user.User;
import com.moneyTracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileJpaRepository profileJpaRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public ProfileEntity getProfileById(int id) {
        return profileJpaRepository.findById(id).orElse(null);
    }

    public ProfileEntity getProfileByUserToken(ProfileTokenPostDto profileTokenPostDto) {
        Optional<User> user = userRepository.findUserByTokensToken(profileTokenPostDto.getToken());
        if(user.isEmpty()) {
            return null;
        }
        Optional<ProfileEntity> profile = profileJpaRepository.findByUser(user.get());
        return profile.orElse(null);
    }

    public void createProfile(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return;
        }
        User u = user.get();
        ProfileEntity profile = ProfileEntity.builder()
                .balance(0.0)
                .email(u.getEmail())
                .firstName(u.getFirstname())
                .lastName(u.getLastname())
                .user(u)
                .build();
        profileJpaRepository.save(profile);
    }

    public void updateProfileBalance(int profileId, Double balance) {
        Optional<ProfileEntity> p = profileJpaRepository.findById(profileId);
        if(p.isEmpty()) {
            return;
        }
        ProfileEntity profile = p.get();
        profile.setBalance(balance);
        profileJpaRepository.save(profile);
    }

    public void updateProfile(ProfilePatchDto patchDto, int profileId) {
        Optional<ProfileEntity> p = profileJpaRepository.findById(profileId);
        if(p.isEmpty()) {
            return;
        }
        ProfileEntity profile = p.get();
        profile.setFirstName(patchDto.getFirstname());
        profile.setLastName(patchDto.getLastname());
        profileJpaRepository.save(profile);
    }
}
