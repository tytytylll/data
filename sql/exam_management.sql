-- ----------------------------
-- 考试管理系统表结构
-- ----------------------------

-- ----------------------------
-- 1、考试科目表
-- ----------------------------
DROP TABLE IF EXISTS exam_subject;
CREATE TABLE exam_subject (
  subject_id        BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '科目ID',
  subject_name      VARCHAR(100)    NOT NULL                   COMMENT '科目名称',
  subject_code      VARCHAR(50)     NOT NULL                   COMMENT '科目编码',
  description       VARCHAR(500)    DEFAULT NULL               COMMENT '科目描述',
  status            CHAR(1)         DEFAULT '0'                COMMENT '状态（0启用 1停用）',
  sort_order        INT(4)          DEFAULT 0                  COMMENT '显示顺序',
  create_by         VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time       DATETIME                                   COMMENT '创建时间',
  update_by         VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time       DATETIME                                   COMMENT '更新时间',
  remark            VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (subject_id),
  UNIQUE KEY uk_subject_code (subject_code)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '考试科目表';

-- ----------------------------
-- 初始化-考试科目表数据
-- ----------------------------
INSERT INTO exam_subject VALUES(1, '计算机基础', 'CS_BASIC', '计算机基础知识考试', '0', 1, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_subject VALUES(2, '英语四级', 'ENG_CET4', '大学英语四级考试', '0', 2, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_subject VALUES(3, '数学', 'MATH', '数学考试', '0', 3, 'admin', SYSDATE(), '', NULL, '');


-- ----------------------------
-- 2、考点表
-- ----------------------------
DROP TABLE IF EXISTS exam_site;
CREATE TABLE exam_site (
  site_id           BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '考点ID',
  site_name         VARCHAR(100)    NOT NULL                   COMMENT '考点名称',
  site_code         VARCHAR(50)     NOT NULL                   COMMENT '考点编码',
  province          VARCHAR(50)     DEFAULT NULL               COMMENT '省份',
  city              VARCHAR(50)     DEFAULT NULL               COMMENT '城市',
  district          VARCHAR(50)     DEFAULT NULL               COMMENT '区县',
  address           VARCHAR(200)    DEFAULT NULL               COMMENT '详细地址',
  contact_person    VARCHAR(50)     DEFAULT NULL               COMMENT '联系人',
  contact_phone     VARCHAR(20)     DEFAULT NULL               COMMENT '联系电话',
  capacity          INT(11)         DEFAULT 0                  COMMENT '容纳人数',
  status            CHAR(1)         DEFAULT '0'                COMMENT '状态（0启用 1禁用）',
  sort_order        INT(4)          DEFAULT 0                  COMMENT '显示顺序',
  create_by         VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time       DATETIME                                   COMMENT '创建时间',
  update_by         VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time       DATETIME                                   COMMENT '更新时间',
  remark            VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (site_id),
  UNIQUE KEY uk_site_code (site_code)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '考点表';

-- ----------------------------
-- 初始化-考点表数据
-- ----------------------------
INSERT INTO exam_site VALUES(1, '深圳市第一考点', 'SZ_SITE_01', '广东省', '深圳市', '南山区', '科技园南区科苑路15号', '张三', '13800138001', 500, '0', 1, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_site VALUES(2, '深圳市第二考点', 'SZ_SITE_02', '广东省', '深圳市', '福田区', '福田中心区益田路1号', '李四', '13800138002', 300, '0', 2, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_site VALUES(3, '长沙市第一考点', 'CS_SITE_01', '湖南省', '长沙市', '岳麓区', '岳麓大道569号', '王五', '13800138003', 400, '0', 3, 'admin', SYSDATE(), '', NULL, '');


-- ----------------------------
-- 3、考试场次表
-- ----------------------------
DROP TABLE IF EXISTS exam_session;
CREATE TABLE exam_session (
  session_id        BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '场次ID',
  subject_id        BIGINT(20)      NOT NULL                   COMMENT '科目ID',
  site_id           BIGINT(20)      NOT NULL                   COMMENT '考点ID',
  session_name      VARCHAR(100)    NOT NULL                   COMMENT '场次名称',
  session_code      VARCHAR(50)     NOT NULL                   COMMENT '场次编码',
  exam_date         DATE            NOT NULL                   COMMENT '考试日期',
  start_time        TIME            NOT NULL                   COMMENT '开始时间',
  end_time          TIME            NOT NULL                   COMMENT '结束时间',
  max_candidates    INT(11)         DEFAULT 0                  COMMENT '最大考生数',
  current_candidates INT(11)        DEFAULT 0                  COMMENT '当前报名人数',
  status            CHAR(1)         DEFAULT '0'                COMMENT '状态（0待开始 1进行中 2已结束 3已取消）',
  sort_order        INT(4)          DEFAULT 0                  COMMENT '显示顺序',
  create_by         VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  create_time       DATETIME                                   COMMENT '创建时间',
  update_by         VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  update_time       DATETIME                                   COMMENT '更新时间',
  remark            VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (session_id),
  UNIQUE KEY uk_session_code (session_code),
  KEY idx_subject_id (subject_id),
  KEY idx_site_id (site_id),
  KEY idx_exam_date (exam_date)
) ENGINE=INNODB AUTO_INCREMENT=1000 COMMENT = '考试场次表';

-- ----------------------------
-- 初始化-考试场次表数据
-- ----------------------------
INSERT INTO exam_session VALUES(1, 1, 1, '计算机基础-深圳一考-上午场', 'CS_BASIC_SZ01_AM', '2025-06-15', '09:00:00', '11:00:00', 100, 0, '0', 1, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_session VALUES(2, 1, 1, '计算机基础-深圳一考-下午场', 'CS_BASIC_SZ01_PM', '2025-06-15', '14:00:00', '16:00:00', 100, 0, '0', 2, 'admin', SYSDATE(), '', NULL, '');
INSERT INTO exam_session VALUES(3, 2, 2, '英语四级-深圳二考-上午场', 'ENG_CET4_SZ02_AM', '2025-06-20', '09:00:00', '11:30:00', 150, 0, '0', 3, 'admin', SYSDATE(), '', NULL, '');
