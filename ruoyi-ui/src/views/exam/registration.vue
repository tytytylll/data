<template>
  <div class="exam-registration">
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
      <!-- 考试列表 -->
      <div class="exam-list-section">
        <div class="section-header">
          <h2 class="section-title">
            <i class="el-icon-document"></i> 考试科目列表
          </h2>
          <el-input
            v-model="searchKeyword"
            placeholder="搜索考试科目"
            prefix-icon="el-icon-search"
            style="width: 300px"
            clearable
            @input="handleSearch"
          />
        </div>

        <!-- 科目筛选标签 -->
        <div class="filter-tags">
          <el-tag
            :type="selectedCategory === '' ? 'primary' : 'info'"
            @click="handleCategoryFilter('')"
            style="cursor: pointer; margin-right: 10px"
          >
            全部
          </el-tag>
          <el-tag
            v-for="category in categories"
            :key="category"
            :type="selectedCategory === category ? 'primary' : 'info'"
            @click="handleCategoryFilter(category)"
            style="cursor: pointer; margin-right: 10px"
          >
            {{ category }}
          </el-tag>
        </div>

        <!-- 考试科目卡片 -->
        <el-row :gutter="20" class="exam-cards">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="exam in filteredExams" :key="exam.id">
            <div class="exam-card">
              <div class="exam-card-header">
                <el-tag :type="getExamStatusTagType(exam.status)" size="small">
                  {{ exam.status }}
                </el-tag>
                <span class="exam-category">{{ exam.category }}</span>
              </div>
              <h3 class="exam-title" @click="handleViewDetail(exam)" style="cursor: pointer;">{{ exam.name }}</h3>
              <div class="exam-info">
                <div class="info-item">
                  <i class="el-icon-time"></i>
                  <span>{{ exam.duration }}分钟</span>
                </div>
                <div class="info-item">
                  <i class="el-icon-tickets"></i>
                  <span>{{ exam.totalQuestions }}题</span>
                </div>
                <div class="info-item">
                  <i class="el-icon-user"></i>
                  <span>已报名 {{ exam.registeredCount }}人</span>
                </div>
              </div>
              <div class="exam-time">
                <i class="el-icon-date"></i>
                报名截止：{{ exam.registrationDeadline }}
              </div>
              <el-button
                type="primary"
                size="small"
                :disabled="exam.status !== '报名中'"
                @click="handleRegister(exam)"
                style="width: 100%; margin-top: 12px"
              >
                {{ exam.status === '报名中' ? '立即报名' : exam.status }}
              </el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <!-- 报名对话框 -->
    <el-dialog
      title="选择考点和场次"
      :visible.sync="registrationDialogVisible"
      width="800px"
      top="5vh"
      @close="handleDialogClose"
    >
      <div v-if="selectedExam" class="registration-dialog">
        <div class="exam-info-box">
          <h3>{{ selectedExam.name }}</h3>
          <p>考试时长：{{ selectedExam.duration }}分钟 | 题目数量：{{ selectedExam.totalQuestions }}题</p>
        </div>

        <!-- 考点选择 -->
        <div class="site-selection">
          <h4><i class="el-icon-location"></i> 选择考点</h4>
          <el-radio-group v-model="selectedSiteId" @change="handleSiteChange">
            <el-radio
              v-for="site in examSites"
              :key="site.id"
              :label="site.id"
              border
              style="width: 100%; margin-bottom: 10px"
            >
              <div class="site-info">
                <div class="site-name">{{ site.name }}</div>
                <div class="site-address">{{ site.address }}</div>
                <div class="site-capacity">
                  剩余名额：<span :class="site.remainingCapacity < 10 ? 'text-danger' : 'text-success'">
                    {{ site.remainingCapacity }}
                  </span> / {{ site.totalCapacity }}
                </div>
              </div>
            </el-radio>
          </el-radio-group>
        </div>

        <!-- 场次选择 -->
        <div class="session-selection" v-if="selectedSiteId">
          <h4><i class="el-icon-time"></i> 选择场次</h4>
          <el-radio-group v-model="selectedSessionId">
            <el-radio
              v-for="session in examSessions"
              :key="session.id"
              :label="session.id"
              :disabled="session.remainingCapacity === 0"
              border
              style="width: 100%; margin-bottom: 10px"
            >
              <div class="session-info">
                <div class="session-time">
                  <i class="el-icon-date"></i> {{ session.date }} {{ session.startTime }} - {{ session.endTime }}
                </div>
                <div class="session-capacity">
                  剩余名额：<span :class="session.remainingCapacity < 5 ? 'text-danger' : 'text-success'">
                    {{ session.remainingCapacity }}
                  </span> / {{ session.totalCapacity }}
                </div>
              </div>
            </el-radio>
          </el-radio-group>
        </div>

        <!-- 报名信息填写 -->
        <div class="registration-form" v-if="selectedSessionId">
          <h4><i class="el-icon-edit"></i> 填写报名信息</h4>
          <el-form :model="registrationForm" :rules="registrationRules" ref="registrationForm" label-width="100px">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="registrationForm.name" placeholder="请输入真实姓名"></el-input>
            </el-form-item>
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="registrationForm.idCard" placeholder="请输入身份证号"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="registrationForm.phone" placeholder="请输入手机号码"></el-input>
            </el-form-item>
            <el-form-item label="电子邮箱" prop="email">
              <el-input v-model="registrationForm.email" placeholder="请输入电子邮箱"></el-input>
            </el-form-item>
            <el-form-item label="备注">
              <el-input
                v-model="registrationForm.remark"
                type="textarea"
                :rows="3"
                placeholder="请输入备注信息（选填）"
              ></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="registrationDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          :disabled="!selectedSessionId"
          @click="handleSubmitRegistration"
        >
          确认报名
        </el-button>
      </span>
    </el-dialog>

    <!-- 科目详情对话框 -->
    <el-dialog
      title="考试科目详情"
      :visible.sync="detailDialogVisible"
      width="500px"
    >
      <div v-if="detailExam" class="exam-detail">
        <div class="detail-header">
          <el-tag :type="getExamStatusTagType(detailExam.status)" size="medium">
            {{ detailExam.status }}
          </el-tag>
          <span class="detail-category">{{ detailExam.category }}</span>
        </div>
        <h2 class="detail-title">{{ detailExam.name }}</h2>
        <el-divider></el-divider>
        <div class="detail-info">
          <div class="info-row">
            <span class="info-label"><i class="el-icon-time"></i> 考试时长：</span>
            <span class="info-value">{{ detailExam.duration }} 分钟</span>
          </div>
          <div class="info-row">
            <span class="info-label"><i class="el-icon-tickets"></i> 题目数量：</span>
            <span class="info-value">{{ detailExam.totalQuestions }} 题</span>
          </div>
          <div class="info-row">
            <span class="info-label"><i class="el-icon-user"></i> 已报名人数：</span>
            <span class="info-value">{{ detailExam.registeredCount }} 人</span>
          </div>
          <div class="info-row">
            <span class="info-label"><i class="el-icon-date"></i> 报名截止：</span>
            <span class="info-value">{{ detailExam.registrationDeadline }}</span>
          </div>
          <div class="info-row" v-if="detailExam.description">
            <span class="info-label"><i class="el-icon-document"></i> 科目描述：</span>
            <span class="info-value">{{ detailExam.description }}</span>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="detailDialogVisible = false">关 闭</el-button>
        <el-button
          type="primary"
          :disabled="detailExam && detailExam.status !== '报名中'"
          @click="handleRegisterFromDetail"
        >
          立即报名
        </el-button>
      </span>
    </el-dialog>

    <!-- 页脚 -->
    <div class="footer">
      <p>Copyright © 2018-2025 exam-system.com All Rights Reserved.</p>
    </div>
  </div>
