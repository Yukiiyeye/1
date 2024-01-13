package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.Scan;

import java.util.List;

public interface ScanService {
    void addScan(Scan scan);
    List findAllScan();
    List findScanByMytask_id(Integer mytask_id);

    List findScanByTester(String tester);
    void updateScan(Scan scan);
}
