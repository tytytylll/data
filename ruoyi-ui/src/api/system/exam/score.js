import request from '@/utils/request'

// 查询我的成绩列表
export function listMyScore(query) {
  return request({
    url: '/system/exam/score/my',
    method: 'get',
    params: query
  })
}

// 查询成绩详细
export function getScore(scoreId) {
  return request({
    url: '/system/exam/score/' + scoreId,
    method: 'get'
  })
}

// 查询成绩列表（管理员）
export function listScore(query) {
  return request({
    url: '/system/exam/score/list',
    method: 'get',
    params: query
  })
}

// 新增成绩
export function addScore(data) {
  return request({
    url: '/system/exam/score',
    method: 'post',
    data: data
  })
}

// 修改成绩
export function updateScore(data) {
  return request({
    url: '/system/exam/score',
    method: 'put',
    data: data
  })
}

// 删除成绩
export function delScore(scoreId) {
  return request({
    url: '/system/exam/score/' + scoreId,
    method: 'delete'
  })
}

// 发布成绩
export function publishScore(scoreIds) {
  return request({
    url: '/system/exam/score/publish/' + scoreIds,
    method: 'put'
  })
}

// 取消发布成绩
export function unpublishScore(scoreIds) {
  return request({
    url: '/system/exam/score/unpublish/' + scoreIds,
    method: 'put'
  })
}

// 设置定时发布
export function schedulePublish(scoreIds, scheduledPublishTime) {
  return request({
    url: '/system/exam/score/schedulePublish/' + scoreIds,
    method: 'put',
    data: { scheduledPublishTime }
  })
}

// 取消定时发布
export function cancelSchedule(scoreIds) {
  return request({
    url: '/system/exam/score/cancelSchedule/' + scoreIds,
    method: 'put'
  })
}

// 导出成绩
export function exportScore(query) {
  return request({
    url: '/system/exam/score/export',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}
