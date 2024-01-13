<template>
  <el-radio-group v-model="isCollapse" style="margin-bottom: 30px">
    <el-radio-button :label="false">展开菜单</el-radio-button>
    <el-radio-button :label="true">关闭菜单</el-radio-button>
    <el-button type="primary" @click="$router.push('/admin')">回到主页</el-button>
  </el-radio-group>
  <el-aside style="flex: 1 1 220px;">
    <el-row class="tac">
      <el-col :span="12">
        <h5 class="mb-2">欢迎您！{{ currentUser }}</h5>
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            @close="handleClose"
        >

          <!-- 用户管理菜单项 -->
          <el-sub-menu index="1" v-if="checkRole(1)" @click="$router.push('/usermanage')">
            <template #title>
              <el-icon><setting /></el-icon>
              <span>用户管理</span>
            </template>
          </el-sub-menu>

          <!-- 组织机构菜单项 -->
          <el-sub-menu index="2" v-if="checkRole(1) || checkRole(2)">
            <template #title>
              <el-icon><Discount /></el-icon>
              <span>组织机构</span>
            </template>
            <el-menu-item index="2-1" @click="$router.push('/org')">所有组织机构</el-menu-item>
          </el-sub-menu>

          <!-- 安全资产菜单项 -->
          <el-sub-menu index="3" v-if="checkRole(1) || checkRole(2)" @click="$router.push('/asset')">
            <template #title>
              <el-icon><Star /></el-icon>
              <span>安全资产</span>
            </template>
            <el-menu-item index="3-1" @click="$router.push('assetCategory')">资产分类管理</el-menu-item>
            <el-menu-item index="3-2" @click="$router.push('assetLabel')">资产标签管理</el-menu-item>
          </el-sub-menu>

          <!-- 演练任务管理菜单项 -->
          <el-sub-menu index="4" v-if="checkRole(1) || checkRole(2)" @click="$router.push('/taskManage')">
            <template #title>
              <el-icon><setting /></el-icon>
              <span>演练任务管理</span>
            </template>
          </el-sub-menu>

          <!-- 我的演练任务菜单项 -->
          <el-sub-menu index="5" v-if="checkRole(3)" @click="$router.push('/mytask')">
            <template #title>
              <el-icon><icon-menu /></el-icon>
              <span>我的演练任务</span>
            </template>
          </el-sub-menu>

          <!-- 我的漏洞扫描菜单项 -->
          <el-sub-menu index="6" v-if="checkRole(3)" @click="$router.push('/myscan')">
            <template #title>
              <el-icon><document /></el-icon>
              <span>我的漏洞扫描</span>
            </template>
          </el-sub-menu>

          <!-- 漏洞利用管理菜单项 -->
          <el-sub-menu index="7" v-if="checkRole(3)" @click="$router.push('/exploit')">
            <template #title>
              <el-icon><document /></el-icon>
              <span>漏洞利用管理</span>
            </template>
          </el-sub-menu>

          <!-- 横向移动管理菜单项 -->
          <el-sub-menu index="8" v-if="checkRole(3)" @click="$router.push('/move')">
            <template #title>
              <el-icon><document /></el-icon>
              <span>横向移动管理</span>
            </template>
          </el-sub-menu>

        </el-menu>
      </el-col>
    </el-row>

    <el-row class="tac">
      <el-col :span="12">
        <el-button @click="logout">退出</el-button>
      </el-col>
    </el-row>
  </el-aside>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import router from "@/router";
const isCollapse = ref(false)
const currentUser = localStorage.getItem('username') || 'Unknown User'; // 从 localStorage 中获取用户名
const checkRole = (role) => {
  const userRole = localStorage.getItem("role");
  return userRole === "1" || userRole === role.toString();
};

const logout = () => {
  localStorage.removeItem('token');
  router.push('/login');
};
</script>

<style scoped>


</style>
