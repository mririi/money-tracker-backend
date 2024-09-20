package com.moneyTracker.controllers;

import com.moneyTracker.dtos.ProfilePatchDto;
import com.moneyTracker.dtos.ProfileTokenPostDto;
import com.moneyTracker.entities.ProfileEntity;
import com.moneyTracker.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/profile")
    public ProfileEntity getProfileByUserToken(@RequestBody final ProfileTokenPostDto profileTokenPostDto) {
        return profileService.getProfileByUserToken(profileTokenPostDto);
    }

    @PatchMapping("/{id}")
    public void getProfileByUserToken(@RequestBody final ProfilePatchDto patchDto, @PathVariable("id") final int profileId) {
        profileService.updateProfile(patchDto, profileId);
    }

    @PutMapping("/{id}/balance")
    public void updateProfileBalance(@PathVariable("id") final int profileId, @RequestParam("balance") final Double balance) {
        profileService.updateProfileBalance(profileId, balance);
    }

    @GetMapping("/{id}/balance")
    public Double getProfileBalance(@PathVariable("id") final int id) {
        ProfileEntity profile = profileService.getProfileById(id);
        return profile != null ? profile.getBalance() : 0.0;
    }
}
