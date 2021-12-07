package com.kjde1033.bqgnmgt.service.impl;

import com.kjde1033.bqgnmgt.dao.MasterDao;
import com.kjde1033.bqgnmgt.model.vo.MasterVo;
import com.kjde1033.bqgnmgt.service.MasterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MasterServiceImpl implements MasterService {

    @Resource
    private MasterDao masterDao;

    @Override
    public List<MasterVo> getMasterList(String query,int size, int pageNum) {
        int index = (pageNum-1)*size;
        return masterDao.queryMasters(query,index,size);
    }

    @Override
    public int queryMasterCount(String query) {
        return masterDao.queryMasterCount(query);
    }

    @Override
    public boolean delMaster(int masterId) {
        int i = masterDao.updMasterStatus(masterId);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<MasterVo> getMasterListOut() {

        return masterDao.queryMastersOut();
    }

    @Override
    public int queryMastersOutCount(String query) {
        return masterDao.queryMastersOutCount(query);
    }
}
