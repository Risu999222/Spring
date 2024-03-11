
	function generateRandomString(length) {
	    var result = '';
	    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	    var charactersLength = characters.length;
	    for (var i = 0; i < length; i++) {
	        result += characters.charAt(Math.floor(Math.random() * charactersLength));
	    }
	    return result;
	}
	var randomString = generateRandomString(20); 
	pm.globals.set("requestId", randomString);
	
	pm.globals.set("timestamp", new Date().getTime());

Above is the script to add in pre request script of postman


Postman body exmaple for any request
{
    "token": "{{requestId}}",
	"data": {
        
    },
	"reqid": "{{requestId}}",
	"_client_ts": "{{timestamp}}",
	"_client_type" : "web"
}

	SQL Scripts
	-- Create the departments table
	CREATE TABLE departments (
	    id SERIAL PRIMARY KEY,
	    deptname VARCHAR(100) NOT NULL
	);
	
	-- Populate the departments table with at least 10 records
	INSERT INTO departments (deptname) VALUES
	('Sales'),
	('Marketing'),
	('Finance'),
	('Human Resources'),
	('Information Technology'),
	('Research and Development'),
	('Customer Service'),
	('Operations'),
	('Legal'),
	('Administration');
	
	-- Create the ranks table
	CREATE TABLE ranks (
	    id SERIAL PRIMARY KEY,
	    rankdesc VARCHAR(100) NOT NULL,
	    parentrankid INT
	);
	
	-- Populate the ranks table with at least 10 records
	INSERT INTO ranks (rankdesc, parentrankid) VALUES
	('Manager', NULL),
	('Assistant Manager', 1),
	('Senior Executive', 1),
	('Executive', 3),
	('Senior Analyst', NULL),
	('Analyst', 5),
	('Senior Consultant', 1),
	('Consultant', 7),
	('Associate', 1),
	('Senior Associate', 9);
	
	-- Create the employee table
	CREATE TABLE employee (
	    id BIGSERIAL PRIMARY KEY,
	    empid BIGINT UNIQUE NOT NULL,
	    fname VARCHAR(50) NOT NULL,
	    fullname VARCHAR(100),
	    dob DATE NOT NULL,
	    doj DATE NOT NULL,
	    salary INTEGER NOT NULL,
	    reportsto BIGINT,
	    deptid BIGINT REFERENCES departments(id),
	    rankid BIGINT REFERENCES ranks(id),
	    createdat TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	    updatedat TIMESTAMP,
	    client_reqid VARCHAR(50) NOT NULL
	);
	
	
	Select * from ranks;
	
	Select * from employee;
	
	Select e.fullname, r.rankdesc, d.deptname from employee e 
	join ranks r on e.rankid = r.id
	join departments d on e.deptid = d.id
	WHERE e.id = 1;
	
	-- Create employee_shadow table
	CREATE TABLE employee_shadow (
	    id BIGSERIAL PRIMARY KEY,
	    empid BIGINT NOT NULL,
	    fname VARCHAR(50) NOT NULL,
	    fullname VARCHAR(100),
	    dob DATE NOT NULL,
	    doj DATE NOT NULL,
	    salary INTEGER NOT NULL,
	    reportsto INT,
	    deptid INT REFERENCES departments(id),
	    rankid INT REFERENCES ranks(id),
	    createdat TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	    updatedat TIMESTAMP,
	    client_reqid VARCHAR(50) NOT NULL
	);
	
	Select * from employee_shadow;
