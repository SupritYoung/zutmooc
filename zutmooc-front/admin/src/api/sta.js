import request from '@/utils/request'

export default {
  //生成统计数据
  createStaData(day) {
    return request({
      url: `/staservice/statistics/registerCount/${day}`,
      method: 'post'
    })
  },
  //获取统计数据
  getData(searchObj) {
    return request({
      url: `/staservice/statistics/showData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  }
}
