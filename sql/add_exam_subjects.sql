-- Add more exam subjects

-- Add new exam subjects
INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('NCRE Level 2', 'NCRE_L2', 'National Computer Rank Examination Level 2', '0', 4, 'admin', NOW());

INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('CET-6', 'ENG_CET6', 'College English Test Band 6', '0', 5, 'admin', NOW());

INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('Putonghua Test', 'PTH_TEST', 'Putonghua Proficiency Test', '0', 6, 'admin', NOW());

INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('Teacher Certificate', 'TEACHER_CERT', 'Teacher Qualification Examination', '0', 7, 'admin', NOW());

INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('Accounting Exam', 'ACCOUNTING', 'Accounting Qualification Examination', '0', 8, 'admin', NOW());

INSERT INTO exam_subject (subject_name, subject_code, description, status, sort_order, create_by, create_time) VALUES
('Driver Theory Test', 'DRIVER_L1', 'Motor Vehicle Driver Theory Test', '0', 9, 'admin', NOW());
