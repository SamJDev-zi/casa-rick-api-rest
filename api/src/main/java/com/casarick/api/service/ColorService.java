package com.casarick.api.service;

import com.casarick.api.dto.ColorDTO;
import com.casarick.api.model.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<ColorDTO> getAllColors();
    ColorDTO getColorById(Long id);
    Optional<ColorDTO> getColorByName(String name);
    ColorDTO createNewColor(Color color);
}
