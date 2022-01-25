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
	user_role_id SERIAL REFERENCES ers_user_roles(ers_user_role_id)
);

CREATE TABLE ers_reimbursement(
	reimb_id SERIAL PRIMARY KEY,
	reim_amount INTEGER,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	--reimb_reciept
	reimb_author INTEGER REFERENCES ers_users(ers_users_id),
	reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id SERIAL REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id SERIAL REFERENCES ers_reimbursement_type(reimb_type_id)
);

WITH user_data (username, user_password, first_name, last_name, email, users_role) AS VALUES
	(),

	reimb_data (amount, submitted, resolved, description, author, resolver, reimburs_type, status) AS
		VALUES (),

	user_insert AS (
		INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email)
			SELECT username, user_password, first_name, last_name, email
			FROM user_data
		RETURNING ers_username, ers_users_id, user_role_id
	),

	role_insert AS (
		INSERT INTO ers_user_roles
			SELECT user_insert.user_role_id, user_data.users_role
			FROM user_data JOIN user_insert ON ers_username = username
	),

	reimb_insert AS (
		INSERT INTO ers_reimbursement
			SELECT amount, submitted, resolved, description, author, resolver
			FROM reimd_data
		RETURNING reimb_status_id, reimb_type_id
	),

	type_insert AS (
		INSERT INTO ers_reimbursement_type (reimb_type_id, reimb_type)
			SELECT reimb_insert.reimb_type_id, reimburs_type
			FROM reimb_data JOIN reimb_insert ON
	),

