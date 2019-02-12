package org.upstartcommerce.avataxsdk.core.data

sealed trait QueryOptions {
  def top: Option[Int]
  def skip: Option[Int]
}

final case class BasicQueryOptions(top: Option[Int], skip: Option[Int]) extends QueryOptions {
  def withTop(top: Int): BasicQueryOptions   = copy(top = Some(top))
  def withSkip(skip: Int): BasicQueryOptions = copy(skip = Some(skip))
}

object QueryOptions {
  def basic(top: Option[Int] = None, skip: Option[Int] = None): BasicQueryOptions =
    BasicQueryOptions(top, skip)

  def filtrable(filter: Option[FilterAst],
                top: Option[Int],
                skip: Option[Int],
                orderBy: Option[String]): FiltrableQueryOptions =
    FiltrableQueryOptions(filter, top, skip, orderBy)
}

final case class FiltrableQueryOptions(filter: Option[FilterAst] = None,
                                       top: Option[Int] = None,
                                       skip: Option[Int] = None,
                                       orderBy: Option[String] = None)
    extends QueryOptions {
  def withFilter(filter: FilterAst): FiltrableQueryOptions = copy(filter = Some(filter))
  def withTop(top: Int): FiltrableQueryOptions             = copy(top = Some(top))
  def withSkip(skip: Int): FiltrableQueryOptions           = copy(skip = Some(skip))
  def withOrderBy(orderBy: String): FiltrableQueryOptions  = copy(orderBy = Some(orderBy))
}

final case class Include(toInclude:List[String] = List.empty) {
  override def toString: String = toInclude.mkString(",")
  def and(other:String):Include = Include(toInclude :+ other)
  def merge(other:Include):Include = Include(toInclude ++ other.toInclude)
}
