package prv.zielony.scala.tutorial.functional.programming.idea

import java.io.{BufferedReader, InputStreamReader, FileInputStream, File}

/**
  * Created by kowczarek on 2016-02-08.
  */
trait FileParser {

  def parseIntFile(file:Option[File]):Either[ParserError, Traversable[Int]] = {

    file.map { actualFile =>

      if(actualFile.isDirectory || !actualFile.exists) {
        return Left(new FileNotFoundError(actualFile.getName))
      }

      val reader = new BufferedReader(new InputStreamReader(new FileInputStream(actualFile)))
      var result:List[Int] = List();

      var line:String = "";
      try {
        while((line = (reader.readLine())) != null) {
          result = line.toInt :: result
        }

        return Right(result)
      }
      catch {
        case numberFormatException:NumberFormatException => Left(NumberFormatError(line))
      }
    }.getOrElse(Left(FileNotFoundError(null)));
  }
}

class ParserError(val cause:String);

case class NumberFormatError(val line:String) extends ParserError(s"Line not parseable: $line");

case class FileNotFoundError(val fileName:String) extends ParserError(s"File not found: $fileName");


