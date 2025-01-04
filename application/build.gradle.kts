val javaFakerVersion: String by ext
val mockkVersion: String by ext

dependencies {
    implementation(project(":domain"))

    testImplementation("com.github.javafaker:javafaker:$javaFakerVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation(kotlin("test"))
}
