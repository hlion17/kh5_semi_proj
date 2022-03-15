package service.impl;

import java.util.List;

import dto.ExpireDate;
import openapi.ExpireDateApi;
import service.face.ExpireDateService;

public class ExpireServiceImpl implements ExpireDateService {

    private ExpireDateApi expireDateApi = new ExpireDateApi();

    @Override
    public List<ExpireDate> getList(String itemName) {
        List<ExpireDate> result = expireDateApi.findAll(itemName);
        return result;
    }
}
