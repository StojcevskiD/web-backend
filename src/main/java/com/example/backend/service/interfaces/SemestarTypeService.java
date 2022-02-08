package com.example.backend.service.interfaces;

import com.example.backend.model.SemestarType;

public interface SemestarTypeService {
    SemestarType findSemestarTypeByName(String name);
}
