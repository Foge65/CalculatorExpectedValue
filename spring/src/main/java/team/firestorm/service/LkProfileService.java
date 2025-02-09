package team.firestorm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.firestorm.entity.LkProfileEntity;
import team.firestorm.repository.LkProfileRepository;

@Service
@RequiredArgsConstructor
public class LkProfileService {
    private final LkProfileRepository repository;

    public LkProfileEntity findUserByToken(String token) {
        return repository.findLkProfileEntityByAccessToken(token);
    }

}
