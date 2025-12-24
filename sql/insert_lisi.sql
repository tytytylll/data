-- 插入学生李四
INSERT INTO sys_user (user_name, nick_name, phonenumber, password, status, del_flag, create_time)
VALUES ('13800138002', '李四', '13800138002', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', NOW());

-- 获取新插入用户的ID并分配普通角色
SET @user_id = LAST_INSERT_ID();
INSERT INTO sys_user_role (user_id, role_id) VALUES (@user_id, 2);
