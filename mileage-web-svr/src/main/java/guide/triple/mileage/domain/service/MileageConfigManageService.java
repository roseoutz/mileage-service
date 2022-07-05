package guide.triple.mileage.domain.service;


import guide.triple.mileage.domain.dto.MileageConfigDTO;

import java.util.List;

public interface MileageConfigManageService {

    String get(String key);

    List<MileageConfigDTO> getAll(String prefix);

}
