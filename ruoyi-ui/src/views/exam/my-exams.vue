<template>
  <div class="my-exams">
    <!-- 顶部导航栏 -->
    <div class="header">
      <div class="header-content">
        <div class="logo-section">
          <i class="el-icon-reading"></i>
          <span class="title">考试管理系统</span>
        </div>
        <div class="nav-section">
          <el-button icon="el-icon-arrow-left" @click="goBack">返回首页</el-button>
          <el-button type="primary" icon="el-icon-s-platform" @click="goToAdmin">进入后台管理</el-button>
        </div>
      </div>
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <div class="my-registration-section">
        <h2 class="section-title">
          <i class="el-icon-tickets"></i> 我的报名
        </h2>
        
        <el-table :data="myRegistrations" stripe style="width: 100%">
          <el-table-column prop="examName" label="考试科目" min-width="180"></el-table-column>
          <el-table-column prop="examSite" label="考点" width="150"></el-table-column>
          <el-table-column prop="examSession" label="场次" width="180"></el-table-column>
          <el-table-column prop="seatNumber" label="座位号" width="100"></el-table-column>
          <el-table-column prop="registrationTime" label="报名时间" width="180"></el-table-column>
          <el-table-column prop="status" label="状态" width="100">
            <template slot-scope="scope">
              <el-tag :type="getRegistrationStatusType(scope.row.status)">
                {{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" fixed="right">
            <template slot-scope="scope">
              <div class="table-actions">
                <el-button
                  v-if="scope.row.status === '待确认'"
                  type="primary"
                  size="mini"
                  @click="handleConfirmRegistration(scope.row)"
                >
                  确认报名
                </el-button>
                <el-button
                  v-if="scope.row.status === '已确认'"
                  type="success"
                  size="mini"
                  @click="handleViewDetails(scope.row)"
                >
                  查看详情
                </el-button>
                <el-button
                  v-if="scope.row.status === '待确认' || scope.row.status === '已确认'"
                  type="danger"
                  size="mini"
                  @click="handleCancelRegistration(scope.row)"
                >
                  取消报名
                </el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      <p>Copyright © 2018-2025 exam-system.com All Rights Reserved.</p>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  name: 'MyExams',
  data() {
    return {
      myRegistrations: []
    }
  },
  created() {
    this.loadMyRegistrations()
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    
    goToAdmin() {
      this.$router.push('/index')
    },
    
    loadMyRegistrations() {
      request({
        url: '/system/exam/registration/my',
        method: 'get'
      }).then(response => {
        if (response.data) {
          this.myRegistrations = response.data.map(item => ({
            id: item.registrationId,
            examName: item.subjectName,
            examSite: item.siteName,
            examSession: `${item.examDate} ${item.startTime}-${item.endTime}`,
            seatNumber: item.seatNumber || '-',
            registrationTime: item.createTime,
            status: item.status === '0' ? '待确认' : item.status === '1' ? '已确认' : '已取消'
          }))
        }
      }).catch(() => {
        this.myRegistrations = []
      })
    },
    
    handleConfirmRegistration(row) {
      this.$confirm('确认报名后将无法修改考点和场次，是否继续？', '确认报名', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request({
          url: '/system/exam/registration/confirm/' + row.id,
          method: 'put'
        }).then(() => {
          this.$message.success('报名已确认')
          this.loadMyRegistrations()
        })
      }).catch(() => {
        this.$message.info('已取消确认')
      })
    },
    
    handleViewDetails(row) {
      this.$alert(
        `考试科目：${row.examName}\n考点：${row.examSite}\n场次：${row.examSession}\n座位号：${row.seatNumber}`,
        '报名详情',
        {
          confirmButtonText: '确定'
        }
      )
    },
    
    handleCancelRegistration(row) {
      this.$confirm('取消报名后需要重新报名，是否继续？', '取消报名', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request({
          url: '/system/exam/registration/' + row.id,
          method: 'delete'
        }).then(() => {
          this.$message.success('已取消报名')
          this.loadMyRegistrations()
        })
      }).catch(() => {
        this.$message.info('已取消操作')
      })
    },
    
    getRegistrationStatusType(status) {
      const typeMap = {
        '待确认': 'warning',
        '已确认': 'success',
        '已取消': 'info'
      }
      return typeMap[status] || 'info'
    }
  }
}
</script>

<style lang="scss" scoped>
.my-exams {
  min-height: 100vh;
  background: #f5f7fa;
  display: flex;
  flex-direction: column;
}

.header {
  background: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  position: sticky;
  top: 0;
  z-index: 1000;
  border-bottom: 1px solid #e8eaec;
  
  .header-content {
    max-width: 1400px;
    margin: 0 auto;
    padding: 0 30px;
    height: 64px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    
    .logo-section {
      display: flex;
      align-items: center;
      font-size: 20px;
      font-weight: 600;
      color: #2c3e50;
      
      i {
        font-size: 28px;
        color: #1890ff;
        margin-right: 12px;
      }
    }
    
    .nav-section {
      display: flex;
      gap: 10px;
    }
  }
}

.main-content {
  flex: 1;
  max-width: 1400px;
  width: 100%;
  margin: 0 auto;
  padding: 40px 30px;
}

.my-registration-section {
  background: #ffffff;
  border-radius: 8px;
  padding: 24px 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #e8eaec;
  
  .section-title {
    font-size: 18px;
    color: #2c3e50;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    font-weight: 600;
    
    i {
      margin-right: 8px;
      color: #1890ff;
    }
  }
  
  .table-actions {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
    
    .el-button {
      margin: 0;
    }
  }
}

.footer {
  background: #ffffff;
  color: #606266;
  text-align: center;
  padding: 24px;
  margin-top: 40px;
  border-top: 1px solid #e8eaec;
  
  p {
    margin: 0;
    font-size: 13px;
  }
}
</style>
