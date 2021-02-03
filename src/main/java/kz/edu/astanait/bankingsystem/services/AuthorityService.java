package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.models.Authority;
import kz.edu.astanait.bankingsystem.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority getAuthority(Long authorityId) {
        return authorityRepository.findById(authorityId).orElseThrow(() -> new IllegalStateException("Authority not found"));
    }
}
