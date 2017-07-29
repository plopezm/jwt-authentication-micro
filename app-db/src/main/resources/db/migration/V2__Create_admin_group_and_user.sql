INSERT INTO Permissions (name) VALUES ('ALL');
INSERT INTO Groups (name) VALUES ('Admin');
INSERT INTO Permissions_Groups (group_id, permission_id) VALUES (1, 1);
INSERT INTO Users (username, password, group_id) VALUES ('admin', '1234', 1)