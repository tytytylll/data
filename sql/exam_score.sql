-- ----------------------------
-- 成绩表
-- ----------------------------
drop table if exists exam_score;
create table exam_score (
  score_id        bigint(20)      not null auto_increment    comment '成绩ID',
  user_id         bigint(20)      not null                   comment '用户ID',
  subject_id      bigint(20)      not null                   comment '科目ID',
  site_id         bigint(20)      default null               comment '考点ID',
  session_id      bigint(20)      default null               comment '场次ID',
  exam_time       datetime        default null               comment '考试时间',
  score           decimal(5,2)    default null               comment '成绩分数',
  pass_status     char(1)         default '0'                comment '是否通过（0未通过 1通过）',
  score_level     varchar(20)     default null               comment '成绩等级（优秀/良好/及格/不及格）',
  status          char(1)         default '0'                comment '状态（0正常 1作废）',
  publish_status  char(1)         default '0'                comment '发布状态（0未发布 1已发布）',
  publish_time    datetime        default null               comment '发布时间',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime        default null               comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime        default null               comment '更新时间',
  remark          varchar(500)    default null               comment '备注',
  primary key (score_id),
  key idx_user_id (user_id),
  key idx_subject_id (subject_id)
) engine=innodb auto_increment=1 comment = '考试成绩表';

-- ----------------------------
-- 插入测试数据
-- ----------------------------
INSERT INTO exam_score (user_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time, remark) VALUES
(1, 1, 1, 1, '2024-03-25 09:00:00', 85.50, '1', '良好', '0', '1', '2024-03-26 10:00:00', 'admin', NOW(), '测试成绩1'),
(1, 2, 1, 2, '2024-03-28 14:00:00', 92.00, '1', '优秀', '0', '1', '2024-03-29 10:00:00', 'admin', NOW(), '测试成绩2'),
(2, 1, 2, 1, '2024-03-25 09:00:00', 58.00, '0', '不及格', '0', '1', '2024-03-26 10:00:00', 'admin', NOW(), '测试成绩3');
