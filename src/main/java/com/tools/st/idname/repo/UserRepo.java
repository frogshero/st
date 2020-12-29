package com.tools.st.idname.repo;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserRepo extends AbstractRepo<Long, IdNameEntityBase> {

    public UserRepo(@Autowired IdNameConfiguration config) {
        super(DataTableEnum.user,
                config.getUserMaxSize(), config.getUserDuration(), TimeUnit.HOURS);
    }
}
