INSERT INTO app_management.roles (code, description) VALUES ('ROLE_ADMIN', 'System Administrator. All access.');
INSERT INTO app_management.roles (code, description) VALUES ('ROLE_MANAGER', 'Project Manager. All access to projects and tasks.');
INSERT INTO app_management.roles (code, description) VALUES ('ROLE_USER', 'User. All access to tasks.');

INSERT INTO app_management.status_project (code, name) VALUES ('PENDING', 'Project is Pending');
INSERT INTO app_management.status_project (code, name) VALUES ('IN_PROGRESS', 'The Project is in Progress');
INSERT INTO app_management.status_project (code, name) VALUES ('COMPLETED', 'The Project is completed');
INSERT INTO app_management.status_project (code, name) VALUES ('CANCELLED', 'The Project is cancelled');
INSERT INTO app_management.status_project (code, name) VALUES ('IN_WAIT', 'The Project is in Wait');
INSERT INTO app_management.status_project (code, name) VALUES ('IN_ANALYSIS', 'The Project is in analysis');

INSERT INTO status_task (code, name) VALUES ('TODO', 'To Do - The task is pending and has not started yet.');
INSERT INTO status_task (code, name) VALUES ('IN_PROGRESS', 'In Progress - The task has been initiated and is currently being worked on.');
INSERT INTO status_task (code, name) VALUES ('COMPLETED', 'Completed - The task has been successfully finished and all requirements have been met.');
INSERT INTO status_task (code, name) VALUES ('PAUSED', 'Paused - The task has been temporarily stopped and will resume later.');
INSERT INTO status_task (code, name) VALUES ('BLOCKED', 'Blocked - The task cannot progress due to some issue or external dependency.');
INSERT INTO status_task (code, name) VALUES ('UNDER_REVIEW', 'Under Review - The task has been completed and is currently being reviewed by a supervisor.');
INSERT INTO status_task (code, name) VALUES ('REJECTED', 'Rejected - The task has been reviewed but does not meet the established standards and needs corrections or modifications.');
INSERT INTO status_task (code, name) VALUES ('ON_HOLD', 'On Hold - The task is waiting for a condition to be met or an event to occur before it can start or continue.');
INSERT INTO status_task (code, name) VALUES ('REASSIGNED', 'Reassigned - The task has been assigned to another user due to changes in resource allocation or responsibilities.');
INSERT INTO status_task (code, name) VALUES ('OVERDUE', 'Overdue - The task has not been completed within the planned timeframe.');
INSERT INTO status_task (code, name) VALUES ('CANCELLED', 'Cancelled - The task has been cancelled and is no longer required or relevant for the project.');
INSERT INTO status_task (code, name) VALUES ('AT_RISK', 'At Risk - The task is facing challenges or issues that may affect its successful completion.');

INSERT INTO priority (code, name) VALUES ('HIGH', 'High - This task is critical and requires immediate attention. It has a significant impact on the project and must be prioritized above all others.');
INSERT INTO priority (code, name) VALUES ('MEDIUM', 'Medium - This task is important and needs to be addressed promptly, but it does not have the same level of urgency as high-priority tasks.');
INSERT INTO priority (code, name) VALUES ('LOW', 'Low - This task is important, but it can be managed with some flexibility in the timelines. It does not require immediate attention.');
INSERT INTO priority (code, name) VALUES ('URGENT', 'Urgent - This task demands swift action and needs to be dealt with promptly, but its impact may not be as critical as high-priority tasks.');
INSERT INTO priority (code, name) VALUES ('NORMAL', 'Normal - This task has a standard priority and should be completed within the planned timelines without additional urgency.');
INSERT INTO priority (code, name) VALUES ('CRITICAL', 'Critical - This task is essential for the success of the project. Failure to address it properly could have severe consequences for the project.');
INSERT INTO priority (code, name) VALUES ('IMMEDIATE', 'Immediate - This task requires immediate action and must be tackled without delay.');
INSERT INTO priority (code, name) VALUES ('NOT_PRIORITIZED', 'Not Prioritized - This task has not yet been assigned a specific priority level and needs further evaluation and classification.');

INSERT INTO users (name, email, password, active, create_date) VALUES ('Admin', 'angel.villa@gmail.com', '12345', '1', NOW());

INSERT INTO profiles (photo, description, interest, id_user) VALUES (null, 'User administration', null,(SELECT id_user from users where users.email='angel.villa@gmail.com'));

INSERT INTO users_roles (id_user, id_rol) VALUES ((SELECT id_user from users where users.email='angel.villa@gmail.com'), (select id_rol from roles where code='ROLE_ADMIN'));