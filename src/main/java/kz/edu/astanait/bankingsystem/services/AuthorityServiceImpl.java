package kz.edu.astanait.bankingsystem.services;

import kz.edu.astanait.bankingsystem.exceptions.AuthorityNotFoundException;
import kz.edu.astanait.bankingsystem.models.Authority;
import kz.edu.astanait.bankingsystem.repositories.AuthorityRepository;
import kz.edu.astanait.bankingsystem.services.interfaces.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority save(Authority entity) {
        return authorityRepository.save(entity);
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new AuthorityNotFoundException("Authority not found"));
    }

    @Override
    public void delete(Authority entity) {
        authorityRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return authorityRepository.count();
    }
}
