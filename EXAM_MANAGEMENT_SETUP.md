# 考试管理系统设置指南

## 功能概述

已完成以下三个管理功能的开发：

### 1. 考试科目管理 (37410)
- 新增、编辑、启用/停用考试科目
- 科目编码唯一性校验
- 科目名称唯一性校验
- 支持导出Excel

### 2. 考点管理 (37411)
- 新增、编辑、启用/禁用考点
- 支持省份、城市、区县三级地址
- 考点容纳人数管理
- 联系人和联系电话管理

### 3. 考试场次管理 (37412)
- 为特定科目在特定考点创建考试场次
- 控制单场最大人数
- 考试日期和时间段设置
- 场次状态管理（待开始、进行中、已结束、已取消）

## 部署步骤

### 1. 数据库初始化

执行以下SQL文件创建表结构和初始数据：

```bash
mysql -u root -p your_database < d:\code\ruoyi\sql\exam_management.sql
```

该文件包含：
- `exam_subject` - 考试科目表
- `exam_site` - 考点表
- `exam_session` - 考试场次表

### 2. 后端代码

已创建的后端文件：

**实体类 (Entity)**
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\domain\ExamSubject.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\domain\ExamSite.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\domain\ExamSession.java`

**Mapper接口**
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\mapper\ExamSubjectMapper.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\mapper\ExamSiteMapper.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\mapper\ExamSessionMapper.java`

**Mapper XML**
- `d:\code\ruoyi\ruoyi-system\src\main\resources\mapper\system\ExamSubjectMapper.xml`
- `d:\code\ruoyi\ruoyi-system\src\main\resources\mapper\system\ExamSiteMapper.xml`
- `d:\code\ruoyi\ruoyi-system\src\main\resources\mapper\system\ExamSessionMapper.xml`

**Service接口**
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\IExamSubjectService.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\IExamSiteService.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\IExamSessionService.java`

**Service实现**
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\impl\ExamSubjectServiceImpl.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\impl\ExamSiteServiceImpl.java`
- `d:\code\ruoyi\ruoyi-system\src\main\java\com\ruoyi\system\service\impl\ExamSessionServiceImpl.java`

**Controller**
- `d:\code\ruoyi\ruoyi-admin\src\main\java\com\ruoyi\web\controller\system\ExamSubjectController.java`
- `d:\code\ruoyi\ruoyi-admin\src\main\java\com\ruoyi\web\controller\system\ExamSiteController.java`
- `d:\code\ruoyi\ruoyi-admin\src\main\java\com\ruoyi\web\controller\system\ExamSessionController.java`

### 3. 前端代码

已创建的前端文件：

**API接口**
- `d:\code\ruoyi\ruoyi-ui\src\api\system\exam\subject.js`
- `d:\code\ruoyi\ruoyi-ui\src\api\system\exam\site.js`
- `d:\code\ruoyi\ruoyi-ui\src\api\system\exam\session.js`

**Vue页面**
- `d:\code\ruoyi\ruoyi-ui\src\views\system\exam\subject\index.vue`
- `d:\code\ruoyi\ruoyi-ui\src\views\system\exam\site\index.vue`
- `d:\code\ruoyi\ruoyi-ui\src\views\system\exam\session\index.vue`

### 4. 添加菜单和权限

需要在系统管理中添加以下菜单项：

#### 4.1 通过数据库直接插入菜单（推荐）

```sql
-- 考试管理菜单
INSERT INTO sys_menu VALUES(2000, '考试管理', 0, 4, 'exam', NULL, NULL, 1, 0, 'M', '0', '0', '', 'education', 'admin', SYSDATE(), '', NULL, '考试管理目录');

-- 考试科目菜单
INSERT INTO sys_menu VALUES(2001, '考试科目', 2000, 1, 'subject', 'system/exam/subject/index', NULL, 1, 0, 'C', '0', '0', 'system:subject:list', 'skill', 'admin', SYSDATE(), '', NULL, '考试科目菜单');
INSERT INTO sys_menu VALUES(2002, '科目查询', 2001, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:subject:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2003, '科目新增', 2001, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:subject:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2004, '科目修改', 2001, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:subject:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2005, '科目删除', 2001, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:subject:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2006, '科目导出', 2001, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:subject:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 考点管理菜单
INSERT INTO sys_menu VALUES(2007, '考点管理', 2000, 2, 'site', 'system/exam/site/index', NULL, 1, 0, 'C', '0', '0', 'system:site:list', 'guide', 'admin', SYSDATE(), '', NULL, '考点管理菜单');
INSERT INTO sys_menu VALUES(2008, '考点查询', 2007, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:site:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2009, '考点新增', 2007, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:site:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2010, '考点修改', 2007, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:site:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2011, '考点删除', 2007, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:site:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2012, '考点导出', 2007, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:site:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 考试场次菜单
INSERT INTO sys_menu VALUES(2013, '考试场次', 2000, 3, 'session', 'system/exam/session/index', NULL, 1, 0, 'C', '0', '0', 'system:session:list', 'date', 'admin', SYSDATE(), '', NULL, '考试场次菜单');
INSERT INTO sys_menu VALUES(2014, '场次查询', 2013, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:session:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2015, '场次新增', 2013, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:session:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2016, '场次修改', 2013, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:session:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2017, '场次删除', 2013, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:session:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2018, '场次导出', 2013, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:session:export', '#', 'admin', SYSDATE(), '', NULL, '');
```

