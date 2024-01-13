<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="350px">
        <div class="top_menu">
          <el-page-header @click="$router.push('/main')">
            <template #content>
              <div class="flex items-center">
                <span  > 管理员 </span>
                <el-tag>Default</el-tag>
              </div>
            </template>

          </el-page-header>
        </div>
        <div class="admin_menu">
          <el-row class="tac">
            <el-col :span="12">
              <h5 class="mb-2">活动管理</h5>
              <el-menu
                  default-active="2"
                  class="el-menu-vertical-demo"
              >
                <el-sub-menu index="1">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span @click="$router.push('/admin/userManage')">用户管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="1-1">item one</el-menu-item>
                    <el-menu-item index="1-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="1-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="1-4">
                    <template #title>item four</template>
                    <el-menu-item index="1-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="2">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>活动管理</span>
                  </template>

                  <el-sub-menu index="2-1" @click="handleQueryClick">
                    <template #title>查询活动</template>
                    <el-menu-item index="2-1-1">根据id查询</el-menu-item>
                    <el-menu-item index="2-1-2">根据名称查询</el-menu-item>
                    <el-menu-item index="2-1-3">根据状态查询</el-menu-item>
                    <el-menu-item index="2-1-4">查询全部活动</el-menu-item>

                  </el-sub-menu>
                  <el-menu-item index="2-2" @click="handleAddClick">添加活动</el-menu-item>

                  <el-menu-item index="2-3">item three</el-menu-item>
                  <el-sub-menu index="2-4">
                    <template #title>item four</template>
                    <el-menu-item index="2-4-1">item one</el-menu-item>
                  </el-sub-menu>

                </el-sub-menu>
                <el-sub-menu index="3">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>课程管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="3-1">item one</el-menu-item>
                    <el-menu-item index="3-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="3-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="3-4">
                    <template #title>item four</template>
                    <el-menu-item index="3-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="4">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>学习单元管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="4-1">item one</el-menu-item>
                    <el-menu-item index="4-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="4-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="4-4">
                    <template #title>item four</template>
                    <el-menu-item index="4-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="5">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>学生管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="5-1">item one</el-menu-item>
                    <el-menu-item index="5-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="5-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="5-4">
                    <template #title>item four</template>
                    <el-menu-item index="5-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="6">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>教师管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="6-1">item one</el-menu-item>
                    <el-menu-item index="6-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="6-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="6-4">
                    <template #title>item four</template>
                    <el-menu-item index="6-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>
                <el-sub-menu index="7">
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>课堂管理</span>
                  </template>
                  <el-menu-item-group title="Group One">
                    <el-menu-item index="7-1">item one</el-menu-item>
                    <el-menu-item index="7-2">item two</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="Group Two">
                    <el-menu-item index="7-3">item three</el-menu-item>
                  </el-menu-item-group>
                  <el-sub-menu index="7-4">
                    <template #title>item four</template>
                    <el-menu-item index="7-4-1">item one</el-menu-item>
                  </el-sub-menu>
                </el-sub-menu>

              </el-menu>
            </el-col>

          </el-row>


        </div>
      </el-aside>

      <div v-if="showInputBoxes" >
        <el-form
            label-width="100px"
            :model="formLabelAlign"
            style="max-width: 460px"
        >
          <el-form-item label="id">
            <el-input v-model="formLabelAlign.id" />
            <el-button v-on:click="findById">查询</el-button>
          </el-form-item>
          <el-form-item label="name">
            <el-input v-model="formLabelAlign.name" />
            <el-button v-on:click="findByName">查询</el-button>
          </el-form-item>
          <el-form-item label="status">
            <el-input v-model="formLabelAlign.status" />
            <el-button v-on:click="findByStatus">查询</el-button>
          </el-form-item>
          <el-button v-on:click="findAll">查询全部活动</el-button>
        </el-form>
        <el-table :data="queryResult" v-if="queryResult.length > 0">
          <!-- 使用 el-table-column 定义表格列 -->
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="Name" prop="name"></el-table-column>
          <el-table-column label="Category" prop="category"></el-table-column>
          <el-table-column label="title" prop="title"></el-table-column>
          <el-table-column label="creator" prop="creator"></el-table-column>
          <el-table-column label="abstract_" prop="abstract_"></el-table-column>
          <el-table-column label="detailed_desc" prop="detailed_desc"></el-table-column>
          <el-table-column label="status" prop="status"></el-table-column>
          <el-table-column label="create_time" prop="create_time"></el-table-column>
          <el-table-column label="archived_time" prop="archived_time"></el-table-column>
          <el-table-column label="start_time" prop="start_time"></el-table-column>
          <el-table-column label="end_time" prop="end_time"></el-table-column>
          <el-table-column label="pic_urls" prop="pic_urls"></el-table-column>


          <!-- ... （根据实际数据结构添加其他列） -->
        </el-table>
        <!-- 分页组件 -->
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalItems"
        />
      </div>


      <div class="addbox">
        <div v-if="showAddBox">
          <el-form
              label-width="100px"
              :model="addActivity"
              style="max-width: 460px"
          >
            <el-form-item label="name">
              <el-input v-model="addActivity.name" />
            </el-form-item>
            <el-form-item label="category">
              <el-input v-model="addActivity.category" />
            </el-form-item>
            <el-form-item label="title">
              <el-input v-model="addActivity.title" />
            </el-form-item>
            <el-form-item label="creator">
              <el-input v-model="addActivity.creator" />
            </el-form-item>
            <el-form-item label="abstract_">
              <el-input v-model="addActivity.abstract_" />
            </el-form-item>
            <el-form-item label="detailed_desc">
              <el-input v-model="addActivity.detailed_desc" />
            </el-form-item>
            <el-button v-on:click="addAct">添加活动</el-button>

          </el-form>
        </div>
      </div>


    </el-container>
  </div>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import { Location } from '@element-plus/icons-vue';
