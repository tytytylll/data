-- 添加定时发布时间字段
ALTER TABLE exam_score ADD COLUMN scheduled_publish_time datetime DEFAULT NULL COMMENT '定时发布时间' AFTER publish_time;

-- 添加定时任务：每分钟检查一次待发布的成绩
INSERT INTO sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, remark) 
VALUES ('成绩定时发布任务', 'DEFAULT', 'examScoreTask.publishScheduledScores()', '0 * * * * ?', '2', '1', '0', 'admin', NOW(), '每分钟检查并发布已到定时发布时间的成绩');
