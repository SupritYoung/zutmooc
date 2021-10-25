import request from '@/utils/request'

export default {

  isCollect(courseId) {
    return request({
      url: `/api/edu/course-collect/auth/is-collect/${courseId}`,
      method: 'get'
    })
  },

  addCollect(courseId) {
    return request({
      url: `/api/edu/course-collect/auth/save/${courseId}`,
      method: 'post'
    })
  },

  getCollectList() {
    return request({
      url: '/api/edu/course-collect/auth/list',
      method: 'get'
    })
  },

  removeById(courseId) {
    return request({
      url: `/api/edu/course-collect/auth/remove/${courseId}`,
      method: 'delete'
    })
  }
}
