package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.entity.Scan;
import com.yyx.assetsys.mapper.ScanMapper;
import com.yyx.assetsys.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanServiceImpl implements ScanService {
    @Autowired
    private ScanMapper scanMapper;
    @Override
    public void addScan(Scan scan) {
        scanMapper.addScan(scan);

    }


    @Override
    public List findAllScan() {
        return scanMapper.findAllScan();
    }

    @Override
    public List<Scan> findScanByMytask_id(Integer mytask_id) {
        return scanMapper.findScanByMytask_id(mytask_id);
    }

    @Override
    public List<Scan> findScanByTester(String tester) {
        return scanMapper.findScanByTester(tester);
    }

    @Override
    public void updateScan(Scan scan) {
        scanMapper.updateScan(scan);
    }
}
