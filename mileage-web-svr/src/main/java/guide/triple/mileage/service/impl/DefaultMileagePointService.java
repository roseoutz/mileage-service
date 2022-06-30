package guide.triple.mileage.service.impl;

import guide.triple.mileage.dto.EventsRequestDTO;
import guide.triple.mileage.entity.composite.MileageId;
import guide.triple.mileage.event.MileagePointEventFactory;
import guide.triple.mileage.repository.MileageReviewRepository;
import guide.triple.mileage.service.MileagePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : guide.triple.mileage.service.impl
 * fileName       : DefaultMileagePointService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
@RequiredArgsConstructor
@Service("defaultMileagePointService")
public class DefaultMileagePointService implements MileagePointService {

    private final MileageReviewRepository mileageReviewRepository;
    private final MileagePointEventFactory eventFactory;


    @Override
    public void event(EventsRequestDTO requestDTO) {
        // eventFactory.get(requestDTO.getAction())
        //     .ifPresent(eventHandler -> eventHandler.handle(requestDTO));

    }

    private void add(EventsRequestDTO requestDTO) {
        MileageId mileageId = new MileageId(requestDTO.getUserId(), requestDTO.getPlaceId());

        mileageReviewRepository.findByReviewId(requestDTO.getReviewId());
    }
}
