-- 成绩管理菜单SQL

-- 成绩管理菜单
INSERT INTO sys_menu VALUES(2019, '成绩管理', 2000, 4, 'score', 'system/exam/score/index', NULL, '', 1, 0, 'C', '0', '0', 'system:score:list', 'chart', 'admin', SYSDATE(), '', NULL, '成绩管理菜单');
INSERT INTO sys_menu VALUES(2020, '成绩查询', 2019, 1, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:score:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2021, '成绩新增', 2019, 2, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:score:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2022, '成绩修改', 2019, 3, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:score:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2023, '成绩删除', 2019, 4, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:score:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2024, '成绩导出', 2019, 5, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:score:export', '#', 'admin', SYSDATE(), '', NULL, '');
