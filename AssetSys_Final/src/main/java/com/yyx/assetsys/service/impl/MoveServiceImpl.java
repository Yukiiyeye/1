package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.entity.Move;
import com.yyx.assetsys.service.MoveService;
import com.yyx.assetsys.mapper.MoveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveServiceImpl implements MoveService {
    @Autowired
    private MoveMapper moveMapper;

    @Override
    public void addMove(Move move) {
        moveMapper.addMove(move);
    }

    @Override
    public List findMoveByMytask_id(Integer mytask_id) {
        return moveMapper.findMoveByMytask_id(mytask_id);
    }

    @Override
    public List findAllMove() {
        return moveMapper.findAllMove();
    }

    @Override
    public void updateMove(Move move) {
        moveMapper.updateMove(move);
    }

    @Override
    public List findMoveByTester(String tester) {
        return moveMapper.findMoveByTester(tester);
    }
}