import axios from "axios";
import router from "@/router";
import {ElMessage} from "element-plus";
import {handleCurrentChange} from "element-plus/es/components/tree/src/model/util";
const API_BASE_URL = 'http://localhost:8080'; // 后端服务的地址
const showInputBoxes = ref(false);
const showAddBox = ref(false);
const queryResult = ref([]); // 查询结果数组，用于存放从后端获取的数据
const currentPage = ref(1); // 当前页码
const pageSize = ref(10); // 每页显示条数
const totalItems = ref(0); // 总条数
const pagedQueryResult = ref([]); // 当前页的查询结果

const handleCurrentChange = async (page) => {
  currentPage.value = page;
  await fetchData(); // 更新当前页的数据
};

const fetchData = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/activity/findAll`, {
      params: {
        page: currentPage.value,
        size: pageSize.value,
      },
    });

    totalItems.value = response.data.totalColumn;
    pagedQueryResult.value = response.data.content;
  } catch (error) {
    console.error(error);
  }
};

// 在组件初始化时调用一次 fetchData 获取第一页数据
onMounted(fetchData);
const handleQueryClick = () => {
  // 显示查询表单，隐藏添加表单
  showInputBoxes.value = true;
  showAddBox.value = false;
};

const handleAddClick = () => {
  // 显示添加表单，隐藏查询表单
  showAddBox.value = true;
  showInputBoxes.value = false;
};



const resultItemDetails = ref(null);

const addActivity = reactive(
    {
      name:'',
      category:'',
      title:'',
      creator:'',
      abstract_:'',
      detailed_desc:'',

    }
)
const formLabelAlign = reactive({
  id: '',
  name: '',
  status: '',
})

const addAct =async ()=>{
  try {
    // 向后端发送添加活动的请求
    const response = await axios.post(`${API_BASE_URL}/activity/save`, addActivity);
    // console.log(response.data); // 输出后端返回的消息
    ElMessage({
      message: response.data,
      type: 'success',
    })

  } catch (error) {
    console.error(error);
    // 处理失败的逻辑，例如显示错误消息
  }
};
const findById = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/activity/findById`, {
      params: { id: formLabelAlign.id },
    });
    queryResult.value = [response.data]; // 使用从服务器接收到的数据更新 queryResult
  } catch (error) {
    console.error(error);
  }
};

const findByName = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/activity/findByName`, {
      params: { name: formLabelAlign.name },
    });
    queryResult.value = response.data; // 使用从服务器接收到的数据更新 queryResult
  } catch (error) {
    console.error(error);
  }
};

const findByStatus = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/activity/findByStatus`, {
      params: { status: formLabelAlign.status },
    });
    queryResult.value = response.data; // 使用从服务器接收到的数据更新 queryResult
  } catch (error) {
    console.error(error);
  }
};

const findAll = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/activity/findAll`);
    queryResult.value = response.data; // 使用从服务器接收到的数据更新 queryResult
  } catch (error) {
    console.error(error);
  }
};

const showResultMessageBox = (resultItem) => {
  // ... (已有的代码)

  // 可选地，在显示详细消息时更新 queryResult
  queryResult.value = [resultItem];

}

</script>
<style scoped>
.top_menu
{
  margin-left: 30px;
}
.admin_menu{
  width: 350px;
}
.addbox{
  margin-right: 500px;
}

</style>
