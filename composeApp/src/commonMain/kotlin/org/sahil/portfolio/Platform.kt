package org.sahil.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform