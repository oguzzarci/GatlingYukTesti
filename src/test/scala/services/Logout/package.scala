package services

import io.gatling.core.Predef._
import io.gatling.http.Predef._

package object Logout {

  val LogoutToken: String = Login.LogoutToken

  private val headerLogout = Map (
    //GET https://github.com/ HTTP/1.1
    "Host" -> "github.com",
    "Connection" -> "keep-alive",
    "Cache-Control" -> "max-age=0",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3",
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
    "Referer" -> "https://github.com/oguzzarci",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "tr-TR,tr;q=0.9,en-US;q=0.8,en;q=0.7"

  )


  def run = {

    exec(http("Logout")
      .post("/logout")
      .headers(headerLogout)
      .formParam("utf8","✓")
      .formParam("authenticity_token",s"${LogoutToken}")
      .check(status is 302)
    )

      .exec(http("HomeLogout")
        .get("/")
        .headers(headerHome)
        .check(status is 200)
      )



  }

}
