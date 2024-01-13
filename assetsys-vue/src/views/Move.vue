<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav>
        </admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>横向移动管理</h3>
          <!-- 添加一个查询按钮，点击时调用对应的查询方法 -->
          <el-button @click="getData">查询全部</el-button>
          <el-button type="primary" @click="addRow">添加</el-button>
          <el-table :data="queryResult">

            <!-- 列配置 -->
            <el-table-column label="任务id">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.mytask_id }}</span>
                <el-input v-show="row.editable" v-model="row.mytask_id"></el-input>
              </template>
            </el-table-column>

            <el-table-column label="移动名称">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.moveName }}</span>
                <el-input v-show="row.editable" v-model="row.moveName"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="tester" label="测试员">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.tester }}</span>
                <el-input v-show="row.editable" v-model="row.tester"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="attackTime" label="attack时间">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.attackTime }}</span>
                <el-input v-show="row.editable" v-model="row.attackTime"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="targetIP" label="目标ip">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.targetIP }}</span>
                <el-input v-show="row.editable" v-model="row.targetIP"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="exploitVuls" label="exploitVuls">
              <template #default="{ row }">
                <span v-show="!row.editable">{{row.exploitVuls}}</span>
                <el-input v-show="row.editable" v-model="row.exploitVuls"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="movePath" label="移动路径">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.movePath }}</span>
                <el-input v-show="row.editable" v-model="row.movePath"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="moveResult" label="移动结果">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.moveResult }}</span>
                <el-input v-show="row.editable" v-model="row.moveResult"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="moveTime" label="移动时间">
              <template #default="{ row }">
                <span v-show="!row.editable">{{ row.moveTime }}</span>
                <el-input v-show="row.editable" v-model="row.moveTime"></el-input>
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
  moveName: "",
  tester: "",
  attackTime: "",
  targetIP: "",
  exploitVuls: "",
  movePath: "",
  moveResult: "",
  moveTime: "",


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
        url: `${API_BASE_URL}/move/findMoveByTester?tester=${localStorage.getItem("username")}`,
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      })
          .then((response) => {
            // 获取正确的id值
            const tester = localStorage.getItem("username");
            console.log("username:",tester)

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
          url: API_BASE_URL + "/move/addMove",
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
      this.queryResult.push({ exploitName: "", tester: localStorage.getItem("username"),exploitVul:"",targetIP:"", editable: true });
    },

  },
  mounted() {
    // 在组件加载时调用getData方法
    this.getData();
  },

};
</script>

