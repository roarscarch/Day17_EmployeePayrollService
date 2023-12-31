-- UC1 create Db
CREATE DATABASE payroll_service;
use payroll_service;


--UC2 create table
CREATE TABLE IF NOT EXISTS employee_payroll (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    salary DOUBLE NOT NULL,
    startDate DATE NOT NULL
);

--UC3 Insert Data
INSERT INTO employee_payroll (name, salary, startDate) VALUES
('Chimany', 35000.00, '2023-02-15'),
('Vaisha', 35000.00, '2022-09-14'),
('Himanshu', 35000.00, '2021-08-13'),
('Priya', 42000.50, '2020-05-20'),
('Rahul', 30000.75, '2023-01-10'),
('Kavita', 38000.25, '2022-11-05');


--UC4 Retrieve Data
SELECT * FROM employee_payroll;

--UC5 Retrieve data for a particular date
SELECT * FROM employee_payroll WHERE startDate BETWEEN CAST('2020-01-01' AS DATE) AND DATE(NOW());

--UC6 Alter table to have gender
ALTER TABLE employee_payroll ADD gender CHAR(1) AFTER name;
UPDATE employee_payroll SET gender = 'm' WHERE name IN ('Himanshu', 'Rahul','Chinmay','Vaisha');
UPDATE employee_payroll SET gender = 'f' WHERE name = 'Kavita';

--UC7 Group BY Gender
SELECT
    gender,
    SUM(salary) AS total_salary,
    AVG(salary) AS average_salary,
    MIN(salary) AS min_salary,
    MAX(salary) AS max_salary,
    COUNT(*) AS employee_count
FROM
    employee_payroll
GROUP BY
    gender;

    --UC8 Alter table to contain phone address and department
    ALTER TABLE employee_payroll ADD phone_number VARCHAR(20) AFTER name;
    ALTER TABLE employee_payroll ADD address VARCHAR(250) AFTER phone_number;
    ALTER TABLE employee_payroll ADD department VARCHAR(250) NOT NULL AFTER address;
    ALTER TABLE employee_payroll ALTER address SET DEFAULT 'Default Address';
    DESC employee_payroll;


--UC9 Break Salary into Basic pay , Taxable Pay, Net pay etc

ALTER TABLE employee_payroll RENAME COLUMN salary TO basic_pay;
ALTER TABLE employee_payroll ADD deductions DOUBLE NOT NULL AFTER basic_pay;
ALTER TABLE employee_payroll ADD taxable_pay DOUBLE NOT NULL AFTER deductions;
ALTER TABLE employee_payroll ADD tax DOUBLE NOT NULL AFTER taxable_pay;
ALTER TABLE employee_payroll ADD net_pay DOUBLE NOT NULL AFTER tax;
DESC employee_payroll;

-- UC 10 Normalisation of database.
-- how? making of seperate tables for department  , salary , address  and employees table

CREATE TABLE IF NOT EXISTS departments (
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS addresses (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(250) NOT NULL,
    city VARCHAR(150) NOT NULL,
    state VARCHAR(150) NOT NULL,
    zip VARCHAR(10) NOT NULL
);


CREATE TABLE IF NOT EXISTS worker (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    gender CHAR(1),
    phone_number VARCHAR(20),
    address VARCHAR(250),
    start_date DATE NOT NULL,
    department_id INT,
    address_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id),
    FOREIGN KEY (address_id) REFERENCES addresses(address_id)
);
CREATE TABLE IF NOT EXISTS salaries (
    salary_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    basic_pay DOUBLE NOT NULL,
    deductions DOUBLE NOT NULL,
    taxable_pay DOUBLE NOT NULL,
    tax DOUBLE NOT NULL,
    net_pay DOUBLE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES worker(employee_id)
);
