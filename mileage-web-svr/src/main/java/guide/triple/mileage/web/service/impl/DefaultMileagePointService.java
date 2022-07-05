package guide.triple.mileage.web.service.impl;

import guide.triple.mileage.common.constant.ActionType;
import guide.triple.mileage.common.constant.ErrorCode;
import guide.triple.mileage.common.exception.MileageException;
import guide.triple.mileage.common.util.StringCheckUtil;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;
import guide.triple.mileage.domain.dto.MileageReviewDTO;
import guide.triple.mileage.domain.service.MileageHistoryManageService;
import guide.triple.mileage.domain.service.MileageReviewManageService;
import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.dto.ResponseDTO;
import guide.triple.mileage.web.service.MileagePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@Service("defaultMileageService")
public class DefaultMileagePointService implements MileagePointService {

    private final MileageReviewManageService reviewService;
    private final MileageHistoryManageService historyService;

    @Override
    public ResponseDTO event(EventsRequestDTO eventsRequestDTO) {
        MileageReviewDTO reviewDTO;

        if (
                !StringCheckUtil.isNonNull(eventsRequestDTO.getUserId())
                || !StringCheckUtil.isNonNull(eventsRequestDTO.getPlaceId())
                || !StringCheckUtil.isNonNull(eventsRequestDTO.getReviewId())
        ) {
            throw new MileageException(ErrorCode.Error_REQUEST_PARAMETER_INVALID);
        }

        switch (eventsRequestDTO.getAction()) {
            case ADD:
                reviewDTO = addReview(eventsRequestDTO);
                break;
            case MOD:
                reviewDTO = updateReview(eventsRequestDTO);
                break;
            case DELETE:
                reviewDTO = deleteReview(eventsRequestDTO);
                break;
            default:
                throw new MileageException(ErrorCode.ERROR_REQUEST_ACTION_NOT_FOUND);
        }

        addHistory(reviewDTO, eventsRequestDTO.getAction());

        return ResponseDTO
                .builder()
                .success(true)
                .statusCode(200)
                .build();
    }


    private MileageReviewDTO addReview(EventsRequestDTO requestDTO) {
        return reviewService.add(toReviewDTO(requestDTO));
    }

    private MileageReviewDTO updateReview(EventsRequestDTO requestDTO) {
        return reviewService.update(toReviewDTO(requestDTO));
    }

    private MileageReviewDTO deleteReview(EventsRequestDTO requestDTO) {
        return reviewService.delete(toReviewDTO(requestDTO));
    }

    private MileageReviewDTO toReviewDTO(EventsRequestDTO requestDTO) {
        return MileageReviewDTO
                .builder()
                .userId(requestDTO.getUserId())
                .placeId(requestDTO.getPlaceId())
                .reviewId(requestDTO.getReviewId())
                .text(requestDTO.getContent() != null && requestDTO.getContent().length() > 0)
                .image(requestDTO.getAttachedPhotoIds() != null && !requestDTO.getAttachedPhotoIds().isEmpty())
                .build();
    }

    private void addHistory(final MileageReviewDTO reviewDTO, final ActionType actionType) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> historyService.add(toHistoryDTO(reviewDTO, actionType)));
    }

    private MileageHistoryDTO toHistoryDTO(MileageReviewDTO reviewDTO, ActionType actionType) {
        return MileageHistoryDTO
                .builder()
                .actionType(actionType)
                .userId(reviewDTO.getUserId())
                .placeId(reviewDTO.getPlaceId())
                .reviewId(reviewDTO.getReviewId())
                .bonusPoint(reviewDTO.getBonusPoint())
                .imagePoint(reviewDTO.getImagePoint())
                .textPoint(reviewDTO.getTextPoint())
                .build();
    }
}
