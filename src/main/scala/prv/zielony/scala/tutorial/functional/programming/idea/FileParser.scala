package prv.zielony.scala.tutorial.functional.programming.idea

import java.io.{BufferedReader, InputStreamReader, FileInputStream, File}

/**
  * Created by kowczarek on 2016-02-08.
  */
trait FileParser {

  def parseIntFile(file:Option[File]):Either[ParserError, Traversable[Int]] = {

    file.map { actualFile =>

      val reader = new BufferedReader(new InputStreamReader(new FileInputStream(actualFile)))
      var result:List[Int] = List();

      var line:String = "";
        while(line != null) {
          if(!line.equals("")) {
            result = line.toInt :: result
          }
          line = reader.readLine()
        }

        return Right(result)

    }.getOrElse(Left(FileNotFoundError(null)));
  }
}

class ParserError(val cause:String);

case class NumberFormatError(val line:String) extends ParserError(s"Line not parseable: $line");

case class FileNotFoundError(val fileName:String) extends ParserError(s"File not found: $fileName");

case class IOError(val fileName:String) extends ParserError(s"IO Error @ file: $fileName");

