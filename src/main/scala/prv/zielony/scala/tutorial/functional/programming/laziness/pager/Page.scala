package prv.zielony.scala.tutorial.functional.programming.laziness.pager

import java.io.{FileInputStream, InputStreamReader, BufferedReader, File}

import scala.io.Source

/**
 * Created by zielony on 09.02.16.
 */
class LinePager(file:File, pageSize:Int) {

  var position:Int = 0;

  val stream:Stream[String] = Source.fromFile(file).getLines().toStream

  def next():Traversable[String] = {
    stream.take(pageSize).toList
  }
}
