package com.casarick.api.service;

import com.casarick.api.dto.ColorDTO;
import com.casarick.api.model.Color;

import java.util.List;

public interface ColorService {
    List<ColorDTO> getAllColors();
    ColorDTO getColorById(Long id);
    ColorDTO createNewColor(Color color);
}
