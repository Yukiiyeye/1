package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.Move;

import java.util.List;

public interface MoveService {
    void addMove(Move move);
    List findMoveByMytask_id(Integer mytask_id);
    List findAllMove();

    void updateMove(Move move);

    List findMoveByTester(String tester);
}