</template>

<script>
import { listSubject } from "@/api/system/exam/subject"
import { optionselect as getSiteOptions } from "@/api/system/exam/site"
import { listSession } from "@/api/system/exam/session"

export default {
  name: 'ExamRegistration',
  data() {
    return {
      searchKeyword: '',
      selectedCategory: '',
      categories: [],
      examList: [],
      registrationDialogVisible: false,
      selectedExam: null,
      selectedSiteId: null,
      selectedSessionId: null,
      examSites: [],
      examSessions: [],
      detailDialogVisible: false,
      detailExam: null,
      registrationForm: {
        name: '',
        idCard: '',
        phone: '',
        email: '',
        remark: ''
      },
      registrationRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '身份证号格式不正确', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { pattern: /^1[3-9]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入电子邮箱', trigger: 'blur' },
          { type: 'email', message: '邮箱格式不正确', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    filteredExams() {
      let result = this.examList
      
      if (this.selectedCategory) {
        result = result.filter(exam => exam.category === this.selectedCategory)
      }
      
      if (this.searchKeyword) {
        result = result.filter(exam => 
          exam.name.toLowerCase().includes(this.searchKeyword.toLowerCase())
        )
      }
      
      return result
    }
  },
  created() {
    this.loadExamList()
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
    
    goToAdmin() {
      this.$router.push('/index')
    },
    
    handleSearch() {
      // 搜索已在computed中处理
    },
    
    handleCategoryFilter(category) {
      this.selectedCategory = category
    },
    
    getExamStatusTagType(status) {
      const typeMap = {
        '报名中': 'success',
        '已结束': 'info',
        '即将开始': 'warning'
      }
      return typeMap[status] || 'info'
    },
    
    loadExamList() {
      // 调用后端API获取考试科目列表
      listSubject({ status: '0' }).then(response => {
        const subjects = response.rows || []
        this.examList = subjects.map(item => ({
          id: item.subjectId,
          name: item.subjectName,
          category: item.subjectCode || '其他',
          status: '报名中',
          duration: 120,
          totalQuestions: 100,
          registeredCount: 0,
          registrationDeadline: this.getDefaultDeadline(),
          description: item.description
        }))
        // 提取分类
        const categorySet = new Set(this.examList.map(e => e.category))
        this.categories = Array.from(categorySet)
      }).catch(() => {
        this.$message.error('获取考试科目列表失败')
      })
    },
    
    getDefaultDeadline() {
      const date = new Date()
      date.setDate(date.getDate() + 30)
      return date.toISOString().split('T')[0]
    },
    
    handleRegister(exam) {
      this.selectedExam = exam
      this.registrationDialogVisible = true
      this.loadExamSites(exam.id)
    },

    handleViewDetail(exam) {
      this.detailExam = exam
      this.detailDialogVisible = true
    },

    handleRegisterFromDetail() {
      this.detailDialogVisible = false
      this.handleRegister(this.detailExam)
    },
    
    loadExamSites(examId) {
      // 调用后端API获取考点列表
      getSiteOptions().then(response => {
        const sites = response.data || []
        this.examSites = sites.map(item => {
          const totalCapacity = item.capacity || 100
          // 如果后端返回了剩余名额则使用，否则默认有剩余名额
          const remainingCapacity = item.remainingCapacity !== undefined 
            ? item.remainingCapacity 
            : Math.floor(totalCapacity * 0.7) // 默认剩余70%名额
          return {
            id: item.siteId,
            name: item.siteName,
            address: item.address || '',
            totalCapacity: totalCapacity,
            remainingCapacity: remainingCapacity
          }
        })
      }).catch(() => {
        this.$message.error('获取考点列表失败')
      })
    },
    
    handleSiteChange(siteId) {
      this.selectedSessionId = null
      this.loadExamSessions(siteId)
    },
    
    loadExamSessions(siteId) {
      // 调用后端API获取场次列表
      listSession({ siteId: siteId, status: '0', pageNum: 1, pageSize: 100 }).then(response => {
        const sessions = response.rows || []
        this.examSessions = sessions.map(item => {
          const totalCapacity = item.capacity || 50
          // 如果后端返回了剩余名额则使用，否则默认有剩余名额
          const remainingCapacity = item.remainingCapacity !== undefined 
            ? item.remainingCapacity 
            : Math.floor(totalCapacity * 0.6) // 默认剩余60%名额
          return {
            id: item.sessionId,
            date: item.examDate,
            startTime: item.startTime,
            endTime: item.endTime,
            totalCapacity: totalCapacity,
            remainingCapacity: remainingCapacity
          }
        })
      }).catch(() => {
        this.$message.error('获取场次列表失败')
      })
    },
    
    handleSubmitRegistration() {
      this.$refs.registrationForm.validate(valid => {
        if (valid) {
          // TODO: 调用后端API提交报名
          const registrationData = {
            examId: this.selectedExam.id,
            siteId: this.selectedSiteId,
            sessionId: this.selectedSessionId,
            ...this.registrationForm
          }
          
          console.log('提交报名数据：', registrationData)
          
          this.$message.success('报名成功！')
          this.registrationDialogVisible = false
          this.$router.push('/exam/my-exams')
        } else {
          this.$message.error('请填写完整的报名信息')
          return false
        }
      })
    },
    
    handleDialogClose() {
      this.selectedExam = null
      this.selectedSiteId = null
      this.selectedSessionId = null
      this.examSites = []
      this.examSessions = []
      this.$refs.registrationForm && this.$refs.registrationForm.resetFields()
    }
  }
}
</script>

<style lang="scss" scoped>
.exam-registration {
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

.exam-list-section {
  background: #ffffff;
  border-radius: 8px;
  padding: 24px 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  border: 1px solid #e8eaec;
  
  .section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    
    .section-title {
      font-size: 18px;
      color: #2c3e50;
      display: flex;
      align-items: center;
      font-weight: 600;
      margin: 0;
      
      i {
        margin-right: 8px;
        color: #1890ff;
      }
    }
  }
  
  .filter-tags {
    margin-bottom: 20px;
  }
  
  .exam-cards {
    margin-top: 20px;
    
    .el-col {
      margin-bottom: 20px;
    }
  }
}

.exam-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  border: 1px solid #e8eaec;
  transition: all 0.3s ease;
  height: 100%;
  display: flex;
  flex-direction: column;
  
  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
    border-color: #1890ff;
  }
  
  .exam-card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;
    
    .exam-category {
      font-size: 12px;
      color: #909399;
    }
  }
  
  .exam-title {
    font-size: 16px;
    font-weight: 600;
    color: #2c3e50;
    margin-bottom: 12px;
    line-height: 1.5;
  }
  
  .exam-info {
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-bottom: 12px;
    
    .info-item {
      display: flex;
      align-items: center;
      font-size: 13px;
      color: #606266;
      
      i {
        margin-right: 6px;
        color: #909399;
      }
    }
  }
  
  .exam-time {
    font-size: 12px;
    color: #909399;
    margin-bottom: 12px;
    
    i {
      margin-right: 4px;
    }
  }
}

