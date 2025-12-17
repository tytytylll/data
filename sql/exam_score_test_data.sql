-- ----------------------------
-- 考试成绩测试数据
-- 科目: 1-计算机基础, 2-英语四级, 3-数学
-- 考点: 1-深圳一考, 2-深圳二考, 3-长沙一考
-- 场次: 1-计算机上午, 2-计算机下午, 3-英语上午
-- 用户: 1-admin, 2-ry (若依系统默认用户)
-- ----------------------------

-- 清空现有测试数据
DELETE FROM exam_score;

-- 用户1 (admin) 的成绩数据
INSERT INTO exam_score (user_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time, remark) VALUES
(1, 1, 1, 1, '2025-03-15 09:00:00', 92.50, '1', '优秀', '0', '1', '2025-03-16 10:00:00', 'admin', NOW(), '计算机基础考试-优秀'),
(1, 2, 2, 3, '2025-03-20 09:00:00', 85.00, '1', '良好', '0', '1', '2025-03-21 10:00:00', 'admin', NOW(), '英语四级考试-良好'),
(1, 3, 1, 1, '2025-04-10 09:00:00', 78.50, '1', '及格', '0', '1', '2025-04-11 10:00:00', 'admin', NOW(), '数学考试-及格'),
(1, 1, 3, 2, '2025-05-05 14:00:00', 95.00, '1', '优秀', '0', '1', '2025-05-06 10:00:00', 'admin', NOW(), '计算机基础补考-优秀');

-- 用户2 (ry) 的成绩数据
INSERT INTO exam_score (user_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time, remark) VALUES
(2, 1, 1, 1, '2025-03-15 09:00:00', 68.00, '1', '及格', '0', '1', '2025-03-16 10:00:00', 'admin', NOW(), '计算机基础考试-及格'),
(2, 2, 2, 3, '2025-03-20 09:00:00', 55.50, '0', '不及格', '0', '1', '2025-03-21 10:00:00', 'admin', NOW(), '英语四级考试-不及格'),
(2, 3, 1, 1, '2025-04-10 09:00:00', 88.00, '1', '良好', '0', '1', '2025-04-11 10:00:00', 'admin', NOW(), '数学考试-良好');

-- 未发布的成绩（用于测试发布功能）
INSERT INTO exam_score (user_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time, remark) VALUES
(1, 2, 1, 3, '2025-06-01 09:00:00', 90.00, '1', '优秀', '0', '0', NULL, 'admin', NOW(), '待发布-英语四级'),
(2, 1, 2, 2, '2025-06-05 14:00:00', 72.50, '1', '及格', '0', '0', NULL, 'admin', NOW(), '待发布-计算机基础');

-- 更多用户的成绩（如果有其他测试用户）
INSERT INTO exam_score (user_id, subject_id, site_id, session_id, exam_time, score, pass_status, score_level, status, publish_status, publish_time, create_by, create_time, remark) VALUES
(1, 3, 2, 1, '2025-06-10 09:00:00', 82.00, '1', '良好', '0', '1', '2025-06-11 10:00:00', 'admin', NOW(), '数学补考-良好'),
(2, 2, 3, 3, '2025-06-15 09:00:00', 76.00, '1', '及格', '0', '1', '2025-06-16 10:00:00', 'admin', NOW(), '英语四级补考-及格');
