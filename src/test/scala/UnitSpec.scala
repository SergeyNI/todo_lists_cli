package UnitSpec

import org.scalatest._
import flatspec._
import matchers._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.OptionValues
// import org.scalatest.flatspec.AnyFlatSpec


abstract class UnitSpec extends AnyFunSpec with should.Matchers with Inside with Inspectors with OptionValues