#### 4.2 通过系统界面添加菜单

登录系统后，进入 **系统管理 > 菜单管理**，按照以下层级添加菜单：

1. **考试管理** (目录)
   - 菜单名称：考试管理
   - 显示排序：4
   - 菜单图标：education
   
2. **考试科目** (菜单)
   - 上级菜单：考试管理
   - 菜单名称：考试科目
   - 路由地址：subject
   - 组件路径：system/exam/subject/index
   - 权限字符：system:subject:list
   - 菜单图标：skill
   
   添加按钮权限：
   - 科目查询：system:subject:query
   - 科目新增：system:subject:add
   - 科目修改：system:subject:edit
   - 科目删除：system:subject:remove
   - 科目导出：system:subject:export

3. **考点管理** (菜单)
   - 上级菜单：考试管理
   - 菜单名称：考点管理
   - 路由地址：site
   - 组件路径：system/exam/site/index
   - 权限字符：system:site:list
   - 菜单图标：guide
   
   添加按钮权限：
   - 考点查询：system:site:query
   - 考点新增：system:site:add
   - 考点修改：system:site:edit
   - 考点删除：system:site:remove
   - 考点导出：system:site:export

4. **考试场次** (菜单)
   - 上级菜单：考试管理
   - 菜单名称：考试场次
   - 路由地址：session
   - 组件路径：system/exam/session/index
   - 权限字符：system:session:list
   - 菜单图标：date
   
   添加按钮权限：
   - 场次查询：system:session:query
   - 场次新增：system:session:add
   - 场次修改：system:session:edit
   - 场次删除：system:session:remove
   - 场次导出：system:session:export

### 5. 启动应用

#### 5.1 启动后端

```bash
cd d:\code\ruoyi
.\ry.bat
```

或者在IDE中运行 `RuoYiApplication.java`

#### 5.2 启动前端

```bash
cd d:\code\ruoyi\ruoyi-ui
npm run dev
```

### 6. 访问系统

访问地址：http://localhost:80

默认账号：admin / admin123

## 功能使用说明

### 考试科目管理
1. 进入 **考试管理 > 考试科目**
2. 点击"新增"按钮，填写科目信息
3. 可以通过状态开关快速启用/停用科目
4. 支持按科目名称、编码、状态查询

### 考点管理
1. 进入 **考试管理 > 考点管理**
2. 点击"新增"按钮，填写考点详细信息
3. 包括地址、联系人、容纳人数等
4. 可以通过状态开关快速启用/禁用考点

### 考试场次管理
1. 进入 **考试管理 > 考试场次**
2. 点击"新增"按钮
3. 选择考试科目和考点
4. 设置考试日期、时间段、最大人数
5. 系统会自动关联显示科目名称和考点名称
6. 支持按科目、考点、日期、状态等条件查询

## 数据关系

```
考试科目 (exam_subject)
    ↓ (一对多)
考试场次 (exam_session)
    ↓ (多对一)
考点 (exam_site)
```

一个考试科目可以在多个考点创建多个场次，每个场次对应一个科目和一个考点。

## 注意事项

1. 科目编码和考点编码必须唯一
2. 场次编码必须唯一
3. 删除科目或考点前，请确保没有关联的考试场次
4. 考试场次的最大人数不能超过考点的容纳人数（建议在业务逻辑中添加此校验）
5. 已有考生报名的场次建议不要删除，可以设置为"已取消"状态

## 后续优化建议

1. 添加考试场次与考点容纳人数的校验
2. 添加考试场次时间冲突检测（同一考点同一时间段不能有多个场次）
3. 添加考生报名功能
4. 添加考试成绩管理
5. 添加考试统计报表
6. 添加考试通知功能
