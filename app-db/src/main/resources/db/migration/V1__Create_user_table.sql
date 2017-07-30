CREATE TABLE Permissions (
  id          bigserial NOT NULL PRIMARY KEY,
  version     bigint NOT NULL DEFAULT 0,
  name        VARCHAR(75) NOT NULL
);

CREATE TABLE Groups (
  id          bigserial NOT NULL PRIMARY KEY,
  version     bigint NOT NULL DEFAULT 0,
  name        VARCHAR(75) NOT NULL
);

CREATE TABLE Permissions_Groups (
  group_id          bigint    NOT NULL,
  permission_id     bigint    NOT NULL,
  FOREIGN KEY (group_id) REFERENCES Groups(id),
  FOREIGN KEY (permission_id) REFERENCES Permissions(id),
  CONSTRAINT PK_perm_groups PRIMARY KEY (group_id, permission_id)
);

CREATE TABLE Users (
  id          bigserial NOT NULL PRIMARY KEY,
  version     bigint NOT NULL DEFAULT 0,
  username    VARCHAR(75) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  salt        bytea[64] NOT NULL DEFAULT '{0}',
  group_id    bigint not null,
  FOREIGN KEY (group_id) REFERENCES Groups(id)
);