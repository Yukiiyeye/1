package com.yyx.assetsys.controller;

import com.yyx.assetsys.entity.Org;
import com.yyx.assetsys.entity.Result;
import com.yyx.assetsys.service.OrgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrgService orgService;

    @GetMapping("/findOrgByOrgName")
    public List<Org> findOrgByOrgName(String orgName){
        return  orgService.findOrgByOrgName(orgName);
    }
    @PostMapping("/addOrg")
    public String addOrg(@RequestBody Org org){
        orgService.addOrg(org);
        return Result.success().getMessage();
    }
    @GetMapping("/findByFatherOrg")
    public List<Org> findOrgByFatherOrg(String fatherOrg){
        return orgService.findOrgByFatherOrg(fatherOrg);
    }
    @PostMapping("/updateOrg")
    public String updateOrg(@RequestBody Org org){
        orgService.updateOrg(org);
        return Result.success().getMessage();
    }
    @GetMapping("/findAllOrg")
    public List<Org> findAllOrg(){
        return orgService.findAllOrg();
    }
    @GetMapping("/findOrgById")
    public Org findOrgById(Integer id){
        return orgService.findOrgById(id);
    }

}
