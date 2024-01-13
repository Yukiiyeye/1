<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav />
      </el-aside>
      <el-main>
        <div>
          <h3>演练任务管理</h3>
          <el-select v-model="queryType" placeholder="选择查询类型">
            <el-option label="任务名称" value="taskName" />
            <el-option label="任务描述" value="taskDesc" />
            <el-option label="任务状态" value="taskStatus" />
          </el-select>

          <el-input v-model="queryKeyword" placeholder="输入查询关键字" style="width: 200px" />
          <el-button :icon="Search" color="pink" @click="queryData">查询</el-button>
          <el-button :icon="Search" color="blue" @click="getData">查询全部</el-button>
          <el-button color="#626aef" :dark="isDark" plain @click="addRow">添加</el-button>
          <el-table
              style="width: 1400px"
              :data="queryResult">
            <el-table-column label="任务名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.taskName }}</span>
                <el-input v-show="row.editable" v-model="row.taskName" />
              </template>
            </el-table-column>
            <el-table-column prop="taskDesc" label="任务描述">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.taskDesc }}</span>
                <el-input v-show="row.editable" v-model="row.taskDesc" />
              </template>
            </el-table-column>
            <el-table-column prop="taskStatus" label="任务状态">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ getStatusText(row.taskStatus) }}</span>
                <el-input v-show="row.editable" v-model="row.taskStatus" />
              </template>
            </el-table-column>
            <el-table-column prop="relatedOrg" label="组织机构">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ getstatusText2(row.relatedOrg) }}</span>
                <el-input v-show="row.editable" v-model="row.relatedOrg" />
              </template>
            </el-table-column>
            <el-table-column prop="joinedTesters" label="测试人员">
              <template #default="{ row }">
                <span v-show="!row.editable || row.taskStatus !== 0">{{ row.joinedTesters }}</span>
                <el-input v-show="row.editable && row.taskStatus === 0" v-model="row.joinedTesters" />
              </template>
            </el-table-column>
            <el-table-column prop="bugNum" label="已发现漏洞数量">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.bugNum }}</span>
                <el-input v-show="row.editable" v-model="row.bugNum" />
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="{ row }">
                <div class="buttons">
                  <el-button type="primary" :icon="Edit" @click="() => editRow(row)"></el-button>
                  <el-button type="success" :icon="Check" @click="() => saveEdit(row)" :disabled="!row.editable"></el-button>
                  <el-button type="primary" @click="open">指派</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import axios, { AxiosResponse } from "axios";
import { ref, onMounted } from 'vue';
import {ElMessage, ElMessageBox} from "element-plus";
import { Search } from '@element-plus/icons-vue';
import AdminNav from "@/components/AdminNav.vue";
import { Edit, Check } from '@element-plus/icons-vue';
const API_BASE_URL = "http://localhost:8080";
const queryResult = ref([]);
const queryType = ref("taskName");
const queryKeyword = ref("");

const getData = (): void => {
  axios.get(`${API_BASE_URL}/task/findAllTask`, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
  }as any)
      .then((res: AxiosResponse) => {
        console.log(res);
        queryResult.value = res.data.map((org) => ({ ...org, editable: false }));
      })
      .catch((err: Error) => {
        console.error(err);
        ElMessage.error("查询失败");
      });
};

const queryData = () => {
  const queryUrlMap = {
    taskName: "/task/findTaskByTaskName",
    taskDesc: "/task/findTaskByTaskDesc",
    taskStatus: "/task/findTaskByTaskStatus",
  };

  const queryUrl = `${API_BASE_URL}${queryUrlMap[queryType.value]}?${queryType.value}=${encodeURIComponent(queryKeyword.value)}`;

  axios.get(queryUrl, {
    headers: {
      Authorization: localStorage.getItem("token"),
    },
  }as any)
      .then((res) => {
        console.log(res);
        queryResult.value = res.data.map((org) => ({ ...org, editable: false }));
      })
      .catch((err) => {
        console.error(err);
        ElMessage.error("查询失败");
      });
};

const saveEdit = (row) => {
  if (row.id) {
    axios.post(`${API_BASE_URL}/task/updateTask`, row, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }as any)
        .then((res) => {
          console.log(res);
          ElMessage.success("保存成功");
          getData();
        })
        .catch((err) => {
          console.error(err);
          ElMessage.error("保存失败");
        });
  } else {
    axios.post(`${API_BASE_URL}/task/addTask`, row, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }as any)
        .then((res) => {
          console.log(res);
          ElMessage.success("添加成功");
          getData();
        })
        .catch((err) => {
          console.error(err);
          ElMessage.error("添加失败");
        });
  }
};

const editRow = (row) => {
  row.editable = !row.editable;
};

const addRow = () => {
  queryResult.value.push({
    taskName: "",
    taskDesc: "",
    taskStatus: "",
    relatedOrg: "",
    joinedTesters: "",
    startTime: "",
    endTime: "",
    bugNum: "",
    editable: true,
  });
};

const getStatusText = (status) => {
  switch (status) {
    case 0:
      return '未开始';
    case 1:
      return '进行中';
    case 2:
      return '已完成';
    default:
      return '未知状态';
  }
};
const getstatusText2 = (status) => {
  switch (status) {
    case 1:
      return '重庆大学';
    case 2:
      return '福州大学';
    case 3:
      return '清华大学';
    case 4:
      return '北京大学';
    case 5:
      return '上海交通大学';
    default:
      return '未知组织机构';
  }
};
const open = async () => {
  try {
    // 向后端发起获取用户列表的请求，假设接口为 /user/findAllUser
    const response = await axios.get(`${API_BASE_URL}/user/findAllUser`, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }as any) as AxiosResponse;

    const userList = response.data; // 假设后端返回一个用户列表数组

    // 构建下拉框选项
    const inputOptions = userList.reduce((options, user) => {
      options[user.id] = user.username;
      return options;
    }, {});

    // 弹出消息框，并让管理员选择用户
    const { value } = await ElMessageBox.prompt('请选择用户', '任务指派', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      inputType: 'select', // 使用下拉框
      inputOptions,
    });

    // 根据选择的用户ID执行相应的逻辑
    if (value) {
      const selectedUser = userList.find(user => user.id === Number(value));
      if (selectedUser) {
        // 在这里可以处理选择的用户信息
        ElMessage({
          type: 'success',
          message: `您选择了用户:${selectedUser.username}`,
        });
      } else {
        ElMessage({
          type: 'error',
          message: '选择的用户不存在',
        });
      }
    } else {
      ElMessage({
        type: 'info',
        message: '选择被取消',
      });
    }
  } catch (error) {
    console.error(error);
    ElMessage.error('获取用户列表失败');
  }
};


onMounted(() => {
  getData();
});
</script>