<template>
  <div class="all">
  <div class="login">
    <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-width="80px" class="login-form">
      <h3 class="title">Asset后台管理</h3>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>

        <div class="buttons">
          <el-button type="success" @click="login" :loading="loading" style="width: 150px">
            {{ loading ? '登录中...' : '登录' }}
          </el-button>

          <el-button type="primary" @click="$router.push('/register')" style="width: 150px">立即注册</el-button>
        </div>



      </el-form-item>
    </el-form>
    <div class="el-login-footer">
      <span>Copyright © yezzy All Rights Reserved.</span>
    </div>
  </div>
  </div>
</template>



<script>
import axios from "axios";
import router from "@/router";
import Cookies from "js-cookie";
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        rememberMe: false,
      },
      loginRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
      },
      loading: false,
    };
  },
  methods: {
    login() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          axios({
            method: "post",
            url: "http://localhost:8080/user/login",
            data: {
              username: this.loginForm.username,
              password: this.loginForm.password,
            },
          })
              .then((response) => {
                // 判断后端返回的响应中的 code 是否为 0，表示成功
                if (response.data.code === 0) {

                  const token = response.data.data.token;
                  const id = response.data.data.id;
                  const role = response.data.data.role;
                  const username = response.data.data.username;
                  const expirationTime = new Date().getTime() + 60 * 60 * 1000;

                  // 安全地存储令牌，例如使用localStorage
                  localStorage.setItem("token", token);
                  localStorage.setItem("id",id)
                  localStorage.setItem("role",role)
                  localStorage.setItem("username",username)
                  console.log(response)
                  console.log(response.data.data.id)
                  console.log(response.data.data.username)
                  console.log(response.data.data.role)


                  // 设置 axios 请求头，将令牌发送到后端
                  axios.defaults.headers.common["Authorization"] = `${token}`;

                  this.$message.success("登录成功！");
                  router.push("/admin");
                } else {
                  // 登录失败，显示后端返回的错误信息
                  this.$message.error(response.data.message);
                }
              })
              .catch((error) => {
                console.error(error);
                // 处理其他可能的错误
                this.$message.error("登录失败，请稍后重试");
              })
              .finally(() => {
                this.loading = false; // 请求完成后，设置 loading 为 false
              });
        } else {
          this.$message.error("请输入用户名和密码");
          return false;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : password,
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
  },
};
</script>

<style>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;


}

.buttons{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.all {
  background-image: url("../assets/img/login-bg.jpg");
  background-size: cover;
  height: 100vh; /* 占据整个视口高度 */
}
</style>
