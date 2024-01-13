package com.yyx.assetsys.controller;

import com.yyx.assetsys.entity.Result;
import com.yyx.assetsys.entity.Scan;
import com.yyx.assetsys.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scan")
public class ScanController {
    @Autowired
    private ScanService scanService;
    @PostMapping("/addScan")
    public String addScan(@RequestBody Scan scan){
        scanService.addScan(scan);
        return Result.success().getMessage();

    }
    @GetMapping("/findAllScan")
    public List<Scan> findAllScan(){
        return scanService.findAllScan();
    }
    @GetMapping("/findScanByMytask_id")
    public List<Scan> findScanByMytask_id(Integer mytask_id){
        return scanService.findScanByMytask_id(mytask_id);
    }
    @GetMapping("/findScanByTester")
    public List<Scan> findScanByTester(String tester){
        return scanService.findScanByTester(tester);
    }
    @PostMapping("/updateScan")
    public String updateScan(@RequestBody Scan scan){
        scanService.updateScan(scan);
        return Result.success().getMessage();

    }
}
