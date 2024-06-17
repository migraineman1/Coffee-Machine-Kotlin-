package machine

class CoffeeMachine () {
    // properties representing initial inventory
    private var currentWater = 400
    private var currentMilk = 540
    private var currentBeans = 120
    private var currentCups = 9
    private var money = 550

    // method to display current supplies
    private fun displayContents() {
        println("The coffee machine has:")
        println("$currentWater ml of water")
        println("$currentMilk ml of milk")
        println("$currentBeans g of coffee beans")
        println("$currentCups disposable cups")
        println("\$$money of money")
    }

    // method to see if enough supplies to make a cup or print supplies missing
    private fun canBuy(water: Int = 0, milk: Int = 0, beans: Int = 0, cups: Int = 1): Boolean {
        var missing = "Sorry, not enough"
        var canMake = true
        if (water > currentWater) {
            missing += " water"
            canMake = false
        }
        if (milk > currentMilk) {
            missing += " milk"
            canMake = false
        }
        if (beans > currentBeans) {
            missing += " beans"
            canMake = false
        }
        if (cups > currentCups) {
            missing += " cups"
            canMake = false
        }
        if (canMake) { println("I have enough resources, making you a coffee!") }
        else { println("$missing!") }
        return canMake
    }

    // function to buy espresso
    private fun buyEspresso() {
        if (canBuy(250, 0, 16, 1)) {
            currentWater -= 250
            currentBeans -= 16
            money += 4
            currentCups -= 1
        }
    }

    // function to buy latte
    private fun buyLatte() {
        if (canBuy(350, 75, 20, 1)) {
            currentWater -= 350
            currentMilk -= 75
            currentBeans -= 20
            money += 7
            currentCups -= 1
        }
    }

    // function to buy espresso
    private fun buyCappuccino() {
        if (canBuy(200, 100, 12, 1)) {
            currentWater -= 200
            currentMilk -= 100
            currentBeans -= 12
            money += 6
            currentCups -= 1
        }
    }

    // function to refill the machine with supplies
    private fun fillMachine(water: Int, milk: Int, beans: Int, cups: Int) {

        currentWater += water
        currentMilk += milk
        currentBeans += beans
        currentCups += cups
    }

    // function to empty money from the machine
    private fun emptyMoney() {
        println("I gave you \$$money")
        money = 0
    }

    // method to receive instructions from the user
    fun instruction (instruction: String) {
        if (instruction == "emptyMoney") {
            emptyMoney()
            return
        }

        if (instruction == "displayContents") {
            displayContents()
            return
        }

        val command = instruction.split(" ")
        if (command[0] == "buy"){
            when (command[1]) {
                "Espresso" -> buyEspresso()
                "Latte" -> buyLatte()
                "Cappuccino" -> buyCappuccino()
            }
        }

        if (command[0] == "fill") {
            fillMachine(command[1].toInt(), command[2].toInt(), command[3].toInt(), command[4].toInt())
        }

    }

}



fun main() {

    val coffeeMachine = CoffeeMachine()

    // function to buy some coffee
    fun buyCoffee() {
        // prompt for type of coffee desired
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        val coffeeType = readln()

        when (coffeeType) {
            "1" -> coffeeMachine.instruction("buy Espresso")
            "2" -> coffeeMachine.instruction("buy Latte")
            "3" -> coffeeMachine.instruction("buy Cappuccino")
            "back" -> return
        }
    }

    // function to refill the machine with supplies
    fun fillMachine() {

        println("Write how many ml of water you want to add:")
        val water = readln().toInt()

        println("Write how many ml of milk you want to add:")
        val milk = readln().toInt()

        println("Write how many grams of coffee beans you want to add:")
        val beans = readln().toInt()

        println("Write how many disposable cups you want to add:")
        val cups = readln().toInt()

        coffeeMachine.instruction("fill $water $milk $beans $cups")
    }

    // main loop of program
    do {

        // prompt for initial function input
        println("Write action (buy, fill, take, remaining, exit):")

        // read in the initial option
        val option = readln()
        when (option) {
            "buy"       -> buyCoffee()
            "fill"      -> fillMachine()
            "take"      -> coffeeMachine.instruction("emptyMoney")
            "remaining" -> coffeeMachine.instruction("displayContents")
        }
    } while (option != "exit")

}


