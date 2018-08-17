import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

object chatMessage extends App {
  val system = ActorSystem("PingPongSystem")
  val pong = system.actorOf(Props[Pong], name = "pong")
  val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
  ping ! StartMessage
}

class Ping(pong: ActorRef) extends Actor {
  var count = 0
  lazy val x = (1 to 50).toList
  val m = Map(1 -> "A", 2 -> "B", 3 -> "C", 4 -> "D", 5 -> "E")

  def incrementAndPrint {
    count += 1;
    println("ping")
    println("Sum is " + x.reduce(_ + _))
    println(m.get(count))
    if(count%2==0){
      println("Even numbers are- "+x.filter(_%2==0))
    }
    else {

      println("Using fold left sum- "+x.foldLeft(0)(_+_))
    }

  }

  def receive = {
    case StartMessage =>
      incrementAndPrint
      pong ! PingMessage
    case PongMessage =>
      incrementAndPrint
      if (count > 5) {
        sender ! StopMessage
        println("ping stopped")
        context.stop(self)
      } else {
        sender ! PingMessage
      }
    case _ => println("Ping got something unexpected.")
  }
}

class Pong extends Actor {
  def receive = {
    case PingMessage =>
      println(" pong")
      sender ! PongMessage
    case StopMessage =>
      println("pong stopped")
      context.stop(self)
    case _ => println("Pong got something unexpected.")
  }
}



