package guide.triple.mileage.web.service;

import guide.triple.mileage.web.dto.EventsRequestDTO;
import guide.triple.mileage.web.dto.ResponseDTO;

public interface MileagePointService {

    ResponseDTO event(EventsRequestDTO eventsRequestDTO);
}
