import request from '@/utils/request'

export default {
  //条件分页课程查询的方法
  getCourseList(page,limit,searchObj) {
    return request({
      url: `/eduservice/coursefront/getFrontCourseList/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  //查询所有分类的方法
  getAllSubject() {
    return request({
      url: '/eduservice/subject/getAllSubject',
      method: 'get'
    })
  },
  //课程详情的方法
  getCourseInfo(id) {
    return request({
      url: '/eduservice/coursefront/getFrontCourseInfo/'+id,
      method: 'get'
    })
  },
  //根据课程名称模糊查询
  findCourseByTitle(title) {
    return request({
      url: '/eduservice/coursefront/findCourseByTitle/'+title,
      method: 'get'
    })
  },


/*---------评论-----------*/
  //根据课程id获取所有评论
  getComment(id,crruent,limit){
    return request({
      url:`/eduservice/comment/getCommentPage/${id}/${crruent}/${limit}`,
      method:'get',
    })
  },
  //添加评论
  addComment(commnet){
    return request({
      url:`/eduservice/comment/saveComment`,
      method:'post',
      data:commnet
    })
  },
  //删除评论
  remove(id){
    return request({
      url:`/eduservice/comment/delete/${id}`,
      method:'delete',
    })
  }
/*---------结束--------*/
}
