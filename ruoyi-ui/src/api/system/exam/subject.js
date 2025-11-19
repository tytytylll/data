import request from '@/utils/request'

// 查询考试科目列表
export function listSubject(query) {
  return request({
    url: '/system/exam/subject/list',
    method: 'get',
    params: query
  })
}

// 查询考试科目详细
export function getSubject(subjectId) {
  return request({
    url: '/system/exam/subject/' + subjectId,
    method: 'get'
  })
}

// 新增考试科目
export function addSubject(data) {
  return request({
    url: '/system/exam/subject',
    method: 'post',
    data: data
  })
}

// 修改考试科目
export function updateSubject(data) {
  return request({
    url: '/system/exam/subject',
    method: 'put',
    data: data
  })
}

// 删除考试科目
export function delSubject(subjectId) {
  return request({
    url: '/system/exam/subject/' + subjectId,
    method: 'delete'
  })
}

// 查询考试科目选择框列表
export function optionselect() {
  return request({
    url: '/system/exam/subject/optionselect',
    method: 'get'
  })
}
