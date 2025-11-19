import request from '@/utils/request'

// 查询考点列表
export function listSite(query) {
  return request({
    url: '/system/exam/site/list',
    method: 'get',
    params: query
  })
}

// 查询考点详细
export function getSite(siteId) {
  return request({
    url: '/system/exam/site/' + siteId,
    method: 'get'
  })
}

// 新增考点
export function addSite(data) {
  return request({
    url: '/system/exam/site',
    method: 'post',
    data: data
  })
}

// 修改考点
export function updateSite(data) {
  return request({
    url: '/system/exam/site',
    method: 'put',
    data: data
  })
}

// 删除考点
export function delSite(siteId) {
  return request({
    url: '/system/exam/site/' + siteId,
    method: 'delete'
  })
}

// 查询考点选择框列表
export function optionselect() {
  return request({
    url: '/system/exam/site/optionselect',
    method: 'get'
  })
}
