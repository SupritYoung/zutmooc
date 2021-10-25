import request from '@/utils/request'

export default {
    //查询热门课程和名师
  getRecommend(id) {
    return request({
      url: '/recommend/getRecommend/'+id,
      method: 'get'
    })
  }
}
