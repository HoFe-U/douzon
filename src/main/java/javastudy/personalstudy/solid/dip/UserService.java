package javastudy.personalstudy.solid.dip;

// 잘못된 예제
class AUserService {
    private ADatabase database;

    public AUserService() {
        this.database = new ADatabase();
    }

    // UserService에서 직접 Database를 사용하는 로직
}

class ADatabase {
    // 데이터베이스 관련 로직
}

// 올바른 예제
interface Database {
    // 데이터베이스 관련 메서드
}

class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    // Database 인터페이스에 정의된 메서드를 사용하는 로직
}

class MySQLDatabase implements Database {
    // MySQL 데이터베이스에 대한 구현
}

class PostgreSQLDatabase implements Database {
    // PostgreSQL 데이터베이스에 대한 구현
}
