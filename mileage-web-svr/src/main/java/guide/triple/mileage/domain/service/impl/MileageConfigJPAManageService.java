package guide.triple.mileage.domain.service.impl;

import guide.triple.mileage.domain.dto.MileageConfigDTO;
import guide.triple.mileage.domain.entity.MileageConfigEntity;
import guide.triple.mileage.domain.repository.MileageConfigRepository;
import guide.triple.mileage.domain.service.MileageConfigManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("mileageConfigJPAManageService")
public class MileageConfigJPAManageService implements MileageConfigManageService {

    private final MileageConfigRepository mileageConfigRepository;


    @Override
    @Cacheable(cacheNames = "configs")
    public String get(String key) {
        return mileageConfigRepository.findById(key)
                .map(MileageConfigEntity::getConfigValue)
                .orElse(null);
    }

    @Override
    @Cacheable(cacheNames = "configs")
    public List<MileageConfigDTO> getAll(String prefix) {
        return mileageConfigRepository.findAll(toSpecification(prefix))
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Specification<MileageConfigEntity> toSpecification(String prefix) {
        return (root, query, criteriaBuilder) -> {
            criteriaBuilder.like(root.get("configKey"), prefix+"%");
            return criteriaBuilder.conjunction();
        };
    }

    private MileageConfigDTO toDTO (MileageConfigEntity entity) {
        return MileageConfigDTO
                .builder()
                .key(entity.getConfigKey())
                .value(entity.getConfigValue())
                .insertTime(entity.getInsertTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }
}
