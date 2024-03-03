fun main() {
    // 16-bit plaintext message
    println("Plaintext (Binary) > ")
    val plainText = readlnOrNull()

    // Key generation
    val keys = keyGeneration()

    // Encryption
    // Add round 0 key: plaintext XOR k0
    val addKey0 = xor(StringBuilder(plainText), keys[0])
    val subedNibbles = subNib(addKey0)
    val shiftedRow = shiftRow(subedNibbles)
    val mixedCols = mixColumns(shiftedRow)
    // add round 1 key
    val roundOneCiphertext = xor(mixedCols, keys[1])
    // final round
    val nibSub = subNib(roundOneCiphertext)
    val roundTwoShiftedRow = shiftRow(nibSub)
    // add round 2 key: roundTwoShiftedRow XOR k2
    val ciphertext = xor(roundTwoShiftedRow, keys[2])

    println("Ciphertext (Binary) >> $ciphertext")
}

fun keyGeneration(): MutableList<StringBuilder> {
    val keys = mutableListOf<StringBuilder>()

    println("Encryption Key (Binary) > ")
    val k0 = readlnOrNull()

    // divide key into w0, w1, w2 and w3
    val keyNibbles = divideKeyToBytes(k0!!)

    val w0 = StringBuilder(keyNibbles[0])
    val w1 = StringBuilder(keyNibbles[1])
    val w2 = xor(xor(StringBuilder(w0), StringBuilder("10000000")), subNib(rotNib(StringBuilder(w1))))
    val w3 = xor(w2, w1)
    val w4 = xor(xor(StringBuilder(w2), StringBuilder("00110000")), subNib(rotNib(StringBuilder(w3))))
    val w5 = xor(w4, w3)

    val k1 = w2.append(w3)
    val k2 = w4.append(w5)

    keys.add(StringBuilder(k0))
    keys.add(k1)
    keys.add(k2)

    println("k0 >> $k0")
    println("k1 >> $k1")
    println("k2 >> $k2")

    return keys
}

fun divideKeyToBytes(key: String): MutableList<String> {
    val keyBytes = mutableListOf<String>()
    var start = 0
    var end = 8

    while (end <= key.length) {
        keyBytes.add(key.substring(start ,end))

        start += 8
        end += 8
    }

    return keyBytes
}

fun divideKeyToNibbles(key: String): MutableList<String> {
    val keyNibbles = mutableListOf<String>()
    var start = 0
    var end = 4

    while (end <= key.length) {
        keyNibbles.add(key.substring(start ,end))

        start += 4
        end += 4
    }

    return keyNibbles
}

fun xor(str1: StringBuilder, str2: StringBuilder): StringBuilder {
    val len = str1.length
    var i = 0

    val xor = StringBuilder("")

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

// swap the byte's nibbles
fun rotNib(byte: StringBuilder): StringBuilder {
    val newByte = StringBuilder("")

    newByte.append(byte.substring(4, 8))
    newByte.append(byte.substring(0, 4))

    return newByte
}

fun subNib(byte: StringBuilder): StringBuilder {
    val byteNibbles = divideKeyToNibbles(byte.toString())
    val subedNibbles = StringBuilder("")

    for (nibble in byteNibbles) {
        when (nibble) {
            "0000" -> subedNibbles.append("1001")
            "0001" -> subedNibbles.append("0100")
            "0010" -> subedNibbles.append("1010")
            "0011" -> subedNibbles.append("1011")
            "0100" -> subedNibbles.append("1101")
            "0101" -> subedNibbles.append("0001")
            "0110" -> subedNibbles.append("1000")
            "0111" -> subedNibbles.append("0101")
            "1000" -> subedNibbles.append("0110")
            "1001" -> subedNibbles.append("0010")
            "1010" -> subedNibbles.append("0000")
            "1011" -> subedNibbles.append("0011")
            "1100" -> subedNibbles.append("1100")
            "1101" -> subedNibbles.append("1110")
            "1110" -> subedNibbles.append("1111")
            "1111" -> subedNibbles.append("0111")
        }
    }

    return subedNibbles
}

fun shiftRow(key: StringBuilder): StringBuilder {
    val keyNibbles = divideKeyToNibbles(key.toString())
    val shiftedRow = StringBuilder("")

    val tmp = keyNibbles[1]

    keyNibbles[1] = keyNibbles[3]
    keyNibbles[3] = tmp

    for (item in keyNibbles) {
        shiftedRow.append(item)
    }

    return shiftedRow
}

fun mixColumns(key: StringBuilder): StringBuilder {
    val keyNibbles = divideKeyToNibbles(key.toString())

    val s00New = xor(StringBuilder(keyNibbles[0]), binaryMul(4, keyNibbles[2]))
    val s10New = xor(binaryMul(4, keyNibbles[0]), StringBuilder(keyNibbles[2]))
    val s01New = xor(StringBuilder(keyNibbles[1]), binaryMul(4, keyNibbles[3]))
    val s11New = xor(binaryMul(4, keyNibbles[1]), StringBuilder(keyNibbles[3]))

    return s00New.append(s10New).append(s01New).append(s11New)
}

fun binaryMul(multiplier: Int, str2: String): StringBuilder {
    var mulResult: Int
    var nibble = 0b0000

    when (str2) {
        "0000" -> nibble = 0b0000
        "0001" -> nibble = 0b0001
        "0010" -> nibble = 0b0010
        "0011" -> nibble = 0b0011
        "0100" -> nibble = 0b0100
        "0101" -> nibble = 0b0101
        "0110" -> nibble = 0b0110
        "0111" -> nibble = 0b0111
        "1000" -> nibble = 0b1000
        "1001" -> nibble = 0b1001
        "1010" -> nibble = 0b1010
        "1011" -> nibble = 0b1011
        "1100" -> nibble = 0b1100
        "1101" -> nibble = 0b1101
        "1110" -> nibble = 0b1110
        "1111" -> nibble = 0b1111
    }

    mulResult = multiplier * nibble

    val resultBinStr = mulResult.toString(2)
    var finalResultBinStr = StringBuilder(resultBinStr)

    if (resultBinStr.length > 4) {
        mulResult -= 0b1111

        if (mulResult.toString(2).length > 4) {
            finalResultBinStr = StringBuilder("")
            finalResultBinStr.append(mulResult.toString(2)[mulResult.toString(2).length-1])
            finalResultBinStr.append(mulResult.toString(2).substring(0, 3))
        }
    }

    return finalResultBinStr
}
