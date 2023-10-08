static void main(String[] args) {
    User user = new User("John", "Wick")
    user.takeCalculator()
    Calculator calculator = new Calculator(user)
    calculator.startCalculation(User.writeOperation())
}