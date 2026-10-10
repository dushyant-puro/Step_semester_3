package week_08.oop.class_problems;
import java.time.*;
public class EmployeeLeaveRequestManagement {
 enum Status{PENDING,APPROVED,REJECTED}
 static class LeaveRequest{String employee;LocalDate from,to;Status status=Status.PENDING;LeaveRequest(String e,LocalDate f,LocalDate t){if(t.isBefore(f))throw new IllegalArgumentException("End before start");employee=e;from=f;to=t;}void approve(){if(status!=Status.PENDING)throw new IllegalStateException("Already reviewed");status=Status.APPROVED;}void reject(){if(status!=Status.PENDING)throw new IllegalStateException("Already reviewed");status=Status.REJECTED;}}
 public static void main(String[]x){LeaveRequest r=new LeaveRequest("John Doe",LocalDate.parse("2024-10-10"),LocalDate.parse("2024-10-12"));System.out.println("Leave request submitted for "+r.employee+". Status: "+r.status);}
}