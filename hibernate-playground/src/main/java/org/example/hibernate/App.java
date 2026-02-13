package org.example.hibernate;

import java.util.List;

import org.example.hibernate.entity.Department;
import org.example.hibernate.entity.Employee;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

	public static void main(String[] args) {
		System.out.println("=================================");
		System.out.println("HIBERNATE PLAYGROUND STARTED");
		System.out.println("=================================\n");

		Long deptId = demoCascadePersist();
		demoFetchType(deptId);
		demoOrphanRemoval(deptId);

		// Setup more data for N+1
		createDataForNPlusOne();
		demoNPlusOneProblem();
		demoNPlusOneSolution();
		demoCascadeRemove(); // Uses new data from N+1 setup to demonstrate remove
		demoLazyInitExceptionSesionClosed();
		HibernateUtil.shutdown();
		System.out.println("\n=================================");
		System.out.println("HIBERNATE PLAYGROUND FINISHED");
		System.out.println("=================================");
	}

	private static Long demoCascadePersist() {
		System.out.println(">>> SCENARIO 1: CASCADE PERSIST");
		System.out.println("Creating Department 'IT' and adding 2 Employees.");
		System.out.println("Only saving Department. Employees should be saved automatically.");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Department dept = new Department("IT");
		Employee emp1 = new Employee("Alice");
		Employee emp2 = new Employee("Bob");

		dept.addEmployee(emp1);
		dept.addEmployee(emp2);
//		emp1.setDepartment(dept);
//		emp2.setDepartment(dept);
		session.persist(dept);
//		session.persist(emp1);
//		session.persist(emp2);

//		session.persist(emp1);
//		session.persist(emp2);

		tx.commit();
		session.close();

		System.out.println("Department saved with ID: " + dept.getId());
		System.out.println("--------------------------------------------------\n");
		return dept.getId();
	}

	private static void demoFetchType(Long deptId) {
		System.out.println(">>> SCENARIO 2: FETCH TYPE (LAZY LOADING)");
		System.out.println("Loading Department. Employees should NOT be fetched yet.");

		Session session = HibernateUtil.getSessionFactory().openSession();

		// 1. Load Department
		Department dept = session.get(Department.class, deptId);
		System.out.println("Department Loaded: " + dept.getName());
		System.out.println("(Check logs: Only 1 select on departments table)");

		System.out.println("... Accessing Employees List ...");
		// 2. Access collection - Triggers initialization
		System.out.println("Employee Count: " + dept.getEmployees().size());
		System.out.println("(Check logs: Now a select on employees table occurred)");

		session.close();
		System.out.println("--------------------------------------------------\n");
	}

	private static void demoOrphanRemoval(Long deptId) {
		System.out.println(">>> SCENARIO 3: ORPHAN REMOVAL");
		System.out.println("Removing 'Bob' from IT Department's list.");
		System.out.println("Saving Department. 'Bob' should be DELETED from DB.");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Department dept = session.get(Department.class, deptId);
		List<Employee> employees = dept.getEmployees();

		// Find Bob and remove him
		Employee bob = employees.stream().filter(e -> e.getName().equals("Bob")).findFirst().orElse(null);

		if (bob != null) {
			dept.removeEmployee(bob);
			System.out.println("Removed Bob from list.");
		}
//
//		// We only merge/update Department. Bob is an "orphan" and should be deleted.
		session.merge(dept);
//
//
		tx.commit();
		session.close();
		System.out.println("--------------------------------------------------\n");
	}

	private static void createDataForNPlusOne() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		for (int i = 1; i <= 3; i++) {
			Department d = new Department("Dept_" + i);
			d.addEmployee(new Employee("Emp_" + i + "_A"));
			d.addEmployee(new Employee("Emp_" + i + "_B"));
			session.persist(d);
		}

		tx.commit();
		session.close();
	}

	private static void demoNPlusOneProblem() {
		System.out.println(">>> SCENARIO 4: N+1 PROBLEM");
		System.out.println("Loading all Departments, then printing their employees.");

		Session session = HibernateUtil.getSessionFactory().openSession();

		// 1. Initial Query (1 query)
		List<Department> depts = session.createQuery("FROM Department", Department.class).getResultList();
		System.out.println("Loaded " + depts.size() + " departments.");

		// 2. Iterate (N queries)
		int count = 0;
		for (Department d : depts) {
			System.out.print("Dept: " + d.getName() + " -> Employees: ");
			// This triggers a separate SELECT for each department's employees
			for (Employee e : d.getEmployees()) {
				System.out.print(e.getName() + " ");
			}
			System.out.println();
			count++;
			if (count > 5) {
				break; // Limit output
			}
		}

		session.close();
		System.out.println("(Check logs: You should see 1 SELECT for Depts + N SELECTs for Employees)");
		System.out.println("--------------------------------------------------\n");
	}

	private static void demoLazyInitExceptionSesionClosed() {
		System.out.println(">>> SCENARIO 5: Lazy initialization exception");
		System.out.println("Loading all Departments, then printing their employees.");

		Session session = HibernateUtil.getSessionFactory().openSession();

		// 1. Initial Query (1 query)
		List<Department> depts = session.createQuery("FROM Department", Department.class).getResultList();
		System.out.println("Loaded " + depts.size() + " departments.");
		session.close();
		int count = 0;
		for (Department d : depts) {
			System.out.print("Dept: " + d.getName() + " -> Employees: ");
			// This triggers a separate SELECT for each department's employees, but since
			// session is closed, it will throw exception
			for (Employee e : d.getEmployees()) {
				System.out.print(e.getName() + " ");
			}
			System.out.println();
			count++;
			if (count > 5) {
				break; // Limit output
			}
		}

		System.out.println("(Check logs: You should see 1 SELECT for Depts + N SELECTs for Employees)");
		System.out.println("--------------------------------------------------\n");
	}

	private static void demoNPlusOneSolution() {
		System.out.println(">>> SCENARIO 5: N+1 SOLUTION (JOIN FETCH)");
		System.out.println("Using 'JOIN FETCH' to load Departments AND Employees in one go.");

		Session session = HibernateUtil.getSessionFactory().openSession();

		// 1. Optimized Query (1 query to fetch everything)
		List<Department> depts = session
				.createQuery("SELECT DISTINCT d FROM Department d JOIN FETCH d.employees", Department.class)
				.getResultList();

		System.out.println("Loaded " + depts.size() + " departments.");

		for (Department d : depts) {
			System.out.print("Dept: " + d.getName() + " -> Employees: ");
			// No extra SQL triggered here!
			for (Employee e : d.getEmployees()) {
				System.out.print(e.getName() + " ");
			}
			System.out.println();
		}

		session.close();
		System.out.println("(Check logs: Only 1 SELECT with JOIN)");
		System.out.println("--------------------------------------------------\n");
	}

	private static void demoCascadeRemove() {
		System.out.println(">>> SCENARIO 6: CASCADE REMOVE");
		System.out.println("Deleting a Department. All its employees should be deleted.");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Pick one generic dept
		Department dept = session.createQuery("FROM Department WHERE name = 'Dept_1'", Department.class)
				.getSingleResult();

		System.out.println("Deleting: " + dept);
		session.remove(dept);

		tx.commit();
		session.close();
		System.out.println("(Check logs: DELETE statement for Employees followed by DELETE for Department)");
		System.out.println("--------------------------------------------------\n");
	}
}
