<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav>
        </admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>我的演练任务</h3>
          <!-- 添加一个查询按钮，点击时调用对应的查询方法 -->
          <el-button @click="getData">查询全部</el-button>
          <el-table :data="queryResult">

            <!-- 列配置 -->
            <el-table-column label="任务id">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.task_id }}</span>
                <el-input v-show="row.editable" v-model="row.task_id"></el-input>
              </template>
            </el-table-column>

            <el-table-column label="演练任务名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.taskName }}</span>
                <el-input v-show="row.editable" v-model="row.taskName"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="relatedOrg" label="组织结构名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ getStatusText2(row.relatedOrg) }}</span>
                <el-input v-show="row.editable" v-model="row.relatedOrg"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="startTime" label="开始时间">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.startTime }}</span>
                <el-input v-show="row.editable" v-model="row.startTime"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.endTime }}</span>
                <el-input v-show="row.editable" v-model="row.endTime"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="taskStatus" label="任务状态">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ getStatusText(row.taskStatus) }}</span>
                <el-input v-show="row.editable" v-model="row.taskStatus"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="finished_status" label="完成状态">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ getFinishedStatusText(row.finished_status) }}</span>
                <el-input v-show="row.editable" v-model="row.finished_status"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="submit_scan_num" label="漏洞扫描个数">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.submit_scan_num }}</span>
                <el-input v-show="row.editable" v-model="row.submit_scan_num"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="submit_exploit_num" label="漏洞利用个数">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.submit_exploit_num }}</span>
                <el-input v-show="row.editable" v-model="row.submit_exploit_num"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="submit_move_num" label="横向移动个数">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.submit_move_num }}</span>
                <el-input v-show="row.editable" v-model="row.submit_move_num"></el-input>
              </template>

            </el-table-column>

            <el-table-column label="操作">
              <template #default="{ row }">
                <div class="buttons">
                  <el-button :disabled="disableAcceptButton(row)" type="primary" size="small" @click="accept(row)">接受</el-button>
                  <el-button :disabled="disableFinishButton(row)" type="success" size="small" @click="finish(row)">完成</el-button>
                  <el-button :disabled="disableRejectButton(row)" type="danger" size="small" @click="reject(row)">拒绝</el-button>
                </div>


              </template>
            </el-table-column>


          </el-table>

        </div>
      </el-main>
    </el-container>
  </div>

</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
import { ref } from "vue";
import AdminNav from "@/components/AdminNav.vue";

const API_BASE_URL = "http://localhost:8080"; // 后端服务的地址
const queryResult = ref([]);
const addFormData = ref({
  task_id: "",
  taskName: "",
  relatedOrg: "",
  startTime: "",
  endTime: "",
  taskStatus: "",
  finished_status: "",
  submit_scan_num: "",
  submit_exploit_num: "",
  submit_move_num: "",
});
const addDialogVisible = ref(false);
export default {
  components: { AdminNav },
  data() {
    return {
      queryResult,
      addFormData,
      addDialogVisible,
      userInfo: null,
      currentFinishedStatus: 0,
    };
  },
  methods: {
    getData() {
      axios({
        method: "get",
        url: `${API_BASE_URL}/mytask/findMytaskByUser_id?user_id=${localStorage.getItem("id")}`,
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      })
          .then((response) => {
            // 获取正确的id值
            const user_id = localStorage.getItem("id");
            const username = localStorage.getItem("username");
            console.log("username:",username)
            console.log("User ID:", user_id);

            // 处理请求成功的逻辑
            // this.currentFinishedStatus = response.data.finished_status;
            this.queryResult = response.data;
          })
          .catch((error) => {
            // 处理请求失败的逻辑
            ElMessage.error("查询失败");
            console.error("Error:", error);
          });
    },
    getStatusText(status) {
      // 根据状态值返回相应的文字
      switch (status) {
        case "0":
          return '进行中';
        case "1":
          return '已结束';
        default:
          return '未知状态';
      }
    },
    getFinishedStatusText(status) {
      // 根据状态值返回相应的文字
      switch (status) {
        case 0:
          return '待接受';
        case 1:
          return '已接受';
        case 2:
          return '已完成';
        case 3:
          return '已拒绝';
        default:
          return '未知状态';
      }
    },
    getStatusText2(status) {
      // 根据状态值返回相应的文字
      switch (status) {
        case 0:
          return '福建师范大学';
        case 1:
          return '厦门大学';
        case 2:
          return '福州大学';
        case 3:
          return '北京大学';
        case 4:
          return '清华大学';
        default:
          return '未知状态';
      }
    },
    reject(row){
      const task_id = row.task_id;
      axios(
          {
            method: "post",
            url: `${API_BASE_URL}/mytask/updateMytaskStatus`,
            headers: {
              Authorization: localStorage.getItem("token"),
            },
            data: {
              task_id: task_id,
              finished_status: 3,
            },
          })
          .then(response => {
            // 请求成功
            // 弹出成功提示消息框
            ElMessage.warning('任务拒绝成功');
          })
          .catch(error => {
            // 请求失败
            // 弹出失败提示消息框
            ElMessage.error('任务更新失败，请重试');
          });
    },
    accept(row) {
      const task_id = row.task_id;
      axios({
        method: "post",
        url: `${API_BASE_URL}/mytask/updateMytaskStatus`,
        headers: {
          Authorization: localStorage.getItem("token"),
        },
        data: {
          task_id: task_id,
          finished_status: 1,
        },
      })
          .then(response => {
            // 请求成功
            // 弹出成功提示消息框
            ElMessage.success('任务接受成功');
          })
          .catch(error => {
            // 请求失败
            // 弹出失败提示消息框
            ElMessage.error('任务更新失败，请重试');
          });
    },
    finish(row){
      const task_id = row.task_id;
      axios(
          {
            method: "post",
            url: `${API_BASE_URL}/mytask/updateMytaskStatus`,
            headers: {
              Authorization: localStorage.getItem("token"),
            },
            data: {
              task_id: task_id,
              finished_status: 2,
            },
          })
          .then(response => {
            // 请求成功
            // 弹出成功提示消息框
            ElMessage.success('任务完成！');
          })
          .catch(error => {
            // 请求失败
            // 弹出失败提示消息框
            ElMessage.error('任务更新失败，请重试');
          });
    },
    disableAcceptButton(row) {
      return row.finished_status !== 0;
    },

    disableFinishButton(row) {
     return row.finished_status !== 1;
    },

    disableRejectButton(row) {
     return row.finished_status !== 0;

    },

  },
  mounted() {
    // 在组件加载时调用getData方法
    this.getData();
  },

};
</script>

<style scoped>
.buttons {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
</style>