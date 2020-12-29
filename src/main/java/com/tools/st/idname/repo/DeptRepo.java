package com.tools.st.idname.repo;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DeptRepo extends AbstractRepo<Long, IdNameEntityBase> {

    public DeptRepo(@Autowired IdNameConfiguration config) {
        super(DataTableEnum.dept,
                config.getDeptMaxSize(), config.getDeptDuration(), TimeUnit.HOURS);
    }

}
