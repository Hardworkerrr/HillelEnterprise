class User {
    def private name
    def private secondName
    def private isReadyForCalculation = false

    User(name, secondName) {
        this.name = name
        this.secondName = secondName
    }

    String getName() {
        return name
    }

    String getSecondName() {
        return secondName
    }

    void setName(def name) {
        this.name = name
    }

    Boolean getIsReadyForCalculation() {
        return isReadyForCalculation
    }

    void setSecondName(def secondName) {
        this.secondName = secondName
    }

    void takeCalculator() {
        println 'Now you can start calculation'
        isReadyForCalculation = true;
    }

    static String writeOperation() {
        println 'Hello, which operation you want to perform ? + , -, /, *, div, sin, cos, tan, log, ln, ^, sqrt or fact. Type one of them below.'
        Scanner scanner = new Scanner(System.in);
        println 'Write operation :'
        def input = scanner.nextLine()
        return input
    }
}
