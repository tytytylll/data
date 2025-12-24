-- Add exam sessions for new subjects

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1010, 1, 'NCRE L2 - Site1 AM', 'NCRE_L2_SZ01_AM', '2025-03-15', '09:00:00', '11:00:00', 120, 0, '0', 4, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1010, 2, 'NCRE L2 - Site2 PM', 'NCRE_L2_SZ02_PM', '2025-03-15', '14:00:00', '16:00:00', 100, 0, '0', 5, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1011, 1, 'CET-6 - Site1 AM', 'ENG_CET6_SZ01_AM', '2025-06-14', '09:00:00', '11:25:00', 200, 0, '0', 6, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1011, 3, 'CET-6 - Site3 AM', 'ENG_CET6_CS01_AM', '2025-06-14', '09:00:00', '11:25:00', 180, 0, '0', 7, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1012, 2, 'Putonghua - Site2 AM', 'PTH_SZ02_AM', '2025-04-20', '08:30:00', '12:00:00', 50, 0, '0', 8, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1013, 1, 'Teacher Cert - Site1 AM', 'TEACHER_SZ01_AM', '2025-03-08', '09:00:00', '11:00:00', 150, 0, '0', 9, 'admin', NOW());

INSERT INTO exam_session (subject_id, site_id, session_name, session_code, exam_date, start_time, end_time, max_candidates, current_candidates, status, sort_order, create_by, create_time) VALUES
(1013, 3, 'Teacher Cert - Site3 AM', 'TEACHER_CS01_AM', '2025-03-08', '09:00:00', '11:00:00', 130, 0, '0', 10, 'admin', NOW());
