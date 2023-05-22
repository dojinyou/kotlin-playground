import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.Charset

fun main() {
    val email = "evan@vcnc.co.kr"
    val userName = "&1231"
    val rideId = "RQCFDSGTVRBA7UDW"
    val rideIdHiddenField = rideId?.let { "&ride_id=$it" } ?: ""
    val hiddenField = "email=$email&name=$userName$rideIdHiddenField"

    val uriString = UriComponentsBuilder.fromUriString("https://survey.typeform.com")
        .path("/to/HAk244fE")
        .fragment(hiddenField)
        .toUriString()

    println(uriString)

    println(URLEncoder.encode(userName, Charset.defaultCharset()))
}
