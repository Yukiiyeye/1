package com.yyx.assetsys;

import com.yyx.assetsys.service.OrgService;
import com.yyx.assetsys.service.impl.OrgServiceImpl;
import com.yyx.assetsys.entity.Org;
import com.yyx.assetsys.mapper.OrgMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrgServiceImplTest {

    @Mock
    private OrgMapper orgMapper;

    @InjectMocks
    private OrgService orgService = new OrgServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindOrgByOrgName() {
        List<Org> mockOrgList = new ArrayList<>();
        mockOrgList.add(new Org());
        mockOrgList.add(new Org());

        Mockito.when(orgMapper.findOrgByOrgName("%Test%")).thenReturn(mockOrgList);

        List<Org> result = orgService.findOrgByOrgName("Test");

        assertEquals(mockOrgList, result);
    }

    @Test
    public void testFindOrgByFatherOrg() {
        List<Org> mockOrgList = new ArrayList<>();
        mockOrgList.add(new Org());
        mockOrgList.add(new Org());

        Mockito.when(orgMapper.findOrgByFatherOrg("%Parent%")).thenReturn(mockOrgList);

        List<Org> result = orgService.findOrgByFatherOrg("Parent");

        assertEquals(mockOrgList, result);
    }

    @Test
    public void testAddOrg() {
        Org orgToAdd = new Org();

        orgService.addOrg(orgToAdd);

        Mockito.verify(orgMapper).addOrg(orgToAdd);
    }

    @Test
    public void testUpdateOrg() {
        Org orgToUpdate = new Org();

        orgService.updateOrg(orgToUpdate);

        Mockito.verify(orgMapper).updateOrg(orgToUpdate);
    }

    @Test
    public void testFindAllOrg() {
        List<Org> mockOrgList = new ArrayList<>();
        mockOrgList.add(new Org());
        mockOrgList.add(new Org());

        Mockito.when(orgMapper.findAllOrg()).thenReturn(mockOrgList);

        List<Org> result = orgService.findAllOrg();

        assertEquals(mockOrgList, result);
    }

    @Test
    public void testFindOrgById() {
        Org mockOrg = new Org();

        Mockito.when(orgMapper.findOrgById(9)).thenReturn(mockOrg);
        Mockito.when(orgMapper.findOrgById(10)).thenReturn(null);

        Org resultFound = orgService.findOrgById(9);
        Org resultNotFound = orgService.findOrgById(10);

        assertEquals(mockOrg, resultFound);
        assertNull(resultNotFound);
    }


}

