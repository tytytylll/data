<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考试科目" prop="subjectId">
        <el-select v-model="queryParams.subjectId" placeholder="请选择科目" clearable>
          <el-option
            v-for="item in subjectOptions"
            :key="item.subjectId"
            :label="item.subjectName"
            :value="item.subjectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="考点" prop="siteId">
        <el-select v-model="queryParams.siteId" placeholder="请选择考点" clearable>
          <el-option
            v-for="item in siteOptions"
            :key="item.siteId"
            :label="item.siteName"
            :value="item.siteId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="场次" prop="sessionId">
        <el-select v-model="queryParams.sessionId" placeholder="请选择场次" clearable>
          <el-option
            v-for="item in sessionOptions"
            :key="item.sessionId"
            :label="item.sessionName"
            :value="item.sessionId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否通过" prop="passStatus">
        <el-select v-model="queryParams.passStatus" placeholder="请选择" clearable>
          <el-option label="通过" value="1" />
          <el-option label="未通过" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="发布状态" prop="publishStatus">
        <el-select v-model="queryParams.publishStatus" placeholder="请选择" clearable>
          <el-option label="已发布" value="1" />
          <el-option label="未发布" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:score:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:score:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:score:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          :disabled="multiple"
          @click="handlePublish"
          v-hasPermi="['system:score:edit']"
        >发布成绩</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-time"
          size="mini"
          :disabled="multiple"
          @click="handleSchedulePublish"
          v-hasPermi="['system:score:edit']"
        >定时发布</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:score:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成绩ID" align="center" prop="scoreId" width="80" />
      <el-table-column label="考生姓名" align="center" prop="candidateName" width="120" />
      <el-table-column label="考试科目" align="center" prop="subjectName" width="120" />
      <el-table-column label="考点" align="center" prop="siteName" width="150" :show-overflow-tooltip="true" />
      <el-table-column label="场次" align="center" prop="sessionName" width="150" :show-overflow-tooltip="true" />
      <el-table-column label="考试时间" align="center" prop="examTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.examTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="成绩" align="center" prop="score" width="80" />
      <el-table-column label="等级" align="center" prop="scoreLevel" width="80" />
      <el-table-column label="是否通过" align="center" prop="passStatus" width="90">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.passStatus === '1'" type="success">通过</el-tag>
          <el-tag v-else type="danger">未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布状态" align="center" prop="publishStatus" width="90">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.publishStatus === '1'" type="success">已发布</el-tag>
          <el-tag v-else type="info">未发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center" prop="publishTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="定时发布" align="center" prop="scheduledPublishTime" width="160">
        <template slot-scope="scope">
          <span v-if="scope.row.scheduledPublishTime">{{ parseTime(scope.row.scheduledPublishTime) }}</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:score:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload2"
            @click="handlePublish(scope.row)"
            v-if="scope.row.publishStatus === '0'"
            v-hasPermi="['system:score:edit']"
          >发布</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleUnpublish(scope.row)"
            v-if="scope.row.publishStatus === '1'"
            v-hasPermi="['system:score:edit']"
          >取消发布</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-time"
            @click="handleSchedulePublish(scope.row)"
            v-if="scope.row.publishStatus === '0' && !scope.row.scheduledPublishTime"
            v-hasPermi="['system:score:edit']"
          >定时</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="handleCancelSchedule(scope.row)"
            v-if="scope.row.scheduledPublishTime"
            v-hasPermi="['system:score:edit']"
          >取消定时</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:score:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="考生" prop="candidateId">
          <el-select v-model="form.candidateId" placeholder="请选择考生" filterable>
            <el-option
              v-for="item in candidateOptions"
              :key="item.candidateId"
              :label="item.candidateName"
              :value="item.candidateId"
            />
          </el-select>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="考试科目" prop="subjectId">
              <el-select v-model="form.subjectId" placeholder="请选择科目">
                <el-option
                  v-for="item in subjectOptions"
                  :key="item.subjectId"
                  :label="item.subjectName"
                  :value="item.subjectId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="考点" prop="siteId">
              <el-select v-model="form.siteId" placeholder="请选择考点">
                <el-option
                  v-for="item in siteOptions"
                  :key="item.siteId"
                  :label="item.siteName"
                  :value="item.siteId"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="场次" prop="sessionId">
              <el-select v-model="form.sessionId" placeholder="请选择场次">
                <el-option
                  v-for="item in sessionOptions"
                  :key="item.sessionId"
                  :label="item.sessionName"
                  :value="item.sessionId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="考试时间" prop="examTime">
              <el-date-picker
                v-model="form.examTime"
                type="datetime"
                placeholder="选择考试时间"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="成绩分数" prop="score">
              <el-input v-model="form.score" placeholder="请输入分数" type="number" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="成绩等级" prop="scoreLevel">
              <el-select v-model="form.scoreLevel" placeholder="请选择" style="width: 100%">
                <el-option label="优秀" value="优秀" />
                <el-option label="良好" value="良好" />
                <el-option label="合格" value="合格" />
                <el-option label="不合格" value="不合格" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否通过" prop="passStatus">
              <el-select v-model="form.passStatus" placeholder="请选择" style="width: 100%">
                <el-option label="通过" value="1" />
                <el-option label="未通过" value="0" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 定时发布对话框 -->
    <el-dialog title="定时发布成绩" :visible.sync="scheduleOpen" width="400px" append-to-body>
      <el-form ref="scheduleForm" :model="scheduleForm" :rules="scheduleRules" label-width="120px">
        <el-form-item label="定时发布时间" prop="scheduledPublishTime">
          <el-date-picker
            v-model="scheduleForm.scheduledPublishTime"
            type="datetime"
            placeholder="选择发布时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            :picker-options="pickerOptions"
            style="width: 100%"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSchedule">确 定</el-button>
        <el-button @click="scheduleOpen = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listScore, getScore, delScore, addScore, updateScore, publishScore, unpublishScore, schedulePublish, cancelSchedule } from "@/api/system/exam/score"
