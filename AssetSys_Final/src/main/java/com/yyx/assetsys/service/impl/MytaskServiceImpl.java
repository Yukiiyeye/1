package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.mapper.MytaskMapper;
import com.yyx.assetsys.entity.Mytask;

import com.yyx.assetsys.service.MytaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MytaskServiceImpl implements MytaskService {
    @Autowired
    private MytaskMapper mytaskMapper;
    @Override
    public void addMytask(Mytask mytask) {
        mytaskMapper.addMytask(mytask);

    }

    @Override
    public void updateMytaskStatus(Mytask mytask) {
        mytaskMapper.updateMytaskStatus(mytask);
    }

    @Override
    public List findMytaskByUser_id(Integer user_id) {
        return mytaskMapper.findMytaskByUser_id(user_id);
    }
}
