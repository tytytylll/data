<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="场次名称" prop="sessionName">
        <el-input
          v-model="queryParams.sessionName"
          placeholder="请输入场次名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试科目" prop="subjectId">
        <el-select v-model="queryParams.subjectId" placeholder="请选择考试科目" clearable>
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
      <el-form-item label="考试日期" prop="examDate">
        <el-date-picker
          v-model="queryParams.examDate"
          type="date"
          placeholder="选择考试日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="场次状态" clearable>
          <el-option label="待开始" value="0" />
          <el-option label="进行中" value="1" />
          <el-option label="已结束" value="2" />
          <el-option label="已取消" value="3" />
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
          v-hasPermi="['system:session:add']"
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
          v-hasPermi="['system:session:edit']"
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
          v-hasPermi="['system:session:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:session:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sessionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="场次ID" align="center" prop="sessionId" width="80" />
      <el-table-column label="场次名称" align="center" prop="sessionName" width="200" :show-overflow-tooltip="true" />
      <el-table-column label="场次编码" align="center" prop="sessionCode" width="150" />
      <el-table-column label="考试科目" align="center" prop="subjectName" width="120" />
      <el-table-column label="考点" align="center" prop="siteName" width="150" />
      <el-table-column label="考试日期" align="center" prop="examDate" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.examDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最大人数" align="center" prop="maxCandidates" width="90" />
      <el-table-column label="已报名" align="center" prop="currentCandidates" width="90" />
      <el-table-column label="状态" align="center" prop="status" width="90">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === '0'" type="info">待开始</el-tag>
          <el-tag v-else-if="scope.row.status === '1'" type="success">进行中</el-tag>
          <el-tag v-else-if="scope.row.status === '2'" type="warning">已结束</el-tag>
          <el-tag v-else-if="scope.row.status === '3'" type="danger">已取消</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:session:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:session:remove']"
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

    <!-- 添加或修改考试场次对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="考试科目" prop="subjectId">
              <el-select v-model="form.subjectId" placeholder="请选择考试科目">
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
        <el-form-item label="场次名称" prop="sessionName">
          <el-input v-model="form.sessionName" placeholder="请输入场次名称" />
        </el-form-item>
        <el-form-item label="场次编码" prop="sessionCode">
          <el-input v-model="form.sessionCode" placeholder="请输入场次编码" />
        </el-form-item>
        <el-row>
          <el-col :span="8">
            <el-form-item label="考试日期" prop="examDate">
              <el-date-picker
                v-model="form.examDate"
                type="date"
                placeholder="选择考试日期"
                value-format="yyyy-MM-dd"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开始时间" prop="startTime">
              <el-time-picker
                v-model="form.startTime"
                placeholder="选择开始时间"
                value-format="HH:mm:ss"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="结束时间" prop="endTime">
              <el-time-picker
                v-model="form.endTime"
                placeholder="选择结束时间"
                value-format="HH:mm:ss"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="最大人数" prop="maxCandidates">
              <el-input-number v-model="form.maxCandidates" controls-position="right" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="显示顺序" prop="sortOrder">
              <el-input-number v-model="form.sortOrder" controls-position="right" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="状态" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
                <el-option label="待开始" value="0" />
                <el-option label="进行中" value="1" />
                <el-option label="已结束" value="2" />
                <el-option label="已取消" value="3" />
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
  </div>
</template>

<script>
import { listSession, getSession, delSession, addSession, updateSession } from "@/api/system/exam/session"
import { optionselect as getSubjectOptions } from "@/api/system/exam/subject"
import { optionselect as getSiteOptions } from "@/api/system/exam/site"

export default {
  name: "ExamSession",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 考试场次表格数据
      sessionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 科目选项
      subjectOptions: [],
      // 考点选项
      siteOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sessionName: undefined,
        subjectId: undefined,
        siteId: undefined,
        examDate: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        subjectId: [
          { required: true, message: "考试科目不能为空", trigger: "change" }
        ],
        siteId: [
          { required: true, message: "考点不能为空", trigger: "change" }
        ],
        sessionName: [
          { required: true, message: "场次名称不能为空", trigger: "blur" }
        ],
        sessionCode: [
          { required: true, message: "场次编码不能为空", trigger: "blur" }
        ],
        examDate: [
          { required: true, message: "考试日期不能为空", trigger: "change" }
        ],
        startTime: [
          { required: true, message: "开始时间不能为空", trigger: "change" }
        ],
        endTime: [
          { required: true, message: "结束时间不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
    this.getSubjectOptions()
    this.getSiteOptions()
  },
  methods: {
    /** 查询考试场次列表 */
    getList() {
      this.loading = true
      listSession(this.queryParams).then(response => {
        this.sessionList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 查询科目选项 */
    getSubjectOptions() {
      getSubjectOptions().then(response => {
        this.subjectOptions = response.data
      })
    },
    /** 查询考点选项 */
    getSiteOptions() {
      getSiteOptions().then(response => {
        this.siteOptions = response.data
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        sessionId: undefined,
        subjectId: undefined,
        siteId: undefined,
        sessionName: undefined,
        sessionCode: undefined,
        examDate: undefined,
        startTime: undefined,
        endTime: undefined,
        maxCandidates: 0,
        currentCandidates: 0,
        sortOrder: 0,
        status: "0",
        remark: undefined
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sessionId)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加考试场次"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const sessionId = row.sessionId || this.ids
      getSession(sessionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改考试场次"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sessionId != undefined) {
            updateSession(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addSession(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sessionIds = row.sessionId || this.ids
      this.$modal.confirm('是否确认删除场次编号为"' + sessionIds + '"的数据项？').then(() => {
        return delSession(sessionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/exam/session/export', {
        ...this.queryParams
      }, `session_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