import { optionselect as getSubjectOptions } from "@/api/system/exam/subject"
import { optionselect as getSiteOptions } from "@/api/system/exam/site"
import { listSession } from "@/api/system/exam/session"
import { optionselect as getCandidateOptions } from "@/api/system/exam/candidate"

export default {
  name: "ExamScore",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      scoreList: [],
      title: "",
      open: false,
      subjectOptions: [],
      siteOptions: [],
      sessionOptions: [],
      candidateOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subjectId: undefined,
        siteId: undefined,
        sessionId: undefined,
        passStatus: undefined,
        publishStatus: undefined
      },
      form: {},
      rules: {
        candidateId: [{ required: true, message: "考生不能为空", trigger: "change" }],
        subjectId: [{ required: true, message: "考试科目不能为空", trigger: "change" }],
        score: [{ required: true, message: "成绩分数不能为空", trigger: "blur" }]
      },
      scheduleOpen: false,
      scheduleForm: {
        scheduledPublishTime: undefined
      },
      scheduleRules: {
        scheduledPublishTime: [{ required: true, message: "请选择定时发布时间", trigger: "change" }]
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7
        }
      }
    }
  },
  created() {
    this.getList()
    this.getSubjectOptions()
    this.getSiteOptions()
    this.getSessionOptions()
    this.getCandidateOptions()
  },
  methods: {
    getList() {
      this.loading = true
      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getSubjectOptions() {
      getSubjectOptions().then(response => {
        this.subjectOptions = response.data
      })
    },
    getSiteOptions() {
      getSiteOptions().then(response => {
        this.siteOptions = response.data
      })
    },
    getSessionOptions() {
      listSession({ pageNum: 1, pageSize: 1000 }).then(response => {
        this.sessionOptions = response.rows
      })
    },
    getCandidateOptions() {
      getCandidateOptions().then(response => {
        this.candidateOptions = response.data
      })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        scoreId: undefined,
        candidateId: undefined,
        subjectId: undefined,
        siteId: undefined,
        sessionId: undefined,
        examTime: undefined,
        score: undefined,
        passStatus: "0",
        scoreLevel: undefined,
        status: "0",
        publishStatus: "0",
        remark: undefined
      }
      this.resetForm("form")
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.scoreId)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加成绩"
    },
    handleUpdate(row) {
      this.reset()
      const scoreId = row.scoreId || this.ids
      getScore(scoreId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改成绩"
      })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scoreId != undefined) {
            updateScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addScore(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认删除成绩编号为"' + scoreIds + '"的数据项？').then(() => {
        return delScore(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handlePublish(row) {
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认发布选中的成绩？发布后考生可查看成绩。').then(() => {
        return publishScore(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("发布成功")
      }).catch(() => {})
    },
    handleUnpublish(row) {
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认取消发布选中的成绩？取消后考生将无法查看成绩。').then(() => {
        return unpublishScore(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("取消发布成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('system/exam/score/export', {
        ...this.queryParams
      }, `score_${new Date().getTime()}.xlsx`)
    },
    handleSchedulePublish(row) {
      this.scheduleForm.scheduledPublishTime = undefined
      this.scheduleForm.scoreIds = row.scoreId ? [row.scoreId] : this.ids
      this.scheduleOpen = true
    },
    submitSchedule() {
      this.$refs["scheduleForm"].validate(valid => {
        if (valid) {
          schedulePublish(this.scheduleForm.scoreIds, this.scheduleForm.scheduledPublishTime).then(response => {
            this.$modal.msgSuccess("设置定时发布成功")
            this.scheduleOpen = false
            this.getList()
          })
        }
      })
    },
    handleCancelSchedule(row) {
      const scoreIds = row.scoreId || this.ids
      this.$modal.confirm('是否确认取消定时发布？').then(() => {
        return cancelSchedule(scoreIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("取消定时发布成功")
      }).catch(() => {})
    }
  }
}
</script>
