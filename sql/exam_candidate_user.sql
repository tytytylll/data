-- 给考生表添加用户ID关联字段
ALTER TABLE exam_candidate ADD COLUMN user_id bigint DEFAULT NULL COMMENT '关联用户ID' AFTER status;

-- 示例：将考生张三关联到admin用户(user_id=1)
UPDATE exam_candidate SET user_id = 1 WHERE candidate_id = 1;

-- 添加测试成绩数据（已发布）
INSERT INTO exam_score (candidate_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time) 
VALUES (1, 1, 1, 1, '2024-12-20 09:00:00', 85.5, '1', '良好', '0', '1', NOW(), 'admin', NOW());
