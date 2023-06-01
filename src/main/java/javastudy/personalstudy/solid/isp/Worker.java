
package javastudy.personalstudy.solid.isp;

// 잘못된 예제
interface AWorker {
    void work();
    void eat();
    void sleep();
}

class AEngineer implements AWorker {
    public void work() {
        // 엔지니어의 일하는 로직
    }

    public void eat() {
        // 엔지니어의 식사하는 로직
    }

    public void sleep() {
        // 엔지니어의 잠자는 로직
    }
}

// 올바른 예제
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

interface Sleepable {
    void sleep();
}

class Engineer implements Workable {
    public void work() {
        // 엔지니어의 일하는 로직
    }
}

class OfficeWorker implements Workable, Eatable, Sleepable {
    public void work() {
        // 사무직의 일하는 로직
    }

    public void eat() {
        // 사무직의 식사하는 로직
    }

    public void sleep() {
        // 사무직의 잠자는 로직
    }
}
