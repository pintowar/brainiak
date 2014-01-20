package brainiak.search.types

import brainiak.search.Search

/**
 * Created by thiago on 1/18/14.
 */
object GraphSearch {
  def apply: GraphSearch = new GraphSearch
}

class GraphSearch extends Search {
  def graph: Boolean = true
}
