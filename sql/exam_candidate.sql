-- 考生信息表
DROP TABLE IF EXISTS exam_candidate;
CREATE TABLE exam_candidate (
  candidate_id    bigint(20)      NOT NULL AUTO_INCREMENT    COMMENT '考生ID',
  candidate_name  varchar(50)     NOT NULL                   COMMENT '考生姓名',
  id_card         varchar(18)     DEFAULT NULL               COMMENT '身份证号',
  phone           varchar(11)     DEFAULT NULL               COMMENT '手机号码',
  gender          char(1)         DEFAULT '0'                COMMENT '性别（0男 1女 2未知）',
  email           varchar(50)     DEFAULT NULL               COMMENT '邮箱',
  status          char(1)         DEFAULT '0'                COMMENT '状态（0正常 1停用）',
  user_id         bigint(20)      DEFAULT NULL               COMMENT '关联用户ID',
  create_by       varchar(64)     DEFAULT ''                 COMMENT '创建者',
  create_time     datetime        DEFAULT NULL               COMMENT '创建时间',
  update_by       varchar(64)     DEFAULT ''                 COMMENT '更新者',
  update_time     datetime        DEFAULT NULL               COMMENT '更新时间',
  remark          varchar(500)    DEFAULT NULL               COMMENT '备注',
  PRIMARY KEY (candidate_id),
  UNIQUE KEY idx_id_card (id_card),
  KEY idx_user_id (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='考生信息表';

-- 修改成绩表，添加考生ID字段
ALTER TABLE exam_score ADD COLUMN candidate_id bigint(20) DEFAULT NULL COMMENT '考生ID' AFTER score_id;
ALTER TABLE exam_score ADD KEY idx_candidate_id (candidate_id);

-- 如果exam_candidate表已存在但缺少user_id字段，执行以下语句添加
-- ALTER TABLE exam_candidate ADD COLUMN user_id bigint(20) DEFAULT NULL COMMENT '关联用户ID' AFTER status;
-- ALTER TABLE exam_candidate ADD KEY idx_user_id (user_id);

-- 考生管理菜单
INSERT INTO sys_menu VALUES(2025, '考生管理', 2000, 5, 'candidate', 'system/exam/candidate/index', NULL, '', 1, 0, 'C', '0', '0', 'system:candidate:list', 'peoples', 'admin', SYSDATE(), '', NULL, '考生管理菜单');
INSERT INTO sys_menu VALUES(2026, '考生查询', 2025, 1, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:candidate:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2027, '考生新增', 2025, 2, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:candidate:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2028, '考生修改', 2025, 3, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:candidate:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2029, '考生删除', 2025, 4, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:candidate:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2030, '考生导出', 2025, 5, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:candidate:export', '#', 'admin', SYSDATE(), '', NULL, '');
