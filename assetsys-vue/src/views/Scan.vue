<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav>
        </admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>我的漏洞扫描</h3>
          <el-button :icon="Search" color="blue" @click="getData">查询全部</el-button>
          <el-button color="#626aef" :dark="isDark" plain @click="addRow">添加</el-button>
          <el-table :data="queryResult">

            <!-- 列配置 -->
            <el-table-column label="任务id">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.mytask_id }}</span>
                <el-input v-show="row.editable" v-model="row.mytask_id"></el-input>
              </template>
            </el-table-column>

            <el-table-column label="扫描名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.scanName }}</span>
                <el-input v-show="row.editable" v-model="row.scanName"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="tester" label="测试员">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.tester }}</span>
                <el-input v-show="row.editable" v-model="row.tester"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="scanTarget" label="目标">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.scanTarget }}</span>
                <el-input v-show="row.editable" v-model="row.scanTarget"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="scanPolicy" label="扫描策略">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.scanPolicy }}</span>
                <el-input v-show="row.editable" v-model="row.scanPolicy"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="scanTime" label="扫描时间">
              <template #default="{ row }">
                <span v-show="!row.editable">{{row.scanTime}}</span>
                <el-input v-show="row.editable" v-model="row.scanTime"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="foundVul" label="foundVul">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.foundVul }}</span>
                <el-input v-show="row.editable" v-model="row.foundVul"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="vulNo" label="漏洞id">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.vulNo }}</span>
                <el-input v-show="row.editable" v-model="row.vulNo"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="scanTools" label="扫描工具">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.scanTools }}</span>
                <el-input v-show="row.editable" v-model="row.scanTools"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="riskLevel" label="风险等级">
              <template #default="{ row }">
                <span v-show="!row.editable" :style="getRiskLevelStyle(row.riskLevel)">{{ row.riskLevel }}</span>
                <el-input v-show="row.editable" v-model="row.riskLevel"></el-input>
              </template>

            </el-table-column>
            <el-table-column label="操作">
              <template #default="{ row }">
                <el-button type="success" size="mini" @click="saveEdit(row)" :disabled="!row.editable">保存</el-button>
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
  mytask_id: "",
  scanName: "",
  tester: "",
  scanTarget: "",
  scanPolicy: "",
  scanTime: "",
  foundVul: "",
  vulNo: "",
  scanTools: "",
  riskLevel: "",
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
        url: `${API_BASE_URL}/scan/findScanByTester?tester=${localStorage.getItem("username")}`,
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      })
          .then((response) => {
            // 获取正确的id值
            const tester = localStorage.getItem("username");
            console.log("username:",tester)

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
    saveEdit(row) {
     {
        // 没有 id，执行新增操作
        axios({
          method: "post",
          url: API_BASE_URL + "/scan/addScan",
          headers: {
            Authorization: localStorage.getItem("token"),
          },
          data: row,
        })
            .then((res) => {
              console.log(res);
              ElMessage.success("添加成功");
              this.getData();
            })
            .catch((err) => {
              console.error(err);
              ElMessage.error("添加失败");
            });
      }
    },
    editRow(row) {
      // 切换编辑状态
      row.editable = !row.editable;
    },
    addRow() {
      // 添加一行新的空数据
      this.queryResult.push({ scanName: "", tester: localStorage.getItem("username"),scanTarget:"",scanPolicy:"",foundVul:"",vulNo:"",scanTools:"",riskLevel:"", editable: true });
    },
    getRiskLevelStyle(riskLevel) {
      // 根据风险等级返回相应的颜色
      switch (riskLevel) {
        case '1':
        case '2':
          return { color: 'green' };
        case '3':
        case '4':
          return { color: 'yellow' };
        case '5':
          return { color: 'red' };
        default:
          return {}; // 默认情况下，不设置特定颜色
      }
    },

  },
  mounted() {
    // 在组件加载时调用getData方法
    this.getData();
  },

};
</script>

