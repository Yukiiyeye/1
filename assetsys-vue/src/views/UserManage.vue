<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav></admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>用户管理</h3>
          <div class="table-container">
            <el-input v-model="queryKeyword" placeholder="输入查询关键字" style="width: 200px;"></el-input>
            <el-button type="primary" @click="queryData">查询</el-button>
            <el-button @click="getData">查询全部</el-button>
            <el-table :data="queryResult">
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column label="用户名">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.username }}</span>
                  <el-input v-show="row.editable" v-model="row.username"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="name" label="昵称">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.name }}</span>
                  <el-input v-show="row.editable" v-model="row.name"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="remark" label="描述">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.remark }}</span>
                  <el-input v-show="row.editable" v-model="row.remark"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="role" label="当前权限">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ getStatusText(row.role) }}</span>
                  <el-input v-show="row.editable" v-model="row.role"></el-input>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template #default="{ row }">
                  <el-button type="primary" :icon="Edit" @click="() => editRow(row)"></el-button>
                  <el-button type="success" :icon="Check" @click="() => saveEdit(row)" :disabled="!row.editable"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import AdminNav from '@/components/AdminNav.vue';
import {Check, Edit} from "@element-plus/icons-vue";

const API_BASE_URL = 'http://localhost:8080';

interface UserData {
  id: number;
  username: string;
  name: string;
  remark: string;
  role: number;
  editable?: boolean;
}

export default {
  computed: {
    Check() {
      return Check
    },
    Edit() {
      return Edit
    }
  },
  components: { AdminNav },
  data() {
    return {
      queryResult: ref<UserData[]>([]),
      addFormData: ref({
        id: '',
        username: '',
        name: '',
        remark: '',
        role: '',
      }),
      addDialogVisible: ref(false),
      queryKeyword: ref(''),
    };
  },
  methods: {
    getData() {
      axios.get(`${API_BASE_URL}/user/findAllUser`, {
        headers: {
          Authorization: localStorage.getItem('token'),
        },
      }as any)
          .then((res: AxiosResponse) => {
            console.log(res);
            this.queryResult = res.data.map((org: UserData) => ({ ...org, editable: false }));
          })
          .catch((err: Error) => {
            console.error(err);
            ElMessage.error('查询失败');
          });
    },
    queryData() {
      axios.get(`${API_BASE_URL}/user/findByName`, {
        headers: {
          Authorization: localStorage.getItem('token'),
        },
      }as any)
          .then((res: AxiosResponse) => {
            console.log(res);
            this.queryResult = res.data.map((org: UserData) => ({ ...org, editable: false }));
          })
          .catch((err: Error) => {
            console.error(err);
            ElMessage.error('查询失败');
          });
    },
    saveEdit(row: UserData) {
      axios.post(`${API_BASE_URL}/user/updateUser`, row, {
        headers: {
          Authorization: localStorage.getItem('token'),
        },
      }as any)
          .then((res: AxiosResponse) => {
            console.log(res);
            ElMessage.success('保存成功');
            this.getData();
          })
          .catch((err: Error) => {
            console.error(err);
            ElMessage.error('保存失败');
          });
    },
    editRow(row: UserData) {
      row.editable = !row.editable;
    },
    getStatusText(role: number) {
      switch (role) {
        case 1:
          return '管理员';
        case 2:
          return '资产管理员';
        case 3:
          return '渗透测试员';
        default:
          return '未知人员';
      }
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style scoped>
.table-container {
  max-width: 1220px;
  margin: 0 auto;
}
</style>
