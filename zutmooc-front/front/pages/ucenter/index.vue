<template>
  <div>
    <div class="msg">
      欢迎来到我要自学网！好的学校，没有围墙。
      <br>
      Welcome to I want to learn by myself! Good schools have no walls.
      <br>
    </div>
    <div class="context">
      您的用户id：{{this.loginInfo.id}}
    </div>
    <div class="context">
      您的用户名：{{this.loginInfo.nickname}}
    </div>
    <div class="context">
      您的手机号：{{this.loginInfo.mobile}}
    </div>
  </div>
</template>

<script>
import cookie from "js-cookie";

export default {
  data() {
    return {
      //用户数据（登录后才给推荐）
      token: '',
      loginInfo: {
        id: '',
        age: '',
        avatar: '',
        mobile: '',
        nickname: '',
        sex: ''
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    //创建方法，从cookie获取用户信息
    getUserInfo() {
      //从cookie获取用户信息
      var userStr = cookie.get('guli_ucenter')
      // 把字符串转换json对象(js对象)
      if (userStr) {
        this.loginInfo = JSON.parse(userStr)
      }
    }
  }
}
</script>

<style>
.msg {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 10vh;
  font-size: 30px;
}
.context {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 1vh;
  font-size: 20px;
}
</style>
