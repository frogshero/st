package com.tools.st.idname.repo;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.model.IdNameEntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SysdictRepo extends AbstractRepo<String, IdNameEntityBase> {

    public SysdictRepo(@Autowired IdNameConfiguration config) {
        super(DataTableEnum.sysDic,
                config.getSysDictMaxSize(), config.getSysDictDuration(), TimeUnit.HOURS);
    }
}
