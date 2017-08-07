INSERT INTO Permissions (name) VALUES ('ALL');
INSERT INTO permissions (name) VALUES ('CREATE');
INSERT INTO permissions (name) VALUES ('DELETE');
INSERT INTO permissions (name) VALUES ('READ');
INSERT INTO Groups (name) VALUES ('Admin');
INSERT INTO Permissions_Groups (group_id, permission_id) VALUES (1, 1);
INSERT INTO Groups (name) VALUES ('User');
INSERT INTO Permissions_Groups (group_id, permission_id) VALUES (2, 4);

INSERT INTO Users (username, password, salt, group_id)
VALUES ('admin',
 '826c1f1614d66f2c6717f930e2773f1925cd01f96d1938ab467a3eb040a4d06626e7f21e4c4993e2e0e7f34d1fb760bd900473851f206bd93057b7733984a754',
  decode('00', 'hex')
  , 1)