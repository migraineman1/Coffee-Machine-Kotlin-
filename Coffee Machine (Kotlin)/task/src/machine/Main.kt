package machine

fun main() {
    // global variables
    var currentWater = 400
    var currentMilk = 540
    var currentBeans = 120
    var currentCups = 9
    var money = 550


    // function to display current supplies
    fun displayContents() {
        println("The coffee machine has:")
        println("$currentWater ml of water")
        println("$currentMilk ml of milk")
        println("$currentBeans g of coffee beans")
        println("$currentCups disposable cups")
        println("\$$money of money")
    }
    // function to see if enough supplies to make a cup or print supplies missing
    fun canBuy(water: Int = 0, milk: Int = 0, beans: Int = 0, cups: Int = 1): Boolean {
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
    fun buyEspresso() {
        if (canBuy(250, 0, 16, 1)) {
            currentWater -= 250
            currentBeans -= 16
            money += 4
            currentCups -= 1
        }
    }

    // function to buy latte
    fun buyLatte() {
        if (canBuy(350, 75, 20, 1)) {
            currentWater -= 350
            currentMilk -= 75
            currentBeans -= 20
            money += 7
            currentCups -= 1
        }
    }

    // function to buy espresso
    fun buyCappuccino() {
        if (canBuy(200, 100, 12, 1)) {
            currentWater -= 200
            currentMilk -= 100
            currentBeans -= 12
            money += 6
            currentCups -= 1
        }
    }



    // function to buy some coffee
    fun buyCoffee() {
        // prompt for type of coffee desired
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")

        // read in purchase option
        val options = readln()
        if (options == "back") return
        val coffeeType = options.toInt()
        when (coffeeType) {
            1 -> buyEspresso()
            2 -> buyLatte()
            3 -> buyCappuccino()
        }

    }

    // function to refill the machine with supplies
    fun fillMachine() {

        println("Write how many ml of water you want to add:")
        var input = readln().toInt()
        currentWater += input

        println("Write how many ml of milk you want to add:")
        input = readln().toInt()
        currentMilk += input

        println("Write how many grams of coffee beans you want to add:")
        input = readln().toInt()
        currentBeans += input

        println("Write how many disposable cups you want to add:")
        input = readln().toInt()
        currentCups += input
    }

    // function to empty money from the machine
    fun emptyMoney() {
        println("I gave you \$$money")
        money = 0
    }

    // list initial supplies
    currentWater = 400
    currentMilk = 540
    currentBeans = 120
    currentCups = 9
    money = 550

    do {


        // prompt for initial function input
        println("Write action (buy, fill, take, remaining, exit):")

        // read in the initial option
        val option = readln()
        when (option) {
            "buy"       -> buyCoffee()
            "fill"      -> fillMachine()
            "take"      -> emptyMoney()
            "remaining" -> displayContents()
        }
    } while (option != "exit")

}