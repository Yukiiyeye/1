<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav>
        </admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>资产分类管理</h3>
          <div class="table-container">
            <el-button @click="getData">查询全部</el-button>
            <el-input v-model="addFormData.category" placeholder="输入分类名称" style="width: 200px;"></el-input>
            <el-button type="primary" @click="addCategory">添加</el-button>
            <el-table
                style="width: 900px"
                :data="queryResult"
            >
              <el-table-column prop="id" label="ID">
              </el-table-column>
              <el-table-column label="资产分类">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.category }}</span>
                  <el-input v-show="row.editable" v-model="row.category"></el-input>
                </template>
              </el-table-column>

              <el-table-column label="操作">
                <template #default="{ row }">
                  <el-button type="primary" size="small" @click="editRow(row)">编辑</el-button>
                  <el-button type="success" size="small" @click="saveEdit(row)" :disabled="!row.editable">保存</el-button>
                </template>
              </el-table-column>

            </el-table>
          </div>

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
  category: "",
});

export default {
  components: { AdminNav },
  data() {
    return {
      queryResult,
      addFormData,
    };
  },
  methods: {
    getData() {
      axios({
        method: "get",
        url: API_BASE_URL + "/asset/findAllCategory",
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      })
          .then((res) => {
            console.log(res);
            this.queryResult = res.data.map(org => ({ ...org, editable: false }));
          })
          .catch((err) => {
            console.error(err);
            ElMessage.error("查询失败");
          });
    },
    saveEdit(row) {
      if (row.id) {
        // 已有 id，执行更新操作
        axios({
          method: "post",
          url: API_BASE_URL + "/asset/updateAssetCategory",
          headers: {
            Authorization: localStorage.getItem("token"),
          },
          data: row,
        })
            .then((res) => {
              console.log(res);
              ElMessage.success("保存成功");
              // 可以在保存成功后将编辑状态设为 false，或者重新获取数据
              this.getData();
            })
            .catch((err) => {
              console.error(err);
              ElMessage.error("保存失败");
            });
      }
    },
    editRow(row) {
      // 切换编辑状态
      row.editable = !row.editable;
    },
    addCategory(){
      if (!this.addFormData.category) {
        ElMessage.warning("请输入标签名称");
        return;
      }

      // 构造要添加的标签数据
      const newCategory = {
        category: this.addFormData.category,
        // 可根据需要添加其他属性
      };

      // 发送添加标签的请求
      axios({
        method: "post",
        url: API_BASE_URL + "/asset/addAssetCategory",
        headers: {
          Authorization: localStorage.getItem("token"),
        },
        data: newCategory,
      })
          .then((res) => {
            console.log(res);
            ElMessage.success("添加成功");
            // 重置输入框内容
            this.addFormData.category = "";
            this.getData();
          })
          .catch((err) => {
            console.error(err);
            ElMessage.error("添加失败");
          });
    }
  },
  mounted() {
    // 在组件加载时调用getData方法
    this.getData();
  },
};
</script>
<style scoped>
.table-container {
  max-width: 1220px; /* 设置表格容器的最大宽度 */
  margin: 0 auto; /* 居中显示，可选 */
}
</style>