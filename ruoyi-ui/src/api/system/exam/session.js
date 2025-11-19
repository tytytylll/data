import request from '@/utils/request'

// 查询考试场次列表
export function listSession(query) {
  return request({
    url: '/system/exam/session/list',
    method: 'get',
    params: query
  })
}

// 查询考试场次详细
export function getSession(sessionId) {
  return request({
    url: '/system/exam/session/' + sessionId,
    method: 'get'
  })
}

// 新增考试场次
export function addSession(data) {
  return request({
    url: '/system/exam/session',
    method: 'post',
    data: data
  })
}

// 修改考试场次
export function updateSession(data) {
  return request({
    url: '/system/exam/session',
    method: 'put',
    data: data
  })
}

// 删除考试场次
export function delSession(sessionId) {
  return request({
    url: '/system/exam/session/' + sessionId,
    method: 'delete'
  })
}
