package scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import services._

class LoginScenario extends Simulation {

  val httpProtocol = http.baseURL("https://github.com")
    .disableFollowRedirect


  val LoginScenario = scenario("GithubLogin")
    .exec(

      HomePage.run,
      Login.run,
      Logout.run
    )

  setUp(

    LoginScenario.inject(

      atOnceUsers(1)

    )

  ).protocols(httpProtocol)

}
