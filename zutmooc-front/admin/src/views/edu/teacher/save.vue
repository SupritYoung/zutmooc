<template>
  <div class="app-container">
    教师添加
    <el-form label-width="120px">
      <el-form-item label="教师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="教师排序">
        <el-input-number
          v-model="teacher.sort"
          :min="0"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="教师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级教师" />
          <el-option :value="2" label="首席教师" />
        </el-select>
      </el-form-item>
      <el-form-item label="教师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="教师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 教师头像 -->
      <el-form-item label="教师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar" />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
        >更换头像
        </el-button>
        <!--
      v-show：是否显示上传组件
      :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      :url：后台上传的url地址
      @close：关闭上传组件
      @crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduoss/fileoss'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
        >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import teacher from '@/api/edu/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  // 声明引入的组件
  components: { ImageCropper, PanThumb },
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },

      imagecropperShow: false, // 上传弹框组件是否显示
      imagecropperKey: 0, // 上传组件key值
      BASE_API: process.env.BASE_API, // 获取dev.env.js里面地址
      saveBtnDisabled: false // 保存按钮是否禁用
    }
  },
  // vue中的监听器
  watch: {
    // 监视路由变化，当路由发生变化时执行该方法
    $route(to, from) {
      this.init()
    }
  },
  // 页面渲染之前执行
  created() {
    this.init()
  },
  methods: {
    // 关闭上传弹框的方法
    close() {
      this.imagecropperShow = false
      // 上传组件初始化（防止bug）
      this.imagecropperKey = this.imagecropperKey + 1
    },
    // 上传成功方法
    cropSuccess(data) {
      this.imagecropperShow = false
      // 上传之后接口返回图片地址
      this.teacher.avatar = data.url
      this.imagecropperKey = this.imagecropperKey + 1
    },
    // 抽取重复代码
    init() {
      // 添加和更新都需要填写表单，但添加更新需要回显数据，而添加不用
      // 回显数据：点击更新，表单中带有该老师的信息数据
      // 判断：如果路由路径中有id值，则判断为更新操作，回显数据
      if (this.$route.params && this.$route.params.id) {
        // 从路径中获取id值
        const id = this.$route.params.id
        // 调用根据id查询的方法
        this.getTeacher(id)
      } else {
        this.teacher = {}
      }
    },
    // 查询指定id的教师信息
    getTeacher(id) {
      teacher.getTeacher(id).then((resposne) => {
        this.teacher = resposne.data.teacher
      })
    },
    saveOrUpdate() {
      // 判断是修改还是添加，根据teacher有没有id
      if (!this.teacher.id) {
        this.saveTeacher()
      } else {
        this.updateTeacher()
      }
    },
    // 修改教师的方法
    updateTeacher() {
      teacher.updateTeacher(this.teacher).then((response) => {
        // 提示信息
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        // 跳回列表页面（路由跳转：重定向）
        this.$router.push({ path: '/teacher/table' })
      })
    },
    // 添加教师的方法
    saveTeacher() {
      teacher.addTeacher(this.teacher).then((response) => {
        // 提示信息
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        // 跳回列表页面（路由跳转：重定向）
        this.$router.push({ path: '/teacher/table' })
      })
    }
  }
}
</script>
