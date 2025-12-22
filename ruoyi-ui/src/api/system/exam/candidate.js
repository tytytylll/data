import request from '@/utils/request'

export function listCandidate(query) {
  return request({
    url: '/system/exam/candidate/list',
    method: 'get',
    params: query
  })
}

export function optionselect() {
  return request({
    url: '/system/exam/candidate/optionselect',
    method: 'get'
  })
}

export function getCandidate(candidateId) {
  return request({
    url: '/system/exam/candidate/' + candidateId,
    method: 'get'
  })
}

export function addCandidate(data) {
  return request({
    url: '/system/exam/candidate',
    method: 'post',
    data: data
  })
}

export function updateCandidate(data) {
  return request({
    url: '/system/exam/candidate',
    method: 'put',
    data: data
  })
}

export function delCandidate(candidateId) {
  return request({
    url: '/system/exam/candidate/' + candidateId,
    method: 'delete'
  })
}
