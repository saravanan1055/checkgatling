package testing

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class jenkins extends Simulation{

  val httpConf = http.baseUrl("https://gorest.co.in")
    .header("Authorization", "Bearer f0da994cbf10e93379816ae6f7308e42dcc36a1ab537813c2b12bd30218ec6ba")

  val scn = scenario("Check the Correaltion")
    .exec(http("Get all the Users")
      .get("/public/v2/users")
      .check(jsonPath("$[0].id").exists.saveAs("newvalue")))

    .exec(http("pull the user")
      .get("/public/v2/users/${newvalue}")
      .check(jsonPath("$[0].id").is("5334838")))


  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
