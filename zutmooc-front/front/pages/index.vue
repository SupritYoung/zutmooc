<template>
  <div>
    <!--  <div class="background">-->
    <!--    <img src="http://suprit-images.oss-cn-beijing.aliyuncs.com/2021-05-06-034758.jpg" width="100%" height="100%" alt=""/>-->
    <!--  </div>-->
    <!--  <div :style="backgroundDiv">-->
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div v-for="banner in bannerList" :key="banner.id" class="swiper-slide" style="background: #FFFFFF;">
          <a target="_blank" :href="banner.linkUrl">
            <img :src="banner.imageUrl" :alt="banner.title">
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="course in eduList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="course.cover"
                        :alt="course.title"
                        height="200" width="300"
                      >
                      <div class="cc-mask">
                        <a :href="'/course/'+course.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" :title="course.title" class="course-title fsize18 c-333">{{ course.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green" v-if="Number(course.price) === 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634人学习</i>
                        |
                        <i class="c-999 f-fA">9634评论</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/course" title="全部课程" class="comm-btn c-btn-2">全部课程</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校课程 结束 -->

      <!-- 推荐课程 开始 -->
      <div v-if="isLogin()">
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">为您推荐的课程</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="rcm">
                <li v-for="course in rcmList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="course.cover"
                        class="img-responsive"
                        :alt="course.title"
                        height="200" width="300"
                      >
                      <div class="cc-mask">
                        <a :href="'/course/'+course.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" :title="course.title" class="course-title fsize18 c-333">{{ course.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green" v-if="Number(course.price) === 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634人学习</i>
                        |
                        <i class="c-999 f-fA">9634评论</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">推荐该课程的理由是：{{ course.reason }}</i>
                      </span>
                    </section>
                    推荐该课程的理由是：{{ course.reason }}
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <!--            <section class="tac pt20">-->
            <!--              <a href="#" title="推荐课程" class="comm-btn c-btn-2">推荐课程</a>-->
            <!--            </section>-->
          </div>
        </section>
      </div>
      <!-- /推荐课程 结束 -->

      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="teacher in teacherList" :key="teacher.id">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a href="/teacher/1" :title="teacher.name">
                        <img :alt="teacher.name" :src="teacher.avatar">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a href="/teacher/1" :title="teacher.name" class="fsize18 c-666">{{ teacher.name }}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{ teacher.career }}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p
                        class="c-999 f-fA"
                      >{{ teacher.intro }}</p>
                    </div>
                  </section>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/teacher" title="全部教师" class="comm-btn c-btn-2">全部教师</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import banner from '@/api/banner'
import index from '@/api/index'
import rcm from '@/api/recommend'
import cookie from "js-cookie";

export default {
  data() {
    return {
      // backgroundDiv: {
      //   backgroundImage: "url(" + require("/assets/img/background.jpeg") + ")",
      // },
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      },
      //banner数组
      bannerList: [],
      eduList: [],
      teacherList: [],

      //推荐课程列表
      rcmList: [],

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
    //调用查询banner的方法
    this.getBannerList()
    //调用查询热门课程和名师的方法
    this.getHotCourseTeacher()
    //获取用户信息
    this.getUserInfo()
  },
  methods: {
    //查询热门课程和名师
    getHotCourseTeacher() {
      index.getIndexData()
        .then(response => {
          this.eduList = response.data.data.eduList
          this.teacherList = response.data.data.teacherList
        })
    },
    //查询推荐课程（只有在登录之后才能推荐）
    getRecommend() {
      rcm.getRecommend(this.loginInfo.id)
        .then(response => {
          this.rcmList = response.data.data.list
        })
    },
    //查询banner数据
    getBannerList() {
      banner.getListBanner()
        .then(response => {
          this.bannerList = response.data.data.list
          console.log(this.bannerList)
        })
    },
    //创建方法，从cookie获取用户信息
    getUserInfo() {
      //从cookie获取用户信息
      var userStr = cookie.get('guli_ucenter')
      // 把字符串转换json对象(js对象)
      if (userStr) {
        this.loginInfo = JSON.parse(userStr)
      }
    },
    isLogin() {
      if (this.loginInfo.id) {
        this.getRecommend()
      }
      return this.loginInfo.id
    }

  }
}
</script>
