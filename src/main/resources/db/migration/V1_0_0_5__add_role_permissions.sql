INSERT INTO user_roles (code) values ('ADMIN'), ('USER');

INSERT INTO public.user_permissions(code, description) VALUES
('CREATE_ONE_TO_ONE','Создать заявку на собеседование'),
('GET_ONE_TO_ONE','Посмотреть заявку на собеседование'),
('UPDATE_ONE_TO_ONE','Обновить заявку на собеседование'),
('ACCEPT_ONE_TO_ONE','Откликнуться на созданную заявку собеседования'),
('CLOSE_ONE_TO_ONE','Закрыть заявку собеседование'),
('DELETE_ONE_TO_ONE','Удалить заявку на собеседование'),
('GET_ALL_ONE_TO_ONE','Получить список всех собеседований'),
('GET_ALL_USER_ONE_TO_ONE','Получить все собеседования для пользователя'),
('CREATE_FEEDBACK','Создать фидбек'),
('GET_FEEDBACK_BY_ONE_TO_ONE_AND_RECIPIENT_ID','Получить фидбек для данного собеседования'),
('GET_ALL_USER_STATISTICS','Получить общую статистику всех пользователей'),
('GET_USER_STATISTICS','Получить общую статистику пользователя'),
('GET_FULL_USER_STATISTICS','Получить полную статистику пользователя'),
('GET_USER_TECHNOLOGY_STATISTICS','Получить статистику пользователя по технологиям'),
('GET_ALL_USER_TECHNOLOGY_STATISTICS','Получить статистику по технологиям для всех пользователей'),
('ADD_LIST_QUESTIONS','Добавить список вопросов'),
('GET_QUESTION','Получить вопрос'),
('UPDATE_QUESTION','Обновить вопрос'),
('GET_ALL_QUESTIONS','Получить все вопросы'),
('ADD_TECHNOLOGY','Добавить технологию'),
('GET_TECHNOLOGY','Получить технологию'),
('GET_ALL_TECHNOLOGY','Получить список всех технологий'),
('GET_USER','Получить пользователя'),
('GET_ALL_USER_PERMISSION','Получить список всех разрешений'),
('GET_USER_PERMISSION','Получить список всех разрешений пользователя'),
('GET_ALL_USER_ROLE','Получить список всех ролей'),
('GET_USER_ROLE','Получить роль пользователя');

-- FOR ADMIN
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'CREATE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'UPDATE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'ACCEPT_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'CLOSE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'DELETE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_USER_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'CREATE_FEEDBACK';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_FEEDBACK_BY_ONE_TO_ONE_AND_RECIPIENT_ID';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_USER_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_USER_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_FULL_USER_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_USER_TECHNOLOGY_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_USER_TECHNOLOGY_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'ADD_LIST_QUESTIONS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_QUESTION,';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'UPDATE_QUESTION';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_QUESTIONS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'ADD_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_USER,';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_USER_PERMISSION';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_USER_PERMISSION';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_ALL_USER_ROLE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'ADMIN' AND up.code = 'GET_USER_ROLE';

--FOR USER
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'CREATE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'UPDATE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'ACCEPT_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'CLOSE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'DELETE_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_ALL_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_ALL_USER_ONE_TO_ONE';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'CREATE_FEEDBACK';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_FEEDBACK_BY_ONE_TO_ONE_AND_RECIPIENT_ID';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_USER_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_FULL_USER_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_USER_TECHNOLOGY_STATISTICS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'ADD_LIST_QUESTIONS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_QUESTION,';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'UPDATE_QUESTION';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_ALL_QUESTIONS';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'ADD_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_ALL_TECHNOLOGY';
INSERT INTO user_roles_permissions SELECT ur.id,up.id FROM user_roles ur,user_permissions up WHERE ur.code = 'USER' AND up.code = 'GET_USER,';