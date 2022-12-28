package HumidSensorApp

import java.io.File
import java.nio.file.Paths

import akka.Done
import akka.actor.ActorSystem
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Flow, Framing, Merge, Sink, Source}
import akka.util.ByteString
import com.google.common.io.Resources

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object CsvReader {
  def main(args: Array[String]): Unit = {
    // Create the Actor system and materializer
    implicit val system: ActorSystem = ActorSystem("csv-reader")

    // Define the flow to parse the CSV rows
    val csvFlow: Flow[ByteString, Seq[String], _] =
      Flow[ByteString]
        .via(Framing.delimiter(ByteString("\n"), maximumFrameLength = 512))
        .map(_.utf8String)
        .map(_.split(",").toSeq)

    // Read the files and concatenate their sources
    val fileSources: Seq[Source[ByteString, Future[IOResult]]] =
      new File(Resources.getResource("dailyReport/").getFile)
        .listFiles()
        .map(path => FileIO.fromPath(Paths.get(path.getPath)))

    val concatenatedSource: Source[ByteString, _] =
      Source.combine(fileSources)(Merge(_))

    // Run the flow to parse the rows and consume them with a sink
    val parsedRows: Source[Seq[String], _] = concatenatedSource.via(csvFlow)
    var mapResult: Map[String, Seq[String]] = Map.empty
    // Define a sink to consume the rows
    val printSink: Sink[Seq[String], Future[Done]] =
      Sink.foreach[Seq[String]] {
        case Seq(a, b) => {
          mapResult += a -> List(b).foldRight(
            mapResult.getOrElse(a, Seq[String]())
          ) {
            case (x, y) => {
              y ++ Seq(x)
            }
          }
        }
      }

    // Run the stream to read and parse the CSV file, then consume the rows with the sink
    parsedRows.runWith(printSink).onComplete { _ =>
      println("Num of processed files: " + mapResult("sensor-id").size)
      println(
        "Num of processed measurements: " + mapResult
          .filterNot(_._1 == "sensor-id")
          .flatMap(_._2)
          .size
      )
      println(
        "Num of failed measurements: " + mapResult
          .flatten(_._2)
          .count(x => x == "NaN")
      )
      println("Sensors with highest avg humidity:")
      println("sensor-id" + ",min" + ",avg" + ",max")
      mapResult
        .filterNot(_._1 == "sensor-id")
        .map { sensorInfo: (String, Seq[String]) =>
          sensorInfo._2.filterNot(x => x == "NaN") match {
            case x if x.isEmpty => (sensorInfo._1, "NaN", "NaN", "NaN")
            case y =>
              val nonNaN = y.filterNot(x => x == "NaN")
              (
                sensorInfo._1,
                y.min.toInt,
                nonNaN.map(_.toInt).sum.toDouble / nonNaN.length,
                y.max.toInt
              )
          }
        }
        .toList
        .sortWith { (a, b) =>
          (a._3, b._3) match {
            case (a: Double, b: Double) => a < b
            case (a: String, b: String) => a < b
            case (_: Double, _: String) => true
            case (_: String, _: Double) => false
          }
        }
        .foreach(x => println(x._1 + "," + x._2 + "," + x._3 + "," + x._4))
      system.terminate()
    }
  }
}
