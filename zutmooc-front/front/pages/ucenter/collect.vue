<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section>
        <div>
          <section class="c-infor-tabTitle c-tab-title">
            <a href="javascript: void(0)" title="我的收藏" class="current">
              收藏列表
            </a>
          </section>
        </div>
        <div class="mt40">
          <section v-if="collectList.length === 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">您还没有收藏视频哦！</span>
          </section>

          <!-- 表格 -->
          <el-table
            v-if="collectList.length > 0"
            :data="collectList"
            border
            fit
            highlight-current-row>

            <el-table-column label="视频信息" align="center" >
              <template slot-scope="scope">
                <div class="info" >
                  <div class="pic">
                    <img :src="scope.row.cover" alt="scope.row.title" width="100px">
                  </div>
                  <div class="title">
                    <a :href="'/course/'+scope.row.courseId">{{ scope.row.title }}</a>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="作者名称" align="center">
              <template slot-scope="scope">
                {{ scope.row.teacherName }}
              </template>
            </el-table-column>
            <el-table-column label="价格" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.price }}
              </template>
            </el-table-column>
            <el-table-column label="收藏时间" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreate }}
              </template>
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
              <template slot-scope="scope">
                <a href="javascript:void(0);" title="取消收藏" @click="removeCollect(scope.row.courseId)">取消收藏</a>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </section>
    </div>
  </article>
</template>
<script>
import collectApi from '~/api/collect'

export default {

  data() {
    return {
      collectList: []
    }
  },

  created() {
    this.fetchCollectList()
  },

  methods: {

    fetchCollectList() {
      collectApi.getCollectList().then(response => {
        this.collectList = response.data.items
      })
    },

    removeCollect(courseId) {
      collectApi.removeById(courseId).then(response => {
        this.$message.success(response.message)
        this.fetchCollectList()// 刷新列表
      })
    }
  }
}
</script>
