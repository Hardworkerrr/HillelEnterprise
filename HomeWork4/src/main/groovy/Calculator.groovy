class Calculator {
    private def user

    public Calculator(User user) {
        this.user = user
    }

    static void startCalculation(String expression) {
        if (expression.matches('[-,+,*,/]')
                || expression.contains('div')
                || expression.contains('exp')) {
            if (expression.matches('exp')) {
                Scanner scanner = new Scanner(System.in)
                println "Type number: "
                BigDecimal firstNumber = new BigDecimal(scanner.next())
                println "Type exponent: "
                BigDecimal secondNumber = new BigDecimal(scanner.next())
                choose(expression, firstNumber, secondNumber)
                return
            }
            Scanner scanner = new Scanner(System.in)
            println "Type 1 number: "
            BigDecimal firstNumber = new BigDecimal(scanner.next())
            println "Type 2 number: "
            BigDecimal secondNumber = new BigDecimal(scanner.next())
            choose(expression, firstNumber, secondNumber)
        } else {
            Scanner scanner = new Scanner(System.in)
            println "Type 1 number: "
            BigDecimal firstNumber = new BigDecimal(scanner.next())
            choose(expression, firstNumber)
        }
    }

    static void choose(String expression, BigDecimal firstNumber, BigDecimal secondNumber) {
        switch (expression) {
            case '+': println 'Result is: ' + sum(firstNumber, secondNumber)
                break
            case '-': println 'Result is: ' + deduction(firstNumber, secondNumber)
                break
            case '*': println 'Result is: ' + multiplication(firstNumber, secondNumber)
                break
            case '/': println 'Result is: ' + division(firstNumber, secondNumber)
                break
            case 'div': println 'Result is: ' + intDivision(firstNumber, secondNumber)
                break
            case 'exp': println 'Result is: ' + exp(firstNumber, secondNumber)
                break
            default: println 'Wrong operation typed. Try again.'
        }
    }

    static void choose(String expression, BigDecimal firstNumber) {
        switch (expression) {
            case 'sin': println 'Result is: ' + sin(firstNumber)
                break
            case 'cos': println 'Result is: ' + cos(firstNumber)
                break
            case 'tan': println 'Result is: ' + tan(firstNumber)
                break
            case 'log': println 'Result is: ' + log(firstNumber)
                break
            case 'ln': println 'Result is: ' + ln(firstNumber)
                break
            case 'sqrt': println 'Result is: ' + sqrt(firstNumber)
                break
            case 'fact': println 'Result is: ' + fact(firstNumber)
                break
            default: println 'Wrong operation typed. Try again.'
        }
    }

    static BigDecimal sum(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber + secondNumber
    }

    static BigDecimal deduction(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber - secondNumber
    }

    static BigDecimal multiplication(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber * secondNumber
    }

    static BigDecimal division(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber / secondNumber
    }

    static BigDecimal intDivision(BigDecimal firstNumber, BigDecimal secondNumber) {
        return (Integer) (firstNumber / secondNumber)
    }

    static BigDecimal sin(BigDecimal firstNumber) {
        return Math.sin(firstNumber)
    }

    static BigDecimal cos(BigDecimal firstNumber) {
        return Math.cos(firstNumber)
    }

    static BigDecimal tan(BigDecimal firstNumber) {
        return Math.tan(firstNumber)
    }

    static BigDecimal log(BigDecimal firstNumber) {
        return Math.log10(firstNumber)
    }

    static BigDecimal ln(BigDecimal firstNumber) {
        return Math.log(firstNumber)
    }

    static BigDecimal exp(BigDecimal firstNumber, BigDecimal secondNumber) {
        return Math.pow(firstNumber, secondNumber)
    }

    static BigDecimal sqrt(BigDecimal firstNumber) {
        return Math.sqrt(firstNumber)
    }

    static BigDecimal fact(BigDecimal firstNumber) {
        int result = 1;
        for (int i = 1; i <= firstNumber; i++) {
            result = result * i;
        }
        return result;
    }
}
