INSERT INTO Permissions (name) VALUES ('ALL');
INSERT INTO permissions (name) VALUES ('CREATE');
INSERT INTO permissions (name) VALUES ('DELETE');
INSERT INTO permissions (name) VALUES ('READ');
INSERT INTO Groups (name) VALUES ('Admin');
INSERT INTO Permissions_Groups (group_id, permission_id) VALUES (1, 1);
INSERT INTO Users (username, password, group_id) VALUES ('admin', 'c6fec4592d456c74eaa2d83054656f1116e95d1fc58d6abba616b5df67438908b911a7e0414d8240da4f467c6b50291d54ef3f254de7784158dce2a9961a8108', 1)