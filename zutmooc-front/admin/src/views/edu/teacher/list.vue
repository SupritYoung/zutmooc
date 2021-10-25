<template>
  <div class="app-container">
    教师列表

    <!--条件查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="教师名" />
      </el-form-item>

      <el-form-item>
        <el-select
          v-model="teacherQuery.level"
          clearable
          placeholder="教师头衔"
        >
          <el-option :value="1" label="高级教师" />
          <el-option :value="2" label="首席教师" />
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button
        type="primary"
        icon="el-icon-search"
        @click="getList()"
      >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table :data="list" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? "高级教师" : "首席教师" }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <!-- 使用路由回显数据 -->
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeTeacher(scope.row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>

<script>
// 引入调用teacher.js文件
import teacher from '@/api/edu/teacher'

export default {
  // 写核心代码位置
  data() {
    // 定义数据变量和初始值
    return {
      list: null, // 查询后接口返回的集合
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
      teacherQuery: {}
    }
  },
  created() {
    // 页面渲染之前执行
    // 调用
    this.getList()
  },
  methods: {
    // 创建具体的方法，调用teacher.js中的方法
    // 教师列表方法
    getList(page = 1) {
      // 默认第一页：没传页数则默认1
      this.page = page // 实现翻页：传页数则赋值
      teacher
        .getTeacherListPage(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          // 请求成功
          // response接口返回数据
          // console.log(response)
          this.list = response.data.rows
          this.total = response.total
          console.log(this.total)
          console.log(this.list)
        })
        .catch((error) => {
          // 请求失败
          console.log(error)
        })
    },

    // 删除教师方法
    removeTeacher(id) {
      this.$confirm('此操作将永久删除教师记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 点击确定，执行then方法
          // 调用删除的方法
          teacher.removeTeacher(id).then((response) => {
            // 删除成功
            // 提示信息
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 回到列表页面
            this.getList()
          })
        }) // 点击取消，执行catch方法
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  }
}
</script>
