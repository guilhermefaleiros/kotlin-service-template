val javaFakerVersion: String by ext

dependencies {
    testImplementation("com.github.javafaker:javafaker:$javaFakerVersion")
    testImplementation(kotlin("test"))
}
