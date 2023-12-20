import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String name;
    private int leaveBalance;

    public Employee(String name, int leaveBalance) {
        this.name = name;
        this.leaveBalance = leaveBalance;
    }

    public String getName() {
        return name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void deductLeave(int days) {
        leaveBalance -= days;
    }
}

public class LeaveManagementSystem {
    private Map<String, Employee> employees;
    private Map<String, ArrayList<String>> leaveRequests;

    public LeaveManagementSystem() {
        employees = new HashMap<>();
        leaveRequests = new HashMap<>();
    }

    public void addEmployee(String name, int leaveBalance) {
        Employee employee = new Employee(name, leaveBalance);
        employees.put(name, employee);
    }

    public void requestLeave(String employeeName, int days) {
        if (employees.containsKey(employeeName)) {
            Employee employee = employees.get(employeeName);

            if (employee.getLeaveBalance() >= days) {
                employee.deductLeave(days);

                if (!leaveRequests.containsKey(employeeName)) {
                    leaveRequests.put(employeeName, new ArrayList<>());
                }

                leaveRequests.get(employeeName).add("Requested " + days + " days leave");
                System.out.println("Leave request submitted successfully.");
            } else {
                System.out.println("Insufficient leave balance.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void approveLeave(String employeeName) {
        if (leaveRequests.containsKey(employeeName)) {
            System.out.println("Leave request for " + employeeName + ": " + leaveRequests.get(employeeName).get(0) + " approved.");
            leaveRequests.get(employeeName).remove(0);
        } else {
            System.out.println("No leave request found for " + employeeName);
        }
    }

    public void displayLeaveBalance(String employeeName) {
        if (employees.containsKey(employeeName)) {
            System.out.println("Leave balance for " + employeeName + ": " + employees.get(employeeName).getLeaveBalance() + " days");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void main(String[] args) {
        LeaveManagementSystem leaveSystem = new LeaveManagementSystem();
        leaveSystem.addEmployee("John Doe", 20);
        leaveSystem.addEmployee("Jane Smith", 15);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Leave Management System");
            System.out.println("1. Request Leave");
            System.out.println("2. Approve Leave");
            System.out.println("3. Check Leave Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.nextLine();
                    System.out.print("Enter number of days for leave: ");
                    int days = scanner.nextInt();
                    leaveSystem.requestLeave(employeeName, days);
                    break;
                case 2:
                    System.out.print("Enter employee name: ");
                    employeeName = scanner.nextLine();
                    leaveSystem.approveLeave(employeeName);
                    break;
                case 3:
                    System.out.print("Enter employee name: ");
                    employeeName = scanner.nextLine();
                    leaveSystem.displayLeaveBalance(employeeName);
                    break;
                case 4:
                    System.out.println("Exiting Employee Leave Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
