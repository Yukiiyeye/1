package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.entity.Org;
import com.yyx.assetsys.mapper.OrgMapper;
import com.yyx.assetsys.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgMapper orgMapper;

    @Override
    public List<Org> findOrgByOrgName(String orgName) {
        return orgMapper.findOrgByOrgName("%" + orgName + "%");
    }

    @Override
    public List<Org> findOrgByFatherOrg(String fatherOrg) {
        return orgMapper.findOrgByFatherOrg("%" + fatherOrg + "%");
    }

    @Override
    public void addOrg(Org org) {
        orgMapper.addOrg(org);

    }

    @Override
    public void updateOrg(Org org) {
        orgMapper.updateOrg(org);

    }

    @Override
    public List<Org> findAllOrg() {
        return orgMapper.findAllOrg();
    }

    @Override
    public Org findOrgById(Integer id) {
        return orgMapper.findOrgById(id);
    }


}
