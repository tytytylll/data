import request from '@/utils/request'

// 查询首页统计数据
export function getHomeStatistics() {
  return request({
    url: '/system/exam/statistics/home',
    method: 'get'
  })
}
