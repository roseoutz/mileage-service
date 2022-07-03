package guide.triple.mileage.domain.service;

import guide.triple.mileage.common.dto.SearchParam;
import guide.triple.mileage.domain.dto.MileageHistoryDTO;

import java.util.List;

/**
 * packageName    : guide.triple.mileage.service
 * fileName       : MileageHistoryManageService
 * author         : kimdonggyuuuuu
 * date           : 2022/06/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/30        kimdonggyuuuuu       최초 생성
 */
public interface MileageHistoryManageService {

    void add(MileageHistoryDTO historyDTO);

    List<MileageHistoryDTO> search(SearchParam searchParam);

}
