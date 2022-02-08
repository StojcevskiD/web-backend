package com.example.backend.service.impl;

import com.example.backend.model.SemestarType;
import com.example.backend.repository.SemestarTypeRepository;
import com.example.backend.service.interfaces.SemestarTypeService;
import org.springframework.stereotype.Service;

@Service
public class SemestarTypeServiceImpl implements SemestarTypeService {
    private final SemestarTypeRepository semestarTypeRepository;

    public SemestarTypeServiceImpl(SemestarTypeRepository semestarTypeRepository) {
        this.semestarTypeRepository = semestarTypeRepository;
    }

    @Override
    public SemestarType findSemestarTypeByName(String name) {
        return semestarTypeRepository.findByName(name);
    }

}
