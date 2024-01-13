<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="300px">
        <admin-nav>
        </admin-nav>
      </el-aside>
      <el-main>
        <div>
          <h3>资产管理</h3>
          <div class="table-container">
            <el-select v-model="queryType" placeholder="选择查询类型">
              <el-option label="资产名称" value="assetName"></el-option>
              <el-option label="资产分类" value="assetCategory"></el-option>
              <el-option label="资产标签" value="assetLabel"></el-option>
              <el-option label="组织结构" value="belongOrg"></el-option>
            </el-select>

            <!-- 添加一个 el-input 元素用于输入查询关键字 -->
            <el-input v-model="queryKeyword" placeholder="输入查询关键字" style="width: 200px;"></el-input>

            <!-- 添加一个查询按钮，点击时调用对应的查询方法 -->
            <el-button type="primary" @click="queryData">查询</el-button>
            <el-button @click="getData">查询全部</el-button>
            <el-button type="primary" @click="addRow">添加</el-button>

            <el-button type="primary" @click="$router.push('/assetCategory')">添加分类</el-button>
            <el-button type="primary" @click="$router.push('/assetLabel')">添加标签</el-button>
            <el-table :data="queryResult">
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column label="资产编号">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetNo }}</span>
                  <el-input v-show="row.editable" v-model="row.assetNo"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetTypeNo" label="资产类型">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetTypeNo }}</span>
                  <el-input v-show="row.editable" v-model="row.assetTypeNo"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetName" label="资产名称">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetName }}</span>
                  <el-input v-show="row.editable" v-model="row.assetName"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetLabel" label="标签">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetLabel }}</span>
                  <el-input v-show="row.editable" v-model="row.assetLabel"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetDesc" label="描述">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetDesc }}</span>
                  <el-input v-show="row.editable" v-model="row.assetDesc"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetCategory" label="分类">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetCategory }}</span>
                  <el-input v-show="row.editable" v-model="row.assetCategory"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetStatus" label="状态">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ getStatusText(row.assetStatus) }}</span>
                  <el-input v-show="row.editable" v-model="row.assetStatus"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="registerTime" label="注册时间">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.registerTime }}</span>
                  <el-input v-show="row.editable" v-model="row.registerTime"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="lastUpdatedTime" label="最后更新时间"></el-table-column>
              <el-table-column prop="belongOrg" label="所属组织">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{getStatusText2( row.belongOrg) }}</span>
                  <el-input v-show="row.editable" v-model="row.belongOrg"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="assetUser" label="使用者">
                <template #default="{ row }">
                  <span v-show="!row.editable">{{ row.assetUser }}</span>
                  <el-input v-show="row.editable" v-model="row.assetUser"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="ip" label="IP"></el-table-column>

              <el-table-column label="操作">
                <template #default="{ row }">
                  <div class="buttons">
                    <el-button type="primary" size="small" @click="editRow(row)">编辑</el-button>
                    <el-button type="success" size="small" @click="saveEdit(row)" :disabled="!row.editable">保存</el-button>
                  </div>

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
  assetNo: "",
  assetTypeNo: "",
  assetName: "",
  assetLabel: "",
  assetDesc: "",
  assetCategory: "",
  assetStatus: "",
  registerTime: "",
  lastUpdatedTime: "",
  belongOrg: "",
  assetUser: "",
  ip: "",
});
const addDialogVisible = ref(false);
const queryType = ref("assetName"); // 默认查询类型为资产名称
const queryKeyword = ref(""); // 查询关键字

export default {
  components: { AdminNav },
  data() {
    return {
      queryResult,
      addFormData,
      addDialogVisible,
      queryType,
      queryKeyword,
    };
  },
  methods: {
    getData() {
      axios({
        method: "get",
        url: API_BASE_URL + "/asset/findAllAsset",
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
    queryData() {
      const queryUrlMap = {
        assetName: "/asset/findAssetByAssetName",
        assetCategory: "/asset/findAssetByAssetCategory",
        assetLabel: "/asset/findAssetByAssetLabel",
        belongOrg: "/asset/findAssetByBelongOrg",
      };

      const queryUrl = `${API_BASE_URL}${queryUrlMap[this.queryType]}?${this.queryType}=${encodeURIComponent(this.queryKeyword)}`;

      axios({
        method: "get",
        url: queryUrl,
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      })
          .then((res) => {
            console.log(res);
            this.queryResult = res.data.map((org) => ({ ...org, editable: false }));
          })
          .catch((err) => {
            console.error(err);
            ElMessage.error("查询失败");
          });
    }
    ,
    saveEdit(row) {
      if (row.id) {
        // 已有 id，执行更新操作
        axios({
          method: "post",
          url: API_BASE_URL + "/asset/updateAsset",
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
      } else {
        // 没有 id，执行新增操作
        axios({
          method: "post",
          url: API_BASE_URL + "/asset/addAsset",
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
      this.queryResult.push({
        assetNo: "",
        assetTypeNo: "",
        assetName: "",
        assetLabel: "",
        assetDesc: "",
        assetCategory: "",
        assetStatus: "",
        registerTime: "",
        lastUpdatedTime: "",
        belongOrg: "",
        assetUser: "",
        ip: "",
        editable: true,
      });
    },
    getStatusText(status) {
      // 根据状态值返回相应的文字
      switch (status) {
        case '0':
          return '使用中';
        case '1':
          return '维修中';
        case '2':
          return '已报废';
        default:
          return '未知状态';
      }
    },
    getStatusText2(status) {
      // 根据状态值返回相应的文字
      switch (status) {
        case 0:
          return '福建师大';
        case 1:
          return '厦门大学';
        case 2:
          return '福州大学';
        default:
          return '北京大学';
      }
    },
  },
  mounted() {
    // 在组件加载时调用getData方法
    this.getData();
  },
  addLabel(){}
};
</script>
<style scoped>
.table-container {
  max-width: 1220px; /* 设置表格容器的最大宽度 */
  margin: 0 auto; /* 居中显示，可选 */
}
.buttons{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>