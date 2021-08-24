import io.bullet.borer._
import io.bullet.borer.derivation.MapBasedCodecs._

object Holder {


  implicit val decode: Decoder[Holder] = Decoder { c =>
    val unbounded = c.readMapOpen(2)

    val cls = c.readString("cls").readString()
    val v = c.readString("value")
    val clsKeyframeNormal = classOf[HolderNormal].getName
    val clsKeyframeLoop = classOf[HolderDifferent].getName
    val ret = cls match {
      case `clsKeyframeNormal` =>
        v.read[HolderNormal]()
      case `clsKeyframeLoop` =>
        v.read[HolderDifferent]()
    }
    v.readMapClose(unbounded, ret)
  }

}

sealed abstract class Holder {
  def properties: Map[String, String]

  def copy(properties: Map[String, String] = properties): Holder
}

object HolderNormal {
  implicit val decoder: Decoder[HolderNormal] = deriveDecoder
}

case class HolderNormal(properties: Map[String, String]) extends Holder {
  def copy(properties: Map[String, String]) = HolderNormal(properties)
}

object HolderDifferent {
  implicit val decoder: Decoder[HolderDifferent] = deriveDecoder
}

case class HolderDifferent(properties: Map[String, String]) extends Holder {

  def copy(properties: Map[String, String]) = HolderDifferent(properties)
}
