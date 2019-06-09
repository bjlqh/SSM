package com.lqh.service.impl;

import com.lqh.dao.ISysLogDao;
import com.lqh.domain.SysLog;
import com.lqh.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLogImpl implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void saveLog(SysLog sysLog) {
        iSysLogDao.saveLog(sysLog);
    }
}
