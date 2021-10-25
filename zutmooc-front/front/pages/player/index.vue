<template>
  <div>
    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css">
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js"/>

    <!-- 定义播放器dom -->
    <div id="J_prismPlayer" class="prism-player"/>
    <a :href="this.fileURL" download="video">点击下载</a>
  </div>
</template>
<script>
import vod from '@/api/vod'

export default {
  name: "palyer",
  layout: 'video',//应用video布局
  // asyncData({ params, error }) {
  //   console.log("===="+this.videoId)
  //
  //   return vod.getPlayAuth(this.videoId)
  //     .then(response => {
  //         return {
  //             playAuth: response.data.data.playAuth
  //         }
  //     })
  // },
  data() {
    return {
      playAuth: null,
      fileURL: null
    }
  },
  methods: {
    getAuth() {
      vod.getPlayAuth(this.videoId).then(res => {
        console.log(res)
        this.playAuth = res.data.data.playAuth
        new Aliplayer({
          id: 'J_prismPlayer',
          vid: this.videoId, // 视频id
          playauth: this.playAuth, // 播放凭证
          encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
          width: '100%',
          height: '500px',
          // 以下可选设置
          cover: 'https://suprit-images.oss-cn-beijing.aliyuncs.com/2021-05-29-070416.jpg', // 封面
          qualitySort: 'asc', // 清晰度排序
          mediaType: 'video', // 返回音频还是视频
          autoplay: false, // 自动播放
          isLive: false, // 直播
          rePlay: false, // 循环播放
          preload: true,
          controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
          useH5Prism: true, // 播放器类型：html5
        }, function (player) {
          console.log('播放器创建成功')
        })
      })
      vod.getFileURL(this.videoId).then(res => {
        this.fileURL = res.data.data.fileURL
      })
    }
  },
  props: ['videoId'],
  mounted() { //页面渲染之后  created
    this.getAuth()
    console.log("------" + this.playAuth);
    console.log("******" + this.videoId)
  }

}
</script>
