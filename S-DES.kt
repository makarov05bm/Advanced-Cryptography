fun hexToBinary(hexNumber: String): String {
    var len = hexNumber.length
    var i = 0

    var binary = ""

    while (i < len) {
        when (hexNumber[i]) {
            '0' -> binary += "0000"
            '1' -> binary += "0001"
            '2' -> binary += "0010"
            '3' -> binary += "0011"
            '4' -> binary += "0100"
            '5' -> binary += "0101"
            '6' -> binary += "0110"
            '7' -> binary += "0111"
            '8' -> binary += "1000"
            '9' -> binary += "1001"
            'A', 'a' -> binary += "1010"
            'B', 'b' -> binary += "1011"
            'C', 'c' -> binary += "1100"
            'D', 'd' -> binary += "1101"
            'E', 'e' -> binary += "1110"
            'F', 'f' -> binary += "1111"
        }

        i++
    }

    return binary
}

fun dividePlainText(message: String): MutableList<String>  {
    val blocs = mutableListOf<String>()

    var start = 0
    var end = 8

    var tmp = StringBuilder("")

    while (end <= message.length+1) {
        blocs.add(message.substring(start, end))

        start += 8
        end += 8
    }

    return blocs
}

fun p10(key: String): StringBuilder {
    var tmpKey = StringBuilder("0000000000")

    tmpKey.setCharAt(0, key[2])
    tmpKey.setCharAt(1, key[4])
    tmpKey.setCharAt(2, key[1])
    tmpKey.setCharAt(3, key[6])
    tmpKey.setCharAt(4, key[3])
    tmpKey.setCharAt(5, key[9])
    tmpKey.setCharAt(6, key[0])
    tmpKey.setCharAt(7, key[8])
    tmpKey.setCharAt(8, key[7])
    tmpKey.setCharAt(9, key[5])

    return tmpKey
}

fun p8(key: StringBuilder): StringBuilder {
    var tmpKey = StringBuilder("00000000")

    tmpKey.setCharAt(0, key[5])
    tmpKey.setCharAt(1, key[2])
    tmpKey.setCharAt(2, key[6])
    tmpKey.setCharAt(3, key[3])
    tmpKey.setCharAt(4, key[7])
    tmpKey.setCharAt(5, key[4])
    tmpKey.setCharAt(6, key[9])
    tmpKey.setCharAt(7, key[8])

    return tmpKey
}

fun p4(key: String): StringBuilder {
    var tmpKey = StringBuilder("0000")

    tmpKey.setCharAt(0, key[1])
    tmpKey.setCharAt(1, key[3])
    tmpKey.setCharAt(2, key[2])
    tmpKey.setCharAt(3, key[0])

    return tmpKey
}

fun oneLeftShift(key: StringBuilder): StringBuilder {
    var tmpKey = StringBuilder("00000")

    tmpKey.setCharAt(0, key[1])
    tmpKey.setCharAt(1, key[2])
    tmpKey.setCharAt(2, key[3])
    tmpKey.setCharAt(3, key[4])
    tmpKey.setCharAt(4, key[0])

    return tmpKey
}

fun twoLeftShift(key: StringBuilder): StringBuilder {
    var tmpKey = StringBuilder("00000")

    tmpKey.setCharAt(0, key[2])
    tmpKey.setCharAt(1, key[3])
    tmpKey.setCharAt(2, key[4])
    tmpKey.setCharAt(3, key[0])
    tmpKey.setCharAt(4, key[1])

    return tmpKey
}

fun divideMainKey(key: StringBuilder): MutableList<StringBuilder> {
    val halves = mutableListOf<StringBuilder>()

    var l = StringBuilder(key.substring(0, 5))
    var r = StringBuilder(key.substring(5, 10))

    halves.add(l)
    halves.add(r)

    return halves
}
fun divideBloc(key: String): MutableList<StringBuilder> {
    val halves = mutableListOf<StringBuilder>()

    var l = StringBuilder(key.substring(0, 4))
    var r = StringBuilder(key.substring(4, 8))

    halves.add(l)
    halves.add(r)

    return halves
}

