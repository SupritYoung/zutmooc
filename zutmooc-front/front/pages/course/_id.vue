<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="#" title class="c-999 fsize14">{{
            courseWebVo.subjectLevelOne
          }}</a>
        \
        <span class="c-333 fsize14">{{ courseWebVo.subjectLevelTwo }}</span>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 357px">
          <section class="p-h-video-box" id="videoPlay">
            <img
              height="357px"
              :src="courseWebVo.cover"
              :alt="courseWebVo.title"
              class="dis c-v-pic"
            />
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ courseWebVo.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff">价格：</span>
              <b class="c-yellow" style="font-size: 24px"
              >￥{{ courseWebVo.price }}</b
              >
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14"
              >主讲： {{ courseWebVo.teacherName }}&nbsp;&nbsp;&nbsp;</span
              >
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="收藏" href="#">收藏</a>
              </span>
            </section>
            <section
              v-if="isbuy"
              class="c-attr-mt"
            >
              <a href="#" title="已经购买" class="comm-btn c-btn-3">已经购买</a>
            </section>
            <section v-else-if="Number(courseWebVo.price) === 0" class="c-attr-mt">
              <a @click="createOrders()" href="#" title="免费获取" class="comm-btn c-btn-3">免费获取</a>
            </section>
            <section v-else class="c-attr-mt">
              <a
                @click="createOrders()"
                href="#"
                title="立即购买"
                class="comm-btn c-btn-3"
              >立即购买</a>
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br/>
                <h6 class="c-fff f-fM mt10">{{ courseWebVo.buyCount }}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br/>
                <h6 class="c-fff f-fM mt10">{{courseWebVo.lessonNum}}</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br/>
                <h6 class="c-fff f-fM mt10">{{courseWebVo.viewCount}}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- /课程封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="courseWebVo.description">
                        {{ courseWebVo.description }}
                      </p>
                    </section>
                  </div>
                </div>
                <!-- /课程介绍 -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>课程大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li
                            class="lh-menu-stair"
                            v-for="chapter in chapterVideoList"
                            :key="chapter.id"
                          >
                            <a
                              href="javascript: void(0)"
                              :title="chapter.title"
                              class="current-1"
                            >
                              <em class="lh-menu-i-1 icon18 mr10"></em
                              >{{ chapter.title }}
                            </a>

                            <ol class="lh-menu-ol" style="display: block">
                              <li
                                class="lh-menu-second ml30"
                                v-for="video in chapter.children"
                                :key="video.id"
                              >

                                <a @click="show(video.videoSourceId,video.title)">
                                  <span class="fr">
                                    <i class="free-icon vam mr10">免费试听</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em
                                  >{{ video.title }}
                                </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /课程大纲 -->
              </article>
            </div>
          </section>
        </article>


        <template v-if="this.vid">
          <el-dialog
            :title="videoTitle"
            :visible.sync="centerDialogVisible"
            width="52%"

            center>
            <player :videoId="this.vid"></player>
          </el-dialog>
        </template>

        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">主讲教师</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto">
                  <li>
                    <div class="u-face">
                      <a href="#">
                        <img
                          :src="courseWebVo.avatar"
                          width="50"
                          height="50"
                          alt
                        />
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{
                          courseWebVo.teacherName
                        }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ courseWebVo.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>

    <!-------------------------评论模块---------------------------->
    <div class="mt50 commentHtml">
      <div>
        <h6 class="c-c-content c-infor-title" id="i-art-comment">
          <span class="commentTitle">课程评论</span>
        </h6>
        <section class="lh-bj-list pr mt20 replyhtml">
          <ul>
            <li class="unBr">
              <aside class="noter-pic">
                <img width="50" height="50" class="picImg"
                     :src="userInfo.avatar"></aside>
              <div class="of">
                <section class="n-reply-wrap">
                  <fieldset>
                    <textarea name="" v-model="comment.content" placeholder="输入您要评论的文字" id="commentContent"></textarea>
                  </fieldset>
                  <p class="of mt5 tar pl10 pr10">
                    <span class="fl "><tt class="c-red commentContentmeg" style="display: none;"></tt></span>
                    <input type="button" @click="addComment()" value="回复" class="lh-reply-btn">
                  </p>
                </section>
              </div>
            </li>
          </ul>
        </section>
        <section class="">
          <section class="question-list lh-bj-list pr">
            <ul class="pr10">
              <li v-for="(comment,index) in data.items" v-bind:key="index">
                <aside class="noter-pic">
                  <img width="50" height="50" class="picImg" :src="comment.avatar">
                </aside>
                <div class="of">
                    <span class="fl">
                    <font class="fsize12 c-blue">
                      {{ comment.nickname }}</font>
                    <font class="fsize12 c-999 ml5">评论：</font></span>
                </div>
                <div v-if="comment.memberId == userInfo.id">
                  <input type="button" @click="remove(comment.id)" value="删除" class="lh-reply-btn">
                </div>
                <div class="noter-txt mt5">
                  <p>{{ comment.content }}</p>
                </div>
                <div class="of mt5">
                  <span class="fr"><font class="fsize12 c-999 ml5">{{ comment.gmtCreate }}</font></span>
                </div>
              </li>
            </ul>
          </section>
        </section>

        <!-- 公共分页 开始 -->
        <div class="paging">
          <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
          <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="首页"
            @click.prevent="gotoPage(1)">首</a>
          <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="前一页"
            @click.prevent="gotoPage(data.current-1)">&lt;</a>
          <a
            v-for="page in data.pages"
            :key="page"
            :class="{current: data.current == page, undisable: data.current == page}"
            :title="'第'+page+'页'"
            href="#"
            @click.prevent="gotoPage(page)">{{ page }}</a>
          <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="后一页"
            @click.prevent="gotoPage(data.current+1)">&gt;</a>
          <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="末页"
            @click.prevent="gotoPage(data.pages)">末</a>
          <div class="clear"/>
        </div>
        <!-- 公共分页 结束 -->
      </div>
    </div>

    <!-------------------------评论结束---------------------------->
    <!-- /课程详情 结束 -->
  </div>
