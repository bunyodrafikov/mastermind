package bunyodrafikov.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val positions = getGuessedInRightPositions(secret, guess)
    val letters = getWrongPosition(secret, guess)
    return Evaluation(positions, letters)
}

private fun getGuessedInRightPositions(secret: String, guess: String): Int {
    var positions = 0
    for(i in secret.indices) { //How many letter guessed
        if(secret[i] == guess[i]) { //Guessed right and in the right position
            positions ++
        }
    }
    return positions
}

fun getWrongPosition(secret: String, guess: String): Int {
    var letters = 0
    var newSecret = ""
    var newGuess = ""

    for (i in secret.indices){
        if(secret[i] != guess[i]){
            newSecret += secret[i]
            newGuess += guess[i]
        }
    }

    val evaluatedChars = mutableListOf<Char>()
    if(newSecret.isNotEmpty()) {
        for (element in guess) {
            val letter = element
            if (!evaluatedChars.contains(letter)) {
                val howManyInSecret = countHowMany(newSecret, letter)
                val howManyInGuess = countHowMany(newGuess, letter)
                letters += if (howManyInSecret == howManyInGuess || howManyInSecret > howManyInGuess) howManyInGuess
                else howManyInSecret

                evaluatedChars.add(letter)
            }
        }
    }

    return letters
}

fun countHowMany(letters: String, letter: Char): Int {
    var howMany = 0
    for (element in letters){
        if (element ==letter){
            howMany++
        }
    }
    return howMany
}