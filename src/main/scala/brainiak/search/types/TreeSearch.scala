package brainiak.search.types

import brainiak.search.Search

/**
 * Created by thiago on 1/18/14.
 */
object TreeSearch {
  def apply: TreeSearch = new TreeSearch
}

class TreeSearch extends Search {
  def graph: Boolean = false
}