</template>

<script>
import courseApi from "@/api/course";
import ordersApi from "@/api/orders";
import player from "@/pages/player/"
import cookie from 'js-cookie'

export default {
  asyncData({params, error}) {
    return {courseId: params.id};
  },
  data() {
    return {
      courseWebVo: {},
      chapterVideoList: [],
      isbuy: false,
      vid: null,
      playAuth: null,
      centerDialogVisible: false,
      videoTitle: null,

      userInfo: {},//用户信息
      teacherId: '',
      data: {},
      page: 1,
      limit: 4,
      comment: {
        content: '',
        courseId: '',
        teacherId: ''
      }
    };
  },
  created() {
    //在页面渲染之前执行
    this.initCourseInfo();
    this.courseId = this.$route.params.id
    this.getUserInfo()
    this.getComment()
  },
  components: {player},
  methods: {
    /*======评论功能====== */
    getComment() {
      courseApi.getComment(this.courseId, this.page, this.limit)
        .then(response => {
          this.data = response.data.data
        })
    },

    //添加评论
    addComment() {
      this.comment.courseId = this.courseId
      console.log(this.comment)
      courseApi.addComment(this.comment)
        .then(response => {
          if (response.data.success) {
            this.$message({
              type: 'success',
              message: '回复成功'
            })
            this.getComment()
            this.comment.content = ''
          } else {
            alert("请登录")
          }
        })
    },
    //分页跳转
    gotoPage(page) {

      courseApi.getComment(this.courseId, page, this.limit).then((response) => {
        this.data = response.data.data;
      })
    },
    //获取用户信息
    getUserInfo() {
      var userStr = cookie.get("guli_ucenter")
      if (userStr) {
        this.userInfo = JSON.parse(userStr)
      }
    },
    //删除评论
    remove(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return courseApi.remove(id)
        })
        .then(response => {
          //刷新页面
          this.getComment()
          this.$message({
            type: 'success',
            message: '删除成功'
          })
        })
        .catch(response => {
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          } else {
            this.$message({
              type: 'error',
              message: '删除失败'
            })
          }
        })
    },
    /*=====================*/
    //查询课程详情信息
    initCourseInfo() {
      courseApi.getCourseInfo(this.courseId).then((response) => {
        (this.courseWebVo = response.data.data.courseWebVo),
          (this.chapterVideoList = response.data.data.chapterVideoList),
          (this.isbuy = response.data.data.isBuy);
      });
    },
    //生成订单
    createOrders() {
      ordersApi.createOrders(this.courseId).then((response) => {
        if (this.courseWebVo.price == 0) {
          this.$message({
            type: 'success',
            message: '恭喜您，免费获取成功'
          })
          this.isbuy = true
        }
          //获取返回订单号
        //生成订单之后，跳转订单显示页面
        else
          this.$router.push({path: "/orders/" + response.data.data.orderId});
      });
    },
    show(id, title) {
      this.vid = id;
      this.videoTitle = title
      this.centerDialogVisible = true
    },
    // getInfo() {
    //   vod.getPlayAuth(this.vid).then((response) => {
    //     console.log("================")
    //     console.log(response);
    //     this.playAuth = response.data.data.playAuth;
    //   });
    // },
    // rednderPlay() {
    //      new Aliplayer({
    //       id: 'J_prismPlayer',
    //       vid: this.vid, // 视频id
    //       playauth: this.playAuth, // 播放凭证
    //       encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
    //       width: '100%',
    //       height: '500px',
    //       // 以下可选设置
    //       cover: 'http://guli.shop/photo/banner/1525939573202.jpg', // 封面
    //       qualitySort: 'asc', // 清晰度排序
    //       mediaType: 'video', // 返回音频还是视频
    //       autoplay: false, // 自动播放
    //       isLive: false, // 直播
    //       rePlay: false, // 循环播放
    //       preload: true,
    //       controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
    //       useH5Prism: true, // 播放器类型：html5
    //   }, function(player) {
    //       console.log('播放器创建成功')
    //   })
    // },
  },
};
</script>
