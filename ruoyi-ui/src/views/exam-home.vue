<template>
  <div class="exam-home">
    <!-- 顶部导航栏 -->
    <div class="header">
      <div class="header-content">
        <div class="logo-section">
          <i class="el-icon-reading"></i>
          <span class="title">考试管理系统</span>
        </div>
        <div class="nav-section">
          <el-button 
            type="primary" 
            icon="el-icon-s-platform" 
            @click="goToAdmin"
            class="admin-btn"
          >
            进入后台管理
          </el-button>
        </div>
      </div>
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <!-- 欢迎区域 -->
      <div class="welcome-section">
        <h1 class="welcome-title">欢迎使用考试管理系统</h1>
        <p class="welcome-subtitle">专业的在线考试平台，让考试更简单、更高效</p>
      </div>

      <!-- 公告栏轮播图 -->
      <div class="announcement-section">
        <div class="announcement-header">
          <i class="el-icon-bell"></i>
          <span class="announcement-title">系统公告</span>
        </div>
        <el-carousel 
          :interval="5000" 
          arrow="hover" 
          height="120px"
          indicator-position="none"
        >
          <el-carousel-item v-for="(item, index) in announcements" :key="index">
            <div class="announcement-item" @click="handleAnnouncementClick(item)">
              <div class="announcement-content">
                <div class="announcement-badge" :class="'badge-' + item.type">
                  {{ item.typeLabel }}
                </div>
                <h3 class="announcement-item-title">{{ item.title }}</h3>
                <p class="announcement-desc">{{ item.content }}</p>
                <div class="announcement-meta">
                  <span class="announcement-time">
                    <i class="el-icon-time"></i> {{ item.publishTime }}
                  </span>
                  <span class="announcement-author">
                    <i class="el-icon-user"></i> {{ item.author }}
                  </span>
                </div>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>

      <!-- 功能卡片区域 -->
      <div class="feature-cards">
        <el-row :gutter="30">
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <div class="feature-card" @click="goToPage('/exam/registration')">
              <div class="card-icon">
                <i class="el-icon-edit-outline"></i>
              </div>
              <h3>考试报名</h3>
              <p>选择考试科目，报名参加考试，查看考点和场次</p>
              <div class="card-arrow">
                <i class="el-icon-arrow-right"></i>
              </div>
            </div>
          </el-col>
          
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <div class="feature-card" @click="goToPage('/exam/my-exams')">
              <div class="card-icon">
                <i class="el-icon-tickets"></i>
              </div>
              <h3>我的考试</h3>
              <p>查看已报名的考试，管理考试信息</p>
              <div class="card-arrow">
                <i class="el-icon-arrow-right"></i>
              </div>
            </div>
          </el-col>
          
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <div class="feature-card" @click="handleFeature('score')">
              <div class="card-icon">
                <i class="el-icon-data-line"></i>
              </div>
              <h3>成绩查询</h3>
              <p>实时查看考试成绩，详细分析报告</p>
              <div class="card-arrow">
                <i class="el-icon-arrow-right"></i>
              </div>
            </div>
          </el-col>
          
          <el-col :xs="24" :sm="12" :md="8" :lg="6">
            <div class="feature-card" @click="handleFeature('profile')">
              <div class="card-icon">
                <i class="el-icon-user"></i>
              </div>
              <h3>个人中心</h3>
              <p>管理个人信息，查看学习记录</p>
              <div class="card-arrow">
                <i class="el-icon-arrow-right"></i>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>

      <!-- 统计信息区域 -->
      <div class="stats-section">
        <el-row :gutter="20">
          <el-col :xs="24" :sm="8">
            <div class="stat-card">
              <div class="stat-number">{{ stats.totalExams }}</div>
              <div class="stat-label">累计考试次数</div>
            </div>
          </el-col>
          <el-col :xs="24" :sm="8">
            <div class="stat-card">
              <div class="stat-number">{{ stats.totalQuestions }}</div>
              <div class="stat-label">题库总题数</div>
            </div>
          </el-col>
          <el-col :xs="24" :sm="8">
            <div class="stat-card">
              <div class="stat-number">{{ stats.avgScore }}</div>
              <div class="stat-label">平均成绩</div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <!-- 页脚 -->
    <div class="footer">
      <p>Copyright © 2018-2025 exam-system.com All Rights Reserved.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ExamHome',
  data() {
    return {
      announcements: [],
      stats: {
        totalExams: 0,
        totalQuestions: 0,
        avgScore: 0
      }
    }
  },
  created() {
    this.loadAnnouncements()
    this.loadStats()
  },
  methods: {
    goToAdmin() {
      this.$router.push('/index')
    },
    
    goToPage(path) {
      this.$router.push(path)
    },
    
    loadStats() {
      // TODO: 调用后端API获取统计数据
      this.stats = {
        totalExams: 156,
        totalQuestions: 2580,
        avgScore: 85.6
      }
    },
    
    handleFeature(type) {
      if (type === 'score') {
        this.$message.info('成绩查询功能开发中...')
      } else if (type === 'profile') {
        this.$message.info('个人中心功能开发中...')
      }
    },
    
    loadAnnouncements() {
      // TODO: 调用后端API获取公告数据
      this.announcements = [
        {
          id: 1,
          type: 'important',
          typeLabel: '重要',
          title: '关于2024年第一季度考试安排的通知',
          content: '各位考生请注意，2024年第一季度综合测试将于3月15日14:00开始，请提前做好准备，准时参加考试。',
          publishTime: '2024-03-01 10:00',
          author: '系统管理员'
        },
        {
          id: 2,
          type: 'notice',
          typeLabel: '通知',
          title: '系统维护通知',
          content: '系统将于本周六凌晨2:00-6:00进行例行维护升级，届时将无法访问，请各位考生合理安排时间。',
          publishTime: '2024-03-05 15:30',
          author: '技术部'
        },
        {
          id: 3,
          type: 'update',
          typeLabel: '更新',
          title: '题库更新公告',
          content: '本周新增Java高级编程、数据结构与算法等科目题库共计500道题目，欢迎大家练习使用。',
          publishTime: '2024-03-08 09:00',
          author: '教务处'
        },
        {
          id: 4,
          type: 'event',
          typeLabel: '活动',
          title: '在线模拟考试活动开启',
          content: '为帮助考生更好地备考，系统开放免费模拟考试功能，欢迎大家积极参与，检验学习成果。',
          publishTime: '2024-03-10 14:00',
          author: '考试中心'
        }
      ]
    },
    
    handleAnnouncementClick(announcement) {
      this.$alert(announcement.content, announcement.title, {
        confirmButtonText: '我知道了',
        callback: () => {
          // TODO: 可以跳转到公告详情页面
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.exam-home {
  min-height: 100vh;
  background: #f5f7fa;
  display: flex;
  flex-direction: column;
}

// 顶部导航栏
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
      
      .title {
        color: #2c3e50;
      }
    }
    
    .nav-section {
      .admin-btn {
        padding: 10px 20px;
        font-size: 14px;
        border-radius: 4px;
        background: #1890ff;
        border: none;
        
        &:hover {
          background: #40a9ff;
          box-shadow: 0 2px 8px rgba(24, 144, 255, 0.3);
        }
      }
    }
  }
}

// 主要内容区域
.main-content {
  flex: 1;
  max-width: 1400px;
  width: 100%;
  margin: 0 auto;
  padding: 40px 30px;
}

// 欢迎区域
.welcome-section {
  background: linear-gradient(135deg, #1890ff 0%, #096dd9 100%);
  border-radius: 8px;
  padding: 50px 30px;
  text-align: center;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  
  .welcome-title {
    font-size: 32px;
    font-weight: 600;
    color: #fff;
    margin-bottom: 12px;
  }
  
  .welcome-subtitle {
    font-size: 16px;
    color: rgba(255, 255, 255, 0.95);
    margin: 0;
  }
}

// 公告栏
.announcement-section {
  background: #ffffff;
  border-radius: 8px;
  padding: 24px 30px;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #e8eaec;
  
  .announcement-header {
    display: flex;
    align-items: center;
    margin-bottom: 16px;
    padding-bottom: 12px;
    border-bottom: 1px solid #e8eaec;
    
    i {
      font-size: 20px;
      color: #1890ff;
      margin-right: 8px;
    }
    
    .announcement-title {
      font-size: 16px;
      font-weight: 600;
      color: #2c3e50;
    }
  }
  
  .announcement-item {
    cursor: pointer;
    padding: 12px;
    border-radius: 4px;
    transition: all 0.3s ease;
    
    &:hover {
      background: #f5f7fa;
    }
    
    .announcement-content {
      .announcement-badge {
        display: inline-block;
        padding: 2px 10px;
        border-radius: 2px;
        font-size: 12px;
        font-weight: 400;
        margin-bottom: 8px;
        
        &.badge-important {
          background: #fff1f0;
          color: #ff4d4f;
          border: 1px solid #ffccc7;
        }
        
        &.badge-notice {
          background: #fffbe6;
          color: #faad14;
          border: 1px solid #ffe58f;
        }
        
        &.badge-update {
          background: #f6ffed;
          color: #52c41a;
          border: 1px solid #b7eb8f;
        }
        
        &.badge-event {
          background: #e6f7ff;
          color: #1890ff;
          border: 1px solid #91d5ff;
        }
      }
      
      .announcement-item-title {
        font-size: 15px;
        font-weight: 500;
        color: #2c3e50;
        margin: 8px 0;
        line-height: 1.5;
      }
      
      .announcement-desc {
        font-size: 14px;
        color: #606266;
        margin: 8px 0;
        line-height: 1.6;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
      }
      
      .announcement-meta {
        display: flex;
        align-items: center;
        gap: 20px;
        margin-top: 10px;
        
        span {
          font-size: 12px;
          color: #909399;
          display: flex;
          align-items: center;
          
          i {
            margin-right: 4px;
          }
        }
      }
    }
  }
}

// 功能卡片
.feature-cards {
  margin-bottom: 30px;
  
  .feature-card {
    background: #fff;
    border-radius: 8px;
    padding: 28px 20px;
    text-align: center;
    transition: all 0.3s ease;
    height: 100%;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
    border: 1px solid #e8eaec;
    cursor: pointer;
    position: relative;
    
    &:hover {
      transform: translateY(-4px);
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
      border-color: #1890ff;
      
      .card-arrow {
        transform: translateX(5px);
      }
    }
    
    .card-icon {
      width: 64px;
      height: 64px;
      margin: 0 auto 18px;
      background: #e6f7ff;
      border-radius: 8px;
      display: flex;
      align-items: center;
      justify-content: center;
      
      i {
        font-size: 32px;
        color: #1890ff;
      }
    }
    
    h3 {
      font-size: 18px;
      color: #2c3e50;
      margin-bottom: 10px;
      font-weight: 600;
    }
    
    p {
      font-size: 13px;
      color: #606266;
      margin-bottom: 18px;
      line-height: 1.6;
      min-height: 40px;
    }
    
    .card-arrow {
      color: #1890ff;
      font-size: 20px;
      transition: transform 0.3s ease;
    }
  }
}

// 统计信息
.stats-section {
  margin-bottom: 30px;
  
  .stat-card {
    background: #ffffff;
    border-radius: 8px;
    padding: 28px;
    text-align: center;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
    border: 1px solid #e8eaec;
    transition: all 0.3s ease;
    
    &:hover {
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
    }
    
    .stat-number {
      font-size: 32px;
      font-weight: 600;
      color: #1890ff;
      margin-bottom: 8px;
    }
    
    .stat-label {
      font-size: 14px;
      color: #606266;
    }
  }
}

// 页脚
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

// 响应式设计
@media (max-width: 768px) {
  .welcome-section {
    padding: 40px 20px;
    
    .welcome-title {
      font-size: 24px;
    }
    
    .welcome-subtitle {
      font-size: 14px;
    }
  }
  
  .announcement-section {
    padding: 15px 20px;
    
    .announcement-header {
      .announcement-title {
        font-size: 16px;
      }
    }
    
    .announcement-item {
      .announcement-content {
        .announcement-item-title {
          font-size: 15px;
        }
        
        .announcement-desc {
          font-size: 13px;
        }
        
        .announcement-meta {
          flex-direction: column;
          align-items: flex-start;
          gap: 5px;
        }
      }
    }
  }
  
  .feature-cards {
    .el-col {
      margin-bottom: 20px;
    }
  }
  
  .stats-section {
    .el-col {
      margin-bottom: 15px;
    }
  }
}
</style>
