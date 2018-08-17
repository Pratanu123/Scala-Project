import akka.actor.{ActorSystem, Props, Actor}

object SimpleActor extends App {

  class Example extends Actor {
    def receive={
      case s: String => println("String: "+s)
      case i: Int => println("Number: "+i)
    }

    def foo=println("Normal Method")
  }

  val system = ActorSystem("SimpleSystem")
  val actor = system.actorOf(Props[Example],"SimpleActor")

  actor ! "Hi there."
  actor ! "42"

}
