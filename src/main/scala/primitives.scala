package com.github.aselab.activerecord

trait RegistrationManager[K, V] {
  protected val registrations: collection.mutable.Map[K, V]

  def register(key: K, value: V) = registrations += (key -> value)
  def unregister(key: K) = registrations -= key
  def get(key: K) = registrations.get(key)
  def getOrRegister(key: K, newValue: => V) =
    registrations.getOrElseUpdate(key, newValue)
}

trait PrimitiveHandler[T] extends RegistrationManager[Class[_], T] {
  val stringHandler: T
  val booleanHandler: T
  val intHandler: T
  val longHandler: T
  val floatHandler: T
  val doubleHandler: T
  val dateHandler: T
  val timestampHandler: T
  val uuidHandler: T
  val bigDecimalHandler: T

  protected lazy val registrations = collection.mutable.Map[Class[_], T](
    classOf[String] -> stringHandler,
    classOf[java.lang.Boolean] -> booleanHandler,
    classOf[Boolean] -> booleanHandler,
    classOf[java.lang.Integer] -> intHandler,
    classOf[Int] -> intHandler,
    classOf[java.lang.Long] -> longHandler,
    classOf[Long] -> longHandler,
    classOf[java.lang.Float] -> floatHandler,
    classOf[Float] -> floatHandler,
    classOf[java.lang.Double] -> doubleHandler,
    classOf[Double] -> doubleHandler,
    classOf[java.util.Date] -> dateHandler,
    classOf[java.sql.Timestamp] -> timestampHandler,
    classOf[java.util.UUID] -> uuidHandler,
    classOf[BigDecimal] -> bigDecimalHandler
  )
}