fun ip8(key: String): StringBuilder {
    var tmpKey = StringBuilder("00000000")

    tmpKey.setCharAt(0, key[1])
    tmpKey.setCharAt(1, key[5])
    tmpKey.setCharAt(2, key[2])
    tmpKey.setCharAt(3, key[0])
    tmpKey.setCharAt(4, key[3])
    tmpKey.setCharAt(5, key[7])
    tmpKey.setCharAt(6, key[4])
    tmpKey.setCharAt(7, key[6])

    return tmpKey
}

fun expansion(key: StringBuilder): StringBuilder {
    var tmpKey = StringBuilder("00000000")

    tmpKey.setCharAt(0, key[3])
    tmpKey.setCharAt(1, key[0])
    tmpKey.setCharAt(2, key[1])
    tmpKey.setCharAt(3, key[2])
    tmpKey.setCharAt(4, key[1])
    tmpKey.setCharAt(5, key[2])
    tmpKey.setCharAt(6, key[3])
    tmpKey.setCharAt(7, key[0])

    return tmpKey
}

fun xor(str1: StringBuilder, str2: StringBuilder): StringBuilder {
    val len = str1.length
    var i = 0

    var xor = StringBuilder("")

    while (i < len) {
        if ((str1[i] == '1' && str2[i] == '0') || (str2[i] == '1' && str1[i] == '0')) {
            xor.append("1")
        } else {
            xor.append("0")
        }

        i++
    }

    return xor
}

fun mapSbox(key: String): MutableList<Int> {
    val pos = mutableListOf<Int>()

    val row: String = key[0].toString() + key[3].toString()
    val col: String = key[1].toString() + key[2].toString()

    when (row) {
        "00" -> pos.add(0)
        "01" -> pos.add(1)
        "10" -> pos.add(2)
        "11" -> pos.add(3)
    }

    when (col) {
        "00" -> pos.add(0)
        "01" -> pos.add(1)
        "10" -> pos.add(2)
        "11" -> pos.add(3)
    }

    return pos
}

fun intToBin(num: Int): String {
    var res = ""

    when (num) {
        0 -> res = "00"
        1 -> res = "01"
        2 -> res = "10"
        3 -> res = "11"
    }

    return res
}

fun ipInverse(key: String): StringBuilder {
    var tmpKey = StringBuilder("00000000")

    tmpKey.setCharAt(0, key[3])
    tmpKey.setCharAt(1, key[0])
    tmpKey.setCharAt(2, key[2])
    tmpKey.setCharAt(3, key[4])
    tmpKey.setCharAt(4, key[6])
    tmpKey.setCharAt(5, key[1])
    tmpKey.setCharAt(6, key[7])
    tmpKey.setCharAt(7, key[5])

    return tmpKey
}

fun binaryToHex(message: String): String {
    val blocs = mutableListOf<String>()

    var start = 0
    var end = 4

    while (end <= message.length+1) {
        blocs.add(message.substring(start, end))

        start += 4
        end += 4
    }

    var hexValue = ""

    for (item in blocs) {
        when (item) {
            "0000" -> hexValue += "0"
            "0001" -> hexValue += "1"
            "0010" -> hexValue += "2"
            "0011" -> hexValue += "3"
            "0100" -> hexValue += "4"
            "0101" -> hexValue += "5"
            "0110" -> hexValue += "6"
            "0111" -> hexValue += "7"
            "1000" -> hexValue += "8"
            "1001" -> hexValue += "9"
            "1010" -> hexValue += "A"
            "1011" -> hexValue += "B"
            "1100" -> hexValue += "C"
            "1101" -> hexValue += "D"
            "1110" -> hexValue += "E"
            "1111" -> hexValue += "F"
        }
    }

    return hexValue
}

