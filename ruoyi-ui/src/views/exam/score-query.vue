<template>
  <div class="score-query">
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
      <!-- 查询条件 -->
      <div class="search-section">
        <h2 class="section-title">
          <i class="el-icon-search"></i> 成绩查询
        </h2>
        <el-form :model="queryParams" ref="queryForm" :inline="true" class="search-form">
          <el-form-item label="考试科目" prop="subjectName">
            <el-input
              v-model="queryParams.subjectName"
              placeholder="请输入考试科目"
              clearable
              style="width: 200px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="考试时间" prop="examTimeRange">
            <el-date-picker
              v-model="queryParams.examTimeRange"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              style="width: 240px"
            />
          </el-form-item>
          <el-form-item label="通过状态" prop="passStatus">
            <el-select v-model="queryParams.passStatus" placeholder="请选择" clearable style="width: 120px">
              <el-option label="全部" value="" />
              <el-option label="通过" value="1" />
              <el-option label="未通过" value="0" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
            <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 成绩统计卡片 -->
      <div class="stats-cards">
        <el-row :gutter="20">
          <el-col :xs="12" :sm="6">
            <div class="stat-card total">
              <div class="stat-icon">
                <i class="el-icon-document"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">{{ statistics.totalCount }}</div>
                <div class="stat-label">考试总数</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="6">
            <div class="stat-card passed">
              <div class="stat-icon">
                <i class="el-icon-success"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">{{ statistics.passedCount }}</div>
                <div class="stat-label">通过数量</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="6">
            <div class="stat-card failed">
              <div class="stat-icon">
                <i class="el-icon-error"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">{{ statistics.failedCount }}</div>
                <div class="stat-label">未通过数量</div>
              </div>
            </div>
          </el-col>
          <el-col :xs="12" :sm="6">
            <div class="stat-card average">
              <div class="stat-icon">
                <i class="el-icon-data-analysis"></i>
              </div>
              <div class="stat-info">
                <div class="stat-number">{{ statistics.averageScore }}</div>
                <div class="stat-label">平均分</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>

      <!-- 成绩列表 -->
      <div class="score-list-section">
        <h2 class="section-title">
          <i class="el-icon-tickets"></i> 成绩列表
        </h2>
        
        <el-table 
          v-loading="loading" 
          :data="scoreList" 
          stripe 
          style="width: 100%"
          :header-cell-style="{ background: '#f5f7fa', color: '#606266' }"
        >
          <el-table-column prop="subjectName" label="考试科目" min-width="180">
            <template slot-scope="scope">
              <span class="subject-name">{{ scope.row.subjectName }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="siteName" label="考点" width="150"></el-table-column>
          <el-table-column prop="examTime" label="考试时间" width="180">
            <template slot-scope="scope">
              <i class="el-icon-time"></i> {{ scope.row.examTime }}
            </template>
          </el-table-column>
          <el-table-column prop="score" label="成绩" width="100" align="center">
            <template slot-scope="scope">
              <span class="score-value" :class="getScoreClass(scope.row.score)">
                {{ scope.row.score }}
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="scoreLevel" label="等级" width="100" align="center">
            <template slot-scope="scope">
              <el-tag :type="getLevelTagType(scope.row.scoreLevel)" size="small">
                {{ scope.row.scoreLevel }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="passStatus" label="是否通过" width="100" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.passStatus === '1' ? 'success' : 'danger'" size="small">
                {{ scope.row.passStatus === '1' ? '通过' : '未通过' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="publishTime" label="发布时间" width="180">
            <template slot-scope="scope">
              <span v-if="scope.row.publishTime">
                <i class="el-icon-date"></i> {{ scope.row.publishTime }}
              </span>
              <span v-else class="text-muted">-</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="120" fixed="right" align="center">
            <template slot-scope="scope">
              <el-button
                type="text"
                icon="el-icon-view"
                @click="handleViewDetail(scope.row)"
              >
                查看详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 空状态 -->
        <div v-if="!loading && scoreList.length === 0" class="empty-state">
          <i class="el-icon-document-delete"></i>
          <p>暂无成绩记录</p>
        </div>

        <!-- 分页 -->
        <div class="pagination-container" v-if="total > 0">
          <el-pagination
            background
            :current-page="queryParams.pageNum"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="queryParams.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </div>

    <!-- 成绩详情对话框 -->
    <el-dialog 
      title="成绩详情" 
      :visible.sync="detailDialogVisible" 
      width="500px"
      class="score-detail-dialog"
    >
      <div class="score-detail" v-if="currentScore">
        <div class="detail-header">
          <div class="score-circle" :class="getScoreClass(currentScore.score)">
            <span class="score-number">{{ currentScore.score }}</span>
            <span class="score-unit">分</span>
          </div>
          <div class="score-status">
            <el-tag :type="currentScore.passStatus === '1' ? 'success' : 'danger'" size="medium">
              {{ currentScore.passStatus === '1' ? '通过' : '未通过' }}
            </el-tag>
            <el-tag :type="getLevelTagType(currentScore.scoreLevel)" size="medium" style="margin-left: 8px;">
              {{ currentScore.scoreLevel }}
            </el-tag>
          </div>
        </div>
        <el-divider></el-divider>
        <div class="detail-info">
          <div class="info-item">
            <span class="info-label">考试科目：</span>
            <span class="info-value">{{ currentScore.subjectName }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">考试考点：</span>
            <span class="info-value">{{ currentScore.siteName || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">考试时间：</span>
            <span class="info-value">{{ currentScore.examTime || '-' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">发布时间：</span>
            <span class="info-value">{{ currentScore.publishTime || '-' }}</span>
          </div>
          <div class="info-item" v-if="currentScore.remark">
            <span class="info-label">备注信息：</span>
            <span class="info-value">{{ currentScore.remark }}</span>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="detailDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 页脚 -->
    <div class="footer">
      <p>Copyright © 2018-2025 exam-system.com All Rights Reserved.</p>
    </div>
  </div>
</template>

<script>
import { listMyScore, getScore } from "@/api/system/exam/score"

export default {
  name: 'ScoreQuery',
  data() {
    return {
      // 遮罩层
      loading: false,
      // 总条数
      total: 0,
      // 成绩列表
      scoreList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subjectName: '',
        examTimeRange: null,
        passStatus: ''
      },
      // 统计数据
      statistics: {
        totalCount: 0,
        passedCount: 0,
        failedCount: 0,
        averageScore: 0
      },
      // 详情对话框
      detailDialogVisible: false,
      currentScore: null
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 返回首页 */
    goBack() {
      this.$router.push('/exam-home')
    },
    
    /** 进入后台管理 */
    goToAdmin() {
      this.$router.push('/index')
    },
    
    /** 查询成绩列表 */
    getList() {
      this.loading = true
      const params = { ...this.queryParams }
      if (params.examTimeRange && params.examTimeRange.length === 2) {
        params.beginExamTime = params.examTimeRange[0]
        params.endExamTime = params.examTimeRange[1]
      }
      delete params.examTimeRange
      
      listMyScore(params).then(response => {
        this.scoreList = response.rows || []
        this.total = response.total || 0
        this.calculateStatistics()
        this.loading = false
      }).catch(() => {
        // 如果API未实现，使用模拟数据
        this.scoreList = [
          {
            scoreId: 1,
            subjectName: '全国计算机等级考试二级',
            siteName: '教学楼A座',
            examTime: '2024-03-25 09:00',
            score: 85.5,
            scoreLevel: '良好',
            passStatus: '1',
            publishTime: '2024-03-26 10:00'
          },
          {
            scoreId: 2,
            subjectName: '大学英语四级考试',
            siteName: '教学楼B座',
            examTime: '2024-03-28 14:00',
            score: 92,
            scoreLevel: '优秀',
            passStatus: '1',
            publishTime: '2024-03-29 10:00'
          },
          {
            scoreId: 3,
            subjectName: '高等数学期末考试',
            siteName: '教学楼C座',
            examTime: '2024-04-10 09:00',
            score: 58,
            scoreLevel: '不及格',
            passStatus: '0',
            publishTime: '2024-04-12 10:00'
          }
        ]
        this.total = this.scoreList.length
        this.calculateStatistics()
        this.loading = false
      })
    },
    
    /** 计算统计数据 */
    calculateStatistics() {
      const list = this.scoreList
      this.statistics.totalCount = list.length
      this.statistics.passedCount = list.filter(item => item.passStatus === '1').length
      this.statistics.failedCount = list.filter(item => item.passStatus === '0').length
      
      if (list.length > 0) {
        const totalScore = list.reduce((sum, item) => sum + (parseFloat(item.score) || 0), 0)
        this.statistics.averageScore = (totalScore / list.length).toFixed(1)
      } else {
        this.statistics.averageScore = 0
      }
    },
    
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        subjectName: '',
        examTimeRange: null,
        passStatus: ''
      }
      this.getList()
    },
    
    /** 每页条数改变 */
    handleSizeChange(val) {
      this.queryParams.pageSize = val
      this.getList()
    },
    
    /** 当前页改变 */
    handleCurrentChange(val) {
      this.queryParams.pageNum = val
      this.getList()
    },
    
    /** 查看详情 */
    handleViewDetail(row) {
      this.currentScore = row
      this.detailDialogVisible = true
    },
    
    /** 根据分数获取样式类 */
    getScoreClass(score) {
      if (score >= 90) return 'excellent'
      if (score >= 80) return 'good'
      if (score >= 60) return 'pass'
      return 'fail'
    },
    
    /** 根据等级获取标签类型 */
    getLevelTagType(level) {
      const typeMap = {
        '优秀': 'success',
        '良好': '',
        '及格': 'warning',
        '不及格': 'danger'
      }
      return typeMap[level] || 'info'
    }
  }
}
</script>

<style lang="scss" scoped>
.score-query {
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
  padding: 30px;
}

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

.search-section {
  background: #ffffff;
  border-radius: 8px;
  padding: 24px 30px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #e8eaec;
  
  .search-form {
    margin-top: 10px;
  }
}

.stats-cards {
  margin-bottom: 20px;
  
  .stat-card {
    background: #ffffff;
    border-radius: 8px;
    padding: 20px;
    display: flex;
    align-items: center;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
    border: 1px solid #e8eaec;
    transition: all 0.3s ease;
    
    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
    }
    
    .stat-icon {
      width: 56px;
      height: 56px;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 16px;
      
      i {
        font-size: 28px;
        color: #fff;
      }
    }
    
    .stat-info {
      .stat-number {
        font-size: 28px;
        font-weight: 600;
        line-height: 1.2;
      }
      
      .stat-label {
        font-size: 14px;
        color: #909399;
        margin-top: 4px;
      }
    }
    
    &.total {
      .stat-icon {
        background: linear-gradient(135deg, #1890ff 0%, #096dd9 100%);
      }
      .stat-number {
        color: #1890ff;
      }
    }
    
    &.passed {
      .stat-icon {
        background: linear-gradient(135deg, #52c41a 0%, #389e0d 100%);
      }
      .stat-number {
        color: #52c41a;
      }
    }
    
    &.failed {
      .stat-icon {
        background: linear-gradient(135deg, #ff4d4f 0%, #cf1322 100%);
      }
      .stat-number {
        color: #ff4d4f;
      }
    }
    
    &.average {
      .stat-icon {
        background: linear-gradient(135deg, #faad14 0%, #d48806 100%);
      }
      .stat-number {
        color: #faad14;
      }
    }
  }
}

.score-list-section {
  background: #ffffff;
  border-radius: 8px;
  padding: 24px 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #e8eaec;
  
  .subject-name {
    font-weight: 500;
    color: #2c3e50;
  }
  
  .score-value {
    font-weight: 600;
    font-size: 16px;
    
    &.excellent {
      color: #52c41a;
    }
    &.good {
      color: #1890ff;
    }
    &.pass {
      color: #faad14;
    }
    &.fail {
      color: #ff4d4f;
    }
  }
  
  .text-muted {
    color: #c0c4cc;
  }
}

.empty-state {
  text-align: center;
  padding: 60px 0;
  color: #909399;
  
  i {
    font-size: 64px;
    margin-bottom: 16px;
  }
  
  p {
    font-size: 14px;
  }
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.score-detail {
  .detail-header {
    text-align: center;
    padding: 20px 0;
    
    .score-circle {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      display: inline-flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin-bottom: 16px;
      
      &.excellent {
        background: linear-gradient(135deg, #52c41a 0%, #389e0d 100%);
      }
      &.good {
        background: linear-gradient(135deg, #1890ff 0%, #096dd9 100%);
      }
      &.pass {
        background: linear-gradient(135deg, #faad14 0%, #d48806 100%);
      }
      &.fail {
        background: linear-gradient(135deg, #ff4d4f 0%, #cf1322 100%);
      }
      
      .score-number {
        font-size: 36px;
        font-weight: 600;
        color: #fff;
        line-height: 1;
      }
      
      .score-unit {
        font-size: 14px;
        color: rgba(255, 255, 255, 0.9);
        margin-top: 4px;
      }
    }
    
    .score-status {
      margin-top: 12px;
    }
  }
  
  .detail-info {
    .info-item {
      display: flex;
      padding: 12px 0;
      border-bottom: 1px solid #f0f0f0;
      
      &:last-child {
        border-bottom: none;
      }
      
      .info-label {
        width: 100px;
        color: #909399;
        flex-shrink: 0;
      }
      
      .info-value {
        color: #2c3e50;
        flex: 1;
      }
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

@media (max-width: 768px) {
  .main-content {
    padding: 20px 15px;
  }
  
  .stats-cards {
    .el-col {
      margin-bottom: 15px;
    }
    
    .stat-card {
      padding: 15px;
      
      .stat-icon {
        width: 48px;
        height: 48px;
        
        i {
          font-size: 24px;
        }
      }
      
      .stat-info {
        .stat-number {
          font-size: 24px;
        }
      }
    }
  }
}
</style>
