-- 考试管理菜单SQL

-- 考试管理菜单
INSERT INTO sys_menu VALUES(2000, '考试管理', 0, 4, 'exam', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'education', 'admin', SYSDATE(), '', NULL, '考试管理目录');

-- 考试科目菜单
INSERT INTO sys_menu VALUES(2001, '考试科目', 2000, 1, 'subject', 'system/exam/subject/index', NULL, '', 1, 0, 'C', '0', '0', 'system:subject:list', 'skill', 'admin', SYSDATE(), '', NULL, '考试科目菜单');
INSERT INTO sys_menu VALUES(2002, '科目查询', 2001, 1, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:subject:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2003, '科目新增', 2001, 2, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:subject:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2004, '科目修改', 2001, 3, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:subject:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2005, '科目删除', 2001, 4, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:subject:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2006, '科目导出', 2001, 5, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:subject:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 考点管理菜单
INSERT INTO sys_menu VALUES(2007, '考点管理', 2000, 2, 'site', 'system/exam/site/index', NULL, '', 1, 0, 'C', '0', '0', 'system:site:list', 'guide', 'admin', SYSDATE(), '', NULL, '考点管理菜单');
INSERT INTO sys_menu VALUES(2008, '考点查询', 2007, 1, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:site:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2009, '考点新增', 2007, 2, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:site:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2010, '考点修改', 2007, 3, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:site:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2011, '考点删除', 2007, 4, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:site:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2012, '考点导出', 2007, 5, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:site:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 考试场次菜单
INSERT INTO sys_menu VALUES(2013, '考试场次', 2000, 3, 'session', 'system/exam/session/index', NULL, '', 1, 0, 'C', '0', '0', 'system:session:list', 'date', 'admin', SYSDATE(), '', NULL, '考试场次菜单');
INSERT INTO sys_menu VALUES(2014, '场次查询', 2013, 1, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:session:query', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2015, '场次新增', 2013, 2, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:session:add', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2016, '场次修改', 2013, 3, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:session:edit', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2017, '场次删除', 2013, 4, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:session:remove', '#', 'admin', SYSDATE(), '', NULL, '');
INSERT INTO sys_menu VALUES(2018, '场次导出', 2013, 5, '', NULL, NULL, '', 1, 0, 'F', '0', '0', 'system:session:export', '#', 'admin', SYSDATE(), '', NULL, '');
