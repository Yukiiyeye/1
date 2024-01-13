package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.Mytask;

import java.util.List;

public interface MytaskService {
    void addMytask(Mytask mytask);
    void updateMytaskStatus(Mytask mytask);
    List findMytaskByUser_id(Integer user_id);

}