.registration-dialog {
  max-height: 70vh;
  overflow-y: auto;
  padding-right: 10px;
  
  .exam-info-box {
    background: #f5f7fa;
    padding: 16px;
    border-radius: 4px;
    margin-bottom: 20px;
    
    h3 {
      font-size: 16px;
      color: #2c3e50;
      margin: 0 0 8px 0;
    }
    
    p {
      font-size: 14px;
      color: #606266;
      margin: 0;
    }
  }
  
  .site-selection,
  .session-selection,
  .registration-form {
    margin-bottom: 24px;
    
    &:last-child {
      margin-bottom: 0;
    }
    
    h4 {
      font-size: 15px;
      color: #2c3e50;
      margin-bottom: 12px;
      display: flex;
      align-items: center;
      
      i {
        margin-right: 6px;
        color: #1890ff;
      }
    }
  }
  
  ::v-deep .el-radio {
    white-space: normal;
    height: auto;
    line-height: 1.5;
    padding: 12px 15px;
    
    .el-radio__label {
      width: 100%;
      white-space: normal;
      padding-left: 10px;
    }
  }
  
  .site-info,
  .session-info {
    width: 100%;
    display: block;
    
    .site-name {
      font-size: 14px;
      font-weight: 600;
      color: #2c3e50;
      margin-bottom: 6px;
      line-height: 1.5;
    }
    
    .site-address {
      font-size: 13px;
      color: #606266;
      margin-bottom: 6px;
      line-height: 1.5;
    }
    
    .site-capacity,
    .session-capacity {
      font-size: 13px;
      color: #909399;
      line-height: 1.5;
      
      .text-danger {
        color: #ff4d4f;
        font-weight: 600;
      }
      
      .text-success {
        color: #52c41a;
        font-weight: 600;
      }
    }
    
    .session-time {
      font-size: 14px;
      color: #2c3e50;
      margin-bottom: 6px;
      line-height: 1.5;
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

.exam-detail {
  .detail-header {
    display: flex;
    align-items: center;
    gap: 12px;
    margin-bottom: 16px;
    
    .detail-category {
      font-size: 14px;
      color: #909399;
    }
  }
  
  .detail-title {
    font-size: 20px;
    font-weight: 600;
    color: #2c3e50;
    margin: 0 0 16px 0;
  }
  
  .detail-info {
    .info-row {
      display: flex;
      padding: 12px 0;
      border-bottom: 1px solid #f0f0f0;
      
      &:last-child {
        border-bottom: none;
      }
      
      .info-label {
        width: 120px;
        color: #909399;
        flex-shrink: 0;
        
        i {
          margin-right: 6px;
        }
      }
      
      .info-value {
        color: #2c3e50;
        flex: 1;
      }
    }
  }
}
</style>
