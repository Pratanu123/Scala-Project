import akka.actor.{ActorSystem, Props, Actor}

object SimpleActor1 extends App {

  def main = {
    val system = ActorSystem("SimpleSystem")
    val actor = system.actorOf(Props[Example],"SimpleActor")

    actor ! "Hi there."
    actor ! "42"
  }
}

class Example extends Actor {
  def receive={
    case s: String => println("String: "+s)
    case i: Int => println("Number: "+i)
  }

  def foo=println("Normal Method")
}