ALTER TABLE employee
    ADD COLUMN employee_number VARCHAR(20),
    ADD CONSTRAINT uk_employee_employee_number UNIQUE (employee_number);