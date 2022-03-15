package service.face;

import dto.ExpireDate;

import java.util.List;

public interface ExpireDateService {

    List<ExpireDate> getList(String itemName);
}
