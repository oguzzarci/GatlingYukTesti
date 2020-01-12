package services

import io.gatling.core.Predef._
import io.gatling.http.Predef._

package object Login {

  val LogoutToken: String = """${LogoutToken}"""


  private val headerLogin = Map(

    //GET https://github.com/login HTTP/1.1
    "Host" -> "github.com",
    "Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Referer" -> "https://github.com/",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"

  )

  private val headerSession = Map (
    //POST https://github.com/session HTTP/1.1
    "Host" -> "github.com",
    "Connection" -> "keep-alive",
    "Content-Length" -> "206",
    "Cache-Control" -> "max-age=0",
    "Origin" -> "https://github.com",
    "Upgrade-Insecure-Requests" -> "1",
    "Content-Type" -> "application/x-www-form-urlencoded",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Referer" -> "https://github.com/login",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"

  )

  private val headerHome = Map (
    //GET https://github.com/ HTTP/1.1
    "Host" -> "github.com",
    "Connection" -> "keep-alive",
    "Cache-Control" -> "max-age=0",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Referer" -> "https://github.com/login",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"

  )

  private val headerOguzzarci = Map (
    //GET https://github.com/oguzzarci HTTP/1.1
    "Host" -> "github.com",
    "Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
    "Referer" -> "https://github.com/",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"

  )


  def run = {

    exec(http("Login")
      .get("/login")
      .headers(headerLogin)
      .check(status is 200)
      .check(css("input[name=authenticity_token]", "value").saveAs("authenticity_token"))
    )

      .exec(http("Session")
        .post("/session")
        .headers(headerSession)
        .formParam("commit","Sign in")
        .formParam("utf8","&#x2713;")
        .formParam("authenticity_token","""${authenticity_token}""")
        .formParam("login","oguzzarci")
        .formParam("password","şifreniz")
        .formParam("webauthn-support","supported")
        .check(status is 302)
      )

      .exec(http("LoginHomePage")
        .get("/")
        .headers(headerHome)
        .check(status is 200)
      )

      .exec(http("OguzZarci")
        .get("/oguzzarci")
        .headers(headerOguzzarci)
        .check(status is 200)
        .check(css("input[name=authenticity_token]", "value").saveAs("LogoutToken"))

      )

  }


}
