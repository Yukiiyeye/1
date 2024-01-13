package com.yyx.assetsys.controller;

import com.yyx.assetsys.entity.Mytask;
import com.yyx.assetsys.entity.Result;
import com.yyx.assetsys.service.MytaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mytask")
public class MytaskController {
    @Autowired
    private MytaskService mytaskService;
    @PostMapping("/addMytask")
    public String addMytask(@RequestBody Mytask mytask){
        mytaskService.addMytask(mytask);
        return Result.success().getMessage();
    }
    @GetMapping("/findMytaskByUser_id")
    public List<Mytask> findMytaskByUser_id(Integer user_id){
        return mytaskService.findMytaskByUser_id(user_id);
    }
    @PostMapping("/updateMytaskStatus")
    public String updateMytaskStatus(@RequestBody Mytask mytask){
        mytaskService.updateMytaskStatus(mytask);
        return Result.success().getMessage();
    }



}
