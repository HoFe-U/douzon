package javastudy.personalstudy.solid.srp;

// 잘못된 예제
class AStudent {
    public void calculateSalary() {
        // 급여 계산 로직
    }

    public void saveToDatabase() {
        // 데이터베이스에 저장하는 로직
    }

    public void sendEmailNotification() {
        // 이메일 알림을 보내는 로직
    }
}

//  <--> 위의 코드가 왜 잘못된걸까???

// 올바른 예제
class Student {
    public void calculateSalary() {
        // 급여 계산 로직
    }
}

class StudentRepository {
    public void saveToDatabase() {
        // 데이터베이스에 저장하는 로직
    }
}

class EmailService {
    public void sendEmailNotification() {
        // 이메일 알림을 보내는 로직
    }
}