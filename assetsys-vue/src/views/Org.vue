<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav></admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>组织机构管理</h3>
          <el-select v-model="queryType" placeholder="选择查询类型">
            <el-option label="机构名字" value="orgName"></el-option>
            <el-option label="父类机构" value="fatherOrg"></el-option>
          </el-select>
          <el-input v-model="queryKeyword" placeholder="输入查询关键字" style="width: 200px;"></el-input>
          <el-button :icon="Search" color="green" @click="queryData">查询</el-button>
          <el-button :icon="Search" color="blue" @click="getData">查询全部</el-button>
          <el-button color="#626aef" :dark="isDark" plain @click="addRow">添加</el-button>
          <el-table v-loading="loading" :data="queryResult">
            <el-table-column prop="id" label="id"></el-table-column>
            <el-table-column label="组织名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.orgName }}</span>
                <el-input v-show="row.editable" v-model="row.orgName"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="fatherOrg" label="父组织">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.fatherOrg }}</span>
                <el-input v-show="row.editable" v-model="row.fatherOrg"></el-input>
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
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import axios, { AxiosResponse } from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import AdminNav from '@/components/AdminNav.vue';
import {Check, Edit, Search} from "@element-plus/icons-vue";
const API_BASE_URL = 'http://localhost:8080';

interface OrgData {
  id: number;
  orgName: string;
  fatherOrg: string;
  editable?: boolean;
}

export default {
  computed: {
    Search() {
      return Search
    },
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
      queryResult: ref<OrgData[]>([]),
      addFormData: ref<OrgData>({ orgName: '', fatherOrg: '' }),
      addDialogVisible: ref(false),
      queryType: ref('orgName'),
      queryKeyword: ref(''),
      loading: true,
    };
  },
  methods: {
    getData() {
      this.loading = true;
      // 模拟加载时长为1秒
      setTimeout(() => {
        axios.get(`${API_BASE_URL}/org/findAllOrg`, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }as any)
            .then((res: AxiosResponse) => {
              console.log(res);
              this.queryResult = res.data.map((org: OrgData) => ({ ...org, editable: false }));
            })
            .catch((err: Error) => {
              console.error(err);
              ElMessage.error('查询失败');
            })
            .then(() => {
              // 加载完成后设置 loading 为 false
              this.loading = false;
            });
      }, 1000); // 1秒的加载时长
    },
    queryData() {
      const queryUrlMap = {
        orgName: '/org/findOrgByOrgName',
        fatherOrg: '/org/findByFatherOrg',
      };

      const queryUrl = `${API_BASE_URL}${queryUrlMap[this.queryType]}?${this.queryType}=${encodeURIComponent(this.queryKeyword)}`;
      this.loading = true;
      setTimeout(() => {
      axios.get(queryUrl, {
        headers: {
          Authorization: localStorage.getItem('token'),
        },
      }as any)
          .then((res: AxiosResponse) => {
            console.log(res);
            this.queryResult = res.data.map((org: OrgData) => ({ ...org, editable: false }));
          })
          .catch((err: Error) => {
            console.error(err);
            ElMessage.error('查询失败');
          })
      .then(() => {
          // 加载完成后设置 loading 为 false
          this.loading = false;
        });
      }, 1000); // 2秒的加载时长
    },
    saveEdit(row: OrgData) {
      if (row.id) {
        axios.post(`${API_BASE_URL}/org/updateOrg`, row, {
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
      } else {
        axios.post(`${API_BASE_URL}/org/addOrg`, row, {
          headers: {
            Authorization: localStorage.getItem('token'),
          },
        }as any)
            .then((res: AxiosResponse) => {
              console.log(res);
              ElMessage.success('添加成功');
              this.getData();
            })
            .catch((err: Error) => {
              console.error(err);
              ElMessage.error('添加失败');
            });
      }
    },
    editRow(row: OrgData) {
      row.editable = !row.editable;
    },
    addRow() {
      this.queryResult.push({ orgName: '', fatherOrg: '', editable: true });
    },
  },
  mounted() {
    this.getData();
  },
};
</script>
