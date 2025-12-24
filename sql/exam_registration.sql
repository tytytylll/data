-- Exam Registration Table
DROP TABLE IF EXISTS exam_registration;
CREATE TABLE exam_registration (
  registration_id   BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT 'Registration ID',
  user_id           BIGINT(20)      NOT NULL                   COMMENT 'User ID',
  subject_id        BIGINT(20)      NOT NULL                   COMMENT 'Subject ID',
  site_id           BIGINT(20)      NOT NULL                   COMMENT 'Site ID',
  session_id        BIGINT(20)      NOT NULL                   COMMENT 'Session ID',
  seat_number       VARCHAR(20)     DEFAULT NULL               COMMENT 'Seat Number',
  status            CHAR(1)         DEFAULT '0'                COMMENT 'Status (0:Pending 1:Confirmed 2:Cancelled)',
  create_by         VARCHAR(64)     DEFAULT ''                 COMMENT 'Created By',
  create_time       DATETIME                                   COMMENT 'Create Time',
  update_by         VARCHAR(64)     DEFAULT ''                 COMMENT 'Updated By',
  update_time       DATETIME                                   COMMENT 'Update Time',
  remark            VARCHAR(500)    DEFAULT NULL               COMMENT 'Remark',
  PRIMARY KEY (registration_id),
  KEY idx_user_id (user_id),
  KEY idx_subject_id (subject_id),
  KEY idx_session_id (session_id)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = 'Exam Registration Table';

-- Add registration data for user wangwu (user_id=113)
INSERT INTO exam_registration (user_id, subject_id, site_id, session_id, seat_number, status, create_by, create_time) VALUES
(113, 1, 1, 1, 'A001', '1', 'admin', NOW()),
(113, 2, 2, 3, 'B015', '0', 'admin', NOW()),
(113, 1010, 1, 1000, 'C008', '1', 'admin', NOW());
