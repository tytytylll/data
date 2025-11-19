# 首页数据更新说明

## 更新内容

已将首页的虚假数据替换为从数据库获取的真实数据。

## 修改的文件

### 1. 后端文件

#### 新增统计API控制器
**文件**: `d:\code\ruoyi\ruoyi-admin\src\main\java\com\ruoyi\web\controller\system\ExamStatisticsController.java`

提供首页统计数据接口：
- 考试科目总数
- 考点总数
- 考试场次总数

**接口地址**: `GET /system/exam/statistics/home`

**返回数据格式**:
```json
{
  "subjectCount": 3,
  "siteCount": 3,
  "sessionCount": 3
}
```

### 2. 前端文件

#### 新增统计API调用文件
**文件**: `d:\code\ruoyi\ruoyi-ui\src\api\system\exam\statistics.js`

封装统计数据API调用方法。

#### 更新首页组件
**文件**: `d:\code\ruoyi\ruoyi-ui\src\views\exam-home.vue`

**主要修改**:

1. **引入API模块**
   ```javascript
   import { getHomeStatistics } from "@/api/system/exam/statistics"
   import { listNotice } from "@/api/system/notice"
   ```

2. **统计数据改为从数据库获取**
   - 原来：显示虚假的"累计考试次数"、"题库总题数"、"平均成绩"
   - 现在：显示真实的"考试科目"、"考点数量"、"考试场次"
   - 数据来源：调用 `getHomeStatistics()` API

3. **公告数据改为从数据库获取**
   - 原来：硬编码的4条虚假公告
   - 现在：从 `sys_notice` 表获取最新的4条公告
   - 数据来源：调用 `listNotice()` API
   - 支持公告类型映射（通知、重要）
   - 如果没有公告，显示默认欢迎公告

4. **统计卡片样式优化**
   - 添加图标显示（文档、建筑、日期图标）
   - 添加悬停动画效果
   - 图标使用渐变色圆形背景

## 数据展示

### 统计数据
根据当前数据库中的实际数据显示：
- **考试科目**: 显示 `exam_subject` 表中的记录数
- **考点数量**: 显示 `exam_site` 表中的记录数
- **考试场次**: 显示 `exam_session` 表中的记录数

### 公告数据
- 从 `sys_notice` 表获取状态为启用（status='0'）的公告
- 最多显示4条最新公告
- 支持轮播展示
- 点击公告可查看详细内容

## 数据流程

```
首页加载
  ↓
调用 getHomeStatistics() API
  ↓
ExamStatisticsController.getHomeStatistics()
  ↓
查询 exam_subject、exam_site、exam_session 表
  ↓
返回统计数据
  ↓
首页显示实时数据
```

```
首页加载
  ↓
调用 listNotice() API
  ↓
查询 sys_notice 表（status='0'）
  ↓
返回公告列表
  ↓
首页轮播显示公告
```

## 测试说明

### 1. 启动后端服务
确保后端服务已启动，新的统计API已加载。

### 2. 访问首页
访问首页 `http://localhost/exam-home`，应该能看到：
- 统计数据显示当前数据库中的真实数据
- 公告栏显示系统中配置的公告信息

### 3. 验证数据
- 在后台管理中添加/删除科目、考点或场次
- 刷新首页，统计数据应该实时更新
- 在系统管理 > 通知公告中添加公告
- 刷新首页，新公告应该显示在轮播中

## 注意事项

1. **权限配置**
   - 统计API不需要特殊权限，任何用户都可以访问
   - 如需限制访问，可在Controller中添加 `@PreAuthorize` 注解

2. **性能优化**
   - 当前统计方式是查询全表后计数
   - 如果数据量大，建议使用 SQL COUNT 查询优化性能

3. **缓存建议**
   - 统计数据变化不频繁，可以考虑添加缓存
   - 建议缓存时间：5-10分钟

4. **错误处理**
   - 如果API调用失败，前端会显示默认值（0或默认公告）
   - 不会影响页面正常显示

## 后续优化建议

1. **统计数据扩展**
   - 添加已报名人数统计
   - 添加今日考试场次统计
   - 添加考试通过率统计

2. **公告功能增强**
   - 添加公告详情页面
   - 支持公告图片展示
   - 添加公告置顶功能

3. **性能优化**
   - 使用 Redis 缓存统计数据
   - 使用 SQL COUNT 优化查询
   - 添加数据预加载机制
