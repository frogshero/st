package com.tools.st.idname.repo;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BusinessDicRepo extends AbstractRepo<Long, IdNameEntityBase> {

    public BusinessDicRepo(@Autowired IdNameConfiguration config) {
        super(DataTableEnum.businessDic,
                config.getBusinessDictMaxSize(),
                config.getBusinessDictDuration(),
                TimeUnit.HOURS);
    }

}
