package prv.zielony.scala.tutorial.functional.programming.idea

import org.junit.runner.RunWith
import org.scalacheck.{Prop, Gen}
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.{PropertyChecks, Checkers}

import java.io.File;

/**
  * Created by kowczarek on 2016-02-09.
  */
@RunWith(classOf[JUnitRunner])
class FileParserTest extends FunSuite with Checkers with PropertyChecks with FileParser {

  val existingFileGenerator = Gen.const("existing.file")

  val nonexistentFileGenerator = Gen.numStr.suchThat { _ != "existing.file"}

  test("defined for an existing file") {
    check( Prop.forAll(existingFileGenerator){ file =>

      val inputFile = new File(getClass.getResource("/" + file).getFile)
      parseIntFile(Option(inputFile)).isRight
    })
  }
}
