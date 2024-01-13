<template>
  <div class="all">
  <div class="register">
    <el-form :model="formData" :rules="rules" ref="registerForm" class="register-form" >
      <h3 class="title">Asset管理系统注册</h3>

      <el-form-item label="账号" prop="username">
        <el-input v-model="formData.username" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="formData.password" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="_昵称" prop="name">
        <el-input v-model="formData.name" style="width: 300px;"></el-input>
      </el-form-item>

     <div class="buttons">
        <el-button v-on:click="register" type="success" class="register-button" style="width: 150px">注册</el-button>


        <el-button type="primary" @click="$router.push('/login')" style="width: 150px">登录</el-button>
     </div>
    </el-form>
  </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "Register",
  data() {
    return {
      formData: {
        username: '',
        password: '',
        name: '',
      },
      rules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 5, max: 12, message: "用户名长度必须在5-12位之间", trigger: "blur" },
          { pattern: /^[a-zA-Z0-9]+$/, message: "用户名只能使用英文字母或者数字", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, message: "密码长度必须至少为5位", trigger: "blur" },
          { pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]+$/, message: "密码不能为纯字母或者纯数字", trigger: "blur" },
        ],
      },
      registrationSuccess: false, // 控制消息显示
    };
  },
  methods: {
    register() {
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          axios({
            method: 'post',
            url: 'http://localhost:8080/user/register',
            data: {
              username: this.formData.username,
              password: this.formData.password,
              name: this.formData.name,
            },
          })
              .then((response) => {
                if (response.data.code === 1) {
                  this.$message.error(response.data.message);
                } else {
                  this.$message.success("注册成功！");
                  this.registrationSuccess = true;
                  router.push('/login');
                }
              })
              .catch((error) => {
                console.error(error);
                this.$message.error(error.response.data.error);
              });
        } else {
          this.$message.error("请输入账号和密码");
          return false;
        }
      });
    },
  },

};
</script>

<style scoped>
.register-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 15px 15px 5px 15px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.buttons{
  display: flow;
  justify-content: center;
  align-items: center;
}


.register {
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
}
.all{
  background-image: url("../assets/img/login-bg.jpg");
  background-size: cover;
}
</style>
