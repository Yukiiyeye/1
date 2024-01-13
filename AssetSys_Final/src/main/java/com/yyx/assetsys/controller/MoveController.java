package com.yyx.assetsys.controller;

import com.yyx.assetsys.entity.Move;
import com.yyx.assetsys.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/move")
public class MoveController {
    @Autowired
    private MoveService moveService;
    @PostMapping("/addMove")
    public void addMove(@RequestBody Move move){
        moveService.addMove(move);

    }
    @GetMapping("/findMoveByMytask_id")
    public List findMoveByMytask_id(Integer mytask_id){
        return moveService.findMoveByMytask_id(mytask_id);
    }
    @GetMapping("/findMoveByTester")
    public List findMoveByTester(String tester){
        return moveService.findMoveByTester(tester);
    }
    @GetMapping("/findAllMove")
    public List findAllMove(){
        return moveService.findAllMove();
    }
    @PostMapping("/updateMove")
    public void updateMove(@RequestBody Move move){
        moveService.updateMove(move);
    }

}
