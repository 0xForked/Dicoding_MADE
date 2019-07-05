
/**
 * Created by A. A. Sumitro on 24/01/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object ApplicationId {
    val id =  "id.aasumitro.made"
}

object Releases {
    val version_code = 1
    val version_name = "1.0-SNAPSHOT"
}

object SDK {
    var compile_version = 29
    var target_version = 29
    var minimum_version = 21
}

object Version {

    internal var gradle_version = "3.4.1"
    internal var kotlin_version = "1.3.41"

    internal var appcompat_version = "1.0.2"
    internal var material_design_version = "1.1.0-alpha07"
    internal var card_view_version = "1.0.0"
    internal var recycler_view_version = "1.0.0"
    internal var ktx_core_version = "1.0.2"
    internal var constraint_layout_version = "1.1.3"

    internal var lifecycle_version = "2.2.0-alpha01"
    internal var room_version = "2.1.0"

    internal var rxjava_version = "2.2.10"
    internal var rxandroid_version = "2.1.1"

    internal var glide_version = "4.8.0"

    internal var retrofit_version = "2.6.0"
    internal var okhttp_version = "3.14.2"

    internal var junit_version = "4.12"
    internal var test_runner_version = "1.1.2-alpha01"
    internal var espresso_core_version = "3.2.0"

    internal var progress_hud = "1.2.0"
}

object GradleDeps {
    var gradle_plugin = "com.android.tools.build:gradle:${Version.gradle_version}"
}

object KotlinDeps {
    var kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin_version}"
    var kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin_version}"
}

object AndroidXDeps {
    var appcompat = "androidx.appcompat:appcompat:${Version.appcompat_version}"
    var card_view = "androidx.cardview:cardview:${Version.card_view_version}"
    var recycler_view = "androidx.recyclerview:recyclerview:${Version.recycler_view_version}"
    var ktx = "androidx.core:core-ktx:${Version.ktx_core_version}"
    var constraint_layout = "androidx.constraintlayout:constraintlayout:${Version.constraint_layout_version}"
    var test_runner = "androidx.test:runner:${Version.test_runner_version}"
    var espresso_core = "androidx.test.espresso:espresso-core:${Version.espresso_core_version}"
}
object GoogleDeps {
    var material = "com.google.android.material:material:${Version.material_design_version}"
}

object ArchCompDeps {
    var lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle_version}"
    var lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle_version}"
    var room_runtime = "androidx.room:room-runtime:${Version.room_version}"
    var room_compiler = "androidx.room:room-compiler:${Version.room_version}"
    var room_rxjava = "androidx.room:room-rxjava2:${Version.room_version}"
}

object NetworkDeps {
    var retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit_version}"
    var retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit_version}"
    var retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Version.retrofit_version}"
    var okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp_version}"

    var glide = "com.github.bumptech.glide:glide:${Version.glide_version}"
    var glide_compiler = "com.github.bumptech.glide:compiler:${Version.glide_version}"
}

object RxDeps {
    var main = "io.reactivex.rxjava2:rxjava:${Version.rxjava_version}"
    var android = "io.reactivex.rxjava2:rxandroid:${Version.rxandroid_version}"
}

object TestDeps {
    var junit = "junit:junit:${Version.junit_version}"
}

object OtherDeps {
    var progress_hud = "com.kaopiz:kprogresshud:${Version.progress_hud}"
}