fun des(plainTextBinary: StringBuilder, key: StringBuilder, s0: Array<Array<Int>>, s1: Array<Array<Int>>, type: String):
        MutableList<String> {
    val plainBlocs = dividePlainText(plainTextBinary.toString())
    val ip8Blocs = mutableListOf<String>()

    // apply ip8 on each 8-bit bloc
    for (plainBloc in plainBlocs) {
        ip8Blocs.add(ip8(plainBloc).toString())
    }

    // expansion of right halves
    val rightHalvesExpanded = mutableListOf<StringBuilder>()

    for (bloc in ip8Blocs) {
        val dividedBloc = divideBloc(bloc)

        // expansion
        if (type == "r1") {
            rightHalvesExpanded.add(expansion(dividedBloc[1]))
        } else {
            rightHalvesExpanded.add(expansion(divideBloc(plainTextBinary.toString())[1]))
        }
    }

    // XOR expanded right halves with K1
    val xorK1 = mutableListOf<StringBuilder>()

    for (item in rightHalvesExpanded) {
        xorK1.add(xor(item, key))
    }

    // XORed halves -> S-Boxes
    var i = 0
    val round1 = mutableListOf<String>()
    for (item in xorK1) {
        val dividedBloc = divideBloc(item.toString())

        val l = dividedBloc[0]
        val r = dividedBloc[1]

        val s0Half = mapSbox(l.toString())
        val s1Half = mapSbox(r.toString())

        val sboxed = intToBin(s0[s0Half[0]][s0Half[1]]) + intToBin(s1[s1Half[0]][s1Half[1]])

        // S-boxed -> p4
        val p4 = p4(sboxed)
        var xor = ""

        xor = if (type == "r1") {
            xor(p4, divideBloc(ip8Blocs[i])[0]).toString()
        } else {
            xor(p4, divideBloc(plainTextBinary.toString())[0]).toString()
        }

        // concatenation of xor and ip8 right nibble
        var sw = ""

        sw = if (type == "r1") {
            divideBloc(ip8Blocs[i])[1].toString() + xor
        } else {
            xor + divideBloc(plainTextBinary.toString())[1]
        }

        round1.add(sw)
    }

    return round1
}

fun main(args: Array<String>) {
    val s0 = arrayOf(
        arrayOf(1, 0, 3, 2),
        arrayOf(3, 2, 1, 0),
        arrayOf(0, 2, 1, 3),
        arrayOf(3, 1, 3, 2)
    )

    val s1 = arrayOf(
        arrayOf(0, 1, 2, 3),
        arrayOf(2, 0, 1, 3),
        arrayOf(3, 0, 1, 0),
        arrayOf(2, 1, 0, 3)
    )

    println("Plaintext message (Hex) > ")
    val plainText = readlnOrNull()
    val plainTextBinary = StringBuilder(hexToBinary(plainText!!))

    if (plainTextBinary.length % 8 != 0) {
        var i = 0

        while (plainTextBinary.length % 8 != 0) {
            plainTextBinary.append("0000")

            i++
        }
    }

    println("Encryption key (Binary) > ")
    // 10-bit original key
    val encKeyBinary = readlnOrNull()

    // 10-bit -> p10
    val p10 = p10(encKeyBinary!!)

    // divide p10
    val mainKeyHalves = divideMainKey(p10)
    val l = mainKeyHalves[0]
    val r = mainKeyHalves[1]

    // shift halves by 1
    val leftShifted = oneLeftShift(l)
    val rightShifted = oneLeftShift(r)

    val p10Shifted = leftShifted.append(rightShifted)

    // shifted halves -> p8
    val k1 = p8(p10Shifted)
    println("K1: $k1")

    // shift already shifted halves by 2
    val leftShifted2 = twoLeftShift(leftShifted)
    val rightShifted2 = twoLeftShift(rightShifted)

    val p10Shifted2 = leftShifted2.append(rightShifted2)

    val k2 = p8(p10Shifted2)
    println("K2: $k2")


    // Ciphering: 1
    val round1 = des(plainTextBinary, k1, s0, s1, "r1")

    // Ciphering: 2
    val round2 = mutableListOf<MutableList<String>>()
    for (item in round1) {
        round2.add(des(StringBuilder(item), k2, s0, s1, "r2"))
    }

    val ipInverse = mutableListOf<String>()

    for (item in round2) {
        ipInverse.add(ipInverse(item[0]).toString())
    }

    var cipherTextBinary = ""

    for (cipherText in ipInverse) {
        cipherTextBinary += cipherText
    }

    println("Cipher text (Binary): $cipherTextBinary")
    println("Cipher text (Hexadecimal): ${binaryToHex(cipherTextBinary)}")
}
