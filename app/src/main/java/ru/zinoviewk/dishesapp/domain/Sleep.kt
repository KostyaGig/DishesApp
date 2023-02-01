package ru.zinoviewk.dishesapp.domain

import kotlinx.coroutines.delay
import kotlin.random.Random

private const val FROM = 100
private const val UNTIL = 300

suspend fun randomSleep() {
    val delay = (Random.nextInt(FROM, UNTIL) * 10).toLong()
    delay(delay)
}