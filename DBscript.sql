DROP TABLE IF EXISTS ers_reimbursement;
DROP TABLE IF EXISTS ers_users;
DROP TABLE IF EXISTS ers_user_roles;
DROP TABLE IF EXISTS ers_reimbursement_type;
DROP TABLE IF EXISTS ers_reimbursement_status;

CREATE TABLE ers_reimbursement_status(
	reimb_status_id INTEGER PRIMARY KEY,
	reimb_status VARCHAR(10)
);

CREATE TABLE ers_reimbursement_type(
	reimb_type_id INTEGER PRIMARY KEY,
	reimb_type VARCHAR(10)
);

CREATE TABLE ers_user_roles(
	ers_user_role_id INTEGER PRIMARY KEY,
	user_role VARCHAR(10)
);

CREATE TABLE ers_users(
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) UNIQUE,
	ers_password VARCHAR(100),
	user_first_name VARCHAR(100),
	user_last_name VARCHAR(100),
	user_email VARCHAR(150) UNIQUE,
	user_role_id INTEGER REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement(
	reimb_id SERIAL PRIMARY KEY,
	reim_amount NUMERIC(6, 2),
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_author INTEGER REFERENCES ers_users(ers_users_id),
	reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id INTEGER REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id INTEGER REFERENCES ers_reimbursement_type(reimb_type_id)
);

INSERT INTO ers_user_roles (ers_user_role_id, user_role)
	VALUES (1, 'Employee'), (2, 'Manager');

INSERT INTO ers_reimbursement_status (reimb_status_id, reimb_status)
	VALUES (1, 'Pending'), (2, 'Approved'), (3, 'Denied');

INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
	VALUES (1, 'Lodging'), (2, 'Travel'), (3, 'Food'), (4, 'Other');

WITH user_data (username, user_password, first_name, last_name, email, users_role) AS (VALUES
	('AliceInWL', '$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES', 'Alice', 'Adams', 'alice@company.com', 1),
	('Bobbert', '$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES', 'Bob', 'Butcher', 'bob@company.com', 1),
	('ChristmasCarol', '$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES', 'Carol', 'Carter', 'carol@company.com', 2),
	('DaveyDave', '$2a$10$aQWQsRhNqAlIvG7M4lliouKpzJlZmW3y5Siwx762NCC5DgzfbtSES', 'David', 'Davidson', 'david@company.com', 2)
	),

	reimb_data (amount, submitted, resolved, description, author, resolver, reimburs_type, status) AS (
		VALUES (35.00, '2022-01-07 14:05:00', NULL, 'Business lunch with client', 'Alice', NULL, 3, 1),
		(1500.00, '2021-01-01 14:00:00', '2021-01-02 14:05:00', 'Business trip to Vegas', 'Alice', 'Carol', 2, 3),
		(12.00, '2022-01-05 14:00:00', '2022-01-05 17:00:00', 'Related business event registration fee', 'Alice', 'David', 4, 2),
		(99.99, '2022-01-06 16:00:00', NULL, 'Super important Certification fee', 'Bob', NULL, 4, 1),
		(542.25, '2022-01-04 8:00:00', '2022-01-05 16:00:00', 'Business trip to inspect client site', 'Bob', 'David', 2, 2),
		(2265.01, '2022-01-04 21:00:00', '2022-01-05 8:00:00', 'emergency stay at a Ritz-Carlton', 'Bob', 'Carol', 1, 3),
		(225.59, '2022-01-07 12:00:00', NULL, 'Finance Summit', 'Carol', NULL, 2, 1),
		(55.00, '2022-01-07 17:00:00', NULL, 'Business dinner', 'David', NULL, 3,1)

	),

	user_insert AS (
		INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id)
			SELECT username, user_password, first_name, last_name, email, users_role
			FROM user_data
		RETURNING user_first_name, ers_users_id
	)

	INSERT INTO ers_reimbursement (reim_amount,
		reimb_submitted,
		reimb_resolved,
		reimb_description,
		reimb_author,
		reimb_resolver,
		reimb_status_id,
		reimb_type_id)
		SELECT amount, submitted::timestamp, resolved::timestamp, description, ui1.ers_users_id, ui2.ers_users_id, status, reimburs_type
		FROM reimb_data
		JOIN user_insert ui1 ON reimb_data.author = ui1.user_first_name
		LEFT JOIN user_insert ui2 ON reimb_data.resolver = ui2.user_first_name;





