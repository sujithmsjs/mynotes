
INSERT INTO dept(deptno,dname,loc) VALUES(10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO dept(deptno,dname,loc) VALUES(20, 'RESEARCH', 'DALLAS');
INSERT INTO dept(deptno,dname,loc) VALUES(30, 'SALES', 'CHICAGO');
INSERT INTO dept(deptno,dname,loc) VALUES(40, 'OPERATIONS', 'BOSTON');



INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7839, 'KING', 'PRESIDENT', null,
PARSEDATETIME('17-11-1981','d-M-yyyy'),
 5000, null, 10 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7698, 'BLAKE', 'MANAGER', 7839,
PARSEDATETIME('1-5-1981','d-M-yyyy'),
 2850, null, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7782, 'CLARK', 'MANAGER', 7839,
PARSEDATETIME('9-6-1981','d-M-yyyy'),
 2450, null, 10);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7566, 'JONES', 'MANAGER', 7839,
PARSEDATETIME('2-4-1981','d-M-yyyy'),
 2975, null, 20);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7788, 'SCOTT', 'ANALYST', 7566,
PARSEDATETIME('19-4-1987','d-M-yyyy'),
 3000, null, 20);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7902, 'FORD', 'ANALYST', 7566,
PARSEDATETIME('3-12-1981','d-M-yyyy'),
 3000, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7369, 'SMITH', 'CLERK', 7902,
PARSEDATETIME('17-12-1980','d-M-yyyy'),
 800, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7499, 'ALLEN', 'SALESMAN', 7698,
PARSEDATETIME('20-2-1981','d-M-yyyy'),
 1600, 300, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7521, 'WARD', 'SALESMAN', 7698,
PARSEDATETIME('22-2-1981','d-M-yyyy'),
 1250, 500, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7654, 'MARTIN', 'SALESMAN', 7698,
PARSEDATETIME('28-9-1981','d-M-yyyy'),
 1250, 1400, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7844, 'TURNER', 'SALESMAN', 7698,
PARSEDATETIME('8-9-1981','d-M-yyyy'),
 1500, 0, 30);

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7876, 'ADAMS', 'CLERK', 7788,
PARSEDATETIME('23-5-1987', 'd-M-yyyy'),
 1100, null, 20 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7900, 'JAMES', 'CLERK', 7698,
PARSEDATETIME('3-12-1981','d-M-yyyy'),
 950, null, 30 );

INSERT INTO emp(empno,ename,job,mgr,hiredate,sal,comm,dept) VALUES(
 7934, 'MILLER', 'CLERK', 7782,
PARSEDATETIME('23-1-1982','d-M-yyyy'),
 1300, null, 10 );

INSERT INTO salgrade(grade,losal,hisal) VALUES (1, 700, 1200);
INSERT INTO salgrade(grade,losal,hisal) VALUES (2, 1201, 1400);
INSERT INTO salgrade(grade,losal,hisal) VALUES (3, 1401, 2000);
INSERT INTO salgrade(grade,losal,hisal) VALUES (4, 2001, 3000);
INSERT INTO salgrade(grade,losal,hisal) VALUES (5, 3001, 9999);

