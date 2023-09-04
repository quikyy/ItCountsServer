-- gender
CREATE TABLE genders (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);
INSERT INTO genders (name) VALUES ('Male');
INSERT INTO genders (name) VALUES ('Female');
INSERT INTO genders (name) VALUES ('None');


-- expense category
CREATE TABLE expense_categories (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO expense_categories (name) VALUES ('Food');
INSERT INTO expense_categories (name) VALUES ('House');
INSERT INTO expense_categories (name) VALUES ('Credit');
INSERT INTO expense_categories (name) VALUES ('Health');

-- user details
CREATE TABLE user_details (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NULL,
  last_name VARCHAR(255) NULL,
  date_of_birth DATE NULL,
  gender_id BIGINT NULL,
  CONSTRAINT user_details_gender_fk1 FOREIGN KEY (gender_id) REFERENCES genders (id),
  PRIMARY KEY(id)
);

-- users
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  register_date timestamp NOT NULL,
  user_details_id BIGINT NULL,
  CONSTRAINT users_user_details_fk1 FOREIGN KEY (user_details_id) REFERENCES user_details (id),
  PRIMARY KEY(id)
);

-- accounts
CREATE TABLE accounts (
 id BIGINT NOT NULL AUTO_INCREMENT,
 owner_id BIGINT NOT NULL,
 guest_id BIGINT NULL,
 CONSTRAINT accounts_users_owner_fk1 FOREIGN KEY (owner_id) REFERENCES users (id),
 CONSTRAINT account_users_guest_fk1 FOREIGN KEY (guest_id) REFERENCES users(id),
 PRIMARY KEY(id)
);

-- accounts expenses
CREATE TABLE accounts_expenses (
 id BIGINT NOT NULL AUTO_INCREMENT,
 amount DECIMAL NOT NULL,
 inserted_date TIMESTAMP NOT NULL,
 spend_date DATE NOT NULL,
 account_id BIGINT NULL,
 user_id BIGINT NULL,
 category_id BIGINT NULL,
 CONSTRAINT accounts_expenses_accounts_fk1 FOREIGN KEY (account_id) REFERENCES users (id),
 CONSTRAINT accounts_expenses_users_fk1 FOREIGN KEY (user_id) REFERENCES users (id),
 CONSTRAINT accounts_expenses_category_fk1 FOREIGN KEY (category_id) REFERENCES expense_categories (id),
 PRIMARY KEY(id)
);

