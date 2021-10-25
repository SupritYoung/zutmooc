import request from '@/utils/request'
export default {
  getPlayAuth(vid) {
    return request({
      url: `/eduvod/video/getPlayAuth/${vid}`,
      method: 'get'
    })
  },
  getFileURL(vid) {
    return request({
      url: `/eduvod/video/getFileURL/${vid}`,
      method: 'get'
    })
  }

}
