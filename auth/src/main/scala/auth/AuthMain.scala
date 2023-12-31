package auth

import api.HttpRoutes
import auth.config.ServiceConfig
import zio.http.Server
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault, http}

object AuthMain extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    for {
      _ <- ZIO.logInfo("Start app")
      server <- zio.http.Server.serve(HttpRoutes.app)
        .provide(
          Server.live,
          ServiceConfig.live,
        )
    } yield server
  }
}